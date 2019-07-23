package com.mo.guard.model.embedded;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RelationAuthResourceId implements Serializable {

    int authSequence;
    int resourceSequence;

    @Override
    public String toString() {
        return authSequence + "-" + resourceSequence;
    }
}
