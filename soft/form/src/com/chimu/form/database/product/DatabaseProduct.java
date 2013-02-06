/*======================================================================
**
**  File: chimu/form/database/product/DatabaseProduct.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product;

import com.chimu.form.database.Table;
import com.chimu.form.database.DatabaseConnection;
import com.chimu.form.database.Scheme;
/**
**/
public interface DatabaseProduct  {

    String key();
    String shortName();

    String name();
    int    code();

    String demoLoginName();
    String demoLoginPassword();

    /*subsystem*/ public Table newTableNamed_for(String tableName,DatabaseConnection dbConnection);
    /*subsystem*/ public Table newTableNamed_for(String tableName,Scheme scheme);

    public boolean matchesDescription(String description);

    public boolean canSupportBoolean();
    
    public String getTypeString(String aString);
    public void setupTypeStrings();

/* the following specifies a list of sql data types (in string) that each product must be
** able to map.  Some will take some precision and length arguments.
** Additional functions will explicitly map to special data types in specific databases.
** For example, see typeMoney()
*/
    public String typeChar();                   //mapped to java.Char[]??
    public String typeString();                 //mapped to java.String
    public String typeVarChar();
    public String typeLongVarChar();            //mapped to java.String

    public String typeDecimal();                //mapped to java.math.BigDecimal
    public String typeNumeric();                //mapped to java.math.BigDecimal
    public String typeMoney();                  //mapped to java.math.BigDecimal
    
    public String typeBit();                    //mapped to java.Boolean    
    public String typeBoolean();                //mapped to java.Boolean
    
    public String typeBinary();                 //mapped to byte arrays
    public String typeVarBinary();              //mapped to byte arrays
    public String typeLongVarBinary();          //mapped to byte arrays
    
    public String typeBigInt();                 //mapped to java.Long
    public String typeTinyInt();                //mapped to java.Byte
    public String typesSmallInt();              //mapped to java.Short
    public String typeInteger();                //mapped to java.Integer
    
    public String typeReal();                   //mapped to java.Real
    public String typeFloat();                  //mapped to java.Double
    public String typeDouble();                 //mapped to java.Double  

    public String typeDate();                   //mapped to java.sql.Date
    public String typeTime();                   //mapped to java.sql.Date
    public String typeTimestamp();              //mapped to java.sql.Timestamp

//not sql 92 standard, but may be implemented by specific databases

    public String typeImage();
    public String typeText();
    
//convenience methods
    public String idColumn();
    public String primaryKeyPrefix();
    public String primaryKeySuffix();
    public String notNull();
    public String nullable();
    
    

    //**********************************************************
    //**********************************************************
    //**********************************************************
//don't know if the below is necessary??

    public void typeChar(String aString);
    public void typeString(String aString);    
    public void typeVarChar(String aString);
    public void typeLongVarChar(String aString);
   
    public void typeDecimal(String aString);
    public void typeNumeric(String aString);
    public void typeMoney(String aString);

    public void typeBit(String aString);
    public void typeBoolean(String aString);

    public void typeBinary(String aString);
    public void typeVarBinary(String aString);
    public void typeLongVarBinary(String aString);
    
    public void typeBigInt(String aString);
    public void typeTinyInt(String aString);
    public void typeSmallInt(String aString);
    public void typeInteger(String aString);
    
    public void typeReal(String aString);
    public void typeFloat(String aString);
    public void typeDouble(String aString);

    public void typeDate(String aString);
    public void typeTime(String aString);
    public void typeTimestamp(String aString);
    
    public void typeImage(String aString);
    public void typeText(String aString);

    public void idColumn(String aString);
    public void primaryKeyPrefix(String aString);
    public void primaryKeySuffix(String aString);
    public void notNull(String aString);
    public void nullable(String aString);
   
}