/*======================================================================
**
**  File: chimu/dome/state/ReplicateStateAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;


abstract class ReplicateStateAbsC implements ReplicateState {
    /*package*/ ReplicateStateAbsC(){};

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************
        //By default a state is dirty
    public boolean isDirty() {return true;}
    public boolean willDelete() {return false;}
    public boolean isNew() {return false;}

    public boolean isClean() {return !isDirty();}

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void delete(StateToReplicateInterface domainObject) {
        domainObject.setState(nextStateAfterDelete());
    }

    public void markDirty(StateToReplicateInterface domainObject) {
        domainObject.setState(nextStateAfterMarkDirty());
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ abstract ReplicateState nextStateAfterDelete();
    /*package*/ abstract ReplicateState nextStateAfterMarkDirty();

}

