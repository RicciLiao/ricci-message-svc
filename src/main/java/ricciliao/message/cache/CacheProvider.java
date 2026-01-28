package ricciliao.message.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.x.starter.mcp.ConsumerCacheRestService;

@Component("cacheProvider")
public class CacheProvider {

    private ConsumerCacheRestService<MessageCodeCacheDto> codeConsumerCacheRestService;

    @Qualifier("codeConsumerCacheRestService")
    @Autowired
    public void setCodeConsumerCacheRestService(ConsumerCacheRestService<MessageCodeCacheDto> codeConsumerCacheRestService) {
        this.codeConsumerCacheRestService = codeConsumerCacheRestService;
    }

    public ConsumerCacheRestService<MessageCodeCacheDto> code() {

        return codeConsumerCacheRestService;
    }

}
