package com.sytoss.exception.no_such_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoSuchCategoryException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(NoSuchCategoryException.class);

    public NoSuchCategoryException(String message) {
        super(message);
        logger.error(message);
    }
}
