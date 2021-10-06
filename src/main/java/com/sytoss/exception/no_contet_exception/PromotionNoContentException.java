package com.sytoss.exception.no_contet_exception;

public class PromotionNoContentException extends Exception{
    public PromotionNoContentException(String message) {
        super(message);
    }

    public PromotionNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
