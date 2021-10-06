package com.sytoss.exception.no_such_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoSuchUserAccountException extends Exception {

    private static final Logger logger = LoggerFactory.getLogger(NoSuchUserAccountException.class);

    public NoSuchUserAccountException(String message) {
        super(message);
        logger.error(message);
    }
}
