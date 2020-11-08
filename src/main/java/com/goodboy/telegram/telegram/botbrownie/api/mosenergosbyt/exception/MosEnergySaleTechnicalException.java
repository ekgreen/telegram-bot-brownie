package com.goodboy.telegram.telegram.botbrownie.api.mosenergosbyt.exception;

public class MosEnergySaleTechnicalException extends RuntimeException {

    public MosEnergySaleTechnicalException() {
        super();
    }

    public MosEnergySaleTechnicalException(String message) {
        super(message);
    }

    public MosEnergySaleTechnicalException(String message, Throwable cause) {
        super(message, cause);
    }

    public MosEnergySaleTechnicalException(Throwable cause) {
        super(cause);
    }

    protected MosEnergySaleTechnicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
