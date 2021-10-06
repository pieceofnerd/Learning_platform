package com.sytoss.exception.no_contet_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoryNoContentException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(CategoryNoContentException.class);

    public CategoryNoContentException(String message) {
        super(message);
        logger.error(message);
    }
}
