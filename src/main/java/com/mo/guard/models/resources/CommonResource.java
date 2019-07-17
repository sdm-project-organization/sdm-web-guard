package com.mo.guard.models.resources;

import lombok.Data;

@Data
public abstract class CommonResource<T, M> {

    protected static final int INIT_SEQUENCE = 0;

    // * Boxed Class 필수
    protected Integer sequence;
    protected Integer displayOrder;
    protected String displayName;
    protected String desc;
    protected Byte activeFlag;
    protected Byte enableFlag;

    public abstract M toInsert() throws Exception;

    public abstract M toUpdate() throws Exception;

    public abstract T toEntity();

}
