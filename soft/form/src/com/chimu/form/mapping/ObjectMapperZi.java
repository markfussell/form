/*======================================================================
**
**  File: chimu/form/mapping/ObjectMapperZi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.streams.*;

import com.chimu.form.database.*;
import com.chimu.form.query.*;


/**
ObjectMapperZi simply shows the methods that went away since the previous version.
**/
public interface ObjectMapperZi extends ObjectMapper {

    //**********************************************************
    //(P)******************** Setup Slots **********************
    //**********************************************************

    //**************************************
    //(P)********* Direct Slots ************
    //**************************************

    public Slot newDirectSlot(String slotName, String mappedColumnName);
    public Slot newDirectSlot(String slotAndColunName);

    //**************************************
    //(P)******** Identity Slot ************
    //**************************************

    public IdentitySlot newIdentitySlot(
            String slotName,

            String    mappedColumnName
            );

    public IdentitySlot newIdentitySlot(
            String slotName,

            Function1Arg     getter,
            Procedure2Arg    setter,

            String    mappedColumnName
            );


    //**************************************
    //(P)***** Transformation Slots ********
    //**************************************

    public Slot newTransformationSlot(
            String slotName,

            String          mappedColumnName,
            Function1Arg    decodeFromDatabase,
            Function1Arg    encodeForDatabase
            );

    //**************************************
    //(P)********* Constant Slot ***********
    //**************************************

    public Slot newConstantSlot(
            String slotName,

            String          mappedColumnName,
            Object          value
            );

    //**************************************
    //(P)**** Forward Association Slots ****
    //**************************************


     public ForwardAssociationSlot newForwardAssociationSlot(
            String slotName,

            String          mappedColumnName,
            ObjectRetriever partnerRetriever
            );



    //**************************************
    //(P)**** Reverse Association Slots ****
    //**************************************


    public ReverseAssociationSlot newReverseAssociationSlot(
            String slotName,

            ObjectRetriever partnerRetriever,
            String          partnerSlotName
            ) ;



    //**************************************
    //(P)*** External Association Slots ****
    //**************************************


    public ExternalAssociationSlot newExternalAssociationSlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName
            );

    public ExternalAssociationSlot newExternalAssociationSlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName,
            String                 partnerConnectorSlotName
            );



    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

};
