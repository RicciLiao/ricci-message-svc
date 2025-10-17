package ricciliao.message.pojo.bo;


import ricciliao.x.component.random.RandomGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class RedisCatchBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 8938462924539717763L;

    private final String id = RandomGenerator.nextString(12).generate();
    private final Instant createdDtm = Instant.now();
    private Instant updatedDtm = Instant.now();

    public Instant getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(Instant updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    public Instant getCreatedDtm() {
        return createdDtm;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RedisCatchBo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreatedDtm(), getUpdatedDtm());
    }
}
