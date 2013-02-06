/*======================================================================
**
**  File: chimu/form/database/support/GeneratorFromDatabase.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.support;

import com.chimu.form.database.Table;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.streams.*;

/**
A GeneratorFromDatabase generates values by using a database to
store counters or seeds.  This allows a current generation to be
related to generations which occured on previous runs of the
application or to generations caused by other clients.

<P>GeneratorFromDatabase has to handle two major issues.  How
generation will occur locally and how generated values effect the
database values.

@see DatabaseSupportPack
@see com.chimu.kernel.streams.Generator
**/
public interface GeneratorFromDatabase extends Generator {

    //**********************************************************
    //(P)******************* Configuration *********************
    //**********************************************************

        /**
         * Setup any transformation that should be applied when
         * transforming between the database column value to the
         * local value
         */
    public void setupRead_write_transformations(
            Function1Arg readFunction,
            Function1Arg writeFunction);

        /**
         * Make the Generator write back values to the database
         */
    public void setupMakeWriteBack();

        /**
         * StartingValue if the database did not provide one.
         */
    public void setupStartingValue(Object value);


        /**
         * Set up the generation functions for incrementing,
         * finding a limit, and testing against the limit.
         *
         * <P>NeedToFetchPredicate can be null which means use
         * currentValue.equals(limitValue) as the predicate
         */
    public void setupNext_limit_needToFetch_functors(
             Function1Arg nextValueFunction,
             Function1Arg limitValueFunction,
             Predicate2Arg needToFetchPredicate);

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * Post construction setup of table to use.
         */
    public void setupTable(Table table);

        /**
         * Don't commit the connection to support transactions
         */
    public void setupDontCommitConnection();

        /**
         * Completed setup
         */
    public void doneSetup();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void clearCache();

}



