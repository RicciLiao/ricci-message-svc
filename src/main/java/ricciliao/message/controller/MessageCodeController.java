package ricciliao.message.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.message.service.MessageCodeService;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimpleData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;

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
    public Response<PayloadData> message(@PathVariable("code") String code,
                                         @PathVariable("consumer") String consumer) {

        return ResponseUtils.success(messageCodeService.getCode(code, consumer));
    }

    @Operation
    @GetMapping("/code/{consumer}")
    public Response<PayloadData> message(@PathVariable("consumer") String consumer) {

        return ResponseUtils.success(SimpleData.of(messageCodeService.listCode(consumer)));
    }

    @Operation
    @GetMapping("/refreshCache")
    public Response<PayloadData> refreshCache() {

        return ResponseUtils.success(SimpleData.of(messageCodeService.refreshCache(false)));
    }

}
