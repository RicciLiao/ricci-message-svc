package ricciliao.message.common;

import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.pojo.po.MessageCodePo;
import ricciliao.x.component.utils.CoreUtils;

public class MessagePojoUtils {
    private MessagePojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static MessageCodeDto convert2Dto(MessageCodePo po) {
        MessageCodeDto dto = new MessageCodeDto();
        dto.setId(po.getId());
        dto.setCode(po.getCode());
        dto.setLevel(po.getLevel());
        dto.setConsumer(po.getConsumer());
        dto.setDescription(po.getDescription());
        dto.setActive(po.getActive());
        dto.setCreatedBy(po.getCreatedBy());
        dto.setCreatedDtm(CoreUtils.toLong(po.getCreatedDtm()));
        dto.setUpdatedBy(po.getUpdatedBy());
        dto.setUpdatedDtm(CoreUtils.toLong(po.getCreatedDtm()));
        dto.setVersion(CoreUtils.toLong(po.getVersion()));

        return dto;
    }


    public static MessageCodeDto convert2Dto(MessageCodeCacheDto cache) {
        MessageCodeDto dto = new MessageCodeDto();
        dto.setCode(cache.getCode());
        dto.setLevel(cache.getLevel());
        dto.setConsumer(cache.getConsumer());
        dto.setDescription(cache.getDescription());

        return dto;
    }

}
