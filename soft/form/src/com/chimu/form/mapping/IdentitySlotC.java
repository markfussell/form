/*======================================================================
**
**  File: chimu/form/mapping/IdentitySlotC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;
import com.chimu.form.mapping.description.SlotTypeConstants;

import com.chimu.kernel.streams.Generator;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;

import java.sql.Connection;

/*package*/  class IdentitySlotC extends SetterSlotC implements IdentitySlotPi {
    /*subsystem*/ public int slotType() {
        return SlotTypeConstants.IDENTITY_SLOT;
    }

    //**********************************************************
    //(P)**************** Not Implemented Yet ******************
    //**********************************************************
    public void    setupContainsPartner() {throw new NotImplementedYetException();};
    public void    setupContainsPartnerNot() {throw new NotImplementedYetException();};
    public void    setupUpdatingByDeltas() {throw new NotImplementedYetException();} ;
    public void    setupUpdatingByReplacing() {throw new NotImplementedYetException();};

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*package*/ IdentitySlotC(
            String          name,
            ObjectMapper    mapper,

            Function1Arg     getter,
            Procedure2Arg    setter,

            Column           mappedColumn,

            Function1Arg    decoder,
            Function1Arg    encoder
            ) {
        super(name,mapper,
            getter,setter,
            mappedColumn,
            decoder,
            encoder
            );
    }

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    public void setupPostInsertIdentityRetrieverFunction(Function2Arg retriever) {
        identityRetrieverFunction = retriever;
    }

    public void setupPostInsertIdentityRetrieverFunction_decoder(Function2Arg retriever, Function1Arg decoder) {
        identityRetrieverFunction = retriever;
        identityRetrieverDecoder = decoder;
    }

    public void setupPreInsertIdentityGenerator(Generator generator) {
        identityGenerator = generator;
    }

        /**
         * IdentityC needs to override and throw an exception for this:
         * ShouldNotImplement("Identity slots can not be wrapped by a holder");
         */
    public void setupHolderConstructor(Function1Arg aFunctor) {
        super.setupHolderConstructor(aFunctor);
    }

    //**************************************
    //(P)********** Validation *************
    //**************************************

    /*subsystem*/ public void crossValidate() {
        return; //[1]

        //[1] Don't need to do anything for an Identity Slot
    }


    //**********************************************************
    //(P)****************      PACKAGE      ********************
    //**********************************************************

        // How to fetch identity?
        // All require interacting with the database, so we need the connection
        // Options include:
        //    "select $sequence.currval from dual"
        //    "get @@IDENTITY" for Sybase
        //    "execute $storedProcedure"
        //    "??"
        // Call a stored procedure


    /*package*/ public Object retrieveInsertIdentityFor_using(Object object, Connection connection) {
          Object columnValue = identityRetrieverFunction.valueWith_with(object,connection);
          return decode(columnValue);
    }

    /*package*/ public Object decode(Object value) {
        if (identityRetrieverDecoder == null) return super.decode(value);
        return identityRetrieverDecoder.valueWith(value);
    }

    /*package*/ public void generateIdentityInto(Object object) {
        if (identityGenerator == null) throw new DevelopmentException("No identity generator but tried to generate identity");
        Object identityKey = identityGenerator.nextValue();
        setObject_usingSlotValue(object,identityKey);
    }

    /*package*/ public boolean hasIdentityGenerator() {
        return identityGenerator != null;
    }

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected Function2Arg identityRetrieverFunction = null;
    protected Function1Arg identityRetrieverDecoder = null;

    protected Generator identityGenerator = null;
};