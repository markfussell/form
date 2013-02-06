/*======================================================================
**
**  File: chimu/dome/client/DomainObject_State_1_AbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.dome.client;

import com.chimu.dome.state.*;

import com.chimu.form.client.*;
import com.chimu.form.client.DomainObject_1_AbsC;
import com.chimu.form.mapping.*;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

/**
**/
public abstract class DomainObject_State_1_AbsC
            extends DomainObject_1_AbsC
            implements StateToReplicateInterface
            {

    protected DomainObject_State_1_AbsC() {
        setToInsertState();
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void delete() {
        state.delete(this);
    }

    public void refresh() {
        state.refresh(this);
    }

        //Should call this synch?  refresh and synch refresh & save, (revert, refresh, save)
    public void synch() {
        state.save(this);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected void setToInsertState() {
        state = StatePack.toInsertState();
    }

    protected void setToCleanState() {
        state = StatePack.cleanState();
    }

    protected void markDirty() {
        state.markDirty(this);
    }

    //**********************************************************
    //******************* State Changes ************************
    //**********************************************************

        /**
         * Change the state of the object to the new state
         */
    /*friend:state*/ public void setState(ReplicateState newState) {
        state = newState;
    }

    //**********************************************************
    //**************** Object Modifications ********************
    //**********************************************************

    /*friend:state*/ public void deleteFromStorage() {
        mapper().deleteObject(this);
    }

    /*friend:state*/ public void refreshFromStorage() {
        mapper().shallowRefreshObject(this);
    }

    /*friend:state*/ public void insertIntoStorage(){
        mapper().insertObject(this);
    }

    /*friend:state*/ public void updateToStorage(){
        mapper().updateObject(this);
    }


    //**************************************
    //**************************************
    //**************************************

        /**
         * initState should initialize the state of the object
         * from the slot values and then make sure the object
         * knows it is not a stub object anymore.
         *
         * <P> Each sublcass should call their parent's <CODE>initState</CODE>
         * method before doing their own.
         *@see #form_extractState
         */
    /*friend:FORM*/ public void form_initState (KeyedArray slotValues) {
        super.form_initState(slotValues);
        setToCleanState();
    }

    //**********************************************************
    //(P)****************** Notification ***********************
    //**********************************************************
/*
     public void form_wroteObject() {
        super.form_wroteObject();
        setToCleanState();
    }
*/
    //**********************************************************
    //(P)*************** Midwrite Callbacks ********************
    //**********************************************************

    /*friend:FORM*/ public void form_updateAfterIdentityFor(ObjectMapperXi om) {
        super.form_updateAfterIdentityFor(om);
        setToCleanState();
    }

    //**********************************************************
    //(P)******************* Constructors **********************
    //**********************************************************

    /*friend:FORM*/ protected DomainObject_State_1_AbsC(CreationInfo parameter) {
        super(parameter);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected ReplicateState state = null;

}


