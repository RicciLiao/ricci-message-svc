package ricciliao.message.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.message.common.MessagePojoUtils;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.pojo.po.MessageCodePo;
import ricciliao.message.repository.MessageCodeRepository;
import ricciliao.message.service.CacheProviderService;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.cache.CacheQuery;
import ricciliao.x.cache.pojo.ConsumerOpBatchQueryDto;
import ricciliao.x.cache.pojo.ConsumerOpDto;
import ricciliao.x.cache.pojo.ProviderInfoDto;
import ricciliao.x.cache.pojo.message.MessageCodeCacheDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("messageCodeService")
public class MessageCodeServiceImpl implements MessageCodeService {

    private MessageCodeRepository messageCodeRepository;
    private CacheProviderService cacheProviderService;

    @Autowired
    public void setCacheProviderService(CacheProviderService cacheProviderService) {
        this.cacheProviderService = cacheProviderService;
    }

    @Autowired
    public void setMessageCodeRepository(MessageCodeRepository messageCodeRepository) {
        this.messageCodeRepository = messageCodeRepository;
    }

    @Override
    public MessageCodeDto getCode(String code, String consumer) {
        MessageCodeCacheDto dto = new MessageCodeCacheDto();
        dto.setCode(code);
        dto.setConsumer(consumer);
        dto.buildCacheKey();
        dto = cacheProviderService.code().get(dto.getCacheKey()).getData();

        return MessagePojoUtils.convert2Dto(dto);
    }

    @Override
    public List<MessageCodeDto> listCode(String consumer) {
        ConsumerOpBatchQueryDto query = new ConsumerOpBatchQueryDto();
        query.getCriteriaMap().put(CacheQuery.Property.CACHE_KEY, consumer + "_*");
        ConsumerOpDto.Batch<MessageCodeCacheDto> batch = cacheProviderService.code().list(query);
        if (Objects.isNull(batch) || CollectionUtils.isEmpty(batch.getData())) {

            return Collections.emptyList();
        }

        return batch.getData().stream().map(cache -> {
                    MessageCodeDto dto = new MessageCodeDto();
                    dto.setCode(cache.getCode());
                    dto.setLevel(cache.getLevel());
                    dto.setConsumer(cache.getConsumer());
                    dto.setDescription(cache.getDescription());

                    return dto;
                })
                .toList();
    }

    @Override
    public boolean refreshCache(boolean focus) {
        LocalDateTime dbMaxDate = messageCodeRepository.refreshCache();
        ProviderInfoDto providerInfo = cacheProviderService.code().providerInfo();
        if (Objects.isNull(providerInfo.getMaxUpdatedDtm()) || dbMaxDate.isAfter(providerInfo.getMaxUpdatedDtm())) {
            cacheProviderService.code().batchDelete(new ConsumerOpBatchQueryDto());
            ConsumerOpDto.Batch<MessageCodeCacheDto> operationBatch = new ConsumerOpDto.Batch<>();
            for (MessageCodePo po : messageCodeRepository.findAll()) {
                MessageCodeCacheDto cache = new MessageCodeCacheDto();
                cache.setCode(po.getCode());
                cache.setLevel(po.getLevel());
                cache.setConsumer(po.getConsumer());
                cache.setDescription(po.getDescription());
                cache.setCreatedDtm(po.getCreatedDtm());
                cache.setUpdatedDtm(po.getUpdatedDtm());
                operationBatch.getData().add(cache.buildCacheKey());
            }

            return cacheProviderService.code().batchCreate(operationBatch).result();
        }

        return false;
    }

}
