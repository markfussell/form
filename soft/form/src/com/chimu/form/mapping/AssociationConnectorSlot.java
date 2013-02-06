/*======================================================================
**
**  File: chimu/form/mapping/AssociationConnectorSlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;

/**
An AssociationConnectorSlot is used by an AssociationConnector to keep
track of the slots that are coupled to by ExternalAssociationSlots.
@see ExternalAssociationSlot
**/
public interface AssociationConnectorSlot { //extends SlotC  {
    public String name();


//*******************Below the line is not public*****************//
    /*package*/ void setupRegisterPartner_slotName(
            ObjectRetrieverPi partnerRetriever,
            String            partnerSlotName
            );
    public String partnerSlotName();
    public void crossValidate();

    public String partnerTableName();

    public Column partnerColumn();

    public Column associationColumn();

    public ObjectRetrieverPi partnerRetriever();

    /*subsystem*/ public AssociationConnectorSlot  pairedAssociationSlot();
    /*subsystem*/ public ObjectRetrieverPi         pairedPartnerRetriever();
    /*subsystem*/ public AssociationConnector      associationConnector();

    public Object newColumnValueFromRow (Row row);
    public void   setRow_usingColumnValue  (Row row, Object value);

};