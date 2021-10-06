package com.sytoss.exception;

public class CourseNotPaidException extends Exception{
    public CourseNotPaidException(String message) {
        super(message);
    }

    public CourseNotPaidException(String message, Throwable cause) {
        super(message, cause);
    }
}
