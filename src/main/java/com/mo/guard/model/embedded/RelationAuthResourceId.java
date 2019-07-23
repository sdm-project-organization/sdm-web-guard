package com.mo.guard.model.embedded;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class RelationAuthResourceId implements Serializable {

    @Column(name = "auth_sq")
    int authSequence;

    @Column(name = "resource_sq")
    int resourceSequence;

    @Override
    public String toString() {
        return authSequence + "-" + resourceSequence;
    }
}
