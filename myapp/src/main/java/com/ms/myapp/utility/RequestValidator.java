package com.ms.myapp.utility;

import com.ms.myapp.exceptions.RequestInvalidException;
import com.ms.myapp.models.CardDto;
import com.ms.myapp.models.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestValidator {

    private static final Logger logger = LoggerFactory.getLogger(RequestValidator.class);

    public static void validateRequest(Request<CardDto> request) {
        if (request == null || request.getRequestData() == null || (request.getRequestData().getRequestType() != null && request.getRequestData().getPayload() == null)) {
            logger.error("Invalid request or Request does not have valid values:{}", request);
            throw new RequestInvalidException("Invalid request or Request does not have valid values.");
        }
    }
}
