package com.sytoss.exception.no_contet_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TopicNoContentException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(TopicNoContentException.class);

    public TopicNoContentException(String message) {
        super(message);
        logger.error(message);
    }
}
