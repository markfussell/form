/*======================================================================
**
**  File: chimu/dome/state/CleanStateC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;

/**
A CleanState is when a replicate has not been changed by the application
since being read from the database
**/
class CleanStateC extends ReplicateStateAbsC {
    /*package*/ CleanStateC(){};

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public boolean isDirty() {return false;}

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void refresh(StateToReplicateInterface domainObject) {
        return;  //Don't need to do anything
    }

    public void save(StateToReplicateInterface domainObject) {
        return;  //Don't need to do anything
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ ReplicateState nextStateAfterRefresh()   {return this;}
    /*package*/ ReplicateState nextStateAfterSave()      {return this;}

    /*package*/ ReplicateState nextStateAfterDelete()    {return StatePack.toDeleteState();}
    /*package*/ ReplicateState nextStateAfterMarkDirty() {return StatePack.toUpdateState();}
}

