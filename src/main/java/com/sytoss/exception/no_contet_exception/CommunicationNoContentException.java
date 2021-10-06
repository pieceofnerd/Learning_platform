package com.sytoss.exception.no_contet_exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommunicationNoContentException extends Exception{

    private static final Logger logger = LoggerFactory.getLogger(CommunicationNoContentException.class);

    public CommunicationNoContentException(String message) {
        super(message);
        logger.error(message);
    }
}
