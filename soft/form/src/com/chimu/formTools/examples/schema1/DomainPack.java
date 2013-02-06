/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1/DomainPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1;

import java.sql.Connection;

/**
DomainPack...
**/
public class DomainPack {

        /**
         * Create a new domain ObjectBase to work with
         */
    static public ObjectBase newObjectBase() {
        return new ObjectBaseC().initialize();
    }
    
    static public ObjectBase newObjectBase_UsingFormConnection(Connection jdbcConnection) {
        return new ObjectBase_FormC().initJdbc(jdbcConnection);
    }
   
}

