package com.mo.guard.constant;

import lombok.Getter;

@Getter
public enum ActiveFlag {
    NO(0, "NO"),
    YES(1, "YES");

    private int value;
    private String desc;

    ActiveFlag(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

}
