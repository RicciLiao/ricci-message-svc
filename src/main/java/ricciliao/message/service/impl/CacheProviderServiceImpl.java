package ricciliao.message.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ricciliao.message.service.CacheProviderService;
import ricciliao.x.cache.ConsumerCacheRestService;
import ricciliao.x.cache.pojo.message.MessageCodeCacheDto;

@Service("cacheProviderService")
public class CacheProviderServiceImpl implements CacheProviderService {

    private ConsumerCacheRestService<MessageCodeCacheDto> codeConsumerCacheRestService;

    @Qualifier("codeConsumerCacheRestService")
    @Autowired
    public void setCodeConsumerCacheRestService(ConsumerCacheRestService<MessageCodeCacheDto> codeConsumerCacheRestService) {
        this.codeConsumerCacheRestService = codeConsumerCacheRestService;
    }

    @Override
    public ConsumerCacheRestService<MessageCodeCacheDto> code() {

        return codeConsumerCacheRestService;
    }

}
