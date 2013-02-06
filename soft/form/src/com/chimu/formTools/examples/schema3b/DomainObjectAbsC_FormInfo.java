/*======================================================================
**
**  File: com/chimu/formTools/examples/schema3b/DomainObjectAbsC_FormInfo.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copyright permissions.
**
======================================================================*/

package com.chimu.formTools.examples.schema3b;

import com.chimu.form.client.*;

import com.chimu.form.*;
import com.chimu.form.mapping.*;
import com.chimu.form.database.support.*;
import com.chimu.form.database.*;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.streams.*;

public class DomainObjectAbsC_FormInfo extends DomainObject_1_AbsC_FormInfo {

    static private Class identityColumnType = Integer.class;

    public void createMappers() {
        super.createMappers();

        myTable  = dbConnection.table(tableName());
        myTable.newBasicColumnNamed_type("id",identityColumnType);
    }



        /**
         * Specifies the mapper this generator is for.  Subclasses
         * should override
         */
    protected String mapperName() {
        String className = myC().getName();
        int dotIndex = className.lastIndexOf(".");
        if (dotIndex > 0) {
            className = className.substring(dotIndex+1);
        };
        if (className.endsWith("Class")) {
            return className.substring(0,className.length()-5);
        };
        return className;
    }

        /**
         * Specifies the OID column in the table for this mapper.
         */
    protected String oidColumnName() {
        return "id";
    }

    public void configureMappers() {
        super.configureMappers();

        myMapper.setupPreInsertIdentityGenerator(identityGenerator());
    }

    //==========================================================
    //(P)==================== SUPPORT ==========================
    //==========================================================

        /**
         * prepareForMapping lets DomainObjectAbsC know about the database
         * connection an prepare for having ObjectMappers created
         */
    static public void prepareForMapping(Orm orm, DatabaseConnection dbConnection) {
        buildIdentityGenerator(dbConnection);
    }

    static public void resetAfterRollback() {
        identityGenerator.clearCache();
    }

    //==========================================================
    //==========================================================
    //==========================================================

    static protected Generator identityGenerator() {
        if (identityGenerator == null) throw new RuntimeException("Need to build the identity generator");
        return identityGenerator;
    }

    static protected void buildIdentityGenerator(DatabaseConnection dbConnection) {
        Table table = (Table) dbConnection.table("GeneratorCounters");
            Column primaryKey = table.newBasicColumnNamed("counter_id");
            table.setupPrimaryKeyColumn(primaryKey);
            table.newBasicColumnNamed_type("counter_value", identityColumnType);
        table.doneSetup();

        identityGenerator =
                (GeneratorFromDatabase)
                DatabaseSupportPack.newIntegerTable_row_column_block_WithinTransactionGenerator(
                    table,"GEN1","counter_value",
                    5);
    }

    static protected GeneratorFromDatabase identityGenerator = null;

}

