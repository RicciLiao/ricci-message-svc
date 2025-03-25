/*
package ricciliao.message.rest.dto;

import org.apache.commons.lang3.StringUtils;
import ricciliao.x.component.cache.pojo.CacheDto;

import java.io.Serial;
import java.util.Objects;

public class MessageCodeCacheDto extends CacheDto {
    @Serial
    private static final long serialVersionUID = 2994016747529851138L;

    private String code;
    private String level;
    private String consumer;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageCodeCacheDto cacheDto)) return false;
        return Objects.equals(getCode(), cacheDto.getCode()) && Objects.equals(getLevel(), cacheDto.getLevel()) && Objects.equals(getConsumer(), cacheDto.getConsumer()) && Objects.equals(getDescription(), cacheDto.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getLevel(), getConsumer(), getDescription());
    }

    @Override
    public MessageCodeCacheDto buildId() {
        this.setId(
                String.format(
                        "%s_%s_%s",
                        StringUtils.trimToEmpty(this.consumer),
                        StringUtils.trimToEmpty(this.level),
                        StringUtils.trimToEmpty(this.code)
                )
        );

        return this;
    }
}
*/
