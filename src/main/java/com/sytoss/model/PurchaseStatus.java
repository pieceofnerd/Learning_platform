package com.sytoss.model;

public enum PurchaseStatus {

    PAYED(8L),
    DECLINED(9L),
    REFUND(10L);

    private final Long value;

    PurchaseStatus(final Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

}
