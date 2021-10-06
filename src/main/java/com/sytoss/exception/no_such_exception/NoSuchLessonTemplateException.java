package com.sytoss.exception.no_such_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoSuchLessonTemplateException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(NoSuchLessonTemplateException.class);

    public NoSuchLessonTemplateException(String message) {
        super(message);
        logger.error(message);
    }
}
