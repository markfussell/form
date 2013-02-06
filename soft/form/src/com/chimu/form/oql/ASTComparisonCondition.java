/* JJT: 0.2.2 */
/*======================================================================
**
**  File: chimu/form/oql/ASTComparisonCondition.java
**
**  Copyright (c) 1997-2000, ChiMu Corporation. All Rights Reserved.
**  See the file COPYING for copying permission.
**
======================================================================*/

package com.chimu.form.oql;
import com.chimu.form.query.*;
import com.chimu.kernel.exceptions.*;


/*package*/ class ASTComparisonCondition extends NamedNodeAbsC {
  ASTComparisonCondition(String id) {
    super(id);
  }

  public static Node jjtCreate(String id) {
    return new ASTComparisonCondition(id);
  }

    //**********************************************************
    //(P)******************* Manual Code ***********************
    //**********************************************************

    //
    public Condition newConditionFor_using(OqlQueryPi oql, QueryDescription query) {
        QueryExpression left   = ((SimpleNode) children.elementAt(0)).newExpressionFor_using(oql,query);
        SimpleNode      rightNode  = (SimpleNode) children.elementAt(1);
        QueryExpression right      = rightNode.newExpressionFor_using(oql,query);

        if (name.equals("=")) {
            if (right == null) {
                return query.newNull(left);
            } else {
                return query.newEqualTo(left,right);
            }
        } else if (name.equals("!=")) {
            if (right == null) {
                return query.newNotNull(left);
            } else {
                return query.newNotEqualTo(left,right);
            }
        } else if (name.equals("<>")) {
            return query.newNotEqualTo(left,right);
        } else if (name.equals("<")) {
            return query.newLessThan(left,right);
        } else if (name.equals("<=")) {
            return query.newLessThanEqualTo(left,right);
        } else if (name.equals(">")) {
            return query.newGreaterThan(left,right);
        } else if (name.equals(">=")) {
            return query.newGreaterThanEqualTo(left,right);
        } else {
            throw new NotImplementedYetException("Condition "+name+" is not yet supported");
        }
    }

}
