/*======================================================================
**
**  File: chimu/form/mapping/MappedObjectXi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.kernel.collections.*;

/**
This contains extended functionality for mapped domain objects
that must be supported to work with the extendedInterface mode
of an ObjectMapper.  By implementing this interface a DomainObject
will be called.

@see ObjectMapper#setupUseExtendedInterface
**/

public interface MappedObjectXi extends MappedObject {


    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * Called whenever FORM needs to insert the identity for the specific
         * object.  This will be followed by a call to #form_updateAfterIdentityFor
         * <P>This should return true if an identity insert was required
         */
    /*friend:FORM*/ public boolean form_forceIdentityFor(ObjectMapperXi om);
    /*friend:FORM*/ public void form_updateAfterIdentityFor(ObjectMapperXi om);
}

