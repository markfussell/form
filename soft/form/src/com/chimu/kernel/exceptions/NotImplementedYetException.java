/*======================================================================
**
**  File: chimu/kernel/exceptions/NotImplementedYetException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.exceptions;


/**
 * Signals that a method was called that is not implemented yet
 */
public class NotImplementedYetException extends DevelopmentException {
    public NotImplementedYetException() {
        super();
    };

    public NotImplementedYetException(String detailMessage) {
        super(detailMessage);
    };
};

