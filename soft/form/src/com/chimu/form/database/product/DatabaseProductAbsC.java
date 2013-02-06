/*======================================================================
**
**  File: chimu/form/database/product/DatabaseProductAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product;

import com.chimu.form.database.support.ConverterLib;
import com.chimu.form.database.Scheme;
import com.chimu.form.database.Table;

import java.sql.Types;
import java.sql.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.meta.*;

public abstract class DatabaseProductAbsC implements DatabaseProduct {
    protected DatabaseProductAbsC() {
    }

    public String toString() {
        return name();
    }

    //**********************************************************
    //************************ Asking **************************
    //**********************************************************

    public String  key() {
        return shortName().toLowerCase();
    }

    public boolean matchesDescription(String description) {
        return description.startsWith(name());
    }

    public boolean canSupportBoolean() {
        return false;
    }
    
    /*subsystem*/ public Table newTableNamed_for(String tableName,Scheme scheme) {
        Table result = this.newTableNamed_for(tableName,scheme.databaseConnection());
        result.setupScheme(scheme);
        return result;
    }
    
    //**********************************************************
    //**********************************************************
    //**********************************************************

    public String typeChar() {
        return typeChar;
    }

    public String typeString() {
        return typeString;
    }
    
    public String typeVarChar() {
        return typeString;
    }
            
    public String typeLongVarChar() {
        return typeString;
    }   

    public String typeDecimal() {
        return typeDecimal;
    }

    public String typeNumeric() {
        return typeNumeric;
    }

    public String typeMoney() {
        return typeMoney;
    }

    public String typeBoolean() {
        
        return typeBoolean;
    }

    public String typeBit() {
        return typeBoolean;
    }

    public String typeBinary() {
        return typeBinary;
    }
    
    public String typeVarBinary() {
        return typeBinary;
    }
        
    public String typeLongVarBinary() {
        return typeLongVarBinary;
    }
    
    public String typeBigInt(){     //??
        return typeBigInt;
    }
    public String typeTinyInt() {
        return typeTinyInt;
    }
    public String typesSmallInt() {
        return typeSmallInt;
    }
    public String typeInteger() {
        return typeInteger;
    }
    
    public String typeReal() {
        return typeReal;
    }

    public String typeFloat() {
        return typeFloat;
    }
    
    public String typeDouble() {
        return typeDouble;
    }

    public String typeDate() {
        return typeDate;
    }

    public String typeTime() {
        return typeTime;
    }

    public String typeTimestamp() {
        return typeTimestamp;
    }

    public String idColumn() {
        return idColumn;
    }

    public String primaryKeyPrefix() {
        return primaryKeyPrefix;
    }

    public String primaryKeySuffix() {
        return primaryKeySuffix;
    }

    public String notNull() {
        return notNull;
    }

    public String nullable() {
        return nullable;
    }


    public String typeImage() {
        return typeImage;
    }
    
    public String typeText() {
        return typeText;
    }
        

    //**********************************************************
    //*********************** Setting **************************
    //**********************************************************

    public void typeChar(String aString) {
        typeChar = aString;
    }

    public void typeString(String aString) {
        typeString = aString;
    }

    public void typeVarChar(String aString) {
        typeVarChar = aString;
    }

    public void typeLongVarChar(String aString) {
        typeLongVarChar = aString;
    }
    
    public void typeDecimal(String aString) {
        typeDecimal = aString;
    }

    public void typeNumeric(String aString) {
        typeNumeric = aString;
    }

    public void typeMoney(String aString) {
        typeMoney = aString;
    }

    public void typeBoolean(String aString) {
        typeBoolean = aString;
    }

    public void typeBit(String aString) {
        typeBit = aString;
    }
    
    public void typeBinary(String aString) {
        typeBinary = aString;
    }
    
    public void typeVarBinary(String aString) {
        typeVarbinary = aString;
    }
    
    public void typeLongVarBinary(String aString) {
        typeLongVarBinary = aString;
    }
   
    public void typeBigInt(String aString) {
        typeBigInt = aString;
    }
    
    public void typeSmallInt(String aString) {
        typeSmallInt = aString;
    }

    public void typeTinyInt(String aString) {
        typeTinyInt = aString;
    }
   
    public void typeInteger(String aString) {
        typeInteger = aString;
    }

    
    public void typeFloat(String aString) {
        typeFloat = aString;
    }

    
    public void typeDouble(String aString) {
        typeDouble = aString;
    }

    public void typeReal(String aString) {
        typeReal = aString;
    }



    public void typeDate(String aString) {
        typeDate = aString;
    }

    public void typeTime(String aString) {
        typeTime = aString;
    }

    public void typeTimestamp(String aString) {
        typeTimestamp = aString;
    }
    
    public void typeText(String aString) {
        typeText = aString;   
    }

    public void typeImage(String aString) {
        typeImage = aString;
    }
    
    //**********************************************************
    //**********************************************************
    //**********************************************************
    

    public void idColumn(String aString) {
        idColumn = aString;
    }

    public void primaryKeyPrefix(String aString) {
        primaryKeyPrefix = aString;
    }

    public void primaryKeySuffix(String aString) {
        primaryKeySuffix = aString;
    }

    public void notNull(String aString) {
        notNull = aString;
    }

    public void nullable(String aString) {
        nullable = aString;
    }

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected String typeChar    = null;
    protected String typeString = null;
    protected String typeLongVarChar = null;
    protected String typeVarChar = null;

    protected String typeDecimal = null;
    protected String typeMoney   = null;
    protected String typeNumeric = null;    

    protected String typeBit    = null;
    protected String typeBoolean    = null;
    
    protected String typeBinary    = null;
    protected String typeVarbinary = null;
    protected String typeLongVarBinary = null;
    
    protected String typeBigInt = null;
    protected String typeInteger = null;
    protected String typeTinyInt = null;
    protected String typeSmallInt = null;

    protected String typeFloat = null;
    protected String typeDouble = null;
    protected String typeReal = null;

    protected String typeDate    = null;
    protected String typeTime = null;
    protected String typeTimestamp = null;

    protected String typeText   = null;
    protected String typeImage  = null;
    
    protected String idColumn    = null;

    protected String primaryKeyPrefix = null;
    protected String primaryKeySuffix = null;
    protected String notNull = null;
    protected String nullable = null;

 
    /**  
     *  This method sets up the default strings for all databases
     *
     *@review [MLF] Why is this needed as a public method?  
     *        Shouldn't this be done as 
     *        part of the construction/init process?  Are there
     *        variations when you would need to either call or not
     *        call this method?  Or when other methods must be called
     *        first?
     *@review [MLF] You might want to call these typeName_Char, etc.
     *        because it would be a bit more readable.
     */
    public void setupTypeStrings() {

        typeChar        = "CHAR";        
        typeString      = "VARCHAR";
        typeVarChar      = "VARCHAR";  //???
        typeLongVarChar = "LONGVARCHAR";    //???

        typeDecimal  = "DECIMAL";
        typeMoney    = "DECIMAL";
        typeNumeric  = "NUMERIC";       //???

        typeBoolean  = "BIT";
        typeBit      = "BIT";
    
        typeBinary   = "BINARY";
        typeVarbinary = "VARBINARY";
        typeLongVarBinary = "LONGVARBINARY";
        
        typeInteger  = "INT";        
        typeTinyInt  = "TINYINT";
        typeSmallInt = "SMALLINT";
        typeBigInt   = "BIGINT";

        typeFloat    = "REAL";
        typeReal     = "REAL";
        typeDouble   = "FLOAT";

        typeDate     = "DATE";
        typeTime     = "TIME";
        typeTimestamp= "TIMESTAMP";

        typeText     = "TEXT";
        typeImage    = "IMAGE";
        
        idColumn = "id INT"+" "+notNull;
        primaryKeyPrefix = ", PRIMARY KEY (";
        primaryKeySuffix = ")";
        notNull      = "NOT NULL";
        nullable     = "";

    }
    
    /**
     * Given a string, return a type string that most resemble the appropriate type
     *@review Having a collection of the typeNames may be more maintainable than a
     *        large case statement.
     *@review I am also not sure how this relates to the typeConstants.  Doesn't 
     *        each typeConstant have a String name... so for a given database we
     *        just need to replace a particular typeConstants with a new named 
     *        typeConstants or maybe one of a different form (e.g. Boolean->Char(1))
     *        That may help to form the basis of automatic conversion routines.
     *        In any case, the strings hear seem redundant with the typeConstants.
     */
    
    public String getTypeString(String aString) {
        String upperStr = aString.toUpperCase();
        if (upperStr.equals("REAL")) {
            return typeFloat;}
        else if (upperStr.equals("DOUBLE")) {
            return typeDouble;}
        else if  (upperStr.equals("DATE")) {
            return typeDate;}
        else if (upperStr.equals("TIME")) {
            return typeTime;}
        else if (upperStr.equals("TIMESTAMP")) {
            return typeTimestamp;}
        else if (upperStr.equals("DECIMAL")) {
            return typeDecimal;}
        else if (upperStr.equals("MONEY")) {
            return typeMoney;}
        else if (upperStr.equals("CHAR")) {
            return typeChar;}
        else if (upperStr.equals("BOOLEAN")) {
            return typeBoolean;}
        else if (upperStr.equals("BINARY")) {
            return typeBinary;}
        else if (upperStr.equals("STRING")) {
            return typeString;}
        else if (upperStr.equals("INTEGER")) {
            return typeInteger;}
        else if (upperStr.equals("FLOAT")) {
            return typeFloat;}
        else if (upperStr.equals("NOTNULL")) {
            return notNull;}
        else if (upperStr.equals("NULLABLE")) {
            return nullable;}
        else {
            return "UNKNOWN";
        }
    }


}
