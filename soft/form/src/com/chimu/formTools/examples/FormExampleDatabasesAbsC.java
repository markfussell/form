/*======================================================================
**
**  File: chimu/formTools/examples/FormExampleDatabasesAbsC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;

import com.chimu.kernel.collections.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.streams.*;
import com.chimu.kernel.exceptions.*;

import com.chimu.form.database.*;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
FormExampleDatabasesAbsC defines the schemes of the example
databases.
**/
public abstract class FormExampleDatabasesAbsC extends ExampleDatabaseCreatorAbsC {

        /**
          * This generates a string that appends the default catalog and scheme
         **/
    protected String genQualifiedName(String tableName) {
        //return tableName;
       
        Catalog aCatalog = setupCatalog(catalogName);
        Scheme aScheme = setupScheme(aCatalog, schemeName);

        Table aTable = aScheme.table(tableName);
        aTable.setupScheme(aScheme);
        return aTable.qualifiedName();
    }

        /**
        ==  Figures out an appropriate catalog given the
        ==  catalog name in the input field.
        ==  Returns the default catalog of the connection, if no name is given
        **/
    protected Catalog setupCatalog(String aName) {
        Catalog aCatalog = null;
        if (catalogName != null) {
            aCatalog = dbConnection.catalog(catalogName);
        } else {
            aCatalog = dbConnection.defaultCatalog();
        }
        return aCatalog;
    }

        /**
        ==  Figures out an appropriate scheme given the scheme
        ==  and catalog name in the input fields.
        ==  Returns the default scheme if no name is given
        **/

    protected Scheme setupScheme(Catalog aCatalog, String schemeName) {
        if (schemeName != null) {
            return aCatalog.scheme(schemeName);
        } else {
            return aCatalog.defaultScheme();
        };
    }



    //==========================================================
    //===================== Generator ==========================
    //==========================================================
    /*
        MASTER DATA IS:
          counter_id    counter_value
          GEN1	200
    */

    protected void createCounter() {
        createEntityTable_FileString_primaryKey("genCounter.txt", genQualifiedName("GeneratorCounters"),  "counter_id");

/*
                createTable_columnsString("GeneratorCounters",
                                                " counter_id VARCHAR(4) "+databaseProduct.notNull()+
                                                ", counter_value INT "+databaseProduct.notNull()+
                                                databaseProduct.primaryKeyPrefix()+"counter_id"+databaseProduct.primaryKeySuffix()+
                                                "");
*/
    }

    protected void populateCounter() {
        Function1Arg[] translators = {identity,identity};
        readAndInsertRowsFrom_into_translators(
            "CounterMasterData.txt", genQualifiedName("GeneratorCounters"),
            translators
        );
    }

    //==========================================================
    //====================== Persons ===========================
    //==========================================================
    /*
        MASTER DATA IS:
           ID,  name, distinguisher,email,     height,company,hired,            base,   rate,...
           1	'Art Larsson'	'c'	'larssar'	63	  3	      11/30/85 0:00:00	20000	1.06
    */

    protected void createPerson() {
        createNonEntityTable_FileString("scheme1Person.txt", genQualifiedName("Person"));

/*
        createEntityTable_columnsString("Person",
                                                ", person_name " + databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", email " + databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", height INT "+ databaseProduct.nullable()+
                                                "");
*/
    }

    protected void populatePerson1() {
        Function1Arg[] translators = {identity,identity,null,identity,identity};
        readAndInsertRowsFrom_into_translators(
            "EmployeeMasterData.txt", genQualifiedName("Person"),
            translators
        );
    }

    //==========================================================
    //===================== Employee ===========================
    //==========================================================
    /*
        Master Data:    Same as Person
    */

    //======================================
    //============== Schemes ===============
    //======================================

    protected void createEmployee2() {
        createEntityTable_FileString_primaryKey("scheme2Employee.txt", genQualifiedName("Employee"),  "id");

/*
        createEntityTable_columnsString("Employee",
                        ", person_name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                        ", email "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                        ", height INT "+ databaseProduct.nullable()+
                        ", employer_ID INT "+ databaseProduct.nullable()+
                        ", hired_date "+databaseProduct.typeDate()+" "+databaseProduct.nullable()+
                        "");
*/
    }

    protected void createEmployee5() {
        createEntityTable_FileString_primaryKey("scheme5Employee.txt", genQualifiedName("Employee"),  "id");

/*
        createEntityTable_columnsString("Employee",
                                                ", person_name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", distinguisher VARCHAR(25) "+ databaseProduct.nullable()+
                                                ", email "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", height INT "+ databaseProduct.nullable()+
                                                ", employer_ID INT "+ databaseProduct.nullable()+
                                                ", hired_date "+databaseProduct.typeDate()+" "+databaseProduct.nullable()+
                                                ", base INT "+ databaseProduct.nullable()+
                                                ", percentage "+ databaseProduct.typeFloat()+" "+databaseProduct.nullable()+
                                                ", annual_salary INT "+databaseProduct.nullable()+
                                                "");
*/
    }

    //======================================
    //============== Populate ==============
    //======================================

    protected void populateEmployee2() {
        Function1Arg[] translators = {identity,identity,null,identity,identity,identity,dateify};
        readAndInsertRowsFrom_into_translators(
            "EmployeeMasterData.txt", genQualifiedName("Employee"),
            translators
        );
    }

    protected void populateEmployee5() {
        Function1Arg[] translators = {identity,identity, identity,identity,identity,identity, dateify, identity, chopMoney, identity};
        readAndInsertRowsFrom_into_translators(
            "EmployeeMasterData.txt", genQualifiedName("Employee"),
            translators
        );
    }

    //==========================================================
    //====================== Company ===========================
    //==========================================================
    /*
        MASTER DATA IS:
          id name rev_currency rev_amount
          1	'Generic Company, Inc.'	'USD'	$5000000.00	'Los Angeles'
    */

    //======================================
    //============== Schemes ===============
    //======================================

    protected void createCompany2() {
        createEntityTable_FileString_primaryKey("scheme2Company.txt", genQualifiedName("Company"),  "id");

/*
        createEntityTable_columnsString("Company",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", revenue "+databaseProduct.typeDecimal()+" "+databaseProduct.nullable()+
                                                "");
*/
    }

    protected void createCompany4c() {
        createEntityTable_FileString_primaryKey("scheme4cCompany.txt", genQualifiedName("Company"),  "id");

/*
        createEntityTable_columnsString("Company",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", revenue_cur VARCHAR(50) "+ databaseProduct.nullable()+
                                                ", revenue "+databaseProduct.typeDecimal()+" "+databaseProduct.nullable()+
                                                "");
*/
    }

    protected void createCompany4d() {
        createEntityTable_FileString_primaryKey("scheme4dCompany.txt", genQualifiedName("Company"),  "id");
/*
        createEntityTable_columnsString("Company",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", revenue_cur VARCHAR(50) "+ databaseProduct.nullable()+
                                                ", revenue "+databaseProduct.typeDecimal()+" "+databaseProduct.nullable()+
                                                ", city "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                "");
*/
    }
    
    
    protected void createCompany4e() {
        createEntityTable_FileString_primaryKey("scheme4eCompany.txt", genQualifiedName("Company"),  "id");
/*
        createEntityTable_columnsString("Company",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", revenue "+databaseProduct.typeDecimal()+" "+databaseProduct.nullable()+
                                                ", city "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", moneyType + databaseProduct.typeInteger() +
                                                "");
*/
    }
    

    //======================================
    //============== Populate ==============
    //======================================

    protected void populateCompany2() {
        Function1Arg[] translators = {identity,identity,null,chopMoney};
        readAndInsertRowsFrom_into_translators(
            "CompanyMasterData.txt", genQualifiedName("Company"),
            translators
        );
    }

    protected void populateCompany4c() {
        Function1Arg[] translators = {identity,identity,identity,chopMoney};
        readAndInsertRowsFrom_into_translators(
            "CompanyMasterData.txt", genQualifiedName("Company"),
            translators
        );
    }

    protected void populateCompany4d() {
        Function1Arg[] translators = {identity,identity,identity,chopMoney, identity};
        readAndInsertRowsFrom_into_translators(
            "CompanyMasterData.txt", genQualifiedName("Company"),
            translators
        );
    }


    protected void populateCompany4e() {
        Function1Arg[] translators = {identity,identity,null,chopMoney, identity, identity};
        readAndInsertRowsFrom_into_translators(
            "CompanyMasterData.txt", genQualifiedName("Company"),
            translators
        );
    }


    //==========================================================
    //====================== Project ===========================
    //==========================================================
    /*
        MASTER DATA IS:
         id name company_id startDate staffed profit_margin
         1	'InfoSeek'	1	1/5/97 0:00:00	1	$0.25
    */

    protected void createProject3() {
        createEntityTable_FileString_primaryKey("scheme3Project.txt", genQualifiedName("Project"),  "id");

/*
        createEntityTable_columnsString("Project",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", company_ID INT "+ databaseProduct.nullable()+
                                                ", start_date "+databaseProduct.typeDate()+" "+databaseProduct.nullable()+
                                                "");
*/
    }
        /*
            Project scheme for Scheme4
        */
    protected void createProject4() {
        createEntityTable_FileString_primaryKey("scheme4Project.txt", genQualifiedName("Project"),  "id");

/*
        createEntityTable_columnsString("Project",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", company_ID INT "+ databaseProduct.nullable()+
                                                ", start_date "+databaseProduct.typeDate()+" "+databaseProduct.nullable()+
                                                ", budgeted CHAR(1) "+ databaseProduct.nullable()+
                                                ", profit_margin " + databaseProduct.typeDecimal()+" "+databaseProduct.nullable()+
                                                "");
*/
    }

    protected void createProject4b() {
        createEntityTable_FileString_primaryKey("scheme4bProject.txt", genQualifiedName("Project"),  "id");

/*
        createEntityTable_columnsString("Project",
                                                ", name "+databaseProduct.typeString()+" "+ databaseProduct.nullable()+
                                                ", company_ID INT "+ databaseProduct.nullable()+
                                                ", start_date "+databaseProduct.typeDate()+" "+databaseProduct.nullable()+
                                                ", budgeted "+databaseProduct.typeBoolean()+
                                                ", profit_margin " + databaseProduct.typeDecimal()+" "+databaseProduct.nullable()+
                                                "");
*/
    }

    //======================================
    //============== Populate ==============
    //======================================

    protected void populateProject3() {
        Function1Arg[] translators = {identity, identity, identity, dateify};
        readAndInsertRowsFrom_into_translators(
            "ProjectMasterData.txt", genQualifiedName("Project"),
            translators
        );
    }

    protected void populateProject4() {
        Function1Arg[] translators = {identity,identity, identity, dateify, identity, identity};
        readAndInsertRowsFrom_into_translators(
            "ProjectMasterData.txt", genQualifiedName("Project"),
            translators
        );
    }

    protected void populateProject4b() {
        Function1Arg[] translators = {identity,identity, identity, dateify, makeBoolean, identity};
        readAndInsertRowsFrom_into_translators(
            "ProjectMasterData.txt", genQualifiedName("Project"),
            translators
        );
    }


    //==========================================================
    //================== Project Members =======================
    //==========================================================
    /*
        MASTER DATA IS:
        project_id  member_id
        1	5

    */

    protected void createProjectMembers() {
        createNonEntityTable_FileString("projectMember.txt", genQualifiedName("ProjectMembers") );

/*
        createTable_columnsString("ProjectMembers",
                                                "  project_ID INT "+databaseProduct.notNull()+
                                                ", employee_ID INT "+databaseProduct.notNull()+
                                                databaseProduct.primaryKeyPrefix()+"project_ID,employee_ID"+databaseProduct.primaryKeySuffix()+
                                                "");
*/
    }

    protected void populateProjectMembers() {
        Function1Arg[] translators = {identity,identity};
        readAndInsertRowsFrom_into_translators(
            "ProjectMembersMasterData.txt", genQualifiedName("ProjectMembers"),
            translators
        );
    }

    //==========================================================
    //======================== Money ===========================
    //==========================================================

    protected void createCurrency4e() {
        createEntityTable_FileString_primaryKey("currency4e.txt", genQualifiedName("Currency"), "id");
    }

    //======================================
    //============== Populate ==============
    //======================================

    protected void populateCurrency4e() {
        Function1Arg[] translators = {identity,identity};
        readAndInsertRowsFrom_into_translators(
            "CurrencyMasterData.txt", genQualifiedName("Currency"),
            translators
        );

    }
}