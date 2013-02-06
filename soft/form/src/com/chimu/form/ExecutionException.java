/*======================================================================
**
**  File: chimu/form/ExecutionException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;

import com.chimu.form.FormRuntimeException;

/**
An ExecutionException is an exception thrown during program execution
that indicates a problem with runtime behavior.
**/
public class ExecutionException extends FormRuntimeException {
    public ExecutionException(String detail) {
	    super(detail);
    }

    public ExecutionException(String detail, Throwable wrappedThrowable) {
	    super(detail,wrappedThrowable);
    }
}



