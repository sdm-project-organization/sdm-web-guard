package com.mo.guard.constant;

import lombok.Getter;

@Getter
public enum ActiveFlag {
    Y("YES", (byte) 1),
    N("NO", (byte) 2);

    private String message;
    private byte value;

    ActiveFlag(String message, byte value) {
        this.message = message;
        this.value = value;
    }

}
