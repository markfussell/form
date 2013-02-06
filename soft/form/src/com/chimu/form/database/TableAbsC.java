/*======================================================================
**
**  File: chimu/form/database/TableAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.meta.*;

import com.chimu.form.query.*;
import com.chimu.form.mapping.*;
import com.chimu.form.database.support.*;

import java.sql.*;

import java.util.Enumeration;
import java.io.PrintWriter;


/*subsystem*/ public abstract class TableAbsC implements TableSi, TableDpi {
    /*package*/ protected TableAbsC(String name, String fullName, Connection connection) {
        this.name = name;
        this.fullName = fullName;
        this.dbConnection = null;
        this.connection = connection;
    }

    /*package*/ protected TableAbsC(String name, Scheme scheme) {
        this.name = name;
        this.fullName = name;
        this.scheme = scheme;
        this.dbConnection = scheme.databaseConnection();
        this.connection   = dbConnection.connection();
    }

    /*package*/ protected TableAbsC(String name, String fullName, DatabaseConnection dbConnection) {
        this.name = name;
        this.fullName = fullName;
        this.dbConnection = dbConnection;
        this.connection = dbConnection.connection();
    }

    /*subsystem*/ public void setupReusePrimaryKeyStatement() {
        reusePrimaryKeyStatement = true;
    }

        /** Required now and mostly to simplify the refactoring process **/
    /*subsystem*/ public void setupScheme(Scheme scheme) {
        this.scheme = scheme;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public Column newColumn_type(String name, int javaDataType) {
        Column column = columnNamed(name);
        if (column != null) return column;

        return newBasicColumn_type(name,javaDataType);
    }

    public Column newColumnNamed_type(String name, Class javaC) {
        Column column = columnNamed(name);
        if (column != null) return column;

        return newBasicColumnNamed_type(name,javaC);
    }

    public Column newColumnNamed(String name) {
        Column column = columnNamed(name);
        if (column != null) return column;

        return newBasicColumnNamed(name);
    }

    //**************************************
    //**************************************
    //**************************************

    public Column newBasicColumnNamed(String name) {
        return newBasicColumn_type(name, TypeConstants.TYPE_Object);
    }

/*
    public Column newBasicColumnNamed_type(String name, TypeDescription javaType) {
        Column column = columnNamed(name);
        if (column != null) return column;

        column = new BasicColumnC(this, name, javaType);
        basicColumns.add(column);
        basicColumnNameMap.atKey_put(name,column);
        basicColumnNameToIndexMap.atKey_put(column.name(),new Integer(basicColumns.size()-1));
        basicColumnsString += ((basicColumns.size() > 1) ? "," : "") + column.name();
        qualifiedColumnsString += ((basicColumns.size() > 1) ? "," : "") + this.name+"."+column.name();

        columnNameMap.atKey_put(name,column);

        return column;
    }
*/

    public Column newBasicColumnNamed_type(String name, Class javaC) {
        Class convertedJavaC = ConverterLib.convertDatatypeC(javaC);
        if (convertedJavaC == null) throw DatabasePackSi.newConfigurationException("Did not recognize "+javaC+" as a valid database type");
        int javaDataType = MetaPack.typeConstantForC(convertedJavaC);
        if (javaDataType <= 0) throw DatabasePackSi.newConfigurationException("Did not recognize "+javaC+" mapped to "+convertedJavaC+" as a valid class constant");
        return newBasicColumn_type(name, javaDataType);
    }

    public Column newBasicColumn_type(String name, int javaDataType) {
        Column column = columnNamed(name);
        if (column != null) {
            if ( !(column.isCompound()) ) {
                return column;
            } else {
                throw DatabasePackSi.newConfigurationException("Column "+name+" already exists");
            }
        }

        column = new BasicColumnC(this, name, javaDataType);
        basicColumns.add(column);
        basicColumnNameMap.atKey_put(name,column);
        basicColumnNameToIndexMap.atKey_put(column.name(),new Integer(basicColumns.size()-1));
            //+unqualifiedReadExpression();
            //+tableQualifiedReadExpression();
        basicColumnsString += ((basicColumns.size() > 1) ? "," : "") + column.name();
        qualifiedColumnsString += ((basicColumns.size() > 1) ? "," : "") + this.name+"."+column.name();

        columnNameMap.atKey_put(name,column);

        return column;
    }

        /**
         *This is meant to support adding use controlled columns to the virtual table
         */
    public Column newExpressionColumn_expression_type(String resultColName, String expression, int javaDataType) {
        Column column = columnNamed(resultColName);
        if (column != null) {
            throw DatabasePackSi.newConfigurationException("Column "+resultColName+" already exists");
        }

        column = new ExpressionColumnC(this, resultColName, javaDataType, expression, expression);
        basicColumns.add(column);
        basicColumnNameMap.atKey_put(resultColName,column);
        basicColumnNameToIndexMap.atKey_put(column.name(),new Integer(basicColumns.size()-1));
        basicColumnsString += ((basicColumns.size() > 1) ? "," : "") + expression;
        qualifiedColumnsString += ((basicColumns.size() > 1) ? "," : "") + expression;
            //Should really do the above after everthing is done
            //and ask the columns themselves for the basic SELECT representation.

        columnNameMap.atKey_put(resultColName,column);

        return column;
    }

        /**
         *@param readExpression must be non-null
         *@param writeExpression can be null
         *@param useColumnAlias boolean should be true if you need the column name aliased for uniqueness
         */
    public Column newExpressionColumn_type_read_write(String resultColName, int javaDataType, String readExpression, String writeExpression) {
        Column column = columnNamed(resultColName);
        if (column != null) {
            throw DatabasePackSi.newConfigurationException("Column "+resultColName+" already exists");
        }

        column = new ExpressionColumnC(this, resultColName, javaDataType, readExpression, writeExpression);
        basicColumns.add(column);
        basicColumnNameMap.atKey_put(resultColName,column);
        basicColumnNameToIndexMap.atKey_put(column.name(),new Integer(basicColumns.size()-1));
        basicColumnsString += ((basicColumns.size() > 1) ? "," : "") + readExpression; //+" AS "+resultColName;
        qualifiedColumnsString += ((basicColumns.size() > 1) ? "," : "") + readExpression;
            //Should really do the above after everthing is done
            //and ask the columns themselves for the basic SELECT representation.

        columnNameMap.atKey_put(resultColName,column);

        return column;
    }

    public CompoundColumn newCompoundColumnNamed(String name, String subColumnName1) {
        List sequence = CollectionsPack.newList();
        sequence.add(subColumnName1);
        return newCompoundColumnNamed(name,sequence);
    }

    public CompoundColumn newCompoundColumnNamed(String name, String subColumnName1, String subColumnName2) {
        List sequence = CollectionsPack.newList();
        sequence.add(subColumnName1);
        sequence.add(subColumnName2);
        return newCompoundColumnNamed(name,sequence);
    }

    public CompoundColumn newCompoundColumnNamed(String name, String subColumnName1, String subColumnName2, String subColumnName3) {
        List sequence = CollectionsPack.newList();
        sequence.add(subColumnName1);
        sequence.add(subColumnName2);
        sequence.add(subColumnName3);
        return newCompoundColumnNamed(name,sequence);
    }

    public CompoundColumn newCompoundColumnNamed(String name, List subColumnNames) {
        Column existingColumn = columnNamed(name);
        if (existingColumn != null) {
            if (existingColumn.isCompound()) {
                return (CompoundColumn) existingColumn;
            } else {
                throw DatabasePackSi.newConfigurationException("Column "+name+" already exists");
            }
        };

        CompoundColumn column = null;

        int size = subColumnNames.size();
        List subColumns = CollectionsPack.newListEmptySize(size);
        for (int i=0; i < size; i++) {
            subColumns.atIndex_put(i,newColumnNamed((String) subColumnNames.atIndex(i)));
        };
        column = new CompoundColumnC(this,name,subColumns);
        compoundColumns.add(column);

        columnNameMap.atKey_put(name,column);

        return column;
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public void setPrimaryKeyColumn(Column column) {
        primaryKeyColumn = (ColumnPi) column;
    }

    public void setupPrimaryKeyColumn(Column column) {
        primaryKeyColumn = (ColumnPi) column;
    }

    public Column primaryKeyColumn() {
        return primaryKeyColumn;
    }


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

    public String toString() {
        String result = "<Table: "+qualifiedName();
        result += basicColumns;
        result += compoundColumns;
        result += ">";
        return result;
    }

    public void setupName(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }

    public String fullName() {
        return this.fullName;
    }

    public Scheme scheme() {
        return this.scheme;

        //if (this.scheme != null) return this.scheme;
        //return dbConnection.defaultScheme();
    }

    public Catalog catalog() {
        return this.scheme().catalog();
    }

    public String qualifiedName() {
        String prefix = this.scheme().qualifiedName();
        if (prefix == null) {  //""
            return this.name();
        } else {
            return prefix+"."+this.name();
        }
    }

    public Array basicColumns() {
        return this.basicColumns;
    }

    public Collection columns() {
        return this.columns;
    }

    public Column columnNamed(String name) {
        return (Column) columnNameMap.atKey(name);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    /*subsystem*/ public Column columnAtIndex(int i) {
        // basicColumns
        return (Column) columns.atIndex(i);
    }

    /*subsystem*/ public Column basicColumnAtIndex(int i) {
        // basicColumns
        return (Column) basicColumns.atIndex(i);
    }

    /*subsystem*/ public int sqlDataTypeForColumnAtIndex(int i) {
        return ((Column) basicColumns.atIndex(i)).sqlDataType();
    }


    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

    /*subsystem*/ public void setColumnJavaTypes() {
        int basicSize = basicColumns.size();
        for (int i = 0; i < basicSize; i++) {
            ((BasicColumnPi) basicColumns.atIndex(i)).setJavaTypeFromSqlType();
        }
    }

        /**
         * Prepare the mapper to be ready for use
         */
    public void doneSetup() {
        if (setupMode >= 2) return;
        validateSetup();
        List errorsList = CollectionsPack.newList();
        updateSchemeFromDatabase(errorsList);
        if (errorsList.size() > 0) {
            throw new RuntimeException("Setup errors "+errorsList);
        }
        buildRuntime();
        setupMode = 2;
    }

    public void doneSetup(List errorsList) {
        if (setupMode >= 2) return;
        validateSetup();
        updateSchemeFromDatabase(errorsList);
        if (errorsList.size() > 0) return;
        buildRuntime();
        setupMode = 2;
    }

    public void rebuildRuntime(List errorsList) {
        forceUpdateSchemeFromDatabase(errorsList);
        if (errorsList.size() > 0) return;
        buildRuntime();
    }

    protected void buildRuntime() {
        int basicSize = basicColumns.size();
        for (int i = 0; i < basicSize; i++) {
            ((BasicColumnPi) basicColumns.atIndex(i)).doneSetup();
        }

        int compoundSize = compoundColumns.size();
        if (compoundSize == 0) {
            columns = basicColumns;
        } else {
            columns = CollectionsPack.newListEmptySize(basicSize+compoundSize);
            for (int i = 0; i<basicSize; i++)       columns.atIndex_put(i,basicColumns.atIndex(i));
            for (int i = 0; i<compoundSize; i++)    columns.atIndex_put(i+basicSize,compoundColumns.atIndex(i));
        }

        buildSelectAllQString();
    }


// Throw exception when validation
//
    public boolean isSetupValid() {
        if (setupMode >= 1) return true;
        try {
            validateSetup();
        } catch (Exception e) {
            return false;
        };
        return true;
    }

    public void validateSetup() {
        if (setupMode >= 1) return;
        if (name == null) throw DatabasePackSi.newConfigurationException("Table has no name");
        if (basicColumns.size() < 1) throw DatabasePackSi.newConfigurationException("No columns defined for table "+name());
        if (connection == null) throw DatabasePackSi.newConfigurationException("No connection for table "+name());
        if (primaryKeyColumn == null) throw DatabasePackSi.newConfigurationException("No PrimaryKeyColumn in table "+name());

        setupMode = 1;
    }


        /**
         * Only update if a basicColumn does not have the database information
         */
    protected void updateSchemeFromDatabase(List errorsList) {
        int numColumns = basicColumns.size();
        for (int i = 0; i < numColumns; i++) {
            BasicColumnPi basicColumn = (BasicColumnPi) basicColumns.atIndex(i);
            if (!basicColumn.hasDatabaseInfo()) {
                forceUpdateSchemeFromDatabase(errorsList);
                return;
            }
        };
    }

    protected void forceUpdateSchemeFromDatabase(List errorsList) {
        Map basicColumnsInfo = retrieveColumnsInfo();
        if (basicColumnsInfo.size() < 1) {
            errorsList.add("No columns in table "+name()+": Table may not exist");    
            return;
        }

        int numColumns = basicColumns.size();
        for (int i = 0; i < numColumns; i++) {
            BasicColumnPi basicColumn = (BasicColumnPi) basicColumns.atIndex(i);
            if (basicColumn instanceof ExpressionColumnC) {
                
            } else {
                Object[] basicColumnInfo = (Object[]) basicColumnsInfo.atKey(basicColumn.name().toLowerCase());
                if (basicColumnInfo == null) {
                    errorsList.add("There is no column named: "+basicColumn.name()+" in table: "+name);
                } else {
                    basicColumn.setSqlDataType(
                            convertSqlDataType( ((Integer) basicColumnInfo[0]).intValue() )
                        );
                    basicColumn.setMaximumLength(((Integer) basicColumnInfo[1]).intValue());
                    basicColumn.setIsNullable(((Integer) basicColumnInfo[2]).intValue() == DatabaseMetaData.columnNullable);
                }
            }
        };
    }

        /**
         * Default Conversion Behavior is to do no translation.
         */
    protected int convertSqlDataType(int value) {
        return value;
    }

        /*
        1.TABLE_CAT String => table catalog (may be null)
        2.TABLE_SCHEM String => table schema (may be null)
        3.TABLE_NAME String => table name
        4.COLUMN_NAME String => column name
        5.DATA_TYPE short => SQL type from java.sql.Types
        6.TYPE_NAME String => Data source dependent type name
        7.COLUMN_SIZE int => column size. For char or date types this is the maximum number of characters, for
          numeric or decimal types this is precision.
        8.BUFFER_LENGTH is not used.
        9.DECIMAL_DIGITS int => the number of fractional digits
       10.NUM_PREC_RADIX int => Radix (typically either 10 or 2)
       11.NULLABLE int => is NULL allowed?
               columnNoNulls - might not allow NULL values
               columnNullable - definitely allows NULL values
               columnNullableUnknown - nullability unknown
       12.REMARKS String => comment describing column (may be null)
       13.COLUMN_DEF String => default value (may be null)
       14.SQL_DATA_TYPE int => unused
       15.SQL_DATETIME_SUB int => unused
       16.CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
       17.ORDINAL_POSITION int => index of column in table (starting at 1)
       18.IS_NULLABLE String => "NO" means column definitely does not allow NULL values; "YES" means the
          column might allow NULL values. An empty string means nobody knows.
       */


    protected Map retrieveColumnsInfo() {
        Map results = CollectionsPack.newMap();
        try {
            Connection con = this.myConnection();
            DatabaseMetaData metaData = con.getMetaData();
//System.out.println("Get columns for "+databaseTableName());
                //These should be null if the catalog/scheme are anonymous
                //might be better to use the anonymous test

            String catalogName = this.catalog().name();
            if (this.catalog().isAnonymous()) {
                catalogName = null;
            }

            String schemeName  = this.scheme().name();
            if (this.scheme().isAnonymous()) {
                schemeName = null;
            }
//System.err.println(catalogName+","+schemeName);

            ResultSet resultSet = metaData.getColumns(catalogName,schemeName,databaseTableName(),null);
            ResultSetMetaData rsMetaData = resultSet.getMetaData();
            int numColumns = rsMetaData.getColumnCount();
            String columnName;
            int sqlDataType, columnSize, nullable;

            while (resultSet.next()) {
                              resultSet.getString(1);    //TABLE_CAT
                              resultSet.getString(2);    //TABLE_SCHEM
                              resultSet.getString(3);    //TABLE_NAME
                columnName  = resultSet.getString(4).toLowerCase();    //COLUMN_NAME
                sqlDataType = resultSet.getInt(5);       //DATA_TYPE
                              resultSet.getString(6);    //TYPE_NAME
                columnSize  = resultSet.getInt(7);       //COLUMN_SIZE
                              resultSet.getObject(8);    //BUFFER_LENGTH
                              resultSet.getInt(9);       //DECIMAL_DIGITS
                              resultSet.getInt(10);      //NUM_PREC_RADIX
                nullable    = resultSet.getInt(11);      //NULLABLE

                //...And Ignore the rest...Seem to be 19 of these at last count, the 19th is unknown
                results.atKey_put(columnName,
                    new Object[] {new Integer(sqlDataType), new Integer(columnSize), new Integer(nullable)}
                    );
            };
            resultSet.close();
        } catch (SQLException e) {
            throw DatabasePackSi.newConfigurationException("Could not retrieve the scheme for table: "+name,e);
        };
        return results;
   };

       /**
        * This returns the name of this table capitalized appropriately for the databse
        */
    protected String databaseTableName() {
        return this.name;
    }


    //**********************************************************
    //(P)********************* Asking **************************
    //**********************************************************

        //This is obsolete?
    /*subsystem*/ public String querySelectStringPrefixedWith(String queryVarName) {
        if (queryVarName == null) return basicColumnsString;
        if (queryVarName.equals(name)) return qualifiedColumnsString;
        throw new NotImplementedYetException("Haven't put in the labeled select string yet");
    }

    /*
        StringBuffer selectStringB = new StringBuffer();
        column = new BasicColumnC(this, name, 0);
        size = basicColumns.size();
        for (int i=0; i<size; i++) {
            Column column = basicColumnAtIndex(i);
            if (i > 0) {
                selectStringB.append(",");
            };
            selectStringB.append(queryVarName+"."+column.name());
        }
    */

    //**********************************************************
    //(P)******************** Searching ************************
    //**********************************************************

    //**************************************
    //(P)*********** FindAny ***************
    //**************************************

    /*subsystem*/ public Row findAny() {
        Array rows = selectAll();

        if (rows.size() == 0) return null;
        return (Row) rows.any();
    }

    //**************************************
    //(P)********** SelectAll **************
    //**************************************
    /*
        970818: May want to refactor all these to go through central:
        selectWhereQuery...  The choice is between sharing prepared
        statements (the current orientation) and between sharing code.
        The prepared statements seem unlikely to work because they
        require database resources: too intensive.  And the overhead
        of keeping statements open is not worth it.
        970818: Refactored.
    */


    /*profiling*/ public void profileSelectAll() {
        profileSelectWhereSqlString(selectAllQString);
    }

    /*subsystem*/ public Array selectAll() {
        return selectWhereSqlString(newSelectAllQString());
    }

    protected String newSelectAllQString() {
        return "SELECT "+basicColumnsString+" FROM "+qualifiedName()+" ";
    };

    protected void buildSelectAllQString() {
        selectAllQString = "SELECT "+basicColumnsString+" FROM "+qualifiedName()+" ";
    };

    protected String selectAllQString = null;


/*
    protected String newSelectAllQString() {
        return "SELECT "+basicColumnsString+" FROM "+name+" "+"WHERE "+restrictionString;
    };
*/

    //**************************************
    //(P)********** CountAll **************
    //**************************************

    /*subsystem*/ public int countAll() {
        return countWhereSqlString(newCountAllQString());
    }

    protected String newCountAllQString() {
        return "SELECT COUNT (*) FROM "+qualifiedName()+" ";
    };

    //**************************************
    //(P)******** findPrimaryKey ***********
    //**************************************


    /*subsystem*/ public Row findPrimaryKey(Object primaryKey) {
        Row row = null;
        try {
            PreparedStatement pstmt = newFindPrimaryKeyStatement();
            try {
                primaryKeyColumn.setPstmt_itemStart_to(pstmt,0,primaryKey);
                ResultSet resultSet = pstmt.executeQuery();
                row = this.newRowFromResultSet(resultSet);
                if (!reusePrimaryKeyStatement) {
                    pstmt.close();
                };
            } catch (Exception e) {
                //TODO: resetPrimaryKeyStatement()
                //pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query ",e);
        };
        return row;
    }

    protected PreparedStatement newFindPrimaryKeyStatement()
            throws SQLException {
        PreparedStatement pstmt = null;

        if (reusePrimaryKeyStatement && (findPrimaryKeyPstmt != null)) {
            return findPrimaryKeyPstmt;
        };  //MLF-971021-1// Test to see if solves Wyzdom project//

        String sqlString = newFindPrimaryKeyQString();

        Connection connection = this.myConnection();
        pstmt = connection.prepareStatement(sqlString);
        findPrimaryKeyPstmt = pstmt;
        return pstmt;
    };


    protected String newFindPrimaryKeyQString() {
        StringBuffer sqlStringB = new StringBuffer();
        sqlStringB.append("SELECT "+basicColumnsString+" FROM "+qualifiedName()+" WHERE ");
        primaryKeyColumn.addWhereParametersTo_in(sqlStringB,this);
        return sqlStringB.toString();
    };


    //**************************************
    //(P)******* SelectWhereQString ********
    //**************************************

        /**
         * Take a partially formed query (no select statement) and select all the objects
         * that match the string
         */
    /*subsystem*/ public Row findWhereSqlString_hasValue(String qstring, Object value)
            throws SQLException {

        Row row;
        try {
            //if (1 == 1) throw new NotImplementedYetException();
            //Hmmm... may need to specify the column so the setPstmt will be correct
            PreparedStatement pstmt = newSelectWhereSqlString_Statement(qstring);
            try {
                setPstmt_item_to(pstmt,0,value);

                ResultSet resultSet = pstmt.executeQuery();

                row = this.newRowFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query where "+qstring,e);
        };

        return row;
    };
        /**
         * Take a partially formed query (no select statement) and select all the objects
         * that match the string
         *
         *@access This should only be used by the externalAssociationConnector and with primarykeys
         */
    /*subsystem*/ public Array /*of Rows*/ selectWhereSqlString_hasValue(String qstring, Object value)
            throws SQLException {

        Array rows;
        try {
            //if (1 == 1) throw new NotImplementedYetException();
            //Hmmm... may need to specify the column so the setPstmt will be correct
            PreparedStatement pstmt = newSelectWhereSqlString_Statement(qstring);
            try {
                setPstmt_item_to(pstmt,0,value);

                ResultSet resultSet = pstmt.executeQuery();

                rows = this.newRowsFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query where "+qstring,e);
        };

        return rows;
    }
 // Might want to put in a finally to close off the pstmt if anything fails

        //This won't work in general because of the lack of a sqlType
    protected void setPstmt_item_to(PreparedStatement pstmt, int item, Object value)
            throws SQLException {
        if (value == null) {
            throw new NotImplementedYetException();  //pstmt.setNull(item+1,sqlType);
        } else {
            try {
                pstmt.setObject(item+1,value);
            } catch (Exception e) {
                throw DatabasePackSi.newJdbcException("Could not set parameter("+item+") value: "+value+" class: "+value.getClass(),e);
            };
        };
    }

    //**************************************
    //(P)************ Query ****************
    //**************************************

    /*subsystem*/ public int countWhereSqlString(String sqlString) {
        int count = -1;
        try {
            PreparedStatement pstmt = null;
            try {
                Connection connection = this.myConnection();
                pstmt = connection.prepareStatement(sqlString);
            } catch (Exception e){
                throw DatabasePackSi.newJdbcException("Coud not prepare a statement ",e);
            };

            try {
                ResultSet resultSet;
                try {
                    resultSet = pstmt.executeQuery();
                } catch (Exception e){
                    throw DatabasePackSi.newJdbcException("Coud not execute a query ",e);
                };
                count = this.getClassountFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };

        return count;
    };

    /*debugging*/ public void profileSelectWhereSqlString(String sqlString) {
        try {
            PreparedStatement pstmt = null;
            try {
                Connection connection = this.myConnection();
                pstmt = connection.prepareStatement(sqlString);
            } catch (Exception e){
                throw DatabasePackSi.newJdbcException("Coud not prepare a statement ",e);
            };

            try {
                ResultSet resultSet;
                try {
                    resultSet = pstmt.executeQuery();
                } catch (Exception e){
                    throw DatabasePackSi.newJdbcException("Coud not execute a query ",e);
                };
                Row row = this.newOutputDbRow();
                int numCols = row.size();
                while (resultSet.next()) {
                    for (int i=0; i < numCols; i++) {
                        resultSet.getString(i+1);
                    };
                };
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };
    };


    /*subsystem*/ public Array selectWhereSqlString(String sqlString) {
        Array rows;
        try {
            PreparedStatement pstmt = null;
            try {
                Connection connection = this.myConnection();
                pstmt = connection.prepareStatement(sqlString);
            } catch (Exception e){
                throw DatabasePackSi.newJdbcException("Coud not prepare a statement ",e);
            };

            try {
                ResultSet resultSet;
                try {
                    resultSet = pstmt.executeQuery();
                } catch (Exception e){
                    throw DatabasePackSi.newJdbcException("Coud not execute a query ",e);
                };
                rows = this.newRowsFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };

        return rows;
    };



        // ***** // ObjectMapper interface needs to convert this back to a single object??

    /*subsystem*/ public Array selectColumn_whereSqlString(Column column, String sqlString) {
        Array rows;
        try {
            PreparedStatement pstmt = null;
            try {
                Connection connection = this.myConnection();
                pstmt = connection.prepareStatement(sqlString);
            } catch (Exception e){
                throw DatabasePackSi.newJdbcException("Coud not prepare a statement ",e);
            };

            try {
                ResultSet resultSet;
                try {
                    resultSet = pstmt.executeQuery();
                } catch (Exception e){
                    throw DatabasePackSi.newJdbcException("Coud not execute a query ",e);
                };
                rows = this.newSingleColumn_rowsFromResultSet((ColumnPi) column, resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };

        return rows;
    };


    /*subsystem*/ public Row findWhereSqlString(String sqlString) {
        Row row;
        try {
            PreparedStatement pstmt = null;
            try {
                Connection connection = this.myConnection();
                pstmt = connection.prepareStatement(sqlString);
            } catch (Exception e){
                throw DatabasePackSi.newJdbcException("Coud not prepare a statement ",e);
            };
            
            try {

                ResultSet resultSet;
                try {
                    resultSet = pstmt.executeQuery();
                } catch (Exception e){
                    throw DatabasePackSi.newJdbcException("Coud not execute a query ",e);
                };
                row = this.newRowFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };

        return row;
    };



    //**************************************
    //(P)************ Column ***************
    //**************************************


// not sure who is supposed to call this
    /*subsystem*/ public Array selectWhereColumn_equals(Column column, Object value) {
        Array rows = null;
        String sqlString = newSelectWhereColumn_QString((ColumnPi) column);

        PreparedStatement pstmt = null;
        try {
            Connection connection = this.myConnection();
            pstmt = connection.prepareStatement(sqlString);
            try {
                ((ColumnPi) column).setPstmt_itemStart_to(pstmt,0,value);

                ResultSet resultSet = pstmt.executeQuery();
                rows = this.newRowsFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };

        return rows;
    };

    /*subsystem*/ public Row findWhereColumn_equals(Column column, Object value) {
        Row row = null;
        String sqlString = newSelectWhereColumn_QString((ColumnPi) column);
        PreparedStatement pstmt = null;
        try {
            Connection connection = this.myConnection();
            pstmt = connection.prepareStatement(sqlString);
            
            try {
                ((ColumnPi) column).setPstmt_itemStart_to(pstmt,0,value);

                ResultSet resultSet = pstmt.executeQuery();
                row = this.newRowFromResultSet(resultSet);
            } finally {
                pstmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query "+sqlString,e);
        };

        return row;
    }

    protected String newSelectWhereColumn_QString(ColumnPi column) {
        StringBuffer sqlStringB = new StringBuffer();
        sqlStringB.append("SELECT "+basicColumnsString+" FROM "+qualifiedName()+" WHERE ");
        column.addWhereParametersTo_in(sqlStringB,this);

        return sqlStringB.toString();
    }

    //**********************************************************
    //********************** Changing **************************
    //**********************************************************

    //**************************************
    //(P)*********** Locking ***************
    //**************************************

        /**
         * There is no general lock table mechanism?
         */
    /*subsystem*/ public abstract void lockTable();
    /*subsystem*/ public abstract void unlockTable() ;

    /*subsystem*/ public void tryToUnlockTableWithoutCommit() {

    }

        /**
         * But the following would be reasonable
         */
    protected void simpleLockTable() {
        try {
            Statement stmt = connection.createStatement();
            try {
                stmt.execute(simpleLockTableQString());
            } finally {
                stmt.close();
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Coud not execute a query ",e);
        };
    }

    protected String simpleLockTableQString() {
        return "LOCK TABLE "+qualifiedName();
    }

    /*subsystem*/ public void simpleUnlockTable() {
        try {
            connection.commit();
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Coud not commit the transaction ",e);
        };
    }


    //**************************************
    //(P)********** InsertRow **************
    //**************************************

        /**
         * There is no general insert row & get identity mechanism
         */
    /*subsystem*/ public abstract Object insertRow_getIdentity(Row row)throws SQLException;


    /*subsystem*/ public void insertRow(Row row)throws SQLException {
        final Connection connection = this.myConnection();

        SqlStatementBuilder sqlStmtB = newInsertRowSqlBuilderFor(row);
        if (sqlStmtB.hasBoundValues()) {
            PreparedStatement pstmt = null;
            pstmt = connection.prepareStatement(sqlStmtB.sqlString());
            sqlStmtB.setBoundValues(pstmt);

            //This is OK because of defaultable values.  Not sure about update though
            //if (i == 0) {
            //    throw new MappingException("Tried to insert an empty row into table "+name);
            //};

            pstmt.executeUpdate();
            this.doneWithStatement(pstmt);
        } else {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sqlStmtB.sqlString());
            this.doneWithStatement(stmt);
        }

        //

    }


    protected SqlStatementBuilder newInsertRowSqlBuilderFor(Row row) {
        String sqlString;
        SqlStatementBuilder sqlStmtB = new SqlStatementBuilderC();

        int size = row.size();

        int index = 0;
        for (int i=0; i < size; i++) {
            Object rowValue = row.atIndex(i);
            BasicColumnPi column = (BasicColumnPi) row.basicColumnAtIndex(i);
            if (column.hasColumnValueFor(rowValue)) {
                if (index > 0) {
                    sqlStmtB.appendSqlString(",");
                    sqlStmtB.appendSqlString2(",");
                };
                column.putInsertSqlInto_forValue(sqlStmtB,rowValue);
                //sqlValuesStringB.append("?");
                index++;
            };
        };


        if (index > 0) {
            sqlString =  "INSERT INTO "+qualifiedName()+" "
                        +"("+sqlStmtB.sqlString()+") "
                        +"VALUES ("+sqlStmtB.sqlString2()+")";
        } else {
            sqlString =  "INSERT INTO "+qualifiedName()+" DEFAULT VALUES";
        };

        sqlStmtB.replaceSqlString(sqlString);
        return sqlStmtB;
    }


        //This is OBSOLETE
    protected PreparedStatement newInsertRowStatementFor(Row row)
            throws SQLException {
        String sqlString = newInsertRowQStringFor(row);

        Connection connection = this.myConnection();
        return connection.prepareStatement(sqlString);
    }

        //This is OBSOLETE
    protected String newInsertRowQStringFor(Row row) {
        String sqlString;
        StringBuffer sqlValuesStringB = new StringBuffer();
        StringBuffer selectStringB = new StringBuffer();

        int size = row.size();

        int index = 0;
        for (int i=0; i < size; i++) {
            Object rowValue = row.atIndex(i);
            if (rowValue != DatabasePackSi.UNSET_COLUMN_VALUE) {
                String basicColumnName = ((Column) basicColumns.atIndex(i)).name();
                if (index > 0) {
                    selectStringB.append(",");
                    sqlValuesStringB.append(",");
                };
                selectStringB.append(basicColumnName);
                sqlValuesStringB.append("?");
                index++;
            };
        };

        if (index > 0) {
            sqlString =  "INSERT INTO "+qualifiedName()+" "
                        +"("+selectStringB.toString()+") "
                        +"VALUES ("+sqlValuesStringB.toString()+")";
        } else {
            sqlString =  "INSERT INTO "+qualifiedName()+" DEFAULT VALUES";
        };

        return sqlString;
    }

    //**************************************
    //(P)********** UpdateRow **************
    //**************************************

    /*subsystem*/ public void updateRow(Row row, Object primaryKeyValue) {
        try {
            SqlStatementBuilder sqlStmtB = newUpdateRowSqlBuilderFor(row, primaryKeyValue);
            if (sqlStmtB == null) return;


            if (sqlStmtB.hasBoundValues()) {
                PreparedStatement pstmt = null;
                pstmt = connection.prepareStatement(sqlStmtB.sqlString());
                sqlStmtB.setBoundValues(pstmt);

                //This is OK because of defaultable values.  Not sure about update though
                //if (i == 0) {
                //    throw new MappingException("Tried to insert an empty row into table "+name);
                //};

                pstmt.executeUpdate();
                this.doneWithStatement(pstmt);
            } else {
                Statement stmt = connection.createStatement();
                stmt.executeUpdate(sqlStmtB.sqlString());
                this.doneWithStatement(stmt);
            }
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Coud not update the row with primaryKey: "+primaryKeyValue,e);
        };

        //throw new NotImplementedYetException("Can use a plain old statement");

        //
// finally: doneWith(pstmt)
    }

        /**
         * This returns 'null' if there are no values in the update (i.e.
         * everything is UNSET).
         */
    protected SqlStatementBuilder newUpdateRowSqlBuilderFor(Row row, Object primaryKeyValue) {
        String sqlString;
        SqlStatementBuilder sqlStmtB = new SqlStatementBuilderC();

        int size = row.size();

        int index = 0;
        for (int i=0; i < size; i++) {
            Object rowValue = row.atIndex(i);
            BasicColumnPi column = (BasicColumnPi) row.basicColumnAtIndex(i);
            if (column.hasColumnValueFor(rowValue)) {
                if (index > 0) {
                    sqlStmtB.appendSqlString(",");
                };
                column.putUpdateSqlInto_forValue(sqlStmtB,rowValue);
                //sqlValuesStringB.append("?");
                index++;
            };
        };
        if (index == 0) return null;

        StringBuffer sqlStringB = new StringBuffer();
        sqlStringB.append("UPDATE "+qualifiedName()+" "
                    +" SET "+sqlStmtB.sqlString()
                    +" WHERE ");
            //primaryKeyColumn.addWhereParametersTo_in(sqlStringB,this);
        sqlStmtB.replaceSqlString(sqlStringB.toString());
        primaryKeyColumn.putWhereParametersInto_forValue(sqlStmtB,primaryKeyValue);   //buffer2?


        return sqlStmtB;
    }


        //This is OBSOLETE
    protected PreparedStatement newUpdateRowStatementFor(Row row)
            throws SQLException {
        PreparedStatement pstmt;
        String sqlString = newUpdateRowQStringFor(row);

        Connection connection = this.myConnection();
        pstmt = connection.prepareStatement(sqlString);
        return pstmt;
    }

        //This is OBSOLETE
    protected String newUpdateRowQStringFor(Row row) {
        StringBuffer sqlValuesStringB = new StringBuffer();

        int size = row.size();

        int index = 0;
        for (int i=0; i < size; i++) {
            Object rowValue = row.atIndex(i);
            if (rowValue != DatabasePackSi.UNSET_COLUMN_VALUE) {
                String basicColumnName = ((Column) basicColumns.atIndex(i)).name();
                if (index > 0) {
                    sqlValuesStringB.append(",");
                };
                sqlValuesStringB.append(basicColumnName);
                sqlValuesStringB.append(" = ?");
                index++;
            };
        };


        StringBuffer sqlStringB = new StringBuffer();
        sqlStringB.append("UPDATE "+qualifiedName()+" "
                    +" SET "+sqlValuesStringB.toString()
                    +" WHERE ");
        primaryKeyColumn.addWhereParametersTo_in(sqlStringB,this);

        return sqlStringB.toString();
    };

    //**************************************
    //(P)********** DeleteRow **************
    //**************************************

    /*subsystem*/ public void deleteRow(Object primaryKeyValue) {
        try {
            SqlStatementBuilder sqlStmtB = newDeleteRowSqlBuilderFor(primaryKeyValue);

            if (sqlStmtB.hasBoundValues()) {
                PreparedStatement pstmt = null;
                    pstmt = connection.prepareStatement(sqlStmtB.sqlString());
                    sqlStmtB.setBoundValues(pstmt);
                    pstmt.executeUpdate();
                this.doneWithStatement(pstmt);
            } else {
                Statement stmt = connection.createStatement();
                    stmt.executeUpdate(sqlStmtB.sqlString());
                this.doneWithStatement(stmt);
            }

        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Coud not delete the row with primaryKey: "+primaryKeyValue,e);
        };
// finally: doneWith(pstmt)

    }

    protected SqlStatementBuilder newDeleteRowSqlBuilderFor(Object primaryKeyValue) {
        SqlStatementBuilder sqlStmtB = new SqlStatementBuilderC();

        sqlStmtB.appendSqlString("DELETE FROM "+qualifiedName()+" WHERE ");
        primaryKeyColumn.putWhereParametersInto_forValue(sqlStmtB,primaryKeyValue);   //buffer2?

        return sqlStmtB;
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    //**************************************
    //(P)********** DeleteRows *************
    //**************************************

    /*subsystem*/ public void deleteWhereColumn_equalsColumnValue(Column column, Object columnValue)
            throws SQLException {
        PreparedStatement pstmt = newDeleteWhereColumn_equalsStatement((ColumnPi) column);
        try {
            ((ColumnPi) column).setPstmt_itemStart_to(pstmt,0,columnValue);
            pstmt.executeUpdate();
        } finally {
            pstmt.close();
        }
    }

    protected PreparedStatement newDeleteWhereColumn_equalsStatement(ColumnPi column)
            throws SQLException {
        String sqlString = newDeleteWhereColumn_equalsQString(column);

        Connection connection = this.myConnection();
        return connection.prepareStatement(sqlString);
    }

    protected String newDeleteWhereColumn_equalsQString(ColumnPi column) {
        StringBuffer sqlStringB = new StringBuffer();
        sqlStringB.append("DELETE FROM "+qualifiedName()+" WHERE ");
        column.addWhereParametersTo_in(sqlStringB,this);
        return sqlStringB.toString();
    }

    /*package*/ void addFirstColumnName_to(String name, StringBuffer sqlStringB) {
        sqlStringB.append(name+"=?");
    }

    /*package*/ void addColumnName_to(String name, StringBuffer sqlStringB) {
        sqlStringB.append("AND "+name+"=?");
    }

    //**********************************************************
    //(P)****************      PRIVATE      ********************
    //**********************************************************

    protected void doneWithStatement(Statement stmt) throws SQLException {
        stmt.close();
    }


    //**********************************************************
    //(P)************ ResultSet to Row conversions *************
    //**********************************************************

    protected int getClassountFromResultSet(ResultSet resultSet) {
        int count = -1;
        try {
            if (resultSet.next()) {
                count = resultSet.getInt(0+1);
            };
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("ResultSet reading failed ",e);  //ProgrammerError
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {};
        };
        return count;
    }

        /**
         * I think this will fail badly with compound columns...
         * Needs to get the collection of basicColumns that make up the compound column
         * and use that as a temporary mini-table
         */
    protected Array newSingleColumn_rowsFromResultSet(ColumnPi column, ResultSet resultSet) {
        List rows = CollectionsPack.newList(100);  // Of KeyedArrays

        try {
            while (resultSet.next()) {
                Row row = this.newOutputDbRowForColumn(column);
                int numCols = row.size();
                for (int i=0; i < numCols; i++) {
                    row.atIndex_put(i,((BasicColumnPi) row.basicColumnAtIndex(i)).getFromResultSet_atIndex(resultSet,i));
                };
                rows.add(row);
            };
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("ResultSet reading for "+column+" failed ",e);  //ProgrammerError
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {};
        };
        return rows;
    }

    protected Array newRowsFromResultSet(ResultSet resultSet) {
        List rows = CollectionsPack.newList(100);  // Of KeyedArrays

        try {
            while (resultSet.next()) {
                Row row = this.newOutputDbRow();
                int numCols = row.size();
                for (int i=0; i < numCols; i++) {
//                    row.atIndex_put(i,resultSet.getObject(i+1));
                    row.atIndex_put(i,((BasicColumnPi) row.basicColumnAtIndex(i)).getFromResultSet_atIndex(resultSet,i));
                    //Decide whether the row needs to be used or can shortcut back into this Table
                };
                rows.add(row);
            };

        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("ResultSet reading failed ",e);  //ProgrammerError
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {};
        };
        return rows;
    }

    protected Row newRowFromResultSet(ResultSet resultSet) {
        Row row = null;
        try {
            if (resultSet.next()) {
                row = this.newOutputDbRow();
                int numCols = row.size();
                for (int i=0; i < numCols; i++) {
//                    row.atIndex_put(i,resultSet.getObject(i+1));
                    row.atIndex_put(i,((BasicColumnPi) row.basicColumnAtIndex(i)).getFromResultSet_atIndex(resultSet,i));
                };
            };
        } catch (Exception e) {
            throw DatabasePackSi.newJdbcException("ResultSet reading failed ",e);  //ProgrammerError
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) {};
        };
        return row;
    }


    //**********************************************************
    //(P)********************** Pstms **************************
    //**********************************************************

    protected void setPstmt_item_to_ofType(PreparedStatement pstmt, int item, Object value, int sqlType)
            throws SQLException {
        if (value == null) {
            pstmt.setNull(item+1,sqlType);
        } else {
            try {
                pstmt.setObject(item+1,value,sqlType);
            } catch (Exception e) {
                throw DatabasePackSi.newJdbcException("Could not set parameter("+item+") value: "+value+" class: "+value.getClass()+" to sqlType: "+sqlType,e);
            };
        };
    }

    protected int setPstmt_usingRow(PreparedStatement pstmt, Row row)
            throws SQLException {
        int i = 0;
        int size = row.size();
        for (; i < size; i++) {
            ((BasicColumnPi) row.basicColumnAtIndex(i)).setPstmt_item_to(pstmt,i,row.atIndex(i));
            // setPstmt_item_to_ofType(pstmt,i,row.atIndex(i),row.sqlDataTypeAtIndex(i));
        };
        return i;
    }

    protected int setPstmt_usingRow_withoutUnsetValues(PreparedStatement pstmt, Row row)
            throws SQLException {
        int index = 0;
        int size = row.size();
        for (int i = 0; i<size; i++) {
            Object rowValue = row.atIndex(i);
            BasicColumnPi column = (BasicColumnPi) row.basicColumnAtIndex(i);
            if (column.hasColumnValueFor(rowValue)) {
                column.setPstmt_item_to(pstmt,index,rowValue);
                // setPstmt_item_to_ofType(pstmt,index,value,row.sqlDataTypeAtIndex(i));
                index++;
            };
        };
        return index;
    }

/* OLD: Replaced with UnsetValues version
    protected int setPstmt_usingRow_withoutNulls(PreparedStatement pstmt, Row row)
            throws SQLException {
        int index = 0;
        int size = row.size();
        for (int i = 0; i<size; i++) {
            Object value = row.atIndex(i);
            if (value != null) {
                setPstmt_item_to_ofType(pstmt,index,value,row.sqlDataTypeAtIndex(i));
                index++;
            };
        };
        return index;
    }
*/


    //**********************************************************
    //******************** Utilities ***************************
    //**********************************************************

    /*subsystem*/ public Row newDbRow() {
        Row row = DatabasePackSi.newRow(this, basicColumnNameToIndexMap);
        int size = row.size();
        for (int i = 0; i<size; i++) {
            row.atIndex_put(i,DatabasePackSi.UNSET_COLUMN_VALUE);
        };
        return row;
    }

    protected Row newOutputDbRow() {
        return DatabasePackSi.newRow(this, basicColumnNameToIndexMap);
    }


    protected Row newOutputDbRowForColumn(ColumnPi column) {
        return DatabasePackSi.newRow(column);
    }


    /*subsystem*/ public Connection connection() {
        // Either fetch this centrally or cache it with the mapper...
        // I will assume the latter for the moment
        return connection;
    }

    protected SqlBuilder aSqlBuilder() {
        return newSqlBuilder();
    }

        /**
         * Use the general SQLBuilder unless overriden by a subclass
         */
    /*subsystem*/ public SqlBuilder newSqlBuilder() {
        return QueryPackSi.newSqlBuilder();
    }

    protected Connection myConnection() {
        // Either fetch this centrally or cache it with the mapper...
        // I will assume the latter for the moment
        return connection;
    }



    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************


    protected String name = null;
    protected String fullName = null;
    protected Scheme scheme;

    protected String basicColumnsString = "";
    protected String qualifiedColumnsString = "";

    protected BasicColumnPi[]                  basicColumnsArray = null;
    protected List /*(BasicColumn)*/       basicColumns              = CollectionsPack.newList();

    protected Map /*(String->Column)*/         basicColumnNameMap        = CollectionsPack.newMap();
    protected Map /*(String->Integer)*/        basicColumnNameToIndexMap = CollectionsPack.newMap();

    protected ColumnPi                         primaryKeyColumn;

    protected List /*(CompoundColumn)*/    compoundColumns       = CollectionsPack.newList();
    protected Map /*(String->CompoundColumn)*/ compoundColumnNameMap = CollectionsPack.newMap();

    protected Map /*(String->Column)*/         columnNameMap         = CollectionsPack.newMap();
    protected List /*(BasicColumn)*/       columns               = CollectionsPack.newList();

    protected Column[] basicColumnArray = null;


    protected String   dbColumnsString = null;
    protected String   qualifiedDbColumnsString = null;


    protected DatabaseConnection  dbConnection;
    protected Connection          connection;

    protected PreparedStatement   findPrimaryKeyPstmt = null;
    protected boolean             reusePrimaryKeyStatement = false;

    protected int setupMode = 0;      //0 = not setup, 1 = validatedForSetup, 2 = setup

/*
    PreparedStatement selectAllPstmt = null;
    PreparedStatement insertRowPstmt = null;
    PreparedStatement updateRowPstmt = null;
*/

    protected PrintWriter ts = null;


    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         */
    /*subsystem*/ public Array /*of Rows*/ selectWhereSqlString_hasValues(String qstring, Array values)
            throws SQLException {

        Array rows = null;
        try {
            if (1 == 1) throw new NotImplementedYetException();
            //setPstmt_item_to(pstmt,0,value);
            PreparedStatement pstmt = newSelectWhereSqlString_Statement(qstring);
            int i = 0;
            Enumeration enum = values.elements();
            while (enum.hasMoreElements()) {
                Object value = enum.nextElement();
                //setPstmt_item_to(pstmt,i,value);
                i++;
            };

            ResultSet resultSet = pstmt.executeQuery();
            rows = this.newRowsFromResultSet(resultSet);
            pstmt.close();
        } catch (Exception e){
            throw DatabasePackSi.newJdbcException("Could not execute a query where "+qstring,e);
        };

        return rows;
    }

    protected PreparedStatement newSelectWhereSqlString_Statement(String qstring)
            throws SQLException {
        String sqlString = newSelectWhereSqlString_QString(qstring);

        Connection connection = this.myConnection();
        return connection.prepareStatement(sqlString);
    }

    protected String newSelectWhereSqlString_QString(String qstring) {
        StringBuffer sqlStringB = new StringBuffer();
        sqlStringB.append("SELECT "+qualifiedColumnsString+" ");  // really need to add column prefixes
        sqlStringB.append(qstring);
        return sqlStringB.toString();
    }


}


