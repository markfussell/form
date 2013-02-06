/*======================================================================
**
**  File: com/chimu/formTools/examples/schema1b/PersonC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema1b;
/**
PersonC_FormInfo creates the ObjectMapper for PersonC.

@see com.chimu.form.description.FormInfo
**/
public class PersonC_FormInfo extends DomainObjectAbsC_FormInfo {

    public void configureMappers() {
       super.configureMappers();

        myMapper.newDirectSlot_column_type("name","person_name",String.class);
        myMapper.newDirectSlot_type("email",String.class);
        myMapper.newDirectSlot_type("height",Integer.class);
    }

    public Class myC() {
       return PersonC.class;
    }
}
