/*======================================================================
**
**  File: chimu/form/transaction/TransactionException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.transaction;

import com.chimu.form.FormRuntimeException;

/**
A TransactionException indicates that a Transaction has failed and
was rolled-back.  TransactionExceptions are always caused by another
exception, which is wrapped within the TransactionException.
**/
public class TransactionException extends FormRuntimeException {
    public TransactionException(String detail) {
	    super(detail);
    }

    public TransactionException(String detail, Throwable wrappedThrowable) {
	    super(detail,wrappedThrowable);
    }
}



