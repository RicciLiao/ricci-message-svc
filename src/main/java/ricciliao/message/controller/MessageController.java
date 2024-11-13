package ricciliao.message.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.common.component.response.ResponseData;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.message.service.MessageCodeService;

@Tag(name = "")
@RestController
public class MessageController {

    private MessageCodeService messageCodeService;

    @Autowired
    public void setMessageCodeService(MessageCodeService messageCodeService) {
        this.messageCodeService = messageCodeService;
    }

    @Operation
    @GetMapping("/message/{p}/{c}")
    public ResponseVo<ResponseData> message(@PathVariable("p") String p,
                                            @PathVariable("c") String c) {

        return ResponseUtils.successResponse(messageCodeService.getCode(p, c));
    }

}
