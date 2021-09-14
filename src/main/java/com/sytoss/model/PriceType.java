package com.sytoss.model;

public enum PriceType {

    REGULAR(4L),

    PREMIUM(5L);

    private final Long value;

    PriceType(final Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }


}
