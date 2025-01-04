package com.ms.myapp.utility;

import com.ms.myapp.exceptions.RequestInvalidException;
import com.ms.myapp.models.CardRequest;
import com.ms.myapp.models.request.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestValidator {

    private static final Logger logger = LoggerFactory.getLogger(RequestValidator.class);

    public static void validateRequest(CardRequest<Request> cardRequest) {
        if (cardRequest == null || cardRequest.getRequest().getRequestData() == null || (cardRequest.getRequest().getRequestData().getRequestType() != null && cardRequest.getRequest().getRequestData().getPayload() == null)) {
            logger.error("Invalid request or Request does not have valid values:{}", cardRequest);
            throw new RequestInvalidException("Invalid request or Request does not have valid values.");
        }
    }
}
