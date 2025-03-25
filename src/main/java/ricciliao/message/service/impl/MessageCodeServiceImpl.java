package ricciliao.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.message.common.MessagePojoUtils;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.pojo.po.MessageCodePo;
import ricciliao.message.repository.MessageCodeRepository;
import ricciliao.message.service.CacheProviderService;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.cache.pojo.ConsumerOpDto;
import ricciliao.x.cache.pojo.ProviderInfoDto;
import ricciliao.x.cache.pojo.message.MessageCodeCacheDto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

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
    public boolean refreshCache(boolean focus) {
        LocalDateTime dbMaxDate = messageCodeRepository.refreshCache();
        ProviderInfoDto providerInfo = cacheProviderService.code().providerInfo();
        if (Objects.isNull(providerInfo.getMaxUpdatedDtm()) || dbMaxDate.isAfter(providerInfo.getMaxUpdatedDtm())) {
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
