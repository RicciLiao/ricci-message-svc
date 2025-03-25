package ricciliao.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ricciliao.message.service.MessageCodeService;

@Component("messageRunner")
public class MessageRunner implements ApplicationRunner {

    private MessageCodeService messageCodeService;

    @Autowired
    public void setMessageCodeService(MessageCodeService messageCodeService) {
        this.messageCodeService = messageCodeService;
    }

    @Override
    public void run(ApplicationArguments args) {
        //messageCodeService.refreshCache(false);
    }

}
