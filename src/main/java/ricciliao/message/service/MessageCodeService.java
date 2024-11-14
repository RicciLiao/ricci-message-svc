package ricciliao.message.service;

import ricciliao.message.pojo.dto.MessageCodeDto;

public interface MessageCodeService {

    MessageCodeDto getCode(String projectCode, Long code);

}
