package ricciliao.message.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.x.starter.mcp.McpConsumerRestService;

@Component("cacheProvider")
public class CacheProvider {

    private McpConsumerRestService<MessageCodeCacheDto> codeConsumerRestService;

    @Qualifier("codeMcpConsumerRestService")
    @Autowired
    public void setCodeConsumerRestService(McpConsumerRestService<MessageCodeCacheDto> codeConsumerRestService) {
        this.codeConsumerRestService = codeConsumerRestService;
    }

    public McpConsumerRestService<MessageCodeCacheDto> code() {

        return codeConsumerRestService;
    }

}
