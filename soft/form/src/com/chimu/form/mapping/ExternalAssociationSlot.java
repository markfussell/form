/*======================================================================
**
**  File: chimu/form/mapping/ExternalAssociationSlot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.Function1Arg;

/**
An ExternalAssociationSlot specifies an association that uses an intermediate
association connector (and corresponding association table) to retrieve the
associated objects.  This is the most flexible of all associations since it
supports: 1-1, 1-N, N-1, and N-M relationships.  It also has more overhead than
the simpler relationships.
**/
public interface ExternalAssociationSlot extends AssociationSlot {

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

        /**
         * Make this association an X-to-one correspondence instead of
         * an X-to-many (the default).  This only impacts the association
         * from our perspective (the X side).  The partner can independently
         * choose whether to make it one-to-Y or many-to-Y from their
         * perspective (the Y side).
         */
    public void setupIsToOne();

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
         * Is this reverse association a one-to-one correspondence?
         * This also means that the return object is expected to be
         * a single object or null
         */
    public boolean isToOne();

};