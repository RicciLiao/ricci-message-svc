package ricciliao.message.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import ricciliao.common.component.context.YamlPropertiesReader;

@Configuration
public class ApplicationProperties {

    public ApplicationProperties() {
        YamlPropertiesReader yamlProperties = new YamlPropertiesReader(new ClassPathResource("application.yml"));
        this.timeZone = yamlProperties.getProperty("time-zone", String.class);
        this.redisHost = yamlProperties.getProperty("redis.host", String.class);
        this.redisPort = yamlProperties.getProperty("redis.port", Integer.class);
        this.password = yamlProperties.getProperty("redis.password", String.class);
        /*this.messageRedisProps =
                new StringRedisWrapperConfig.RedisPropsBo(
                        redisHost,
                        redisPort,
                        password,
                        yamlProperties.getProperty("redis.db.message.database", Integer.class),
                        Duration.ofMillis(yamlProperties.getProperty("redis.db.message.timeout", Long.class)),
                        Duration.ofMillis(yamlProperties.getProperty("redis.db.message.ttl", Long.class)),
                        yamlProperties.getProperty("redis.db.message.pool.min-idle", Integer.class),
                        yamlProperties.getProperty("redis.db.message.pool.max-idle", Integer.class),
                        yamlProperties.getProperty("redis.db.message.pool.max-total", Integer.class)
                );*/
        this.dynamicAopPointCutController = yamlProperties.getProperty("dynamic-aop.point-cut.controller", String.class);
    }

    private final String timeZone;
    private final String dynamicAopPointCutController;
    private final String redisHost;
    private final Integer redisPort;
    private final String password;
    //private final StringRedisWrapperConfig.RedisPropsBo messageRedisProps;

    public String getTimeZone() {
        return timeZone;
    }

    public String getDynamicAopPointCutController() {
        return dynamicAopPointCutController;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public String getPassword() {
        return password;
    }

    /*public StringRedisWrapperConfig.RedisPropsBo getmessageRedisProps() {
        return messageRedisProps;
    }*/

}
