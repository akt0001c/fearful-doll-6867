package com.ms.myapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {
    private Integer cardId;
    private String cardHolderName;
    private String cardNo;
    private Double balance;
    private String status;





}
