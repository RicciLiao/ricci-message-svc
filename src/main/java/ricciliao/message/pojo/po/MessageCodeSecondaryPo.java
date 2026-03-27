package ricciliao.message.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "message_code_secondary")
public class MessageCodeSecondaryPo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "code", nullable = false)
    private Long code;

    @Column(name = "consumer", nullable = false, length = 50)
    private String consumer;

    @Column(name = "level")
    private Character level;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @Column(name = "created_dtm", nullable = false)
    private Instant createdDtm;

    @Column(name = "updated_by", nullable = false)
    private Long updatedBy;

    @Column(name = "updated_dtm", nullable = false)
    private Instant updatedDtm;

    @Column(name = "version", nullable = false)
    private Instant version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getConsumer() {
        return consumer;
    }

    public void setConsumer(String consumer) {
        this.consumer = consumer;
    }

    public Character getLevel() {
        return level;
    }

    public void setLevel(Character level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(o instanceof MessageCodeSecondaryPo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getConsumer(), that.getConsumer()) && Objects.equals(getLevel(), that.getLevel()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getConsumer(), getLevel(), getDescription(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}