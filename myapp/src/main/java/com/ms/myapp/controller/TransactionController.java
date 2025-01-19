package com.ms.myapp.controller;

import com.ms.myapp.models.MasterResponse;
import com.ms.myapp.models.request.Request;
import com.ms.myapp.models.response.Response;
import com.ms.myapp.service.CardService;
import com.ms.myapp.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.ms.myapp.utility.ConstantsURL.GET_ALL_TRANSACTION_URL;

public class TransactionController {

    @Autowired
    private CardService cardService;

    /**
     * It is used to add transaction in database.
     * @param request it contains  request data required to add transaction in db
     * @return ResponseEntity<MasterResponse<response>> It returns the added transaction confirmation.
     */
    public ResponseEntity<MasterResponse<Object>> addTransaction(Request<String> request){
    try {
        //  RequestValidator.validateRequest(cardRequest);
        MasterResponse res = cardService.getTransactionForOneCard();
        return ResponseEntity.status(HttpStatus.OK).body(res);

    } catch (Exception exp) {
        Response<String> response = new Response<>(Constants.BAD_REQUEST, Constants.ERROR_IN_ADDING_TRANSACTION, null);
        MasterResponse res = new MasterResponse(null, response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}

    /**
     * It is used to get all the transaction details of one particular card.
     * @param cardRequest It is  request to fetch all transaction details of one particular card.
     * @return It returns the transaction list of one particular card.
     * @exception com.ms.myapp.exceptions.RequestInvalidException
     *   if we got any exception during the process then it will be handled by catch block and then
     *    a response with bad_request status and error message will be returned by controller.
     */
    @GetMapping(GET_ALL_TRANSACTION_URL)
    public ResponseEntity<MasterResponse<Object>> getAllTransactionForOneCard(@RequestBody Request<String> cardRequest) {
        try {
            //  RequestValidator.validateRequest(cardRequest);
            MasterResponse res = cardService.getTransactionForOneCard();
            return ResponseEntity.status(HttpStatus.OK).body(res);

        } catch (Exception exp) {
            Response<String> response = new Response<>(Constants.BAD_REQUEST, Constants.ERROR_IN_FINDING_TRANSACTIONS, null);
            MasterResponse res = new MasterResponse(null, response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }
}
