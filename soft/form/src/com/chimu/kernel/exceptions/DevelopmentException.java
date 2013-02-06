/*======================================================================
**
**  File: chimu/kernel/exceptions/DevelopmentException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.exceptions;


/**
 * Signals that an error with the development of the code has occured
 * This is a parent class for NotImplementedYet, FailedRequire, ...
 */
public class DevelopmentException extends RuntimeException {
    public DevelopmentException() {
        super();
    };

    public DevelopmentException(String detailMessage) {
        super(detailMessage);
    };
};

