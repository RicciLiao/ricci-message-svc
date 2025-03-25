package ricciliao.message.service;

import ricciliao.x.cache.ConsumerCacheRestService;
import ricciliao.x.cache.pojo.message.MessageCodeCacheDto;

public interface CacheProviderService {

    ConsumerCacheRestService<MessageCodeCacheDto> code();

}
