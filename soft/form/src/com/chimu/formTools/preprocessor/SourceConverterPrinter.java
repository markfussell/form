/*======================================================================
**
**  File: chimu/formTools/preprocessor/SourceConverterPrinter.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.preprocessor;
import java.io.*;
import java.util.Hashtable;
import com.chimu.kernel.functors.*;

//<GenerateObjectBinding{

public /*package*/ class SourceConverterPrinter {
    //==========================================================
    //(P)=================== Constructors ======================
    //==========================================================

    InfoBuilder infoBuilder = new InfoBuilderC();
    boolean ignoreFile = false;

    public void setupInfoBuilder(InfoBuilder ib) {
        this.infoBuilder = ib;
    }
    
    public void doneSetup() {
        infoBuilder.prepareConverter(this);
        ASTFieldDeclaration.setupInfoBuilder(infoBuilder);
    }

    public SourceConverterPrinter(PrintWriter pw) {
        this.pw = pw;
    };

    public SourceConverterPrinter(PrintWriter pw, File formInfoFile) {
        this.pw = pw;
        this.formInfoFile = formInfoFile;
    };


    public PrintWriter formInfoWriter() {
        if (formInfoWriter == null) {
            try {
                
                if (formInfoFile == null) return null; //throw new RuntimeException("No FormInfo File");
                formInfoWriter = new PrintWriter(new FileOutputStream(formInfoFile));
            } catch (Exception e) {e.printStackTrace();}
        }
        return formInfoWriter;
    }

    public void ignoreFile() {
        ignoreFile = true;
    }

    public boolean ignoredFile() {
        return ignoreFile;
    }

    public void close() {
        if (formInfoWriter != null) {
            formInfoWriter.close();
        }
    }

    protected String currentTagName="";
    public void print(String string) {
        if (currentTagName.equals("")) {
            streamPrint(string);
            if (!ignoreFile && string.startsWith("//<")) {
                int tagEnd = string.indexOf('{');
//                System.out.print(tagEnd+string);
                if (tagEnd > 3) {
                    String tagName = string.substring(3,tagEnd).trim().toLowerCase();
                    if (tagName.equals("generateobjectbinding")) {
                        hasOutputBinding = true;
                    }
                    
                    StringBuffer buffer = (StringBuffer) buffers.get(tagName);
                    if (buffer != null) {
//                        System.out.print("buffer:"+tagName);
                        streamPrint(buffer.toString());
                        currentTagName = tagName;
                    } else {
                        Procedure1Arg procedure = (Procedure1Arg) procedures.get(tagName);
//                        System.out.print("procedure:"+tagName);
                        if (procedure != null) {
                            procedure.executeWith(this);
                            currentTagName = tagName;
                        };
                    };
                };
            };
        } else {
            if (string.startsWith("//}>")) {
                currentTagName = "";
                streamPrint(string);
            } else if (string.startsWith("//}")) {
                int tagEnd = string.indexOf('>');
                if (tagEnd > 3) {
                    String tagName = string.substring(3,tagEnd).trim().toLowerCase();
                    if (tagName.equals(currentTagName)) {
                        currentTagName = "";
                        streamPrint(string);
                    };
                };
            };
        };
    };

    public void println(String string) {
        print(string+"\n");
    };

    public void println() {
        print("\n");
    };

    public void inBuffer_print(String bufferName, String string) {
        StringBuffer buffer = (StringBuffer) buffers.get(bufferName);
        if (buffer == null) {
            buffer = new StringBuffer();
            buffers.put(bufferName,buffer);
        };
        buffer.append(string);
    };

    public StringBuffer getBuffer(String bufferName) {
        return (StringBuffer) buffers.get(bufferName);
    };

    public void inBuffer_println(String bufferName, String string) {
        inBuffer_print(bufferName,string+"\n");
    };

    public void registerCallbackNamed_procedure(String callbackName, Procedure1Arg procedure) {
        callbackName = callbackName.toLowerCase();
        if (!procedures.containsKey(callbackName)) {
            procedures.put(callbackName,procedure);
        };
    };


    public void setOuterCName(String className) {
        if (this.outerCName == null) this.outerCName = className;
    };

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    };

    public String packageName() {
        return packageName;
    };

    public void setOuterCToAbstract() {
        this.isOuterCAbstract = true;
    };

    public String outerCName() {
        return outerCName;
    };

    public boolean isOuterCAbstract() {
        return isOuterCAbstract;
    };
    
    public boolean needsToOutputBindingBlock() {
        return !hasOutputBinding && !ignoreFile;
    }

    //==========================================================
    //(P)================      PRIVATE      ====================
    //==========================================================

    protected void streamPrint(String string) {
         pw.print(addUnicodeEscapes(string));
    };

    
    protected String packageName;
    protected String outerCName;
    protected boolean isOuterCAbstract;
    protected boolean hasOutputBinding = false;

    protected int mode = 0;
    protected PrintWriter pw = null;
    protected StringBuffer stringB = new StringBuffer();

    protected Hashtable buffers = new Hashtable();
    protected Hashtable procedures = staticProcedures;

    protected PrintWriter formInfoWriter = null;
    protected File formInfoFile = null;



    private String addUnicodeEscapes(String str) {
        String retval = "";
        char ch;
        for (int i = 0; i < str.length(); i++) {
          ch = str.charAt(i);
          if ((ch < 0x20 || ch > 0x7e) && ch != '\t' && ch != '\n' && ch != '\r' && ch != '\f') {
        String s = "0000" + Integer.toString(ch, 16);
        retval += "\\u" + s.substring(s.length() - 4, s.length());
          } else {
            retval += ch;
          }
        }
        return retval;
    }

    //==========================================================
    //==========================================================
    //==========================================================

    static public void registerStaticCallbackNamed_procedure(String callbackName, Procedure1Arg procedure) {
        callbackName = callbackName.toLowerCase();
        if (!staticProcedures.containsKey(callbackName)) {
            staticProcedures.put(callbackName,procedure);
        };
    };

    static protected Hashtable staticProcedures = new Hashtable();
};
