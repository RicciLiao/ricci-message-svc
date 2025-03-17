package ricciliao.message.common;

import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.pojo.po.MessageCodePo;
import ricciliao.common.component.utils.CoreUtils;

public class MessagePojoUtils {
    private MessagePojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static MessageCodeDto convert2Dto(MessageCodePo po) {
        MessageCodeDto dto = new MessageCodeDto();
        dto.setId(po.getId());
        dto.setCode(po.getCode());
        dto.setType(po.getType());
        dto.setProjectCode(po.getProjectCode());
        dto.setDescription(po.getDescription());
        dto.setActive(CoreUtils.toBoolean(po.getIsActive()));
        dto.setCreatedBy(po.getCreatedBy());
        dto.setCreatedDtm(CoreUtils.toLong(po.getCreatedDtm()));
        dto.setUpdatedBy(po.getUpdatedBy());
        dto.setUpdatedDtm(CoreUtils.toLong(po.getCreatedDtm()));
        dto.setVersion(CoreUtils.toLong(po.getVersion()));

        return dto;
    }

}
