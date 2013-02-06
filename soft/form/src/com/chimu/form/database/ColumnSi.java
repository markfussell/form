/*======================================================================
**
**  File: chimu/form/database/ColumnSi.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

/**
ColumnSi defines the Subsystem interface of a Column.

<P>This interface is private to FORM and should not be used by FORM clients.</P>
**/
/*subsystem*/ public interface ColumnSi extends Column {
    /*subsystem*/ public void addWithVarName_equals_varName_sqlTo(String varName, Column toColumn, String toVarName, StringBuffer sqlStringB);
    /*subsystem*/ public void addWithVarName_expressionSqlTo(String varName, StringBuffer sqlStringB);
    /*subsystem*/ public void addWithVarName_selectSqlTo(String varName, StringBuffer sqlStringB);

    //**************************************
    //(P)********** Validation *************
    //**************************************

    // /*package*/ public void crossValidate();
}