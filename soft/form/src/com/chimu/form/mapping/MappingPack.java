/*======================================================================
**
**  File: chimu/form/mapping/MappingPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.kernel.functors.*;

import com.chimu.form.database.Table;
import java.sql.Connection;
import com.chimu.form.*;


/**
MappingPack contains the FORM Object-Relational mapping classes.
See ObjectMapper, ObjectRetriever, Slot, and other classes for
documentation of the FORM classes.

<P>The MappingPack contains some Functors which have been moved
into com.chimu.kernel.utilities.TranslationLib

@see ObjectMapper
@see ObjectRetriever
@see Slot
@see com.chimu.kernel.utilities.TranslationLib
**/
public class MappingPack {

    //**********************************************************
    //********************** Exceptions ************************
    //**********************************************************

    static final String EXCEPTION_GROUP      = "form.mapping";
    static final String EXCEPTION_RECOGNIZER = "form.mapping";



    static public FormAnomaly optimisticLockAnomaly()   {return optimisticLockAnomaly;}
    static public FormAnomaly configurationAnomaly()    {return configurationAnomaly;}


    static protected FormAnomaly optimisticLockAnomaly = null;
    static protected FormAnomaly configurationAnomaly = null;

    static {
        FormAnomalyC anomaly = null;

        anomaly = new FormAnomalyC();
            anomaly.initGroup_code(EXCEPTION_GROUP,1);
            anomaly.setupName("Optimistic Lock Failure");
            optimisticLockAnomaly = anomaly;

        anomaly = new FormAnomalyC();
            anomaly.initGroup_code(EXCEPTION_GROUP,2);
            anomaly.setupName("Invalid Configuration");
            configurationAnomaly = anomaly;
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    private MappingPack() {};

}
