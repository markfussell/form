/*======================================================================
**
**  File: chimu/form/database/product/DatabaseProductPack.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.database.product;

import java.sql.Connection;
import com.chimu.kernel.collections.*;



/**
DatabaseProductPack
**/
public class DatabaseProductPack {

        /**
         * This returns a Product identifier from the product description
         * return from a JDBC getDatabaseProductName()
         */
    static public DatabaseProduct productFromProductDescription(String productDescription) {
        int size = DatabaseProductConstants.DB_SIZE;
        for (int i=0; i<size; i++) {
            DatabaseProduct product = products[i];
            if (product != null) {
                if (product.matchesDescription(productDescription)) {
                    return product;
                }
            }
        };
        return null;
    }

        /**
         * This returns a Product identifier from the product description
         * return from a JDBC getDatabaseProductName()
         */
    static public int productCodeFromProductDescription(String productDescription) {
        DatabaseProduct product = productFromProductDescription(productDescription);
        if (product == null) return -1;
        return product.code();
    }

        //** REQUIRE: productCode >= 0 && < DB.SIZE
    static public DatabaseProduct productFromCode(int productCode) {
        if (!(productCode >= 0)) {return null;};
        if (!(productCode <  DatabaseProductConstants.DB_SIZE)) {return null;};

        return products[productCode];
    }

    static public DatabaseProduct productFromKey(String productKey) {
        return (DatabaseProduct) keyToProduct.atKey(productKey);
    }

        /**
         * The collection of products
         */
    static public List products() {
        List result = CollectionsPack.newList();
        for (int i = 0; i < products.length; i++) {
            if (products[i] != null) result.add(products[i]);
        }
        return result;
    }

    //**************************************
    //**************************************
    //**************************************

    static public void main(String[] args) {
        System.out.println(products());
    }

    //**************************************
    //**************************************
    //**************************************

    static protected DatabaseProduct[] products = null;
    static protected Map keyToProduct = CollectionsPack.newMap();

    static {
        products = new DatabaseProduct[DatabaseProductConstants.DB_SIZE];

        registerProduct(new com.chimu.form.database.product.access.DatabaseProductC());
        registerProduct(new com.chimu.form.database.product.mssql.DatabaseProductC());
        registerProduct(new com.chimu.form.database.product.informix.DatabaseProductC());
        registerProduct(new com.chimu.form.database.product.sqla.DatabaseProductC());
        registerProduct(new com.chimu.form.database.product.oracle.DatabaseProductC());
        registerProduct(new com.chimu.form.database.product.harmonia.DatabaseProductC());
        registerProduct(new com.chimu.form.database.product.db2.DatabaseProductC());

        registerProduct(new com.chimu.form.database.product.sql92.DatabaseProductC());
    }

    static void registerProduct(DatabaseProduct product) {
        products[product.code()]=product;
        keyToProduct.atKey_put(product.key(),product);
    }

    private DatabaseProductPack() {};

};
