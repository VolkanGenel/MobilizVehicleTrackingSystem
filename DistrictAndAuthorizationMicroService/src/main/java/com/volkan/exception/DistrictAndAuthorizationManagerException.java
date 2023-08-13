package com.volkan.exception;

import lombok.Getter;

@Getter
public class DistrictAndAuthorizationManagerException extends RuntimeException{
    private final EErrorType errorType;

    public DistrictAndAuthorizationManagerException(EErrorType errorType) {
       //super(errorType.getMessage());
        this.errorType = errorType;
    }

    public DistrictAndAuthorizationManagerException(EErrorType errorType, String customMessage) {
        super(customMessage);
        this.errorType = errorType;
    }


}
