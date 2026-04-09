package ricciliao.message.pojo.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class MessageCombineBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1517324798753165416L;

    private Long primaryCode;
    private Long secondaryCode;
    private String level;
    private String consumer;
    private String description;

    public Long getPrimaryCode() {
        return primaryCode;
    }

    public void setPrimaryCode(Long primaryCode) {
        this.primaryCode = primaryCode;
    }

    public Long getSecondaryCode() {
        return secondaryCode;
    }

    public void setSecondaryCode(Long secondaryCode) {
        this.secondaryCode = secondaryCode;
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
        if (!(o instanceof MessageCombineBo that)) return false;
        return Objects.equals(getPrimaryCode(), that.getPrimaryCode()) && Objects.equals(getSecondaryCode(), that.getSecondaryCode()) && Objects.equals(getLevel(), that.getLevel()) && Objects.equals(getConsumer(), that.getConsumer()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrimaryCode(), getSecondaryCode(), getLevel(), getConsumer(), getDescription());
    }
}
