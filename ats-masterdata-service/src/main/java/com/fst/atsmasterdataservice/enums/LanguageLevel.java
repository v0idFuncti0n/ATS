package com.fst.atsmasterdataservice.enums;

public enum LanguageLevel {
    A1(0),
    A2(1),
    B1(2),
    B2(3),
    C1(4),
    C2(5);

    private final int value;

    LanguageLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
