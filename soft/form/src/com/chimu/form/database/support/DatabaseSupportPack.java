/*======================================================================
**
**  File: chimu/form/database/support/DatabaseSupportPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.support;

import com.chimu.form.database.Table;
import com.chimu.form.transaction.*;

import com.chimu.kernel.collections.*;
import com.chimu.kernel.functors.*;
import com.chimu.kernel.streams.*;

import java.sql.Connection;

/**
DatabaseSupportPack contains database and mapping support functionality, and
it especially provides constructors for the DatabaseOriented Generator classes.
**/

public class DatabaseSupportPack {

    //**********************************************************
    //(P)***************** Factory Methods *********************
    //**********************************************************

    static public GeneratorFromDatabase newTable_row_column_Generator(
            Table table,
            Object distinguishingValue, String counterColumn) {
        return new TableRowGeneratorC(table,distinguishingValue,counterColumn);
    }
        /**
         * Build a Generator that uses a table's row to generate the value
         */
    static public GeneratorFromDatabase newTableRowGenerator(
            Table table,
            Object distinguishingValue, String counterColumn) {
        return new TableRowGeneratorC(table,distinguishingValue,counterColumn);
    }

    static public GeneratorFromDatabase newTable_row_column_WithinTransactionGenerator(
            Table table,
            Object distinguishingValue, String counterColumn) {
        GeneratorFromDatabase generator = new TableRowGeneratorC(table,distinguishingValue,counterColumn);
        generator.setupDontCommitConnection();
        return generator;
    }

        /**
         * Build a Generator that uses a table's row to generate the value
         */
    static public GeneratorFromDatabase newTable_row_column_coordinator_TransactionalGenerator(
            Table table,
            Object distinguishingValue, String counterColumn,
            TransactionCoordinator tc) {
        return new TableRowTransactionGeneratorC(table,distinguishingValue,counterColumn,tc);
    }

    //**************************************
    //(P)************ Tables ***************
    //**************************************

    //**********************************************************
    //(P)***************** Common Constructs *******************
    //**********************************************************

        /**
         * Build a Generator that uses a TableRowGenerator with integer values [2^32]
         * and checks out a block at a time.
         */
    static public GeneratorFromDatabase
                        newIntegerTable_row_column_block_Generator(
                            Table table, String generatorIdentifier, String counterColumn,
                            int blockSize) {
        GeneratorFromDatabase gen = newTable_row_column_Generator(table,generatorIdentifier,counterColumn);
        prepareIntegerGenerator_block(gen,blockSize);
        gen.doneSetup();

        return gen;
    }

        /**
         * Build a Generator that uses a TableRowGenerator with integer values [2^32]
         * and checks out a block at a time.
         */
    static public GeneratorFromDatabase
                        newIntegerTable_row_column_block_WithinTransactionGenerator(
                            Table table, String generatorIdentifier, String counterColumn,
                            int blockSize) {
        GeneratorFromDatabase gen = newTable_row_column_WithinTransactionGenerator(table,generatorIdentifier,counterColumn);
        prepareIntegerGenerator_block(gen,blockSize);
        gen.doneSetup();

        return gen;
    }

        /**
         * Prepare a GeneratorFromDatabase to use integer value generation
         * and to fetch blocks of 'blockSize'
         */
    static public void prepareIntegerGenerator_block(GeneratorFromDatabase gen, int blockSize) {
        final int blockSizeMinusOne = blockSize - 1;
                //This is because nextFunction is automatically applied after calculating the
                //limitValue.

        Function1Arg nextFunction = new Function1Arg() {public Object valueWith(Object arg1) {
                return new Integer( ((Number) arg1).intValue() + 1);
        }};
        Function1Arg limitFunction = new Function1Arg() {public Object valueWith(Object arg1) {
                return new Integer( ((Number) arg1).intValue() + blockSizeMinusOne);
        }};

        gen.setupNext_limit_needToFetch_functors(
            nextFunction,limitFunction,null);
        gen.setupMakeWriteBack();

        gen.setupStartingValue(new Integer(1)); //In case the generator counter doesn't exist yet
    }

    //**************************************
    //**************************************
    //**************************************

    private DatabaseSupportPack() {};

};
