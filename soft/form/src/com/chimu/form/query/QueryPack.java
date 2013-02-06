/*======================================================================
**
**  File: chimu/form/query/QueryPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.*;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

import java.util.Enumeration;

/**
QueryPack defines the FORM query capabilities.  Especially see QueryDescription
for the core functionality
@see QueryDescription
**/
public class QueryPack {

        /**
         * Setup the maximum result-column variable length
         * for the database.
         */
    static public void setupVarLength(int varLength) {
        QueryDescriptionC.setupVarLength(varLength);
    }

    private QueryPack() {}; //Library class, not instanciable
};



