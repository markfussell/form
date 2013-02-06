/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/PersonAbsC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;
/**
PersonAbsC_FormInfo creates the ObjectMapper for PersonAbsC.

@see com.chimu.form.description.FormInfo
**/
public class PersonAbsC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
       super.configureMappers();

        myMapper.newDirectSlot_column_type("name","person_name",String.class);
        myMapper.newDirectSlot_type("email",String.class);
        myMapper.newDirectSlot_type("height",Integer.class);
    }

    public Class myC() {
       return PersonAbsC.class;
    }
}

