package com.sytoss.model.enums;

public enum PromotionStatus {

    ANNOUNCED(20L),
    ACTIVE(21L),
    FINISHED(22L);

    private final Long value;

    PromotionStatus(final Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
