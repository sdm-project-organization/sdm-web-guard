package com.mo.guard.constant;

import lombok.Getter;

@Getter
public enum EnableFlag {
    Y("YES", (byte) 1),
    N("NO", (byte) 2);

    private String message;
    private byte value;

    EnableFlag(String message, byte value) {
        this.message = message;
        this.value = value;
    }

}
