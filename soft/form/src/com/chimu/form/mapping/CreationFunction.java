/*======================================================================
**
**  File: chimu/form/mapping/CreationFunction.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

/**
A CreationFunction defines a Functor that creates a MappedObject given certain
CreationInfo.  This interface is used to tell FORM how a particular class of
DomainObject can be constructed.  The CreationInfo allows using a FORM specific
constructor.  See Learning FORM and the FORM Preprocessor for more information.

@see MappedObject
**/
/*subsystem*/ public interface CreationFunction {
    public MappedObject valueWith(CreationInfo cInfo);
}