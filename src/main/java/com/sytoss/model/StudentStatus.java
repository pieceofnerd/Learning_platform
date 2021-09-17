package com.sytoss.model;

public enum StudentStatus {

    NEWBIE(18L),

    ADVANCED(19L);

    private final Long value;

    StudentStatus(final Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

}
