/*======================================================================
**
**  File: chimu/dome/state/StatePack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;

/**
chimu.dome.state contains a domain object state engine for optimistic (delayed)
transaction behavior.  The two starting states are cleanState and toInsertState
which are for objects read from the database or created locally, respectively.
Transitions between the states are cause by markingDirty, deleting, saving
and refreshing.  Saving and refreshing (potentially) modify the object along
with the state transition.

<P>An object must support the StateToReplicateInterface to be used with dome.state.

@see ReplicateState
@see StateToReplicateInterface
**/

public class StatePack {

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * A CleanState is when a replicate has not been changed
         * by the application since being read from the database.
         * This should be the starting state for any object read
         * from the database
         */
    static public ReplicateState cleanState()    {return cleanState;}

        /**
         * A toInsertState is when an object is created locally and
         * is desired to be placed on the database.  This should be
         * the starting state of any object created locally.
         */
    static public ReplicateState toInsertState() {return toInsertState;}

    static /*package*/ ReplicateState toUpdateState() {return toUpdateState;}
    static /*package*/ ReplicateState toDeleteState() {return toDeleteState;}
    static /*package*/ ReplicateState phantomState()  {return phantomState;}

    //**********************************************************
    //**********************************************************
    //**********************************************************

    static ReplicateState cleanState = new CleanStateC();
    static ReplicateState toInsertState = new ToInsertStateC();
    static ReplicateState toUpdateState = new ToUpdateStateC();
    static ReplicateState toDeleteState = new ToDeleteStateC();
    static ReplicateState phantomState = new PhantomStateC();

    //**********************************************************
    //**********************************************************
    //**********************************************************

    private StatePack() {};
};
