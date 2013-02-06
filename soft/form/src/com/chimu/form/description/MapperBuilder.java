/*======================================================================
**
**  File: chimu/form/description/MapperBuilder.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.description;

public interface MapperBuilder {

        /**
         * Create and add to the Orm all mappers that will be used by this builder.
         * During this stage you can not assume any other mappers exist.
         */
    public void createMappers();

        /**
         * Configure the created mappers.
         */
    public void configureMappers();

        /**
         * Creation and configuration have been completed for all Mappers,
         * do any final activities and notifications required for the mapper.
         */
    public void configureCompleted();
}

