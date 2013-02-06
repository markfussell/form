/*======================================================================
**
**  File: chimu/dome/state/ToUpdateStateC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;


class ToUpdateStateC extends ReplicateStateAbsC {
    /*package*/ ToUpdateStateC(){};

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void refresh(StateToReplicateInterface domainObject) {
        domainObject.refreshFromStorage();
        domainObject.setState(nextStateAfterRefresh());
    }

    public void save(StateToReplicateInterface domainObject) {
        domainObject.updateToStorage();
        domainObject.setState(nextStateAfterSave());
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ ReplicateState nextStateAfterRefresh()   {return StatePack.cleanState();}
    /*package*/ ReplicateState nextStateAfterSave()      {return StatePack.cleanState();}

    /*package*/ ReplicateState nextStateAfterDelete()    {return StatePack.toDeleteState();}
    /*package*/ ReplicateState nextStateAfterMarkDirty() {return this;}

}

