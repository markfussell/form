/*======================================================================
**
**  File: chimu/form/mapping/ObjectMapper.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.mapping;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;
import com.chimu.kernel.streams.*;
import com.chimu.kernel.meta.*;

import com.chimu.form.database.*;
import com.chimu.form.query.*;

import java.io.PrintWriter;

/**
An ObjectMapper handles retrieving, storing, and translating
between objects stored on the database and objects in the application.

<P>Primarily this interface is for setup of an ObjectMapper, but it also
provides the interface methods that allow a DomainObject to talk to the
mapper during program execution and provides some Query capabilities which
are inherited from ObjectRetriever.

<P>Construction of an ObjectMapper is accomplished through an Orm.
@see com.chimu.form.Orm
**/
public interface ObjectMapper extends ObjectRetriever {

    //**********************************************************
    //(P)********************** Setup **************************
    //**********************************************************

    //**************************************
    //(P)****** Development Tracing ********
    //**************************************

    public void setupTraceStream (PrintWriter traceStream);
    public void setupTraceStream_traceLevel(PrintWriter traceStream, int traceLevel);

    //**************************************
    //**************************************
    //**************************************

    public void setupFetchIdentityDuringInsert();

        /**
         * Use pre-insert generation of identity with the specified
         * Generator function
         */
    public void setupPreInsertIdentityGenerator(Generator generator);

    //**************************************
    //(P)******** Object Binding ***********
    //**************************************

    public void setupCreationFunction(CreationFunction creationFunction);
    public void setupCreationFunctionFor_name(Class creationC, String creationFunctionName);
    public void setupCreationFunctionRef(MethodReference creationFunctionRef);
    public MethodReference creationFunctionRef();


    public void setupCallbackFunctionRef(MethodReference callbackFunctionRef);
    public void setupCallbackFunctionFor_name(Class callbackC, String methodName);
    public MethodReference callbackFunctionRef();

        /**
         * Specifies that the mapper should use the object initializer
         * instead of slot by slot setter method.  The Object initializer
         * will receive the Replicate itself as the first parameter and a
         * KeyedArray of all the slotValues as the second parameter.
         */
    public void setupUseObjectInitializer(Procedure2Arg objectInitializer);

         /**
         * Specifies that the mapper should use the valuesExtractor instead
         * of slot by slot getter methods.  The valuesExtractor will receive
         * the Replicate as the first argument and should provide a KeyedCollection
         * as the return value.
         */
    public void setupUseValuesExtractor(Function1Arg valuesExtractor);

    public void setupIsNewObjectPredicate(Predicate1Arg isNewObjectPredicate);

    public void setupPostInitializationFunction(Procedure1Arg postInitializationFunction);

        /**
         * Use the extended MappedObjectXi interface and notify the object
         * of automatic insert operations
         */
    public void setupUseExtendedInterface();

    public void setupNeedToRefreshAfterUpdate();
    public void setupNeedToRefreshAfterInsert();

    //**********************************************************
    //(P)******************** Setup Slots **********************
    //**********************************************************

    //**************************************
    //(P)********* Direct Slots ************
    //**************************************

    public Slot newDirectSlot(String slotAndColunName);
    public Slot newDirectSlot_column(String slotName, String mappedColumnName);
    public Slot newDirectSlot_column_type(String slotName, String mappedColumnName, Class columnJavaType);
    public Slot newDirectSlot_type(String slotName, Class columnJavaType);

    //**************************************
    //(P)******** Identity Slot ************
    //**************************************

    public IdentitySlot newIdentitySlot_column(String slotName, String mappedColumnName);
    public IdentitySlot newIdentitySlot_column_type(String slotName, String mappedColumnName, Class columnJavaType);

    //**************************************
    //(P)***** Transformation Slots ********
    //**************************************

    public Slot newTransformSlot_column_decoder_encoder(
            String slotName,
            String          mappedColumnName,
            Function1Arg    decoder,
            Function1Arg    encoder
            );

    public Slot newTransformSlot_column_type_decoder_encoder(
            String slotName,
            String          mappedColumnName,
            Class           columnJavaType,
            Function1Arg    decoder,
            Function1Arg    encoder
            );

    //**************************************
    //(P)********* Constant Slot ***********
    //**************************************

    public Slot newConstantSlot_column_value(
            String slotName,

            String          mappedColumnName,
            Object          value
            );

    public Slot newConstantSlot_column_type_value(
            String slotName,

            String   mappedColumnName,
            Class    columnJavaType,
            Object   slotValue
            );

    //**************************************
    //(P)**** Forward Association Slots ****
    //**************************************

    public ForwardAssociationSlot newForwardSlot_column_partner(
            String slotName,
            String             mappedColumnName,
            ObjectRetriever    partnerMapper
            );

    //**************************************
    //(P)**** Reverse Association Slots ****
    //**************************************

    public ReverseAssociationSlot newReverseSlot_partner_partnerSlot(
            String slotName,

            ObjectRetriever    partnerMapper,
            String             partnerSlotName
            );



    //**************************************
    //(P)*** External Association Slots ****
    //**************************************

    public ExternalAssociationSlot newExternalSlot_connector_mySlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName
            );


    public ExternalAssociationSlot newExternalSlot_connector_mySlot_partnerSlot(
            String                 slotName,

            AssociationConnector   associationConnector,
            String                 myConnectorSlotName,
            String                 partnerConnectorSlotName
            );



    //**********************************************************
    //(P)******************* Done Setup ************************
    //**********************************************************

        /**
         * Prepare the mapper to be ready for use
         *<P>@Require isValidSetup()
         */
    public void doneSetup();

    //**********************************************************
    //(P)************ Object To Mapper Interface ***************
    //**********************************************************

        /**
         * Unstub the passed in stub domainObject by retrieving
         * the information from the database and then initializing
         * the stub.
         *
         *@access Friend(DomainObjectReplicate)
         */
    public void unstubObject(MappedObject domainObject);
    public void deleteObject(MappedObject domainObject);
    public void updateObject(MappedObject domainObject);
    public void shallowRefreshObject(MappedObject domainObject);
    public void insertObject(MappedObject domainObject);
//  public void lockObject(MappedObject domainObject);

    //**********************************************************
    //**********************************************************
    //**********************************************************

        /**
         * Explicitly removes all objects from the IdentityCache.
         * <P>This must be done with care because if any domain
         * objects are still in use, FORM will not know about
         * them (unless you subsequently add them to the cache
         * again).  If FORM does not know about a domain object
         * it will rebuild the object when it gets one from
         * the database.
         */
    public void clearIdentityCache();
    public void removeFromIdentityCache(MappedObject domainObject);

        /**
         *Require: domainObject identityKey is non-null
         *Require: domainObject identityKey does not already exist in cache
         */
    public void addToIdentityCache(MappedObject domainObject);

    public int cacheCount();

    //**********************************************************
    //**********************************************************
    //**********************************************************

//    public void setupMayNeedToRefresh() ;
//    public void setupNeedToFetchIdentityAfterInsert();
        /**
         *@param desiredSqlType comes from the values in java.sql.Types
         *@deprecated
         *@see java.sql.Types
         */
    //public Slot newDirectSlot_column_sqlType(String slotName, String mappedColumnName, int desiredSqlType);
        /**@deprecated Renamed to setupCreationFunction*/
    public void setupCreatorFunction(CreationFunction creationFunction);


};
