package ricciliao.message.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.message.cache.CacheProvider;
import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.common.MessagePojoUtils;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.repository.MessageCodeRepository;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.mcp.ConsumerCache;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpCriteria;
import ricciliao.x.mcp.query.McpQuery;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("messageCodeService")
public class MessageCodeServiceImpl implements MessageCodeService {

    private MessageCodeRepository messageCodeRepository;
    private CacheProvider cacheProvider;

    @Autowired
    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    @Autowired
    public void setMessageCodeRepository(MessageCodeRepository messageCodeRepository) {
        this.messageCodeRepository = messageCodeRepository;
    }

    @Override
    public MessageCodeDto getCode(String code, String consumer) {
        ConsumerCache<MessageCodeCacheDto> cache = cacheProvider.code().get(consumer + "_" + code);
        if (Objects.isNull(cache)) {

            return null;
        }

        return MessagePojoUtils.convert2Dto(cache.getData());
    }

    @Override
    public List<MessageCodeDto> listCode(String consumer) {
        McpQuery query = new McpQuery();
        query.getCriteriaMap().put(McpCriteria.Property.ID, consumer + "_*");
        SimplePayloadData.Collection<ConsumerCache<MessageCodeCacheDto>> collection = cacheProvider.code().list(query);
        if (CollectionUtils.isEmpty(collection.data())) {

            return Collections.emptyList();
        }


        return collection.data().stream()
                .map(cache -> {
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
        Instant dbMaxDate = messageCodeRepository.refreshCache();
        McpProviderInfo providerInfo = cacheProvider.code().info();
        if (Objects.isNull(providerInfo)
                || Objects.isNull(providerInfo.getMaxUpdatedDtm())
                || dbMaxDate.isAfter(providerInfo.getMaxUpdatedDtm())) {
            McpQuery query = new McpQuery();
            query.setLimit(null);
            cacheProvider.code().delete(query);
            SimplePayloadData.Bool bool = cacheProvider.code().create(
                    messageCodeRepository.findAll().stream()
                            .map(po -> {
                                ConsumerCache<MessageCodeCacheDto> cache = ConsumerCache.of(new MessageCodeCacheDto());
                                cache.getData().setCode(po.getCode());
                                cache.getData().setLevel(po.getLevel());
                                cache.getData().setConsumer(po.getConsumer());
                                cache.getData().setDescription(po.getDescription());
                                cache.setCreatedDtm(po.getCreatedDtm());
                                cache.setUpdatedDtm(po.getUpdatedDtm());

                                return cache;
                            })
                            .toList()
            );

            return Objects.nonNull(bool) && bool.data();
        }

        return false;
    }

}
