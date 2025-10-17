package ricciliao.message.common;

import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.message.pojo.dto.MessageCodeDto;

public class MessagePojoUtils {
    private MessagePojoUtils() {
        throw new IllegalStateException("Utility class");
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
