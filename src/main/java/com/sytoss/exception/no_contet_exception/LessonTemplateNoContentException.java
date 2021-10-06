package com.sytoss.exception.no_contet_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LessonTemplateNoContentException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(LessonTemplateNoContentException.class);

    public LessonTemplateNoContentException(String message) {
        super(message);
        logger.error(message);
    }
}
