package ricciliao.message.service;

import ricciliao.message.pojo.dto.MessageCodeDto;

public interface MessageCodeService {

    MessageCodeDto getCode(String code, String consumer);

    boolean refreshCache(boolean focus);

}
