package ricciliao.message.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.common.MessagePojoUtils;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.repository.MessageCodeRepository;
import ricciliao.message.service.CacheProviderService;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.cache.pojo.ConsumerCache;
import ricciliao.x.cache.pojo.ConsumerOperation;
import ricciliao.x.cache.pojo.ProviderInfo;
import ricciliao.x.cache.query.CacheBatchQuery;
import ricciliao.x.cache.query.CacheQuery;
import ricciliao.x.component.payload.SimpleData;

import java.time.Instant;
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
        ConsumerCache<MessageCodeCacheDto> cache = cacheProviderService.code().get(consumer + "_" + code);
        if (Objects.isNull(cache)) {

            return null;
        }

        return MessagePojoUtils.convert2Dto(cache.getStore());
    }

    @Override
    public List<MessageCodeDto> listCode(String consumer) {
        CacheBatchQuery query = new CacheBatchQuery();
        query.getCriteriaMap().put(CacheQuery.Property.CACHE_KEY, consumer + "_*");
        SimpleData.Collection<ConsumerCache<MessageCodeCacheDto>> collection = cacheProviderService.code().list(query);
        if (CollectionUtils.isEmpty(collection.result())) {

            return Collections.emptyList();
        }


        return collection.result().stream()
                .map(cache -> {
                    MessageCodeDto dto = new MessageCodeDto();
                    dto.setCode(cache.getStore().getCode());
                    dto.setLevel(cache.getStore().getLevel());
                    dto.setConsumer(cache.getStore().getConsumer());
                    dto.setDescription(cache.getStore().getDescription());

                    return dto;
                })
                .toList();
    }

    @Override
    public boolean refreshCache(boolean focus) {
        Instant dbMaxDate = messageCodeRepository.refreshCache();
        ProviderInfo providerInfo = cacheProviderService.code().providerInfo();
        if (Objects.isNull(providerInfo)
                || Objects.isNull(providerInfo.getMaxUpdatedDtm())
                || dbMaxDate.isAfter(providerInfo.getMaxUpdatedDtm())) {
            cacheProviderService.code().batchDelete(new CacheBatchQuery());

            SimpleData.Bool bool = cacheProviderService.code().batchCreate(
                    ConsumerOperation.of(
                            messageCodeRepository.findAll().stream()
                                    .map(po -> {
                                        ConsumerCache<MessageCodeCacheDto> cache = new ConsumerCache<>(new MessageCodeCacheDto());
                                        cache.setCacheKey(po.getConsumer() + "_" + po.getCode());
                                        cache.getStore().setCode(po.getCode());
                                        cache.getStore().setLevel(po.getLevel());
                                        cache.getStore().setConsumer(po.getConsumer());
                                        cache.getStore().setDescription(po.getDescription());
                                        cache.setCreatedDtm(po.getCreatedDtm());
                                        cache.setUpdatedDtm(po.getUpdatedDtm());

                                        return cache;
                                    })
                                    .toList()
                    )
            );

            return Objects.nonNull(bool) && bool.result();
        }


        return false;
    }

}
