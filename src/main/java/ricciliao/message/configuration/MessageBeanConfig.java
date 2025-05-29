package ricciliao.message.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import ricciliao.message.filter.MessageFilter;

@Configuration
public class MessageBeanConfig {

    @Bean
    public FilterRegistrationBean<MessageFilter> httpServletWrapperFilter() {

        FilterRegistrationBean<MessageFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MessageFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        registrationBean.setName("httpServletWrapperFilter");

        return registrationBean;
    }

}
