/*======================================================================
**
**  File: chimu/form/FormThrowInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;
import com.chimu.kernel.exceptions.WrappedException;

/**
FormException is a generic exception coming from the FORM
subsystem.  More specific exceptions are generated, see the "see also"
section.

<P>FormExceptions provide extra details on who recognized an error,
during what stage the error was caused, and who caused it.  These may
make error recognition easier.
**/
public interface FormThrowInfo {
    public String recognizer();
    public String stage();
    public String cause();
    public FormAnomaly anomaly();
}



