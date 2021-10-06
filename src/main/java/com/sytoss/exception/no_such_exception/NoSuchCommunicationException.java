package com.sytoss.exception.no_such_exception;

import com.sytoss.model.communication.Communication;

public class NoSuchCommunicationException extends Exception {
    public NoSuchCommunicationException(String message) {
        super(message);
    }
}
