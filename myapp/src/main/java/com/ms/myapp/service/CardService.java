package com.ms.myapp.service;

import com.ms.myapp.models.MasterResponse;
import org.springframework.stereotype.Service;

@Service
public interface CardService {

public MasterResponse addCard();
public MasterResponse removeCard();

public MasterResponse getAllCards();

public MasterResponse changeStatus();

public MasterResponse getTransactionForOneCard();
}
