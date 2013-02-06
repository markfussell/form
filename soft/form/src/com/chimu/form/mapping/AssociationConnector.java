/*======================================================================
**
**  File: chimu/form/mapping/AssociationConnector.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.impl.*;

import java.sql.*;
import java.io.PrintWriter;

/**
An AssociationConnector is responsible for managing Associations via an
external Association table whose rows specify links between objects.
An AssociationConnector has pseudo-slots which are used to connect
(during construction of the slot for the ObjectMapper) between an
ExternalAssociationSlot and the ObjectRetriever at the other side of the
association.

@see ObjectMapper
@see ExternalAssociationSlot
**/

//Connector
public interface AssociationConnector {

    //**********************************************************
    //(P)***************** Slot Creation ***********************
    //**********************************************************

        /**
         * Create a ConnectorSlot with an implicit retriever as
         * retriever of the first [and only] external association
         * slot that references this connector slot.
         */
    public AssociationConnectorSlot newConnectorSlot_column(
            String slotName,
            String  mappedColumnName);


        /**
         * Create a ConnectorSlot with an explicit retriever to fetch
         * values for.
         */
    public AssociationConnectorSlot newConnectorSlot_column_retriever_retrieverSlot(
            String slotName,
            String  mappedColumnName,
            ObjectRetriever associationRetriever, String retrieverExternalSlotName);


        /**
         *@deprecated See newConnectorSlot_column
         */
    public AssociationConnectorSlot newAssociationSlot(
            String slotName,

            String               mappedColumnName
            );

    //**********************************************************
    //********************** Initializing **********************
    //**********************************************************

    public void doneSetup();
        /**
         * Check whether the setup of this mapper appears
         * to be correct.  This can either be done before
         * calling "doneSetup" or it will be done during
         * that call.
         */
    public boolean isSetupValid();
    public void validateSetup();

    //**********************************************************
    //************************* Asking *************************
    //**********************************************************

    public AssociationConnectorSlot associationSlotNamed(String slotName);
    public String name();

    //**************************************
    //(P)****** Development Tracing ********
    //**************************************

    public void setupTraceStream (PrintWriter traceStream);
    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel);

}