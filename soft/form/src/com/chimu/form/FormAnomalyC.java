/*======================================================================
**
**  File: chimu/form/FormAnomalyC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;

/**
FormAnomalyC
**/
public class FormAnomalyC implements FormAnomaly {
    public FormAnomalyC() {

    }

    public boolean equals(Object anObject) {
        if (!(anObject instanceof FormAnomaly)) return false;
        return id().equals(
                ((FormAnomaly) anObject).id()
            );
    }

    public String toString() {
        StringBuffer stringB = new StringBuffer();
        stringB.append("<Anomaly "+group+"#"+code);
        if (name != null) {
            stringB.append(" "+name);
        };
        stringB.append(">");
        return stringB.toString();
    }

    public String id() {
        return group+"#"+code;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void initGroup_code(String group, int code) {
        this.group = group;
        this.code = code;
    }

    public void setupName(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    //**********************************************************
    //********************* ExceptionDetail ********************
    //**********************************************************

    public String describeException(Throwable exception) {
        if (exception instanceof FormThrowInfo) {
            return exception.toString();
        } else {
            return exception.toString();
        }
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected String group = null;
    protected int    code  = -1;
    protected String name  = null;

}



