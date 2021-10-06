package com.sytoss.exception.no_contet_exception;

public class PurchaseNoContentException extends Exception {
    public PurchaseNoContentException(String message) {
        super(message);
    }

    public PurchaseNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
