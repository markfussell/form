/*======================================================================
**
**  File: chimu/form/mapping/AssociationConnectorPi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.form.database.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import java.sql.SQLException;

/**
 * This type is responsible for solely managing the rows that define an association
 * and collaborating with the slots to retrieve the object on the other side of the
 * association
 */

interface AssociationConnectorPi extends AssociationConnectorSi {

    /*package*/ public void setupRegisterExternalSlot(ExternalAssociationSlotPi exSlot, String associationSlotName);

    public Object selectWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue)
            throws SQLException;

    public Object findWhereSlotNamed_equalsColumnValue(String slotName, Object columnValue)
            throws SQLException;

//    public Object selectStubsWhereSlotNamed_equals(String slotName, Object value)
//           throws SQLException;

    //**********************************************************
    //************************ Deleting ************************
    //**********************************************************

    /*package*/ public void deleteWhereSlotNamed_equals(String slotName, MappedObject value)
            throws SQLException;

    /*package*/ public void deleteWhereSlot_equals(int slotNumber, MappedObject value)
            throws SQLException;


    //**********************************************************
    //*********************** Inserting ************************
    //**********************************************************

    /*package*/ public void insertCollection_for_fromSlotNamed(Collection collection, MappedObject object, String slotName)
            throws SQLException;
    /*package*/ public void insertObject_for_fromSlotNamed(MappedObject partnerObject, MappedObject object, String slotName)
            throws SQLException;

    //**********************************************************
    //*********************** Updating *************************
    //**********************************************************

    /*package*/ public void updateCollection_for_fromSlotNamed(Collection collection, MappedObject object, String slotName)
            throws SQLException;
    /*package*/ public void updateObject_for_fromSlotNamed(MappedObject partnerObject, MappedObject object, String slotName)
            throws SQLException;

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*friend(AssociationConnectorSlot)*/ public AssociationConnectorSlot otherSlotFor(AssociationConnectorSlot aSlot);
};