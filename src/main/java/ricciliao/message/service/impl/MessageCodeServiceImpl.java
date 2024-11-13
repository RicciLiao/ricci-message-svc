package ricciliao.message.service.impl;

import ricciliao.message.common.MessagePojoUtils;
import ricciliao.message.pojo.dto.MessageCodeDto;
import ricciliao.message.pojo.po.MessageCodePo;
import ricciliao.message.repository.MessageCodeRepository;
import ricciliao.message.service.MessageCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("messageCodeService")
public class MessageCodeServiceImpl implements MessageCodeService {

    private MessageCodeRepository messageCodeRepository;

    @Autowired
    public void setMessageCodeRepository(MessageCodeRepository messageCodeRepository) {
        this.messageCodeRepository = messageCodeRepository;
    }

    @Override
    public MessageCodeDto getCode(String projectCode, String code) {
        Optional<MessageCodePo> poOptional = messageCodeRepository.findByCodeAndProjectCode(code, projectCode);

        return poOptional.map(MessagePojoUtils::convert2Dto).orElse(null);
    }

}
