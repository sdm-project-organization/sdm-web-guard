package com.mo.guard.model.embedded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationAuthResourceId implements Serializable {

    @Column(name = "auth_sq")
    int authSequence;

    @Column(name = "resource_sq")
    int resourceSequence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelationAuthResourceId)) return false;
        RelationAuthResourceId that = (RelationAuthResourceId) o;
        return Objects.equals(getAuthSequence(), that.getAuthSequence()) &&
                Objects.equals(getResourceSequence(), that.getResourceSequence());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthSequence(), getResourceSequence());
    }

    @Override
    public String toString() {
        return getAuthSequence() + "-" + getResourceSequence();
    }
}
