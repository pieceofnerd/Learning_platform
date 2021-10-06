package com.sytoss.exception;

public class StudyNoContentException extends Exception{
    public StudyNoContentException(String message) {
        super(message);
    }

    public StudyNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
