/*======================================================================
**
**  File: chimu/dome/state/StateToReplicateInterface.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;

/**
The StateToReplicateInterface is the interface expected of an object
that is used with dome.states.  It is divided between a single method
to simply record the desired new state and multiple methods to cause
an actual state change to effected on the object.

@see ReplicateState
@see StatePack
**/

public interface StateToReplicateInterface {

    //**********************************************************
    //******************* State Changes ************************
    //**********************************************************

        /**
         * Change the state of the object to the new state
         */
    void setState(ReplicateState newState);

    //**********************************************************
    //**************** Object Modifications ********************
    //**********************************************************

    void deleteFromStorage();
    void refreshFromStorage();
    void insertIntoStorage();
    void updateToStorage();
}

