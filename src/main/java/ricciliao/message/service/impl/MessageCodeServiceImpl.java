package ricciliao.message.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.common.MessagePojoUtils;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.pojo.po.MessageCodePo;
import ricciliao.message.repository.MessageCodeRepository;
import ricciliao.message.service.CacheProviderService;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.cache.CacheOperationUtils;
import ricciliao.x.cache.pojo.ConsumerCacheStore;
import ricciliao.x.cache.pojo.ConsumerOp;
import ricciliao.x.cache.pojo.ProviderInfo;
import ricciliao.x.cache.query.CacheBatchQuery;
import ricciliao.x.cache.query.CacheQuery;

import java.time.LocalDateTime;
import java.util.Arrays;
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
        ConsumerOp.Single<MessageCodeCacheDto> single = cacheProviderService.code().get(consumer + "_" + code);

        return MessagePojoUtils.convert2Dto(single.getData().getData());
    }

    @Override
    public List<MessageCodeDto> listCode(String consumer) {
        CacheBatchQuery query = new CacheBatchQuery();
        query.getCriteriaMap().put(CacheQuery.Property.CACHE_KEY, consumer + "_*");
        ConsumerOp.Batch<MessageCodeCacheDto> batch = cacheProviderService.code().list(query);
        if (Objects.isNull(batch) || ArrayUtils.isEmpty(batch.getData())) {

            return Collections.emptyList();
        }

        return Arrays.stream(batch.getData()).map(cache -> {
                    MessageCodeDto dto = new MessageCodeDto();
                    dto.setCode(cache.getData().getCode());
                    dto.setLevel(cache.getData().getLevel());
                    dto.setConsumer(cache.getData().getConsumer());
                    dto.setDescription(cache.getData().getDescription());

                    return dto;
                })
                .toList();
    }

    @Override
    public boolean refreshCache(boolean focus) {
        LocalDateTime dbMaxDate = messageCodeRepository.refreshCache();
        ProviderInfo providerInfo = cacheProviderService.code().providerInfo();
        if (Objects.isNull(providerInfo.getMaxUpdatedDtm()) || dbMaxDate.isAfter(providerInfo.getMaxUpdatedDtm())) {
            cacheProviderService.code().batchDelete(new CacheBatchQuery());
            List<MessageCodePo> poList = messageCodeRepository.findAll();
            ConsumerCacheStore<MessageCodeCacheDto>[] codeCaches = CacheOperationUtils.createArray(poList.size());
            for (int i = 0; i < poList.size(); i++) {
                MessageCodePo po = poList.get(i);
                ConsumerCacheStore<MessageCodeCacheDto> cache = new ConsumerCacheStore<>(new MessageCodeCacheDto());
                cache.setCacheKey(po.getConsumer() + "_" + po.getCode());
                cache.getData().setCode(po.getCode());
                cache.getData().setLevel(po.getLevel());
                cache.getData().setConsumer(po.getConsumer());
                cache.getData().setDescription(po.getDescription());
                cache.setCreatedDtm(po.getCreatedDtm());
                cache.setUpdatedDtm(po.getUpdatedDtm());

                codeCaches[i] = cache;
            }
            ConsumerOp.Batch<MessageCodeCacheDto> operationBatch = new ConsumerOp.Batch<>(codeCaches);

            return cacheProviderService.code().batchCreate(operationBatch).result();
        }


        return false;
    }

}
