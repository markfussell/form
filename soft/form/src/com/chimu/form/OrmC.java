/*======================================================================
**
**  File: chimu/form/OrmC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.exceptions.*;
import com.chimu.kernel.functors.*;

import com.chimu.form.description.*;
import com.chimu.form.database.*;
import com.chimu.form.mapping.*;
import com.chimu.form.query.*;
import com.chimu.form.oql.*;

import java.sql.Connection;

import java.io.PrintWriter;

/**
An Orm (Object-Relational Mapper) is a root object that holds
onto all the information needed for mapping objects in a particular
application to the (possibly multiple) Relational databases involved.
**/

/*package*/ class OrmC implements OrmSi {

    /*package*/ OrmC() {}

    //**********************************************************
    //(P)********************* Setup ***************************
    //**********************************************************

      /**
       * This sets up a predicate that can determine whether
       * a particular object belongs to this Orm (i.e. is
       * mapped to the database)
       */
    public void setupMappedObjectDeterminer(Predicate0Arg determiner) {throw new NotImplementedYetException();};

      /**
       * This sets up a function that returns the mapper
       * for any MappedObject (i.e. those objects that pass
       * the above determiner)
       */
    public void setupMapperGetter(Function1Arg getter) {throw new NotImplementedYetException();};

      /**
       * Determine whether an object is mapped by whether
       * it implements the MappedObject interface
       */
    public void useMappedObjectInterface() {
        //This is the default behavior right now
    }

    public void setupTraceStream (PrintWriter traceStream) {
        setupTraceStream_traceLevel(traceStream, 1);
    }

    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel) {
        this.traceStream = traceStream;
        this.traceLevel = traceLevel;
    }

    //**************************************
    //(P)************ FormInfo *************
    //**************************************

    public void addInfo(FormInfo formInfo) {
        formInfos.add(formInfo);
    }

        /**
         *@param formInfoC is a Class that creates FormInfo from newInstance().
         */
    public void addInfoClass_withDb(Class formInfoC, DatabaseConnection dbConnection) {
        try {
            FormInfo formInfo = (FormInfo) formInfoC.newInstance();
            formInfo.initOrm_db(this,dbConnection);
            this.addInfo(formInfo);
        } catch (Exception e) {
            throw new RuntimeWrappedException("Could not create formInfo from "+formInfoC,e);
        }
    }
        /**
         *@param formInfoC is a Class that creates FormInfo from newInstance().
         */
    public void addInfoClass(Class formInfoC) {
        addInfoClass_withDb(formInfoC, defaultDatabaseConnection());
    }

    public void setupDefaultDatabaseConnection(DatabaseConnection defaultDatabaseConnection) {
        this.databaseConnection = defaultDatabaseConnection;
    }

    //**********************************************************
    //(P)***************** Factory Methods *********************
    //**********************************************************


    //**************************************
    //(P)************ Queries **************
    //**************************************

    public QueryDescription newQueryDescription() {
        return QueryPackSi.newQueryDescription(this);
    }

    public OqlQuery newOqlQuery(String oqlString) {
        return OqlPack.newQuery(this, oqlString);
    }

    //**************************************
    //(P)********** ObjectMapper ***********
    //**************************************

        /**
         * All Mappers need a Table to work with and this is
         * the way they interact with the database
         *
         * <P>Mappers must also be "setup" using the setup methods and the
         * slot creation methods.  After setup has been completed,
         * call "doneSetup()" to prepare the mapper for execution.
         *
         * <P>The methods to set a mapper's tracing are now part of setup
         *
         *@see ObjectMapper
         *@see ObjectMapper#doneSetup
         */
    public ObjectMapper newObjectMapper(Table table) {
        ObjectMapper m = MappingPackSi.newObjectMapper(table);
        if (isTracing()) {
            m.setupTraceStream_traceLevel(traceStream,traceLevel);
        };
        addMapper(m);
        return m;
    }

    public ObjectMapper newObjectMapperNamed_table(String name, Table table) {
        ObjectMapper m =  MappingPackSi.newObjectMapperNamed_table(name,table);
        if (isTracing()) {
            m.setupTraceStream_traceLevel(traceStream,traceLevel);
        };
        addMapper(m);
        return m;
    }

    //**************************************
    //(P)***** DistinguishingMapper ********
    //**************************************

        /**@deprecated the name changed to DistinguishingObjectMapper*/
    public DistinguishedObjectMapper newDistinguishedObjectMapper(Table table) {
        return newDistinguishingObjectMapper(table);
    }
        /**@deprecated the name changed to DistinguishingObjectMapper*/
    public DistinguishedObjectMapper newDistinguishedObjectMapperNamed_table(String name, Table table) {
        return newDistinguishingObjectMapperNamed_table(name,table);
    }


    public DistinguishingObjectMapper newDistinguishingObjectMapper(Table table) {
        DistinguishingObjectMapper m =  MappingPackSi.newDistinguishingObjectMapper(table);
        addMapper(m);
        return m;
    }

    public DistinguishingObjectMapper newDistinguishingObjectMapperNamed_table(String name, Table table) {
        DistinguishingObjectMapper m =  MappingPackSi.newDistinguishingObjectMapperNamed_table(name,table);
        addMapper(m);
        return m;
    }

    //**************************************
    //(P)**** CollectiveObjectRetriever ****
    //**************************************

    public CollectiveObjectRetriever newCollectiveObjectRetriever() {
        CollectiveObjectRetriever m =  MappingPackSi.newCollectiveObjectRetriever();
        addRetriever(m);
        return m;
    }

    public CollectiveObjectRetriever newCollectiveObjectRetrieverNamed(String name) {
        CollectiveObjectRetriever m =  MappingPackSi.newCollectiveObjectRetriever(name);
        addRetriever(m);
        return m;
    }

    //**************************************
    //(P)******* AssociationMapper *********
    //**************************************

    public AssociationMapper newAssociationMapper(Table table) {
        return (AssociationMapper) newAssociationConnector(table);
    }

    //**************************************
    //(P)****** AssociationConnector *******
    //**************************************

    public AssociationConnector newAssociationConnector(Table table) {
        AssociationConnector ac =  MappingPackSi.newAssociationConnector(table);
        if (isTracing()) {
            ac.setupTraceStream_traceLevel(traceStream,traceLevel);
        };
        addAssociationConnector(ac);
        return ac;
    }

    public AssociationConnector newAssociationConnectorNamed_table(String name, Table table) {
        AssociationConnector ac =  MappingPackSi.newAssociationConnectorNamed_table(name,table);
        if (isTracing()) {
            ac.setupTraceStream_traceLevel(traceStream,traceLevel);
        };
        addAssociationConnector(ac);
        return ac;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void validateMapperSetups() {
        int size;
        boolean failed = false;
        size = objectMappers.size();
        for (int i = 0; i < size; i++) {
            ObjectMapperSi om = (ObjectMapperSi) objectMappers.atIndex(i);
            try {
                om.validateSetup();
            } catch (Exception e) {
                failed = true;
                System.err.println("Validation failed for "+om.name()+" because: "+e);
                if (isTracing()) e.printStackTrace();
            }
        };

        size = associationConnectors.size();
        for (int i = 0; i < size; i++) {
            AssociationConnector ac = (AssociationConnector) associationConnectors.atIndex(i);
            try {
                ac.validateSetup();
            } catch (Exception e) {
                failed = true;
                System.err.println("Validation failed for "+ac.name()+" because: "+e);
                if (isTracing()) e.printStackTrace();
            }
            ((AssociationConnector) associationConnectors.atIndex(i)).validateSetup();
        };

        size = objectRetrievers.size();
        for (int i = 0; i < size; i++) {
 //           ((CollectiveObjectRetriever) objectRetrievers.atIndex(i)).validateSetup();
        };

        if (failed) throw new ConfigurationException("Validation failed for Orm");
    }

    public void crossValidateMapperSetups() {
        int size;
        boolean failed = false;
        size = objectMappers.size();
        for (int i = 0; i < size; i++) {
            ObjectMapperSi om = (ObjectMapperSi) objectMappers.atIndex(i);
            try {
                om.crossValidate();
            } catch (Exception e) {
                failed = true;
                System.err.println("Cross validation failed for "+om.name()+" because: "+e);
                if (isTracing()) e.printStackTrace();
            }
        }
        size = associationConnectors.size();
        for (int i = 0; i < size; i++) {
            AssociationConnectorSi ac = (AssociationConnectorSi) associationConnectors.atIndex(i);
            try {
                ac.crossValidate();
            } catch (Exception e) {
                failed = true;
                System.err.println("Cross validation failed for "+ac.name()+" because: "+e);
                if (isTracing()) e.printStackTrace();
            }
        }
        if (failed) throw new ConfigurationException("Cross validation failed for Orm");
    }

    public void doneMapperSetups() {
        int size;
        boolean failed = false;
        size = objectMappers.size();
        for (int i = 0; i < size; i++) {
            try {
                ((ObjectMapperSi) objectMappers.atIndex(i)).doneSetup();
            } catch (Exception e) {
                failed = true;
                System.err.println("Mapper setup failed for  "+((ObjectMapperSi) objectMappers.atIndex(i)).name()+" because: "+e);
            }
        }
        if (failed) throw new ConfigurationException("Mapper setup failed for Orm");
        

        size = associationConnectors.size();
        for (int i = 0; i < size; i++) {
            ((AssociationConnector) associationConnectors.atIndex(i)).doneSetup();
        }

        size = objectRetrievers.size();
        for (int i = 0; i < size; i++) {
            ((CollectiveObjectRetriever) objectRetrievers.atIndex(i)).doneSetup();
        }

    }

    public void processFormInfos() {
        int size = formInfos.size();
        for (int i = 0; i < size; i++) {
            MapperBuilder mapperBuilder = ((FormInfo) formInfos.atIndex(i)).mapperBuilder();
            mapperBuilder.createMappers();
        }
        for (int i = 0; i < size; i++) {
            MapperBuilder mapperBuilder = ((FormInfo) formInfos.atIndex(i)).mapperBuilder();
            mapperBuilder.configureMappers();
        }
        for (int i = 0; i < size; i++) {
            MapperBuilder mapperBuilder = ((FormInfo) formInfos.atIndex(i)).mapperBuilder();
            mapperBuilder.configureCompleted();
        }
    }

    public void doneSetup() {
        processFormInfos();
        validateMapperSetups();
        doneMapperSetups();
        crossValidateMapperSetups();
    }

    public void validate() {
        doneSetup();
    }

    //**********************************************************
    //(P)******************** Asking ***************************
    //**********************************************************

    public ObjectMapper mapperNamed(String name) {
        ObjectRetriever or = retrieverNamed_orNull(name);
        if (or == null) throw new MappingException("No ObjectMapper named "+name);

        if (or instanceof ObjectMapper) {
            return (ObjectMapper) or;
        } else {
            throw new MappingException("The name "+name+" is associated with an ObjectRetriever not an ObjectMapper");
        };
    }


    public ObjectMapper mapperNamed_orNull(String name) {
        ObjectRetriever or = retrieverNamed_orNull(name);
        if (or == null) return null;

        if (or instanceof ObjectMapper) {
            return (ObjectMapper) or;
        } else {
            return null;
        };
    }

    public ObjectRetriever retrieverNamed(String name) {
        ObjectRetriever or = retrieverNamed_orNull(name);
        if (or == null) throw new MappingException("No ObjectRetriever named "+name);
        return or;
    }

    public ObjectRetriever retrieverNamed_orNull(String name) {
        ObjectRetriever or = (ObjectRetriever) nameToRetriever.atKey(name);
        if (or == null) return null;
        return or;
    }

    public AssociationConnector  associationConnectorNamed(String name) {
        return (AssociationConnector) nameToConnector.atKey(name);
    }


    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void clearIdentityCaches() {
        int size = objectMappers.size();
        for (int i = 0; i < size; i++) {
            ObjectMapper om = (ObjectMapper) objectMappers.atIndex(i);
            om.clearIdentityCache();
        }
    }

    public Map cacheCounts() {
        Map mapperToCount = CollectionsPack.newMap();
        int size = objectMappers.size();
        for (int i = 0; i < size; i++) {
            ObjectMapper om = (ObjectMapper) objectMappers.atIndex(i);
            mapperToCount.atKey_put(om.name(),new Integer(om.cacheCount()));
        }
        return mapperToCount;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************


    protected void addMapper(ObjectMapper om) {
        objectMappers.add(om);
        String name = om.name();
        if (name != null) {
            nameToRetriever.atKey_put(name,om);
        }
    }

    protected void addRetriever(ObjectRetriever or) {
        objectRetrievers.add(or);
        String name = or.name();
        if (name != null) {
            nameToRetriever.atKey_put(name,or);
        }
    }

    protected void addAssociationConnector(AssociationConnector ac) {
        associationConnectors.add(ac);
        String name = ac.name();
        if (name != null) {
            nameToConnector.atKey_put(name,ac);
        }
    }

    protected boolean isTracing() {
        return traceStream != null;
    }

    /*subsystem*/ public List objectMappers() {
        return objectMappers;
    }

    public DatabaseConnection defaultDatabaseConnection() {
        return databaseConnection;
    }

    //**********************************************************
    //(P)*************** Instance Variables ********************
    //**********************************************************

    protected DatabaseConnection databaseConnection;
    protected List objectMappers         = CollectionsPack.newList();
    protected List associationConnectors = CollectionsPack.newList();
    protected List objectRetrievers      = CollectionsPack.newList();

    protected Map nameToRetriever = CollectionsPack.newMap();
    protected Map nameToConnector = CollectionsPack.newMap();

    protected List formInfos = CollectionsPack.newList();

    //**************************************
    //(P)*********** Tracing ***************
    //**************************************

    protected PrintWriter traceStream = null;
    protected int traceLevel = 1;     //0 = minimal tracing, 1= normal (and slowing), 2=detailed




}
