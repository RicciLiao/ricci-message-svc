package ricciliao.message.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import ricciliao.message.filter.MessageFilter;
import ricciliao.x.log.MdcSupportFilter;

@Configuration
public class MessageBeanConfig {

    @Bean
    public FilterRegistrationBean<MessageFilter> messageFilter() {
        FilterRegistrationBean<MessageFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MessageFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        registrationBean.setName("messageFilter");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<MdcSupportFilter> mdcSupportFilter() {
        FilterRegistrationBean<MdcSupportFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MdcSupportFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setName("mdcSupportFilter");

        return registrationBean;
    }

}
