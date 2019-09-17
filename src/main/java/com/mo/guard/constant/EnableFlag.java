package com.mo.guard.constant;

import lombok.Getter;

@Getter
public enum EnableFlag {
    NO(0, "NO"),
    YES(1, "YES");

    private int value;
    private String desc;

    EnableFlag(int order, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
