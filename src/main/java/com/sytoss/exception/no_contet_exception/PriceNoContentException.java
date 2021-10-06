package com.sytoss.exception.no_contet_exception;

public class PriceNoContentException extends Exception{
    public PriceNoContentException(String message) {
        super(message);
    }

    public PriceNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
