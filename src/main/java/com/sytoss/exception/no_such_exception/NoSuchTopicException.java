package com.sytoss.exception.no_such_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoSuchTopicException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(NoSuchTopicException.class);

    public NoSuchTopicException(String message) {
        super(message);
        logger.error(message);
    }
}
