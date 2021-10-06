package com.sytoss.exception;

public class StudyGroupNoContentException extends Exception{
    public StudyGroupNoContentException(String message) {
        super(message);
    }

    public StudyGroupNoContentException(String message, Throwable cause) {
        super(message, cause);
    }
}
