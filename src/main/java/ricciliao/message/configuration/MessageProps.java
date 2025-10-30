package ricciliao.message.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ricciliao.x.component.props.ApplicationProperties;
import ricciliao.x.starter.XProperties;

@Configuration("messageProps")
public class MessageProps extends ApplicationProperties {

    private final XProperties xProps;

    public MessageProps(@Autowired XProperties xProperties) {
        super();
        this.xProps = xProperties;
    }

    public XProperties getxProps() {
        return xProps;
    }
}
