package com.ms.myapp.controller;

import com.ms.myapp.entities.Card;
import com.ms.myapp.entities.Transaction;
import com.ms.myapp.models.CardRequest;
import com.ms.myapp.models.request.Request;
import com.ms.myapp.models.MasterResponse;
import com.ms.myapp.models.response.Response;
import com.ms.myapp.service.CardService;
import com.ms.myapp.utility.Constants;
import com.ms.myapp.utility.RequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

import static com.ms.myapp.utility.Constants.ADD_CARD_END_POINT_CALLED;
import static com.ms.myapp.utility.Constants.ADD_CARD_END_POINT_RESPONSE;
import static com.ms.myapp.utility.ConstantsURL.*;

/**
 * cardRequest: {
 *     "metaData":"",
 *     "requestData":{
 *        "requestType":"Add",
 *        "payload":{
 *         "key":"abb",
 *         "content":"",
 *          "instruction":"some instruction in few condition"
 *
 *        }
 *
 *
 *     }
 *   }
 *
 *   MasterResponse:{
 *       "metaData":"",
 *       "response":{
 *           "statusCode:"",
 *           "message":"",
 *           "body":{}
 *       }
 *   }
 *
 *
 */

@RestController
@RequestMapping(BASE_URL)
public class CardController {

    @Autowired
    private CardService cardService;

private static final Logger logger= LoggerFactory.getLogger(CardController.class);


/**
* It is used to add the new card into the system.
* @param cardRequest It contains the request to add a new card in database.
* @return ResponseEntity<MasterResponse<Object>> It returns the response object that contains the confirmation about the added card.
 * @exception com.ms.myapp.exceptions.RequestInvalidException
 * Here we are handling the exception using catch block here.
 * if we got any exception during the process then it will be handled by catch block and then
 * a response with bad_request status and error message will be returned by controller.
 *
*/
@PostMapping(ADD_CARD_URL)
public ResponseEntity<MasterResponse<Object>> addCard(@RequestBody CardRequest<Request> cardRequest){
   try {

      logger.info(ADD_CARD_END_POINT_CALLED,cardRequest);

       RequestValidator.validateRequest(cardRequest);
       MasterResponse res = cardService.addCard();
       logger.info(ADD_CARD_END_POINT_RESPONSE,res);

       return ResponseEntity.status(HttpStatus.CREATED).body(res);
   }
    catch (Exception exp)
    {
        Response<String> response= new Response<>(Constants.BAD_REQUEST, Constants.CARD_CANNOT_BE_ADDED,null);
        MasterResponse res= new MasterResponse(null,response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }



}

    /**
     * It is used to remove a card from the system.
     * @param cardRequest It contains the request to remove the card from the database.
     * @return ResponseEntity<MasterResponse<Object>> It returns the response after deleting the card from the database.
     * @exception com.ms.myapp.exceptions.RequestInvalidException
     * if we got any exception during the process then it will be handled by catch block and then
     *   a response with bad_request status and error message will be returned by controller.
     */
    @DeleteMapping(REMOVE_CARD_URL)
public ResponseEntity<MasterResponse<Object>> removeCard(@RequestBody CardRequest<Request> cardRequest){
    try {
        RequestValidator.validateRequest(cardRequest);
        MasterResponse res = cardService.removeCard();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
    }catch (Exception exp)
    {
        Response<String> response= new Response<>(Constants.BAD_REQUEST,Constants.CARD_CANNOT_BE_REMOVED,null);
        MasterResponse res= new MasterResponse(null,response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}

    /**
     * It is used to change the status of the already existing card.
     * @param cardRequest It is the request to change the card status.
     * @return ResponseEntity<MasterResponse<Object>> It returns the response for changing the status of the card
     * @exception com.ms.myapp.exceptions.RequestInvalidException
     * if we got any exception during the process then it will be handled by catch block and then
     *  a response with bad_request status and error message will be returned by controller.
     */
    @PatchMapping(CHANGE_STATUS_URL)
public ResponseEntity<MasterResponse<Object>> changeCardStatus(@RequestBody CardRequest<Request> cardRequest){
    try {
        RequestValidator.validateRequest(cardRequest);
        MasterResponse res = cardService.changeStatus();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(res);
    }catch (Exception exp){
        Response<String> response= new Response<>(Constants.BAD_REQUEST,Constants.ERROR_IN_CHANGING_THE_CARD_STATUS,null);
        MasterResponse res= new MasterResponse(null,response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }
}

    /**
     * It is used to fetch the all the cards available in database.
     * @return It returns the response that contains the list of all cards.
     * @exception com.ms.myapp.exceptions.RequestInvalidException
     * if we got any exception during the process then it will be handled by catch block and then
     *   a response with bad_request status and error message will be returned by controller.
     */
    @GetMapping(GET_ALL_CARDS_URL)
public ResponseEntity<MasterResponse<Object>> getAllCardList(){
    try {
        MasterResponse res = cardService.getAllCards();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }catch (Exception exp)
    {
        Response<String> response= new Response<>(Constants.BAD_REQUEST,Constants.CARD_LIST_NOT_AVAILABLE,null);
        MasterResponse res= new MasterResponse(null,response);
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
public ResponseEntity<MasterResponse<Object>> getAllTransaction(@RequestBody CardRequest<Request> cardRequest){
    try {
        RequestValidator.validateRequest(cardRequest);
        MasterResponse res = cardService.getTransactionForOneCard();
        return ResponseEntity.status(HttpStatus.OK).body(res);

    }catch (Exception exp){
        Response<String> response= new Response<>(Constants.BAD_REQUEST,Constants.ERROR_IN_FINDING_TRANSACTIONS,null);
        MasterResponse res= new MasterResponse(null,response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}



}
