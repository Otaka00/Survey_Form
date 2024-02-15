package com.sumerge.survey.enumeration;

// SectionState.java
public enum SectionState {
    UNOPENED(-1),
    OPENED_UNTOUCHED(0),
    OPENED_TOUCHED(1),
    COMPLETED(2);

    private final int value;

    SectionState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
