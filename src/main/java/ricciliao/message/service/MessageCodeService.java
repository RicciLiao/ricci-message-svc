package ricciliao.message.service;

import ricciliao.message.pojo.dto.MessageCodeDto;

import java.util.List;

public interface MessageCodeService {

    MessageCodeDto getCode(String code, String consumer);

    List<MessageCodeDto> listCode(String consumer);

    boolean refreshCache(boolean focus);

}
