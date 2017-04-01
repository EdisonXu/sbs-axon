package com.edi.learn.axon.common.exception;

import org.axonframework.common.AxonException;

/**
 * Created by Edison on 2017/3/25.
 */
public class OrderCreateFailedException extends AxonException {

    public OrderCreateFailedException(String message) {
        super(message);
    }
}
