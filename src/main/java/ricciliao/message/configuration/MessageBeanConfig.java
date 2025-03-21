package ricciliao.message.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ricciliao.message.aspect.ControllerAspect;
import ricciliao.message.filter.HttpServletWrapperFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import ricciliao.common.component.response.ResponseEmptyDataAspect;
import ricciliao.dynamic.aop.DynamicPointcutAdvisor;

import java.util.TimeZone;

@Configuration
public class MessageBeanConfig {

    private MessageProps messageProps;

    @Autowired
    public void setMessageProps(MessageProps messageProps) {
        this.messageProps = messageProps;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone(messageProps.getTimeZone()));
        // objectMapper java.time.LocalDate/LocalDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Bean
    public DynamicPointcutAdvisor responseDataAspect() {

        return new DynamicPointcutAdvisor(
                messageProps.getDynamicAopPointCutController(),
                new ResponseEmptyDataAspect()
        );
    }

    @Bean
    public DynamicPointcutAdvisor controllerAspect() {

        return new DynamicPointcutAdvisor(
                messageProps.getDynamicAopPointCutController(),
                new ControllerAspect()
        );
    }

    @Bean
    public FilterRegistrationBean<HttpServletWrapperFilter> wrapperFilter() {

        FilterRegistrationBean<HttpServletWrapperFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpServletWrapperFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setName("httpServletWrapperFilter");

        return registrationBean;
    }

}
