package com.edi.learn.axon.exceptions;

import org.axonframework.common.AxonException;

/**
 * Created by Edison Xu on 2017/4/11.
 */
public class NoEnoughStockException extends AxonException {

    public NoEnoughStockException(String message) {
        super(message);
    }

    public NoEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
