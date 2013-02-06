/*======================================================================
**
**  File: chimu/form/query/QueryDescriptionC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.query;

import com.chimu.form.mapping.*;
import com.chimu.form.*;
import com.chimu.form.Orm;

// Standard Imports
import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;
import java.util.Enumeration;
import java.util.Date;

class QueryDescriptionC implements QueryDescriptionPi {
    /*package*/ QueryDescriptionC() {
        this.orm = null;
    }

    /*package*/ QueryDescriptionC(Orm orm) {
        this.orm = orm;
    }

    public Orm orm() {
        return orm;
    }

    public void putSqlInto(SqlBuilder sqlB) {
        if (resultsVar != null) {
            resultsVar.putSelectColumnsInto(sqlB);
        }
        putConditionSqlInto(sqlB);
    }

    public void putCountSqlInto(SqlBuilder sqlB) {
        sqlB.setToCount();
        if (resultsVar != null) {
            resultsVar.putObjectIdentityColumnInto(sqlB);
        }
        putConditionSqlInto(sqlB);
    }

    protected void putConditionSqlInto(SqlBuilder sqlB) {
        if (!hasDistinctResults) {
            sqlB.makeNotDistinct();
        }

        if (condition != null) {
            condition.putExpressionValueInto(sqlB);
        }
        int size;

        size = extentVars.size();
        for (int i = 0; i < size; i++) {
            ExtentVarPi ev = (ExtentVarPi) extentVars.atIndex(i);
            ev.addNeededExtentsTo(sqlB);
        }

        size = queryVars.size();
        for (int i = 0; i < size; i++) {
            QueryVarPi qv = (QueryVarPi) queryVars.atIndex(i);
            qv.addNeededExtentsTo(sqlB);
            qv.addNeededJoinsTo(sqlB);
        }
    }


    //**********************************************************
    //********************** Evaluating ************************
    //**********************************************************

    public Array selectAll() {
        if (resultsVar != null) {
            return resultsVar.selectAll();
        };
        QueryVarPi qv = defaultResultsVar();
        if (qv == null) throw new FailedRequireException("Query must have some variables before evaluating");
        return qv.selectAll();
    }

    public Object findAny() {
        if (resultsVar != null) {
            return resultsVar.findAny();
        };
        QueryVarPi qv = defaultResultsVar();
        if (qv == null) throw new FailedRequireException("Query must have some variables before evaluating");
        return qv.findAny();
    }

    public int countAll() {
        if (resultsVar != null) {
            return resultsVar.countAll();
        };
        QueryVarPi qv = defaultResultsVar();
        if (qv == null) throw new FailedRequireException("Query must have some variables before evaluating");
        return qv.countAll();
    }

    protected QueryVarPi defaultResultsVar() {
        if (resultsVar != null) return resultsVar;
        if (extentVars.size() > 0) return (QueryVarPi) extentVars.atIndex(0);
        return null;
    };

    //**********************************************************
    //****************** Variables and Values ******************
    //**********************************************************

    public ExtentVar  newExtentVar(String name, ObjectRetriever retriever){
        ExtentVar ev;
        if (name != null) {
            // name = name.toLowerCase(); //MLF970915-Don't see why I did this... inconsistent with usage
            ev = new ExtentVarC(this,name,retriever);
            addExtentVar(ev,name);
        } else {
            ev = new ExtentVarC(this,null,retriever);
            addExtentVar(ev);
        }
        return ev;
    }

    public ExtentVar  newExtentVar(ObjectRetriever retriever){
        return newExtentVar(null,retriever);
    }

    public QueryVar defaultQueryVar(ObjectRetriever retriever) {
        QueryVar ev = (QueryVar) defaultQueryVarForRetrievers.atKey(retriever);
        if (ev == null) {
            ev = newExtentVar(null,retriever);
            defaultQueryVarForRetrievers.atKey_put(retriever,ev);
        };
        return ev;
    }

    public void setDefaultQueryVarForRetriever_to(ObjectRetriever retriever, QueryVar qv) {
            //It would be better to simply pass in 'qv' without the redundant retriever
        if (qv.valuesRetriever() != retriever) {
            throw new FailedRequireException("Query variable 'qv' must have the same retriever as 'retriever'");
        };
        defaultQueryVarForRetrievers.atKey_put(retriever,qv);
    };

    public Constant newConstant(Object value) {
        if (value == null) return QueryPackPi.newConstantString((String) value);

        if (value instanceof MappedObject) {
            return QueryPackPi.newConstantMappedObject((MappedObject) value);
        } else if (value instanceof Number) {
            return QueryPackPi.newConstantNumber((Number) value);
        } else if (value instanceof String) {
            return QueryPackPi.newConstantString((String) value);
        } else if (value instanceof Date) {
            if (value instanceof java.sql.Date) {
                return QueryPackPi.newConstantDate((java.sql.Date) value);
            } else if (value instanceof java.sql.Time) {
                return QueryPackPi.newConstantTime((java.sql.Time) value);
            } else {
                return QueryPackPi.newConstantTimestamp((Date) value);
            }
        } else if (value instanceof Boolean) {
            return QueryPackPi.newConstantBoolean((Boolean) value);
        } else {
            throw new NotImplementedYetException("Could not create a constant for type "+value.getClass()+" value = "+value);
        }
    }


    public QueryVar aQueryVarForRetriever(ObjectRetriever retriever) {
        QueryVar qv = (QueryVar) defaultQueryVarForRetrievers.atKey(retriever);
        if (qv != null) return qv;

        int size = extentVars.size();
        for (int i = 0; i < size; i++) {
            if (retriever == ((QueryVar) extentVars.atIndex(i)).valuesRetriever()) {
                return (QueryVar) extentVars.atIndex(i);
            };
        };

        size = queryVars.size();
        for (int i = 0; i < size; i++) {
            if (retriever == ((QueryVar) queryVars.atIndex(i)).valuesRetriever()) {
                return (QueryVar) queryVars.atIndex(i);
            };
        };
        return null;
    };

        /**
         * Create and remember an unbound value so I can later set its value
         */
    public UnboundExpression newUnboundExpression() {
        return QueryPackPi.newUnboundExpression();
    }

    static public void setupVarLength(int varLength) {
        VAR_LENGTH = varLength - 2;  //Take two off for the counters
        VAR_START_LENGTH = 10;
        VAR_MID_LENGTH = VAR_LENGTH / 4;

        VAR_REMAINING_LENGTH = VAR_LENGTH - VAR_START_LENGTH;
    }

    static {
        setupVarLength(28);
    }

    static int VAR_LENGTH           = 26;
    static int VAR_START_LENGTH     = 10;
    static int VAR_MID_LENGTH       = VAR_LENGTH / 4;
    static int VAR_REMAINING_LENGTH = VAR_LENGTH - VAR_START_LENGTH;

    /*package*/ public String variableNameFromPrefix(String nameStart, String nameMiddle, String nameEnd) {
        int startLength = nameStart.length();
        int middleLength = nameMiddle.length();
        int endLength = nameEnd.length();
        String name;
        if (startLength+middleLength+endLength > VAR_LENGTH) {
            if (middleLength > VAR_MID_LENGTH) {
                nameMiddle = nameMiddle.substring(0,VAR_MID_LENGTH);
                middleLength = VAR_MID_LENGTH;
            };
            if (startLength > VAR_START_LENGTH) {
                nameStart = nameStart.substring(0,VAR_START_LENGTH);
                startLength = VAR_START_LENGTH;
            };
            name = nameStart+nameMiddle+nameEnd.substring(0,Math.min(VAR_LENGTH-startLength-middleLength,endLength));
        } else {
            name = nameStart+nameMiddle+nameEnd;
        };
        return variableNameFromPrefix(name);
    }


    /*package*/ public String variableNameFromPrefix(String name) {
            //This should be dependent on the target database type
        int length = name.length();
        if (length > VAR_LENGTH) {
            name = name.substring(0,VAR_START_LENGTH)+name.substring(length-VAR_REMAINING_LENGTH,length);
        };
        String lowercaseName = name.toLowerCase();

        Object index = variableNameCounters.atKey(lowercaseName);
        if (index == null) {
            index = new Integer(1);
            variableNameCounters.atKey_put(lowercaseName,index);
            //return name;  //Allow the first variable to have the base name, but still consider it '1'
            return name+index.toString(); //Revised 980512 to support older DB2 restriction/problem
        } else {
            index = new Integer(((Integer) index).intValue()+1);
            variableNameCounters.atKey_put(lowercaseName,index);
            return name+index.toString();
        };
    }

    //**********************************************************
    //*********************** Results **************************
    //**********************************************************

    public QueryVar resultsVar() {
        return resultsVar;
    };
        /**
         * Set which object variable will be the return values of the query
         *
         * By default this is whatever is the only ObjectVar (including unnamed ones)
         */
    public void setResultsVar (QueryVar qv) {
        resultsVar = (QueryVarPi) qv;
        resultsVar.makeNeededInResults();
    };
    public void addResultsVar (QueryVar ov) {
        throw new NotImplementedYetException("This will allow multiple result variables");
    };

        /**
         * Allow the results of the query to be non-distinct, so the same object
         * can be returned multiple times for a given query.  This is a violation
         * of predicate logic and relational theory, so the meaning of the multiple
         * objects is application dependent.
         */
    public void makeResultsNotDistinct() {
        hasDistinctResults = false;
    }

    //**********************************************************
    //********************** Condition *************************
    //**********************************************************

    public Condition condition() {
        return condition;
    };

    public void setCondition(Condition newCondition) {
        if (condition != null) {throw new FailedRequireException("Can not setCondition unless the current condition is not set");};
        condition = newCondition;
    };


        /**
         * Replace the current condition with another one.  Like setCondition but
         * this does not have a requirement that there be no condition yet.
         */
    public void replaceCondition(Condition newCondition) {
        condition = newCondition;
    };



    //**********************************************************
    //(P)*********** Common condition operations ***************
    //**********************************************************

    //**************************************
    //(P)************ OR *******************
    //**************************************

    public void or(Condition addedCondition){
        if (condition == null) {
            condition = addedCondition;
        } else {
            condition = newOrCondition(condition, addedCondition);
        };
    }

    public void or_equals(QueryExpression qe1, QueryExpression qe2){
        this.or(
            newEqualTo(qe1,qe2)
        );
    }

    public void or_equalsConstant(QueryExpression qe1, Object value){
        if (value == null) {
            this.or(newNull(qe1));
            return;
        };
        this.or(
            newEqualTo(
                qe1,
                newConstant(value)
            )
        );
    }

    //**************************************
    //(P)************ AND ******************
    //**************************************

    public void and(Condition addedCondition) {
        if (condition == null) {
            condition = addedCondition;
        } else {
            condition = newAndCondition(condition, addedCondition);
        };
    }

    public void and_equals(QueryExpression qe1, QueryExpression qe2){
        this.and(
            newEqualTo(qe1,qe2)
        );
    }

    public void and_equalsConstant(QueryExpression qe1, Object value) {
        if (value == null) {
            this.and(newNull(qe1));
            return;
        };
        this.and(
            newEqualTo(
                qe1,
                newConstant(value)
            )
        );
    }

    //**************************************
    //(P)*********** Conditions ************
    //**************************************

    public Condition newEqualTo(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newEqualTo(value1,value2);
    };

    public Condition newNotEqualTo(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newNotEqualTo(value1,value2);
    };

    //**************************************

    public Condition newNull(QueryExpression value1) {
        return QueryPackPi.newNull(value1);
    };

    public Condition newNotNull(QueryExpression value1) {
        return QueryPackPi.newNotNull(value1);
    };

    //**************************************

    public Condition newLike(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newLike(value1,value2);
    };

    public Condition newLikeConstant(QueryExpression value1, Object value2) {
        return QueryPackPi.newLike(value1,newConstant(value2));
    };

    public Condition newNotLike(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newNotLike(value1,value2);
    };

    public Condition newNotLikeConstant(QueryExpression value1, Object value2) {
        return QueryPackPi.newNotLike(value1,newConstant(value2));
    };

    //**************************************

    public Condition newGreaterThan(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newGreaterThan(value1,value2);
    };

    public Condition newGreaterThanEqualTo(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newGreaterThanEqualTo(value1,value2);
    };

    public Condition newLessThan(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newLessThan(value1,value2);
    };

    public Condition newLessThanEqualTo(QueryExpression value1, QueryExpression value2) {
        return QueryPackPi.newLessThanEqualTo(value1,value2);
    };

    //**************************************

    public Condition newAnd(Condition value1, Condition value2) {
        return QueryPackPi.newAnd(value1,value2);
    };

    public Condition newOr(Condition value1, Condition value2) {
        return QueryPackPi.newOr(value1,value2);
    };

    //**************************************

    public Condition newNotTrue(QueryExpression value1) {
        return QueryPackPi.newNotTrue(value1);
    };

    public Condition newTrue(QueryExpression value1) {
        return QueryPackPi.newTrue(value1);
    };

    //**************************************

    public QueryExpression newGenericExpression_between_and(String name,QueryExpression left, QueryExpression right) {
        return new Generic2ValueExpressionC(name,left,right);
    }

    public QueryExpression newGenericExpression_prefixing(String name,QueryExpression value) {
        return new Generic1ValueExpressionC(name,value);
    }

    //**********************************************************
    //(P)******************    PRIVATE    **********************
    //**********************************************************

    /*package*/ public void addExtentVar(ExtentVar ev) {
        extentVars.add(ev);
    }

    /*package*/ public void addExtentVar(ExtentVar ev, String name) {
        extentVars.add(ev);
        registerQueryVar(ev,name);
    }

    /*subsystem*/ public QueryVar queryVarNamed(String name) {
        return (QueryVar) nameToVar.atKey(name);
    }

    /*subsystem*/ public void registerQueryVar(QueryVar qv, String name) {
        QueryVar old = queryVarNamed(name);
        if (old != null) {
            throw new DevelopmentException("QueryVar alread exists: "+old);
        } else {
            nameToVar.atKey_put(name,qv);
        }
    }

    /*package*/ public void addQueryVar(QueryVar qv) {
        queryVars.add(qv);
    }

    protected Condition newAndCondition(Condition con1, Condition con2) {
        return new AndConditionC(con1, con2);
    }

    protected Condition newOrCondition(Condition con1, Condition con2) {
        return new OrConditionC(con1, con2);
    }


    /*package public boolean hasVariableNamed(String name) {
        variableNames.includesKey(name);
    }
    */

    //**********************************************************
    //(P)**************** Instance Variables *******************
    //**********************************************************

    protected QueryVarPi  resultsVar = null;
    protected List  extentVars                   = CollectionsPack.newList();
    protected List  queryVars                    = CollectionsPack.newList();
    protected Condition condition = null;

    protected Map       defaultQueryVarForRetrievers  = CollectionsPack.newMap();
    protected Map       nameToVar = CollectionsPack.newMap();


    protected List unboundValues                = CollectionsPack.newList();
    protected List unattachedSlotValues         = CollectionsPack.newList();
    protected List slotValues                   = CollectionsPack.newList();

    protected Map       variableNameCounters = CollectionsPack.newMap();

    protected boolean hasDistinctResults = true;

    protected final Orm orm;
};