/*======================================================================
**
**  File: chimu/form/FormException.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form;
import com.chimu.kernel.exceptions.WrappedException;

/**
FormException is a generic exception coming from the FORM
subsystem.  More specific exceptions are generated, see the "see also"
section.

<P>FormExceptions provide extra details on who recognized an error,
during what stage the error was caused, and who caused it.  These may
make error recognition easier.
**/
public class FormException extends WrappedException implements FormThrowInfo {
    public FormException(String detail) {
	    super(detail);
    }

    public FormException(String detail, Throwable wrappedThrowable) {
	    super(detail,wrappedThrowable);
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    public void setupRecognizer(String recognizer) {
        this.recognizerString = recognizer;
    }

    public void setupStage(String stage) {
        this.stageString = stage;
    }

    public void setupCause(String cause) {
        this.causeString = cause;
    }

    public void setupAnomaly(FormAnomaly anomaly) {
        this.anomaly = anomaly;
    }

    //**********************************************************
    //********************* ExceptionDetail ********************
    //**********************************************************

        /**
         * Returns a short description of this throwable object.
         *
         * @return  a string representation of this <code>Throwable</code>.
         * @since   JDK1.0
         */
    public String toString() {
        StringBuffer stringB = new StringBuffer();
    	String s = getClass().getName();
    	String message = getMessage();

    	if (wrappedThrowable != null) {
    	    stringB.append(wrappedThrowable.toString() + " <caused> ");
    	};
    	stringB.append(s);
    	if (message != null) {
    	    stringB.append(": " + message);
    	};
    	if (anomaly() != null) {
    	    stringB.append(" <anomaly> "+anomaly().toString());
    	}
    	if (!cause().equals("")) {
    	    stringB.append(" <cause> "+cause());
    	}
    	if (!recognizer().equals("")) {
    	    stringB.append(" <recognizer> "+recognizer());
    	}
    	if (!stage().equals("")) {
    	    stringB.append(" <stage> "+stage());
    	}
    	return stringB.toString();
    }

        /**
         *Returns the subsystem in which the error was recognized (?caused).
         *
         */
    public String recognizer() {
        if (recognizerString != null) return recognizerString;
        return "";
    }

        //Configuration, Execution
        //Usage, Internal, Component, ... {JDBC}

        /**
         * The 'stage' of an error is at what phase of
         * execution the error was caused.  Configuration
         * errors occur during Setup of an Orm or DatabaseConnection
         * although they may only be detected later.
         */
    public String stage() {
        if (stageString != null) return stageString;
        return "";
    }

        /**
         * The 'cause' of an error is
         */
    public String cause() {
        if (causeString != null) return causeString;
        return "";
    }

    public FormAnomaly anomaly() {
        return anomaly;
    }

    //**********************************************************
    //**********************************************************
    //**********************************************************

    protected String recognizerString = null;
    protected String stageString      = null;
    protected String causeString      = null;

    protected FormAnomaly anomaly = null;

}



