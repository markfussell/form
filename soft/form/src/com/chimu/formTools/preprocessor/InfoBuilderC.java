/*======================================================================
**
**  File: chimu/formTools/preprocessor/InfoBuilderC.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;

import com.chimu.kernel.functors.*;
import com.chimu.kernel.collections.*;

import com.chimu.kernel.exceptions.*;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Date;
import java.io.PrintWriter;

/**
 *@version 0.9.1
 */
public class InfoBuilderC implements InfoBuilder {
    protected boolean useConverterWrappers = true;
    
    public void prepareConverter(SourceConverterPrinter converter) {
        // System.out.println("We are here2");
        converter.registerCallbackNamed_procedure("GenerateObjectBinding",callback);
    }

    protected void printDeclarationsFor(Object ostrObj) {
        SourceConverterPrinter ostr = (SourceConverterPrinter) ostrObj;
        StringBuffer slotNamesB = ostr.getBuffer("slotNameConstants");
        StringBuffer columnNamesB = ostr.getBuffer("columnNameConstants");
        StringBuffer initB      = ostr.getBuffer("initializers");
        StringBuffer extractB   = ostr.getBuffer("extractors");
        StringBuffer getterB    = ostr.getBuffer("getters");
        StringBuffer setterB    = ostr.getBuffer("setters");
        String       className  = ostr.outerCName();
        boolean      isAbstract = ostr.isOuterCAbstract();

        String versionString = FormPreprocessor.versionString;
        boolean wantGetters  = FormPreprocessor.wantGetters;
        boolean wantSetters  = FormPreprocessor.wantSetters;

        boolean haveImports      = FormPreprocessor.haveImports;
        boolean formConstructor  = FormPreprocessor.formConstructor;
        boolean wantsCompatibility  = false;

        ostr.println("    //FORM Preprocessor Version "+versionString+" ran on: "+new Date()+" ");
        ostr.println();


        String mappingPrefix     = "com.chimu.form.mapping.";
        String collectionsPrefix = "com.chimu.kernel.collections.";

        if (haveImports) {
            mappingPrefix     = "";
            collectionsPrefix = "";
        };

        if ((initB != null) && (initB.length() > 0)) {
            ostr.println("    //**========================================================");
            ostr.println("    //(P)=========== Slots, Initializing & Extracting ==========");
            ostr.println("    //==========================================================");
            ostr.println();
            if ((FormPreprocessor.useSlotConstants) && (slotNamesB != null)) {
                ostr.print(slotNamesB.toString());  //Not println because has final CR already
                ostr.println();
            }

            ostr.println("    /*friend:FORM*/ public void form_initState("+collectionsPrefix+"KeyedArray slotValues) {");
            ostr.println("        super.form_initState(slotValues);");
            ostr.println();
            ostr.print(initB.toString());  //Not println because has final CR already
            ostr.println("    }");
            ostr.println();

            ostr.println("    /*friend:FORM*/ public void form_extractStateInto("+collectionsPrefix+"KeyedArray slotValues) {");
            ostr.println("        super.form_extractStateInto(slotValues);");
            ostr.println();
            ostr.print(extractB.toString());
            ostr.println("    }");
            ostr.println();
        }

        if (formConstructor) {
            ostr.println("    //**========================================================");
            ostr.println("    //(P)==================== Constructor ======================");
            ostr.println("    //==========================================================");
            ostr.println();

            ostr.println("    /*friend:FORM*/ public "+className+"("+mappingPrefix+"CreationInfo cInfo) {");
            ostr.println("        super(cInfo);");
            ostr.println("    }");
            ostr.println();
        }

        ostr.println("    //**========================================================");
        ostr.println("    //(P)==================== Class Info =======================");
        ostr.println("    //==========================================================");
        ostr.println();

        ostr.println("    static Class myC() {");
        ostr.println("        return "+className+".class;");
        ostr.println("    }");
        ostr.println();

        if (!isAbstract) {
            if (!formConstructor) {
                if (wantsCompatibility) {
                    ostr.println("        /==@deprecated for backward compatibility... use form_creationFunction*/");
                    ostr.println("    static /*friend:FORM*/ public "+mappingPrefix+"CreationFunction basicNewFunction() {");
                    ostr.println("        return form_creationFunction();");
                    ostr.println("    }");
                    ostr.println();
                };
                ostr.println("    static /*friend:FORM*/ public "+mappingPrefix+"CreationFunction form_creationFunction() {");
                ostr.println("        return new "+mappingPrefix+"CreationFunction() {");
                ostr.println("            public "+mappingPrefix+"MappedObject valueWith("+mappingPrefix+"CreationInfo cInfo){ ");
                ostr.println("                return new "+className+"();");
                ostr.println("            }");
                ostr.println("        };");
                ostr.println("    }");
                ostr.println();
            } else {
                ostr.println("    static /*friend:FORM*/ public "+mappingPrefix+"CreationFunction form_creationFunction() {");
                ostr.println("        return new "+mappingPrefix+"CreationFunction() {");
                ostr.println("            public "+mappingPrefix+"MappedObject valueWith("+mappingPrefix+"CreationInfo cInfo){ ");
                ostr.println("                return new "+className+"(cInfo);");
                ostr.println("            }");
                ostr.println("        };");
                ostr.println("    }");
                ostr.println();
            };
        }; //End abstract test

        if (
                (wantGetters && (getterB != null) && (getterB.length()>0)) ||
                (wantSetters && (setterB != null) && (setterB.length()>0))
                ){
            ostr.println("    //**====================================");
            ostr.println("    //(P)============ Accessors ============");
            ostr.println("    //======================================");
            ostr.println();
            if (wantGetters && (getterB != null) && (getterB.length()>0)) {
                ostr.println(getterB.toString());
                ostr.println();
            };
            if (wantSetters && (setterB != null) && (setterB.length()>0)) {
                ostr.println(setterB.toString());
                ostr.println();
            };
        }


        PrintWriter fiw = ostr.formInfoWriter();
        if (fiw != null) {
            String packageName = ostr.packageName();
            fiw.println("//FORM Preprocessor Version "+versionString+" ran on: "+new Date()+" ");
            fiw.println("//<FormIgnore>");
            if (packageName != null) {
                fiw.println("");
                fiw.println("package "+packageName+";");
            };
            fiw.println("");
            fiw.println("/==");
            fiw.println(className+"_FormInfo creates the ObjectMapper for "+className+".");
            fiw.println("");
            fiw.println("@see com.chimu.form.description.FormInfo");
            fiw.println("**/");
            fiw.println("public class "+className+"_FormInfo extends DomainObjectAbsC_FormInfo {");
            if (FormPreprocessor.useSlotConstants) {
                if (slotNamesB != null) {
                    fiw.println("");
                    fiw.print(slotNamesB.toString());  //Not println because has final CR already
                    fiw.println();
                }
                if (columnNamesB != null) {
                    fiw.println("");
                    fiw.print(columnNamesB.toString());  //Not println because has final CR already
                    fiw.println();
                }
            }
            fiw.println("");
            fiw.println("    public void configureMappers() {");
            fiw.println("        super.configureMappers();");

            StringBuffer formInfoB    = ostr.getBuffer("formInfoSlots");
            if (formInfoB != null) {
                fiw.println("");
                fiw.print(formInfoB.toString());
            }
            fiw.println("    }");
            fiw.println("");
            fiw.println("    public Class myC() {");

            fiw.println("       return "+className+".class;");
            fiw.println("    }");
            fiw.println("}");
            fiw.println("");
        }
    };

    protected Procedure1Arg callback = new Procedure1Arg() {public void executeWith(Object ostrObj) {
        printDeclarationsFor(ostrObj);
    }};

    protected Map primitiveTypeWrappers = CollectionsPack.newMap();

    {
        primitiveTypeWrappers.atKey_put("boolean",new String[] {"Boolean","false"});
        primitiveTypeWrappers.atKey_put("char",new String[] {"Character","' '"});
        primitiveTypeWrappers.atKey_put("byte",new String[] {"Byte","0"});
        primitiveTypeWrappers.atKey_put("short",new String[] {"Short","0"});
        primitiveTypeWrappers.atKey_put("int",new String[] {"Integer","0"});
        primitiveTypeWrappers.atKey_put("long",new String[] {"Long","0"});
        primitiveTypeWrappers.atKey_put("float",new String[] {"Float","0.0f"});
        primitiveTypeWrappers.atKey_put("double",new String[] {"Double","0.0"});
    }

    protected Map databaseTypes = CollectionsPack.newMap();
    {
        databaseTypes.atKey_put("boolean","");
        databaseTypes.atKey_put("byte","");
        databaseTypes.atKey_put("char","");
        databaseTypes.atKey_put("byte","");
        databaseTypes.atKey_put("short","");
        databaseTypes.atKey_put("int","");
        databaseTypes.atKey_put("long","");
        databaseTypes.atKey_put("float","");
        databaseTypes.atKey_put("double","");
        databaseTypes.atKey_put("Boolean","");
        databaseTypes.atKey_put("Character","");
        databaseTypes.atKey_put("Byte","");
        databaseTypes.atKey_put("Short","");
        databaseTypes.atKey_put("Integer","");
        databaseTypes.atKey_put("Long","");
        databaseTypes.atKey_put("Float","");
        databaseTypes.atKey_put("Double","");
        databaseTypes.atKey_put("Object","");
        databaseTypes.atKey_put("String","");

        databaseTypes.atKey_put("java.util.Date","");
        databaseTypes.atKey_put("java.sql.Date","");
        databaseTypes.atKey_put("java.sql.Time","");
        databaseTypes.atKey_put("java.sql.Timestamp","");
        databaseTypes.atKey_put("java.math.BigDecimal","");

        databaseTypes.atKey_put("Date","");
        databaseTypes.atKey_put("Date","");
        databaseTypes.atKey_put("Time","");
        databaseTypes.atKey_put("Timestamp","");
        databaseTypes.atKey_put("BigDecimal","");
    }

    public void staticPrepareConverter(SourceConverterPrinter ostr) {
//        SourceConverterPrinter.registerStaticCallbackNamed_procedure("GenerateObjectBinding",callback);
    }

    protected String varNameToSlotNameFor(String varName) {
        String slotName = varName;
        if (slotName.startsWith("my")) {
            slotName = slotName.substring(2,3).toLowerCase()+slotName.substring(3);
        } else if (slotName.startsWith("_")) {
            slotName = slotName.substring(1);
        };

        if (slotName.endsWith("_")) {
            slotName = slotName.substring(0,slotName.length()-1);
        }
        return slotName;
    }

    protected String slotNameToColumnNameFor_type(String slotName, String typeName) {
        String columnName = slotName.substring(0,1).toUpperCase()+slotName.substring(1);
        if (columnName.equals(slotName)) return null;
        return columnName;
    }

    //protected String slotNameToColumnNameFor(String slotName) {
    //    String columnName = slotName.substring(0,1).toUpperCase()+slotName.substring(1);
    //    if (columnName.equals(slotName)) return null;
    //    return columnName;
    //}

    protected String directSlotNameToColumnNameFor_type(String slotName, String typeName) {
        String columnName = slotName.substring(0,1).toUpperCase()+slotName.substring(1);
        return columnName+"_ID";
    }

    /*interface*/ public void printDeclarations(SourceConverterPrinter ostr,String typeName, String varName) {
//        ostr.registerCallbackNamed_procedure("GenerateObjectBinding",callback);
        String className = ostr.outerCName();

        String slotNameBase = varNameToSlotNameFor(varName);
        String slotName;  //Determined momentarily

        if (FormPreprocessor.useSlotConstants) {
            ostr.inBuffer_println("slotNameConstants","    "+FormPreprocessor.slotConstantVisibility+" final String SLOT_"+slotNameBase+" = \""+slotNameBase+"\";");
            slotName = "SLOT_"+slotNameBase;
        } else {
            slotName = "\""+slotNameBase+"\"";
        }

        String[] primitiveTypeWrapper = (String[]) primitiveTypeWrappers.atKey(typeName);
        if (useConverterWrappers) {
            if (primitiveTypeWrapper == null) {
                     //Example: this.groupName =     form_SlotValueTo_String(values.atKey("groupName"));
                ostr.inBuffer_println("initializers","        this."+varName+" = form_SlotValueTo_"+typeName+"(slotValues.atKey("+slotName+"));");
                    //Example: values.atKey_put("groupName",form_SlotValueFrom_String(this.groupName));
                ostr.inBuffer_println("extractors","        slotValues.atKey_put("+slotName+",form_SlotValueFrom_"+typeName+"(this."+varName+"));");

            } else {
                String primitiveC = primitiveTypeWrapper[0];
                String defaultValue = primitiveTypeWrapper[1];

                     //Example: this.groupName =     form_SlotValueToPrimitive_String(values.atKey("groupName"));
                ostr.inBuffer_println("initializers","        this."+varName+" = form_SlotValueToPrimitive_"+primitiveC+"(slotValues.atKey("+slotName+"));");
                    //Example: values.atKey_put("groupName",form_SlotValueFromPrimitive_String(this.groupName));
                ostr.inBuffer_println("extractors","        slotValues.atKey_put("+slotName+",form_SlotValueFromPrimitive_"+primitiveC+"(this."+varName+"));");
            };
        } else {
            if (primitiveTypeWrapper == null) {
                     //Example: this.groupName =     (String) values.atKey("groupName");
                ostr.inBuffer_println("initializers","        this."+varName+" = ("+typeName+") slotValues.atKey("+slotName+");");
                    //Example: values.atKey_put("groupName",this.groupName);
                ostr.inBuffer_println("extractors","        slotValues.atKey_put("+slotName+",this."+varName+");");

                ostr.inBuffer_println("getters","    protected Function1Arg "+varName+"Getter () {");
                ostr.inBuffer_println("getters","        return new Function1Arg() {public Object valueWith(Object o) {");
                ostr.inBuffer_println("getters","            return (("+className+") o)."+varName+";");
                ostr.inBuffer_println("getters","        }};");
                ostr.inBuffer_println("getters","    }");
                ostr.inBuffer_println("getters","");

                ostr.inBuffer_println("setters","    protected Procedure2Arg "+varName+"Setter () {");
                ostr.inBuffer_println("setters","        return new Procedure2Arg() {public void executeWith_with(Object o, Object value) {");
                ostr.inBuffer_println("setters","            try{ (("+className+") o)."+varName+" = ("+typeName+") value);");
                ostr.inBuffer_println("setters","        }};");
                ostr.inBuffer_println("setters","    }");
                ostr.inBuffer_println("setters","");
            } else {
                String primitiveC = primitiveTypeWrapper[0];
                String defaultValue = primitiveTypeWrapper[1];



                    //Example: try{this.isHappy = ((Boolean) values.atKey("isHappy")).booleanValue();} catch (Exception e) {this.isHappy = false;};;
                ostr.inBuffer_println("initializers","        try{ this."+varName+" = (("+primitiveC+") slotValues.atKey("+slotName+"))."+typeName+"Value();  } catch (Exception e) {this."+varName+"="+defaultValue+";};");
                    //Example: values.atKey_put("groupName",new Boolean(this.groupName));
                ostr.inBuffer_println("extractors","        slotValues.atKey_put("+slotName+",new "+primitiveC+"(this."+varName+"));");

                ostr.inBuffer_println("getters","    protected Function1Arg "+varName+"Getter () {");
                ostr.inBuffer_println("getters","        return new Function1Arg() {public Object valueWith(Object o) {");
                ostr.inBuffer_println("getters","            return new "+primitiveC+"((("+className+") o)."+varName+");");
                ostr.inBuffer_println("getters","        }};");
                ostr.inBuffer_println("getters","    }");
                ostr.inBuffer_println("getters","");

                ostr.inBuffer_println("setters","    protected Procedure2Arg "+varName+"Setter () {");
                ostr.inBuffer_println("setters","        return new Procedure2Arg() {public void executeWith_with(Object o, Object value) {");
                ostr.inBuffer_println("setters","            try{ (("+className+") o)."+varName+" = (("+primitiveC+") value)."+typeName+"Value();  } catch (Exception e) {(("+className+") o)."+varName+"="+defaultValue+";};");
                ostr.inBuffer_println("setters","        }};");
                ostr.inBuffer_println("setters","    }");
                ostr.inBuffer_println("setters","");
            };
        }

        if (FormPreprocessor.generateFormInfo) {
            if (databaseTypes.atKey(typeName) != null) {
                String slotType = typeName;
                if (primitiveTypeWrapper != null) {
                    slotType = primitiveTypeWrapper[0];
                };

                String columnNameBase = slotNameToColumnNameFor_type(slotNameBase,typeName);
                String columnName = "\""+columnNameBase+"\"";


                if (FormPreprocessor.useSlotConstants) {
                    ostr.inBuffer_println("columnNameConstants","    "+FormPreprocessor.slotConstantVisibility+" final String COLUMN_"+slotNameBase+" = \""+columnNameBase+"\";");
                    columnName = "COLUMN_"+slotNameBase;
                }

                if (columnNameBase == null) {
                    ostr.inBuffer_println("formInfoSlots","        myMapper.newDirectSlot_type("+slotName+","+slotType+".class);");
                } else {
                    ostr.inBuffer_println("formInfoSlots","        myMapper.newDirectSlot_column_type("+slotName+","+columnName+","+slotType+".class);");
                }

            } else if (collectionTypes.atKey(typeName) != null) {
                    //We have a reverse slot
                ostr.inBuffer_println("formInfoSlots","        myMapper.newReverseSlot_partner_partnerSlot("+slotName+",");
                ostr.inBuffer_println("formInfoSlots","                orm.retrieverNamed(\""+typeName+"\"),");
                ostr.inBuffer_println("formInfoSlots","                \""+className+"\");");
            } else {
                String columnNameBase = directSlotNameToColumnNameFor_type(slotNameBase,typeName);
                String columnName = "\""+columnNameBase+"\"";

                if (FormPreprocessor.useSlotConstants) {
                    ostr.inBuffer_println("columnNameConstants","    "+FormPreprocessor.slotConstantVisibility+" final String COLUMN_"+slotNameBase+" = \""+columnNameBase+"\";");
                    columnName = "COLUMN_"+slotNameBase;
                }

                ostr.inBuffer_println("formInfoSlots","        myMapper.newForwardSlot_column_partner("+slotName+",");
                ostr.inBuffer_println("formInfoSlots","                "+columnName+",");
                ostr.inBuffer_println("formInfoSlots","                orm.retrieverNamed(\""+typeName+"\"));");

            }

        }

    };


    protected Map collectionTypes = CollectionsPack.newMap();
    {
        collectionTypes.atKey_put("java.util.Vector","");
        collectionTypes.atKey_put("java.util.List","");

        collectionTypes.atKey_put("com.chimu.kernel.collections.List","");

        collectionTypes.atKey_put("Vector","");
        collectionTypes.atKey_put("List","");
    }

}

