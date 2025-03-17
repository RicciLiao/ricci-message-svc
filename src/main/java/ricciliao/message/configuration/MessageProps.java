package ricciliao.message.configuration;

import org.springframework.context.annotation.Configuration;
import ricciliao.common.component.props.ApplicationProperties;

@Configuration
public class MessageProps extends ApplicationProperties {

    public MessageProps() {
        super();
        this.timeZone = this.getReader().getProperty("time-zone", String.class);
        this.redisHost = this.getReader().getProperty("redis.host", String.class);
        this.redisPort = this.getReader().getProperty("redis.port", Integer.class);
        this.password = this.getReader().getProperty("redis.password", String.class);
        /*this.messageRedisProps =
                new StringRedisWrapperConfig.RedisPropsBo(
                        redisHost,
                        redisPort,
                        password,
                        this.getYamlProperties().getProperty("redis.db.message.database", Integer.class),
                        Duration.ofMillis(this.getYamlProperties().getProperty("redis.db.message.timeout", Long.class)),
                        Duration.ofMillis(this.getYamlProperties().getProperty("redis.db.message.ttl", Long.class)),
                        this.getYamlProperties().getProperty("redis.db.message.pool.min-idle", Integer.class),
                        this.getYamlProperties().getProperty("redis.db.message.pool.max-idle", Integer.class),
                        this.getYamlProperties().getProperty("redis.db.message.pool.max-total", Integer.class)
                );*/
        this.dynamicAopPointCutController = this.getReader().getProperty("dynamic-aop.point-cut.controller", String.class);
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
