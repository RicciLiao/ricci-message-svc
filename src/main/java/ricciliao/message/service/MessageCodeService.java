package ricciliao.message.service;

import ricciliao.message.pojo.dto.response.GetMessageCodeDto;

import java.util.List;

public interface MessageCodeService {

    GetMessageCodeDto getCode(String code, String consumer);

    List<GetMessageCodeDto> listCode(String consumer);

    boolean refreshCache(boolean focus);

    List<GetMessageCodeDto> listAll();

}
