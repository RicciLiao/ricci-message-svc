package ricciliao.message.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ricciliao.message.filter.MessageFilter;
import ricciliao.x.component.payload.response.ResponseHttpMessageConverter;
import ricciliao.x.log.MdcSupportFilter;

@Configuration
public class MessageBeanConfig {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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

    @Bean
    public RestTemplate messageRestTemplate() {

        return new RestTemplateBuilder()
                .messageConverters(
                        new ResponseHttpMessageConverter(objectMapper),
                        new MappingJackson2HttpMessageConverter(objectMapper)
                )
                .build();
    }

}
