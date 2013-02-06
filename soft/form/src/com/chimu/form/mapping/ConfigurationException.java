/*======================================================================
**
**  File: chimu/form/mapping/ConfigurationException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.FormRuntimeException;

/**
A ConfigurationException is an exception caused by incorrect configuration
of FORM.  This exception is usually only thrown during Orm#doneSetup.
**/
public class ConfigurationException extends FormRuntimeException {
    public ConfigurationException(String detail) {
	    super(detail);
    }

    public ConfigurationException(String detail, Throwable wrappedThrowable) {
	    super(detail,wrappedThrowable);
    }
}



