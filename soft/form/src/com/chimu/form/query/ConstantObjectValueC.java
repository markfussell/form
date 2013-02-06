/*======================================================================
**
**  File: chimu/form/query/ConstantObjectValueC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.ObjectMapperSi;
import com.chimu.form.mapping.MappedObject;

class ConstantObjectValueC implements Constant {
    ConstantObjectValueC(MappedObject object) {
        this.object = object;
    };

    public void putExpressionValueInto(SqlBuilder sqlB) {
        ObjectMapperSi om = ((ObjectMapperSi) object.form_objectMapper());
        Object identity = om.encodeObject(object);
        int sqlType = om.encodeType(object);
        int javaType = om.encodeJavaType(object);
        sqlB.appendValue_javaType_sqlType(identity,javaType,sqlType);
    };


    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected MappedObject object;
};

