package com.volkan.exception;

import lombok.Getter;

@Getter
public class VehicleManagerException extends RuntimeException{
    private final EErrorType errorType;

    public VehicleManagerException(EErrorType errorType) {
       //super(errorType.getMessage());
        this.errorType = errorType;
    }

    public VehicleManagerException(EErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }


}
