package ricciliao.message.utils;

import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.pojo.bo.MessageCombineBo;
import ricciliao.message.pojo.dto.response.GetMessageCodeDto;
import ricciliao.x.component.payload.response.ResponseUtils;
import ricciliao.x.component.payload.response.code.PrimaryCode;
import ricciliao.x.component.payload.response.code.ResponseCode;
import ricciliao.x.component.payload.response.code.SecondaryCode;
import ricciliao.x.component.payload.response.code.SimpleResponseCode;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.util.Objects;

public class MessagePojoUtils {
    private MessagePojoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static GetMessageCodeDto convert2Dto(MessageCodeCacheDto cache) {
        GetMessageCodeDto dto = new GetMessageCodeDto();
        dto.setCode(cache.getCode());
        dto.setLevel(cache.getLevel());
        dto.setConsumer(cache.getConsumer());
        dto.setDescription(cache.getDescription());

        return dto;
    }

    public static GetMessageCodeDto convert2Dto(MessageCombineBo bo) {
        ResponseCode code;
        if(Objects.isNull(bo.getSecondaryCode())){
            code = ResponseCode.of(PrimaryCode.of(bo.getPrimaryCode().intValue(), bo.getDescription()), SecondaryCodeEnum.BLANK);
        } else {
            code = ResponseCode.of(PrimaryCode.of(bo.getPrimaryCode().intValue(), ""), SecondaryCode.of(bo.getSecondaryCode().intValue(), bo.getDescription()));
        }

        GetMessageCodeDto dto = new GetMessageCodeDto();
        SimpleResponseCode responseCode = ResponseUtils.convert2SimpleResponseCode(code);
        dto.setCode(responseCode.getId());
        dto.setDescription(responseCode.getMessage());
        dto.setConsumer(bo.getConsumer());
        dto.setLevel(bo.getLevel());

        return dto;
    }

}
