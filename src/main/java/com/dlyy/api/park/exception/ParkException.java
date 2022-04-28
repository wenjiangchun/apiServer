package com.dlyy.api.park.exception;

public class ParkException extends RuntimeException{

    private int errorCode;


    public ParkException(int errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
