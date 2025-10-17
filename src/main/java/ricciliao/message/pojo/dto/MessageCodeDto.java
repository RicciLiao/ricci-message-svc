package ricciliao.message.pojo.dto;


import ricciliao.x.component.response.data.ResponseData;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

public class MessageCodeDto implements ResponseData {
    @Serial
    private static final long serialVersionUID = 4664705260817765973L;
    private Long id;
    private String code;
    private String level;
    private String consumer;
    private String description;
    private Boolean active;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
    private Instant version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Instant createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(Instant updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    public Instant getVersion() {
        return version;
    }

    public void setVersion(Instant version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageCodeDto dto)) return false;
        return Objects.equals(getId(), dto.getId()) && Objects.equals(getCode(), dto.getCode()) && Objects.equals(getLevel(), dto.getLevel()) && Objects.equals(getConsumer(), dto.getConsumer()) && Objects.equals(getDescription(), dto.getDescription()) && Objects.equals(getActive(), dto.getActive()) && Objects.equals(getCreatedBy(), dto.getCreatedBy()) && Objects.equals(getCreatedDtm(), dto.getCreatedDtm()) && Objects.equals(getUpdatedBy(), dto.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), dto.getUpdatedDtm()) && Objects.equals(getVersion(), dto.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getLevel(), getConsumer(), getDescription(), getActive(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}
