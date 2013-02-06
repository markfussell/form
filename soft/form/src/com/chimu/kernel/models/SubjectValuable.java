/*======================================================================
**
**  File: chimu/kernel/models/SubjectValuable.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.kernel.models;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;

/**
A SubjectValuable object responds to value and value(Object).
**/

public interface SubjectValuable {
    public Object valueFor(Object subject);
//    public void   setValueFor_to(Object subject, Object value);
}



/*

  addObserver(Observer)
     Adds an observer to the set of observers for this object.
  clearChanged()
     Indicates that this object has no longer changed, or that it has already notified all of its observers of its most recent
     change.
  countObservers()
     Returns the number of observers of this object.
  deleteObserver(Observer)
     Deletes an observer from the set of observers of this object.
  deleteObservers()
     Clears the observer list so that this object no longer has any observers.
  hasChanged()
     Tests if this object has changed.
  notifyObservers()
     If this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call
     the clearChanged method to indicate that this object has no longer changed.
  notifyObservers(Object)
     If this object has changed, as indicated by the hasChanged method, then notify all of its observers and then call
     the clearChanged method to indicate that this object has no longer changed.
  setChanged()
     Indicates that this object has changed.
     */