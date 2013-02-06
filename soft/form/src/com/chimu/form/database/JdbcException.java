/*======================================================================
**
**  File: chimu/form/database/JdbcException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.form.FormRuntimeException;

/**
An ExecutionException is an exception thrown during program execution
that indicates a problem with runtime behavior.
**/
public class JdbcException extends FormRuntimeException {
    public JdbcException(String detail) {
	    super(detail);
    }

    public JdbcException(String detail, Throwable wrappedThrowable) {
	    super(detail,wrappedThrowable);
    }
}



