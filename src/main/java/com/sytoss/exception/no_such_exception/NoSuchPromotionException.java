package com.sytoss.exception.no_such_exception;

public class NoSuchPromotionException extends Exception{
    public NoSuchPromotionException(String message) {
        super(message);
    }

    public NoSuchPromotionException(String message, Throwable cause) {
        super(message, cause);
    }
}
