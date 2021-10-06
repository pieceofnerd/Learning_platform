package com.sytoss.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateCourseNameException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(DuplicateCourseNameException.class);

    public DuplicateCourseNameException(String message) {
        super(message);
        logger.error(message);
    }
}
