package ricciliao.message.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.component.response.ResponseCollectionData;
import ricciliao.x.component.response.ResponseData;
import ricciliao.x.component.response.ResponseSimpleData;
import ricciliao.x.component.response.ResponseUtils;
import ricciliao.x.component.response.ResponseVo;

@Tag(name = "MessageCodeController")
@RestController
public class MessageCodeController {

    private MessageCodeService messageCodeService;

    @Autowired
    public void setMessageCodeService(MessageCodeService messageCodeService) {
        this.messageCodeService = messageCodeService;
    }

    @Operation
    @GetMapping("/code/{code}/{consumer}")
    public ResponseVo<ResponseData> message(@PathVariable("code") String code,
                                            @PathVariable("consumer") String consumer) {

        return ResponseUtils.successResponse(messageCodeService.getCode(code, consumer));
    }

    @Operation
    @GetMapping("/code/{consumer}")
    public ResponseVo<ResponseData> message(@PathVariable("consumer") String consumer) {

        return ResponseUtils.successResponse(ResponseCollectionData.data(messageCodeService.listCode(consumer)));
    }

    @Operation
    @GetMapping("/refreshCache")
    public ResponseVo<ResponseData> refreshCache() {

        return ResponseUtils.successResponse(new ResponseSimpleData.Bool(messageCodeService.refreshCache(false)));
    }

}
