/*======================================================================
**
**  File: chimu/form/mapping/ObjectMapperSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.form.database.*;
import com.chimu.form.query.*;

/**
ObjectMapperSi is private to FORM and should not be used by FORM clients
**/
/*subsystem(Query)*/ public interface ObjectMapperSi extends ObjectMapperXi {

    /*subsystem(Query)*/ public int encodeType(MappedObject domainObject);
    /*subsystem(Query)*/ public int encodeJavaType(MappedObject domainObject);
    /*subsystem(Query)*/ Object encodeObject(MappedObject domainObject);


    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         * Prepare the mapper to be ready for use
         *<P>@Require isValidSetup()
         */
    public void doneSetup();


        /**
         * Check whether the setup of this mapper appears
         * to be correct.  This can either be done before
         * calling "doneSetup" or it will be done during
         * that call.
         */
    public boolean isSetupValid();
    public void validateSetup();


        /**
         * Check whether the cross references between all the
         * ObjectMappers is valid.  This can only be done
         * after all the mappers are setup.
         */
    public boolean isConfigurationValid();
    public void configure();

        /**
         * Cross Validate
         */
    public void crossValidate();

};
