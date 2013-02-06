/*======================================================================
**
**  File: chimu/dome/state/ToDeleteStateC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.state;

import com.chimu.kernel.exceptions.*;

class ToDeleteStateC extends ReplicateStateAbsC {
    /*package*/ ToDeleteStateC(){};

    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public boolean willDelete() {return true;}

    //**********************************************************
    //(P)******************** Altering *************************
    //**********************************************************

    public void refresh(StateToReplicateInterface domainObject) {
        domainObject.refreshFromStorage();
        domainObject.setState(nextStateAfterRefresh());
    }

    public void save(StateToReplicateInterface domainObject) {
        domainObject.deleteFromStorage();
        domainObject.setState(nextStateAfterSave());
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*package*/ ReplicateState nextStateAfterRefresh()  {return StatePack.cleanState();}
    /*package*/ ReplicateState nextStateAfterSave()     {return StatePack.phantomState();}

    /*package*/ ReplicateState nextStateAfterDelete()   {return this;}
    /*package*/ ReplicateState nextStateAfterMarkDirty() {
        throw new ShouldNotImplementException("Unexpected state transition: ToDelete->ToUpdate");
//        return StatePack.toUpdateState();
    }

}

