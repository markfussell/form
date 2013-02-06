/*======================================================================
**
**  File: chimu/formTools/examples/ExampleRunnerWindow.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.formTools.examples;


import com.chimu.kernel.collections.*;
import com.chimu.kernel.collections.List;

import com.chimu.form.database.driver.*;

import com.chimu.kernel.bdnm.awt.*;
import java.awt.*;

/**
ExampleRunnerWindow handles the layout and presentation of the ExampleRunner
application model
**/
public class ExampleRunnerWindow extends Frame {

	public ExampleRunnerWindow() {

	}

	public ExampleRunnerWindow(String title) {
	    this();
	    setTitle(title);
	}

    public ExampleRunnerWindow(ExampleRunner anApp) {
        this.application = anApp;
    }

    //==========================================================
    //==========================================================
    //==========================================================


	void populateDataButton_Clicked(java.awt.event.ActionEvent event) {
	    application.doPopulate(
	            dbDriverDisableEscapesCheckbox.getState(),
	            dbDriverGenerateIndexesCheckbox.getState()
	        );
	}

    //==========================================================
    //==========================================================
    //==========================================================

	void isTracing_ItemStateChanged(java.awt.event.ItemEvent event) {
		application.setTracing(isTracing.getState());
	}

	void traceChoice_ItemStateChanged(java.awt.event.ItemEvent event) {
		application.setTraceLevel(traceChoice.getSelectedIndex()-1);
	}

	void dbChoices_ItemStateChanged(java.awt.event.ItemEvent event) {
        application.setDbProductFromName(dbChoices.getSelectedItem());
	}


	void driverChoices_ItemStateChanged(java.awt.event.ItemEvent event) {
        application.setDbDriverFromIndex(driverChoices.getSelectedIndex());
	}


	void testLoginButton_Clicked(java.awt.event.ActionEvent event) {
	    application.doTestLogin();
	}

	void dropDataButton_Clicked(java.awt.event.ActionEvent event) {
		application.doDrop();
	}

  	void dbUrlField_LostFocus(java.awt.event.FocusEvent event) {
        this.application.setDbUrl(dbUrlField.getText());
	}


  	void dbDriverCField_LostFocus(java.awt.event.FocusEvent event) {
        this.application.setCustomDbDriverC(dbDriverCField.getText());
	}

	void notifyRootDirFieldChanged() {
        this.application.setRootDirectory(rootDirField.getText());
	}

	void schemeChoice_ItemStateChanged(java.awt.event.ItemEvent event) {
        application.setCurrentScheme(schemeChoices.getSelectedIndex());
	}

	void passwordField_LostFocus(java.awt.event.FocusEvent event) {
        application.setPassword(passwordField.getText());
	}


	void loginNameField_LostFocus(java.awt.event.FocusEvent event) {
		application.setLoginName(loginNameField.getText());
	}

	void catalogField_LostFocus(java.awt.event.FocusEvent event) {
        application.setCatalog(catalogField.getText());
	}

	void schemeField_LostFocus(java.awt.event.FocusEvent event) {
        application.setScheme(schemeField.getText());
	}


	void runQueryButton_Clicked(java.awt.event.ActionEvent event) {
		// to do:  code goes here.

        int[] queries = querySelectList.getSelectedIndexes();
        int size = queries.length;

        application.doRunQueries(queries);
	}


    //==========================================================
    //==========================================================
    //==========================================================

    void noteResultTextChanged() {
        resultText.setText(application.resultText());
		resultText.invalidate();
    }

    void noteDbUrlChanged() {
        // resultText.setText(application.resultText());
		// resultText.invalidate();
    }

    void noteSchemeListChanged() {
        buildSchemeList();
        if (application.currentScheme == null) {
            populateDataButton.setEnabled(false);
            //dropDataButton.setEnabled(false);
        } else {
            populateDataButton.setEnabled(true);
            //dropDataButton.setEnabled(true);
        }
		schemeChoices.invalidate();
    }

    void noteQueryListChanged() {
        buildQueryList();
		querySelectList.invalidate();
		querySelectList.setEnabled(true);
    }

    void noteDbDriverChanged() {
        buildProductNames();
        dbChoices.invalidate();
    }

    void noteDbProductChanged() {
        //We rely on the list selecting the first item when rebuilt
        //and the ApplicationModel not changing the selected item
    }



    //==========================================================
    //==========================================================
    //==========================================================

    static String formExamplesDirectory = "com.chimu.formTools.examples.";

	protected List getSchemeNameList() {
	    List aSeq = application.getSchemeList();
	    List retSeq = CollectionsPack.newList();
	    for (int i = 0; i < aSeq.size(); i++) {
	        Object aScheme = aSeq.atIndex(i);
	        String name = aScheme.toString();
	        if (name.indexOf(formExamplesDirectory)>-1) {
	            name = "FormTools."+name.substring(formExamplesDirectory.length());
	        };
	        retSeq.add(name);
	    }
	    //System.out.println("Scheme names = "+retSeq);
	    return retSeq;
	}

	public List getQueryNamesList() {
	    List aSeq = application.getQueryList();

	    List retSeq = CollectionsPack.newList();
	    for (int i = 0; i < aSeq.size(); i++) {
            Class testC = (Class) aSeq.atIndex(i);
            //try {
            //    DatabaseTest aTest = (DatabaseTest) testC.newInstance();
            //} catch (Exception e) {};
            if (testC != null) {
    	        String name = testC.getName();
    	        if (name.indexOf(formExamplesDirectory)>-1) {
    	            name = "FormTools."+name.substring(formExamplesDirectory.length());
    	        };
    	        retSeq.add(name);
    	    }
	    }
	    return retSeq;
	}


    void buildSchemeList() {
        rebuildChoice_fromCollection(schemeChoices,getSchemeNameList());
    }

    void buildQueryList() {
        rebuildList_fromCollection(querySelectList,getQueryNamesList());
    }

    void buildProductNames() {
        DatabaseDriver dbDriver = application.dbDriver;
        if (dbDriver == null) {
            productLabel.setVisible(false);
            dbChoices.setVisible(false);
            dbDriverCLabel.setVisible(true);
            dbDriverCField.setVisible(true);
            dbDriverDisableEscapesCheckbox.setVisible(true);
            dbDriverGenerateIndexesCheckbox.setVisible(true);
        } else {
            productLabel.setVisible(true);
            dbChoices.setVisible(true);
            dbDriverCLabel.setVisible(false);
            dbDriverCField.setVisible(false);
            dbDriverDisableEscapesCheckbox.setVisible(false);
            dbDriverGenerateIndexesCheckbox.setVisible(false);

            if (application.dbDriver.needsToKnowDatabaseProduct()) {
                rebuildChoice_fromCollection(dbChoices,application.dbProductNames());
                dbChoices.setEnabled(true);
            } else {
                dbChoices.removeAll();
                dbChoices.addItem("Any");
                dbChoices.setEnabled(false);
            }
        }
    }
//1433
    void buildDriverNames() {
        rebuildChoice_fromCollection(driverChoices,application.dbDriverNames());
    }

    void buildTraceChoices() {
        traceChoice.removeAll();
        traceChoice.addItem("No Tracing");
        traceChoice.addItem("Trace Level 1");
        traceChoice.addItem("Trace Level 2");
        traceChoice.addItem("Trace Level 3");
    }

    void rebuildList_fromCollection(java.awt.List aList, Array collection) {
        aList.removeAll();
        int size = collection.size();
        for (int i = 0; i < size; i++) {
            aList.addItem((String) collection.atIndex(i));
        };
    }

    void rebuildChoice_fromCollection(java.awt.Choice aChoice, Array collection) {
        aChoice.removeAll();
        int size = collection.size();
        for (int i = 0; i < size; i++) {
            aChoice.addItem((String) collection.atIndex(i));
        };
    }

    //==========================================================
    //==========================================================
    //==========================================================



	void querySelectList_ItemStateChanged(java.awt.event.ItemEvent event) {
		runQueryButton.setEnabled(! (querySelectList.getSelectedIndexes().length == 0));
	}


	void QueryFrame1_ComponentShown(java.awt.event.ComponentEvent event) {
		// to do:  code goes here.

        int size = 0;

        rootDirField.setText(application.rootDirectoryName);
        dbUrlField.setText(application.dbUrl);
        loginNameField.setText(application.loginName);
        dbDriverCField.setText(application.customDbDriverCName);


        buildDriverNames();
        buildProductNames();
        buildSchemeList();
        buildQueryList();
        buildTraceChoices();

        // dbUrlField.setText(application.dbUrl);
        // rootDirField.setText(application.rootDirectory.getAbsolutePath());


		driverChoices.invalidate();
		dbChoices.invalidate();
		schemeChoices.invalidate();
		querySelectList.invalidate();
		traceChoice.invalidate();

		dbUrlField.invalidate();
		rootDirField.invalidate();
	}

	void Frame1_WindowClosing(java.awt.event.WindowEvent event) {
	    if (application.acceptCloseRequest()) {
            setVisible(false);          // hide the Frame
            dispose();                  // free the system resources
            System.exit(1);
        }
	}

    //==========================================================
    //==========================================================
    //==========================================================

/*
    public synchronized void show() {
    	move(50, 50);
    	super.show();
    }
*/

    static void addTo_layout_label_component_index_leftWidth(Panel panel, FractionalLayout fLay, java.awt.Label label, java.awt.Component comp, int index, int leftWidth) {
        int leftWidth2 = leftWidth+10;
        int height = 20;
        int base = 40;
        if (label != null) {
            fLay.setConstraint(label,          new FrameConstraint( 0.0,10,           0.0,base+height*index,      0.0,leftWidth,      0.0,(base+height*index + height)));
            panel.add(label);
        }
        fLay.setConstraint(comp,           new FrameConstraint( 0.0,leftWidth2,   0.0,base+height*index,      1.0,0,              0.0,(base+height*index + height)));
        panel.add(comp);
    }

    static void add2To_layout_label_component_index_leftWidth(Panel panel, FractionalLayout fLay, java.awt.Label label, java.awt.Component comp, int index, int leftWidth) {
        int leftWidth2 = leftWidth+10;
        int height = 20;
        int base = 0;
        fLay.setConstraint(label,          new FrameConstraint( 0.0,10,           0.0,base+(height*index),      0.0,leftWidth,      0.0,(base+(height*index) + height)));
        fLay.setConstraint(comp,           new FrameConstraint( 0.0,leftWidth2,   0.0,base+(height*index),      1.0,0,              0.0,(base+(height*index) + height)));
        panel.add(label);
        panel.add(comp);
    }

    static void addTo3_layout_label_component_index_leftWidth(Panel panel, FractionalLayout fLay, java.awt.Label label, java.awt.Component comp, int index, int leftWidth) {
        int leftWidth2 = leftWidth+10;
        int height = 20;
        int base = 50;
        if (label != null) {
            fLay.setConstraint(label,          new FrameConstraint( 0.0,10,           0.0,base+height*index,      0.0,leftWidth,      0.0,(base+height*index + height)));
            panel.add(label);
        }
        fLay.setConstraint(comp,           new FrameConstraint( 0.0,leftWidth2,   0.0,base+height*index,      1.0,0,              0.0,(base+height*index + height)));
        panel.add(comp);
    }

    public void addNotify() {

        super.addNotify();


            //MLF-970927: I do not know why this is in addNotify instead of the constructor.
        FractionalLayout fLay;
        fLay = new FractionalLayout();
		setLayout(fLay);

		setSize(getInsets().left + getInsets().right + 647,getInsets().top + getInsets().bottom + 500);

		//==================================
		//==================================
		//==================================

		topPanel = new java.awt.Panel();
		topPanel.setLayout(new GridLayout(1,2,5,5));
		add(topPanel);

		//==================================
		//==================================
		//==================================

        examplesPanel = new java.awt.Panel();
        add(examplesPanel);

		//==================================
		//==================================
		//==================================

		bottomPanel = new java.awt.Panel();
		bottomPanel.setLayout(new GridLayout(1,1));
		add(bottomPanel);

        fLay.setConstraint(topPanel, new FrameConstraint(            0.0,0,  0.0,   0,      1.0,-10,      0.0, 150));
        fLay.setConstraint(examplesPanel, new FrameConstraint(       0.0,0,  0.0, 150,      1.0,-10,      0.25,150));
        fLay.setConstraint(bottomPanel, new FrameConstraint(         0.0,0,  0.25,150,      1.0,  0,       1.0,  0));

        //======================================================
        //======================================================
        //======================================================

        databasePanel = new java.awt.Panel();
            fLay = new FractionalLayout();
            databasePanel.setLayout(fLay);
        		label7 = new java.awt.Label("Database");
    		        label7.setFont(new Font("Dialog", Font.BOLD, 14));
                    fLay.setConstraint(label7, new FrameConstraint(  0.0,10,  0.0,0,      1.0,-10,      0.0,40));
        	        databasePanel.add(label7);

                int leftWidth = 90;
                int indentWidth    = 10;

/*
                Toolkit tk     = Toolkit.getDefaultToolkit();
                Font font      = new Font("Dialog", Font.BOLD, 14); //databasePanel.getFont();
                FontMetrics fm = tk.getFontMetrics(font);
                int fontWidth  = fm.stringWidth("Name/URL:");
System.out.println(fontWidth);
*/

            		label9 = new java.awt.Label("Driver:",Label.RIGHT);
            		driverChoices = new java.awt.Choice();
            		productLabel = new java.awt.Label("Product:",Label.RIGHT);
            		dbChoices = new java.awt.Choice();
            		label5 = new java.awt.Label("Name/URL:",Label.RIGHT);
                 	dbUrlField = new java.awt.TextField();
            		dbDriverCLabel = new java.awt.Label(" Driver Class:",Label.RIGHT);
                 	dbDriverCField = new java.awt.TextField();
         	        dbDriverDisableEscapesCheckbox = new java.awt.Checkbox("Disable Escapes");
         	        dbDriverGenerateIndexesCheckbox = new java.awt.Checkbox("Generate Indexes");
         	        
        		addTo_layout_label_component_index_leftWidth(databasePanel,fLay,label9,driverChoices,0,leftWidth);
        		addTo_layout_label_component_index_leftWidth(databasePanel,fLay,productLabel,dbChoices,1,leftWidth);
        		addTo_layout_label_component_index_leftWidth(databasePanel,fLay,dbDriverCLabel,dbDriverCField,1,leftWidth);
        		addTo_layout_label_component_index_leftWidth(databasePanel,fLay,label5,dbUrlField,2,leftWidth);
        		addTo_layout_label_component_index_leftWidth(databasePanel,fLay,null,dbDriverDisableEscapesCheckbox,3,leftWidth+indentWidth);
        		addTo_layout_label_component_index_leftWidth(databasePanel,fLay,null,dbDriverGenerateIndexesCheckbox,4,leftWidth+indentWidth);
        	databasePanel.setBounds(48,36,216,100);
        	topPanel.add(databasePanel);

            productLabel.setVisible(true);
            dbChoices.setVisible(true);
            dbDriverCLabel.setVisible(false);
            dbDriverCField.setVisible(false);
            dbDriverDisableEscapesCheckbox.setVisible(false);
            dbDriverGenerateIndexesCheckbox.setVisible(false);

        //======================================================
        //======================================================
        //======================================================

        loginPanel = new java.awt.Panel();
            fLay = new FractionalLayout();
            loginPanel.setLayout(fLay);
        		label10 = new java.awt.Label("Login");
    		        label10.setFont(new Font("Dialog", Font.BOLD, 14));
                    fLay.setConstraint(label10, new FrameConstraint(  0.0,10,  0.0,0,      1.0,-10,      0.0,40));
        	        loginPanel.add(label10);
                //int leftWidth = 72;
        		label3 = new java.awt.Label("Name:",Label.RIGHT);
        		loginNameField = new java.awt.TextField();
        		label4 = new java.awt.Label("Password:",Label.RIGHT);
        		passwordField = new java.awt.TextField();
		        passwordField.setEchoChar('*');
        		testLoginButton = new java.awt.Button("Test Login");
        		
        		label4b = new java.awt.Label("Catalog:",Label.RIGHT);
        		catalogField = new java.awt.TextField();
        		label4c = new java.awt.Label("Scheme:",Label.RIGHT);
        		schemeField = new java.awt.TextField();
        		
        		addTo_layout_label_component_index_leftWidth(loginPanel,fLay,label3,loginNameField,0,leftWidth);
        		addTo_layout_label_component_index_leftWidth(loginPanel,fLay,label4,passwordField,1,leftWidth);
        		addTo_layout_label_component_index_leftWidth(loginPanel,fLay,null,testLoginButton,2,leftWidth);
        		addTo3_layout_label_component_index_leftWidth(loginPanel,fLay,label4b,catalogField,3,leftWidth);
        		addTo3_layout_label_component_index_leftWidth(loginPanel,fLay,label4c,schemeField,4,leftWidth);
        	topPanel.add(loginPanel);



        //======================================================
        //======================================================
        //======================================================

            fLay = new FractionalLayout();
            examplesPanel.setLayout(fLay);
    		label8 = new java.awt.Label("Examples");
        		label8.setFont(new Font("Dialog", Font.BOLD, 14));
                fLay.setConstraint(label8, new FrameConstraint(  0.0,10,  0.0,0,      1.0,-10,      0.0,40));
        		examplesPanel.add(label8);

        //======================================================
        //======================================================
        //======================================================

        examplesLeftPanel = new java.awt.Panel();
            fLay.setConstraint(examplesLeftPanel, new FrameConstraint(  0.0,10,  0.0,40,      0.5,0,      1.0,0));
        	examplesPanel.add(examplesLeftPanel);

        examplesRightPanel = new java.awt.Panel();
            examplesRightPanel.setLayout(null);
            fLay.setConstraint(examplesRightPanel, new FrameConstraint(  0.5,20,  0.0,40,      1.0,0,      1.0,0));
        	examplesPanel.add(examplesRightPanel);

        //======================================================
        //======================================================
        //======================================================


            fLay = new FractionalLayout();
            examplesLeftPanel.setLayout(fLay);
            		label6 = new java.awt.Label("Root Directory:",Label.RIGHT);
            		rootDirField = new java.awt.TextField();
            		label2 = new java.awt.Label("Scheme:",Label.RIGHT);
            		schemeChoices = new java.awt.Choice();
        		add2To_layout_label_component_index_leftWidth(examplesLeftPanel,fLay,label6,rootDirField,0,90);
        		add2To_layout_label_component_index_leftWidth(examplesLeftPanel,fLay,label2,schemeChoices,1,90);

		        //checkbox1 = new java.awt.Checkbox("Use Local Version");
                 //   fLay.setConstraint(checkbox1, new FrameConstraint(  0.0,10,  0.0,60,      1.0,-10,      0.0,80));
    		     //   examplesLeftPanel.add(checkbox1);
		        populateDataButton = new java.awt.Button("Populate Database");
                    fLay.setConstraint(populateDataButton, new FrameConstraint(  0.0,110,  0.0,50,      1.0,-20,      0.0,80));
        		    examplesLeftPanel.add(populateDataButton);

        //======================================================
        //======================================================
        //======================================================

            fLay = new FractionalLayout();
            examplesRightPanel.setLayout(fLay);
        		querySelectList = new java.awt.List(0,true);
                    fLay.setConstraint(querySelectList, new FrameConstraint(  0.0,0,  0.0,0,      1.0,0,      1.0,-50));
            		examplesRightPanel.add(querySelectList);
        		runQueryButton = new java.awt.Button("Run Examples");
                    fLay.setConstraint(runQueryButton, new FrameConstraint(  0.0,20,  1.0,-40,      0.5,-10,      1.0,-10));
        		    examplesRightPanel.add(runQueryButton);
        		//isTracing = new java.awt.Checkbox("Use Tracing");
                //    fLay.setConstraint(isTracing, new FrameConstraint(      0.5,10,  1.0,-40,       1.0,-20,      1.0,-10));
        		//    examplesRightPanel.add(isTracing);
        		traceChoice = new java.awt.Choice();
                    fLay.setConstraint(traceChoice, new FrameConstraint(      0.5,10,  1.0,-40,       1.0,-20,      1.0,-10));
        		    examplesRightPanel.add(traceChoice);


        //======================================================
        //======================================================
        //======================================================

		resultText = new java.awt.TextArea("",0,0,TextArea.SCROLLBARS_VERTICAL_ONLY);
		resultText.setEditable(false);
		bottomPanel.add(resultText);


		setTitle("FORM Example Runner");
		//}}

		//{{INIT_MENUS
		//}}

		//{{REGISTER_LISTENERS
		Window  lWindow = new Window();
		Item    lItem   = new Item();
		Focus   lFocus  = new Focus();
		Action  lAction = new Action();
		Component lComponent = new Component();

		addWindowListener(lWindow);
		addComponentListener(lComponent);

		querySelectList .addItemListener(lItem);
		runQueryButton  .addActionListener(lAction);
		schemeChoices   .addItemListener(lItem);
		loginNameField  .addFocusListener(lFocus);
		passwordField   .addFocusListener(lFocus);
		testLoginButton .addActionListener(lAction);
		catalogField  .addFocusListener(lFocus);
		schemeField   .addFocusListener(lFocus);

		rootDirField    .addFocusListener(lFocus);
		rootDirField    .addActionListener(lAction);

		dbUrlField            .addFocusListener(lFocus);
		dbDriverCField    .addFocusListener(lFocus);
		populateDataButton    .addActionListener(lAction);
		//dropDataButton        .addActionListener(lAction);
		driverChoices   .addItemListener(lItem);
		dbChoices       .addItemListener(lItem);
		//isTracing       .addItemListener(lItem);
		traceChoice     .addItemListener(lItem);
		//}}
    }

	//{{DECLARE_CONTROLS

	java.awt.Panel topPanel;
	java.awt.Panel databasePanel;
	java.awt.Panel loginPanel;

	java.awt.Panel examplesPanel;
	java.awt.Panel examplesLeftPanel;
	java.awt.Panel examplesRightPanel;

	java.awt.Panel bottomPanel;


	java.awt.Choice driverChoices;

	java.awt.Label productLabel;
	java.awt.Choice dbChoices;
	java.awt.TextField dbUrlField;

	java.awt.Label     dbDriverCLabel;
	java.awt.TextField dbDriverCField;
	java.awt.Checkbox  dbDriverDisableEscapesCheckbox;
	java.awt.Checkbox  dbDriverGenerateIndexesCheckbox;


	java.awt.TextField loginNameField;
	java.awt.TextField passwordField;
	java.awt.Button    testLoginButton;
	java.awt.TextField catalogField;
	java.awt.TextField schemeField;

	java.awt.Checkbox checkbox1;
	java.awt.TextField rootDirField;
	java.awt.Choice schemeChoices;
	java.awt.Button populateDataButton;
	java.awt.Button dropDataButton;

	java.awt.List       querySelectList;
	java.awt.Button     runQueryButton;
	java.awt.Checkbox   isTracing;
	java.awt.Choice     traceChoice;
	java.awt.TextArea   resultText;

	java.awt.Label label2;
	java.awt.Label label3;
	java.awt.Label label4;
	java.awt.Label label4b;
	java.awt.Label label4c;
	java.awt.Label label5;
	java.awt.Label label6;
	java.awt.Label label7;
	java.awt.Label label8;
	java.awt.Label label9;
	java.awt.Label label10;
	//}}

	//{{DECLARE_MENUS
	//}}

	class Window extends java.awt.event.WindowAdapter {
		public void windowClosing(java.awt.event.WindowEvent event) {
			Object object = event.getSource();
			if (object == ExampleRunnerWindow.this) {
				Frame1_WindowClosing(event);
			}
		}
	}

	class Component implements java.awt.event.ComponentListener {
		public void componentResized(java.awt.event.ComponentEvent event) {
		}

		public void componentMoved(java.awt.event.ComponentEvent event) {
		}

		public void componentShown(java.awt.event.ComponentEvent event) {
			Object object = event.getSource();
			if (object == ExampleRunnerWindow.this) {
				QueryFrame1_ComponentShown(event);
			}
		}

		public void componentHidden(java.awt.event.ComponentEvent event) {
		}
	}

	class Item implements java.awt.event.ItemListener {
		public void itemStateChanged(java.awt.event.ItemEvent event) {
			Object object = event.getSource();
			if (object == querySelectList) {
				querySelectList_ItemStateChanged(event);
			} else if (object == schemeChoices) {
				schemeChoice_ItemStateChanged(event);
			} else if (object == driverChoices) {
				driverChoices_ItemStateChanged(event);
			} else if (object == dbChoices) {
				dbChoices_ItemStateChanged(event);
			} else if (object == traceChoice) {
				traceChoice_ItemStateChanged(event);
			} else if (object == isTracing) {
				isTracing_ItemStateChanged(event);
		    }
		}
	}

	class Action implements java.awt.event.ActionListener {
		public void actionPerformed(java.awt.event.ActionEvent event) {
			Object object = event.getSource();
			if (object == runQueryButton) {
				runQueryButton_Clicked(event);
			} else if (object == populateDataButton) {
				populateDataButton_Clicked(event);
			} else if (object == testLoginButton) {
				testLoginButton_Clicked(event);
			} else if (object == rootDirField) {
				notifyRootDirFieldChanged();
			} else if (object == dropDataButton) {
				dropDataButton_Clicked(event);
		    }
		}
	}

	class Focus implements java.awt.event.FocusListener {
		public void focusGained(java.awt.event.FocusEvent event) {
		}

		public void focusLost(java.awt.event.FocusEvent event) {
			Object object = event.getSource();
			if (object == loginNameField) {
				loginNameField_LostFocus(event);
			} else if (object == passwordField) {
				passwordField_LostFocus(event);
			} else if (object == catalogField) {
				catalogField_LostFocus(event);
			} else if (object == schemeField) {
				schemeField_LostFocus(event);
			} else if (object == rootDirField) {
				notifyRootDirFieldChanged();
			} else if (object == dbDriverCField) {
				dbDriverCField_LostFocus(event);
			} else if (object == dbUrlField) {
				dbUrlField_LostFocus(event);
			}
		}
	}


    //==========================================================
    //==========================================================
    //==========================================================

    protected ExampleRunner application;

}
