/*======================================================================
**
**  File: chimu/form/mapping/ReverseAssociationSlotSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import java.sql.SQLException;

/**
ReverseAssociationSlotSi is private to the FORM system and should not be used
by FORM clients
**/
/*subsytem(Query)*/ public interface ReverseAssociationSlotSi extends ReverseAssociationSlot, SlotSi {
    // boolean forceServerIdentityOnPartnersOf(Object object);
    // void    postIdentityInsertUpdatePartnerOf(Object object);

    ///*package*/ public void deleteCascadeFor(Object object);

    /*subsytem(Query)*/ public ForwardAssociationSlotSi partnerSlot();
};