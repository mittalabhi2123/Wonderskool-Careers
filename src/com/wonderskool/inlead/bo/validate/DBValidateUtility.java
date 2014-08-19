package com.wonderskool.inlead.bo.validate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;

import com.wonderskool.bo.utility.Utility;

public class DBValidateUtility
{
  public static boolean validUserAccess(String function, JLabel errMsgLbl)
  {
    if (!Utility.getjdbcconnection(errMsgLbl))
      return false;
    boolean access = false;
    boolean isAdmin = false;
    try {
      PreparedStatement userAccessStmt = Utility.conn.prepareStatement("SELECT is_admin,~ FROM user_tbl WHERE LOWER(user_id) = ?".replaceAll("~", function));
      userAccessStmt.setString(1, Utility.userName.toLowerCase());
      ResultSet dataSet = userAccessStmt.executeQuery();
      Utility.finishjdbcconnection();
      if (dataSet.next()) {
        isAdmin = dataSet.getInt(1) == 1;
        access = dataSet.getInt(2) == 1;
      }
      if (isAdmin)
        return true;
      if (!access) {
        errMsgLbl.setText("Error: Access denied. Please contact admin!!!");
        return false;
      }
      return true;
    }
    catch (Exception e) {
      errMsgLbl.setText("Error:" + e.getMessage());
    }
    return true;
  }
}