/*======================================================================
**
**  File: chimu/dome/state/PhantomStateC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;


class PhantomStateC extends ReplicateStateAbsC {
    /*package*/ PhantomStateC(){};

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public boolean isDirty() {return false;}
    public boolean isNew() {return true;}
    
    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void refresh(StateToReplicateInterface domainObject) {
        return; //Don't have anything to do
    }

    public void save(StateToReplicateInterface domainObject) {
        return; //Don't have anything to do
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ ReplicateState nextStateAfterRefresh()  {return this;}
    /*package*/ ReplicateState nextStateAfterSave()     {return this;}

    /*package*/ ReplicateState nextStateAfterDelete()   {return this;}
    /*package*/ ReplicateState nextStateAfterMarkDirty() {return StatePack.toInsertState();}
}

