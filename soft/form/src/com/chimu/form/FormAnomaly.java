/*======================================================================
**
**  File: chimu/form/FormAnomaly.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;

/**
FormAnomaly
**/
public interface FormAnomaly {

    public boolean equals(Object anObject);
    public String toString();
    public String name();
        /**
         * Unique identifier for the Anomaly
         */
    public String id();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String describeException(Throwable exception);
}



