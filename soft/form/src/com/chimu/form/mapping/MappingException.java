/*======================================================================
**
**  File: chimu/form/mapping/MappingException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.FormRuntimeException;

/**
A MappingException is an exception that occured within the FORM
mapping functionality.  It indicates that a mapping configuration
error has occured that is only recognizable at runtime.
@see com.chimu.kernel.exceptions.ConfigurationException
**/
public class MappingException extends FormRuntimeException {
    public MappingException(String detail) {
	    super(detail);
    }

    public MappingException(String detail, Throwable wrappedThrowable) {
	    super(detail,wrappedThrowable);
    }
}



