/*======================================================================
**
**  File: chimu/form/mapping/ReverseAssociationSlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;

import com.chimu.kernel.functors.*;


/**
A ReverseAssociationSlot is used when your partner's table
has a foreign-key back to your table's primary key. A 
ReverseAssociationSlot is specified in terms of a partner
and a ForwardAssociationSlot of that partner.  Forward and
Reverse associations are complementary:

<P>By default a ReverseAssociation is one-to-many, but if you
know the cardinality is restricted to one-to-one you can setup
the slot to know that as well.  In this case the slot will
return either the single object or 'null' instead of a
collection of objects.

@version 0.1
**/
public interface ReverseAssociationSlot extends AssociationSlot{
    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************
    
        /**
        Make this association a one-to-one correspondence instead of
        a one-to-many.
         */
    public void setupIsOneToOne();
    
        /**
         * Use the following Future/Wrapper constructor for building
         * the object.  The Functor takes another Functor (Function0Arg)
         * that will retrieve the object when evaluated.
         */
    public void setupHolderConstructor(Function1Arg aFunctor) ;

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************
    
        /**
        Is this reverse association a one-to-one correspondence?
        This also means that the return object is expected to be
        a single object or null
         */
    public boolean isOneToOne();
};