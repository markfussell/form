/*======================================================================
**
**  File: chimu/form/mapping/AssociationSlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;

/**
An AssociationSlot is used when you want to specify that the value of the
slot comes through a relationship to another mapper.  The different subtypes
of association slots handle different variations for how this relationship
is stored on the database.

<P>AssociationSlot has three subtypes
<UL>
   <LI>ForwardAssociationSlot: Where your table has a column that refers to
       your partner's identity.  This can handle N-1 relationships.
   <LI>ReverseAssociationSlot: Where your partner's table has a column that
       refers to your identity.  This can handle 1-N relationship.
   <LI>ExternalAssociationSlot: Where a third association table contains
       references to both you and your partners identity.  This can handle N-M
       relationships.
</UL>
@see ForwardAssociationSlot
@see ReverseAssociationSlot
@see ExternalAssociationSlot
**/

public interface AssociationSlot extends SetterSlot {
    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

        /**
         * States that the current object contains the partner objects, so whenever
         * the current object is deleted, all the partner objects are also
         * deleted
         *
         *@version NotImplementedYet
         */
    public void    setupContainsPartner();
    public void    setupContainsPartnerNot();

        /**
         * Update the associations by keeping track of what changes occured locally
         * and then sending just the changes to the database.  This means the database
         * is an intersection of changes between this object and any other object
         * interacting with the database
         *
         *@version NotImplementedYet
         */
    public void    setupUpdatingByDeltas();

        /**
         * Update the associations by making the database mirror the state of the current
         * object
         *
         *@version NotImplementedYet
         */
    public void    setupUpdatingByReplacing();

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

//    public ObjectRetriever partnerMapper();
    public ObjectRetriever partnerRetriever();

};