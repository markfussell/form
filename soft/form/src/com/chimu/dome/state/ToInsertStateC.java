/*======================================================================
**
**  File: chimu/dome/state/ToInsertStateC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;


class ToInsertStateC extends ReplicateStateAbsC {
    /*package*/ ToInsertStateC(){};

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public boolean isNew() {return true;}

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void refresh(StateToReplicateInterface domainObject) {
        return; //Don't have anything to do
    }

    public void save(StateToReplicateInterface domainObject) {
        domainObject.insertIntoStorage();
        domainObject.setState(nextStateAfterSave());
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ ReplicateState nextStateAfterRefresh()   {return this;}
    /*package*/ ReplicateState nextStateAfterSave()      {return StatePack.cleanState();}

    /*package*/ ReplicateState nextStateAfterDelete()    {return StatePack.toDeleteState();}
    /*package*/ ReplicateState nextStateAfterMarkDirty() {return this;}

}

