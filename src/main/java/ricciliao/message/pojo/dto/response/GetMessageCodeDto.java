package ricciliao.message.pojo.dto.response;


import ricciliao.x.component.payload.PayloadData;

import java.io.Serial;

public class GetMessageCodeDto implements PayloadData {
    @Serial
    private static final long serialVersionUID = 4664705260817765973L;
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

}
