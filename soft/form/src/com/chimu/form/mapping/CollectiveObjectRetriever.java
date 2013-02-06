/*======================================================================
**
**  File: chimu/form/mapping/CollectiveObjectRetriever.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.query.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import java.io.PrintWriter;

/**
A CollectiveObjectRetriever supports retrieval of heterogeneous objects
that are all mapped using a DistinguishingObjectMapper.

@see DistinguishingObjectMapper
**/

public interface CollectiveObjectRetriever extends ObjectRetriever {

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    //**************************************
    //(P)****** Development Tracing ********
    //**************************************

    public void setupTraceStream (PrintWriter traceStream);
    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel);

        /**
         * Associate the sub-mapper (specific mapper type) with the distinguisherValue
         * so all row that have the distinguisher value will be considered to belong
         * to this mapper
         */
    public void addDistinguishingMapper(DistinguishingObjectMapper mapper);


    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         * Prepare the mapper to be ready for use
         */
    public void doneSetup();

}
