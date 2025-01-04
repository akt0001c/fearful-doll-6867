package com.ms.myapp.models;

import jakarta.validation.Valid;

public class CardRequest<T> {

    @Valid()
    private T request;

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
