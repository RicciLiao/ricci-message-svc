package ricciliao.message.service;


import ricciliao.message.cache.pojo.MessageCodeCacheDto;
import ricciliao.x.starter.cache.ConsumerCacheRestService;

public interface CacheProviderService {

    ConsumerCacheRestService<MessageCodeCacheDto> code();

}
