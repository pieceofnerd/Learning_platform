package com.sytoss.model.enums;

public enum HomeworkStatus {

    CREATED(15L),
    DONE(16L),
    OVERDUE(17L),
    PROVEN(23L);

    private final Long value;

    HomeworkStatus(final Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
