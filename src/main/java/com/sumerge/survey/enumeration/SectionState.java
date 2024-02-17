package com.sumerge.survey.enumeration;
public enum SectionState {
    UNOPENED(0),
    OPENED_UNTOUCHED(1),
    OPENED_TOUCHED(2),
    COMPLETED(3);

    private final int value;

    SectionState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
