/*======================================================================
**
**  File: chimu/kernel/exceptions/ThrowableWrapper.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.exceptions;

/**
A ThrowableWrapper is an interface for exceptions that can wrap other
exceptions.
**/
public interface ThrowableWrapper {
    public Throwable wrappedThrowable();
}

