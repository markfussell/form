/*======================================================================
**
**  File: chimu/kernel/exceptions/FailedRequireException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.exceptions;


/**
 * Signals that an error occured because a subclass or subtype should not
 * implement a particular method (it actively restricts the super's interface)
 * but a client called the method.
 */
public class FailedRequireException extends DevelopmentException {
    public FailedRequireException() {
        super();
    };

    public FailedRequireException(String detailMessage) {
        super("<Require> "+detailMessage);
    };
};

