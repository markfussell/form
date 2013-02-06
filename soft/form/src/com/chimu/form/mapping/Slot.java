/*======================================================================
**
**  File: chimu/form/mapping/Slot.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;
import com.chimu.form.database.*;
import com.chimu.kernel.functors.*;

/**
A slot defines a connection point between state information
represented in an object and state information stored on a database
It provides transformation capabilities in both directions (as needed).

<p> Coming from an object-mapper's point of view, the slots define what state information
is needed to save the object to the database and what state information must
be retrieved from the database to correctly replicate that database version of the
object.

<P> Each slot is responsible for handling the transformations themselves which can
either be very simple or quite extensive.

<P>
A slot defines how to bind some information from an object to a database
A slot is the common ground between the attributes of the database

<P>Mappers and Retrievers are the factories for Slots.
@see com.chimu.form.mapping.ObjectMapper
@see com.chimu.form.mapping.SelectiveObjectRetriever
**/

public interface Slot {
        /**
         * The name of the slot
         */
    public String name();

        /**
         * Specify that this slot needs to be refreshed after an insert to the database
         */
    public void setupRefreshAfterInsert();
    public void setupRefreshAfterUpdate();
    public void setupRefreshAfterInsertOrUpdate();

        /**
         * Specify that this slot should be used with optimistic locking, using
         * the default slotValue.equals(rowSlotValue) algorithm.
         */
    public void setupOptimisticLock();

        /**
         * Specify that this slot should be used with optimistic locking and
         * that it should use optimisticPredicate {Predicate2Arg[slotValue, rowSlotValue]}
         * to test whether the slot matches the rowSlotValue.
         *@param optimisticPredicate returns true if slotValue {arg1} matches
         *rowSlotValue{arg2}.
         */
    public void setupOptimisticLockWithPredicate(Predicate2Arg optimisticPredicate);

        /**
         * Make the slot not write to the database
         */
    public void setupDisableRowWriting();

        /**
         * Make the slot not initialize the object
         */
    public void setupDisableObjectInitializing();


    public void setupGetterAndSetter(Function1Arg getter, Procedure2Arg setter);
    public void setupGetter(Function1Arg getter);
    public void setupSetter(Procedure2Arg setter);

        /**
         * Setup a generator function that will be used when the slot
         * value is to be written for insert into the database.
         *
         * The generator (Function2Arg) will receive the slotValue as
         * the first argument and the object itself as the second
         * argument.  It should return the value to use for the slot
         * when doing a first insert into the database
         *
         *@version NotImplementedYet
         */
    public void setupGenerateForInsertFunction(Function2Arg generator);

        /**
         * The generator (Function2Arg) will receive the slotValue as
         * the first argument and the object itself as the second
         * argument.  It should return the value to use for the slot
         * when doing any updates to the database
         *
         *@version NotImplementedYet
         */
    public void setupGenerateForUpdateFunction(Function2Arg generator);

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * Can this slot have null values
         */
    public boolean isValueNullable();

        /**
         * What is the maximum length a value can be
         */
    public int valueMaximumLength();
};


        /**
         * Make the slot not extract values from the object (coupled with above?)
         */
//    public void setupDisableObjectExtracting();

