/*======================================================================
**
**  File: chimu/kernel/exceptions/FailedInvariantException.java
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
public class FailedInvariantException extends DevelopmentException {
    public FailedInvariantException() {
        super();
    };

    public FailedInvariantException(String detailMessage) {
        super("<Invariant> "+detailMessage);
    };
};

