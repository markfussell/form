/*======================================================================
**
**  File: chimu/dome/state/ReplicateState.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;

/**
ReplicateStates keep track of the current state of a domain object
and know how to transition states based on method "triggers".
ReplicateStates are an internal delegation object to the actual
Replicate.

@see StateToReplicateInterface
@see StatePack
**/
public interface ReplicateState {

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

        /**
         * Is the state "dirty": will a save cause a write
         * to the database
         */
    public boolean isDirty();

        /**
         * Is this a state that will cause the object to be
         * deleted on save?
         */
    public boolean willDelete();
    public boolean isNew();
    public boolean isClean();

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

        /**
         * Refresh the replicate from the database
         */
    public void refresh(StateToReplicateInterface domainObject);

        /**
         * Save the changes (or deletion of) the replicate to the database.
         * Could also call this "record","synch", etc.
         */
    public void save(StateToReplicateInterface domainObject);


    public void delete(StateToReplicateInterface domainObject);
    public void markDirty(StateToReplicateInterface domainObject);
}

