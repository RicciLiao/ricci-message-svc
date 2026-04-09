package ricciliao.message.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ricciliao.message.cache.CacheProvider;
import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.pojo.bo.MessageCombineBo;
import ricciliao.message.pojo.dto.response.GetMessageCodeDto;
import ricciliao.message.repository.MessageCodePrimaryRepository;
import ricciliao.message.repository.MessageCodeSecondaryRepository;
import ricciliao.message.service.MessageCodeService;
import ricciliao.message.utils.MessagePojoUtils;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.mcp.ConsumerCache;
import ricciliao.x.mcp.McpProviderInfo;
import ricciliao.x.mcp.query.McpCriteria;
import ricciliao.x.mcp.query.McpQuery;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service("messageCodeService")
public class MessageCodeServiceImpl implements MessageCodeService {

    private MessageCodePrimaryRepository messageCodePrimaryRepository;
    private MessageCodeSecondaryRepository messageCodeSecondaryRepository;
    private CacheProvider cacheProvider;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Autowired
    public void setMessageCodePrimaryRepository(MessageCodePrimaryRepository messageCodePrimaryRepository) {
        this.messageCodePrimaryRepository = messageCodePrimaryRepository;
    }

    @Autowired
    public void setMessageCodeSecondaryRepository(MessageCodeSecondaryRepository messageCodeSecondaryRepository) {
        this.messageCodeSecondaryRepository = messageCodeSecondaryRepository;
    }

    @Autowired
    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    @Override
    public GetMessageCodeDto getCode(String code, String consumer) {
        ConsumerCache<MessageCodeCacheDto> cache = cacheProvider.code().get(consumer + "_" + code);
        if (Objects.isNull(cache)) {

            return null;
        }

        return MessagePojoUtils.convert2Dto(cache.getData());
    }

    @Override
    public List<GetMessageCodeDto> listCode(String consumer) {
        McpQuery query = new McpQuery();
        query.getCriteriaMap().put(McpCriteria.Property.ID, consumer + "_*");
        SimplePayloadData.Collection<ConsumerCache<MessageCodeCacheDto>> collection = cacheProvider.code().list(query);
        if (CollectionUtils.isEmpty(collection.data())) {

            return Collections.emptyList();
        }


        return collection.data().stream()
                .map(cache -> {
                    GetMessageCodeDto dto = new GetMessageCodeDto();
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
        Instant primaryMaxDate = messageCodePrimaryRepository.refreshCache();
        Instant secondaryMaxDate = messageCodeSecondaryRepository.refreshCache();
        Instant dbMaxDate = primaryMaxDate.isAfter(secondaryMaxDate) ? primaryMaxDate : secondaryMaxDate;
        McpProviderInfo providerInfo = cacheProvider.code().info();
        if (Objects.isNull(providerInfo)
            || Objects.isNull(providerInfo.getMaxUpdatedDtm())
            || dbMaxDate.isAfter(providerInfo.getMaxUpdatedDtm())) {
            McpQuery query = new McpQuery();
            query.setLimit(null);
            cacheProvider.code().delete(query);
            SimplePayloadData.Bool bool = cacheProvider.code().create(
                    this.listAll().stream()
                            .map(dto -> {
                                ConsumerCache<MessageCodeCacheDto> cache = ConsumerCache.of(new MessageCodeCacheDto());
                                cache.getData().setCode(dto.getCode());
                                cache.getData().setLevel(dto.getLevel());
                                cache.getData().setConsumer(dto.getConsumer());
                                cache.getData().setDescription(dto.getDescription());

                                return cache;
                            })
                            .toList()
            );

            return Objects.nonNull(bool) && bool.data();
        }

        return false;
    }

    @Override
    public List<GetMessageCodeDto> listAll() {

        return
                namedParameterJdbcTemplate.query(
                                """
                                        select p.code                   as primary_code,
                                               s.code                   as secondary_code,
                                               ifnull(s.level, p.level) as level,
                                               s.consumer               as consumer,
                                               s.description            as description,
                                               s.updated_dtm            as updated_dtm
                                        from message_code_secondary s
                                                 cross join message_code_primary p
                                        union
                                        select p.code as primary_code,
                                               null   as secondary_code,
                                               p.level,
                                               s.consumer,
                                               p.description,
                                               p.updated_dtm
                                        from message_code_primary p
                                                 cross join (select distinct consumer from message_code_secondary) as s;
                                        """,
                                new HashMap<>(),
                                new BeanPropertyRowMapper<>(MessageCombineBo.class)
                        )
                        .stream()
                        .map(MessagePojoUtils::convert2Dto)
                        .toList();
    }

}
