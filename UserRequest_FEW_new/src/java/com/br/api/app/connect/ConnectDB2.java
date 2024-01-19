/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Wattana
 */
public class ConnectDB2 {

    public static String ErrorLogs;

    public static Connection ConnectionDB() throws Exception {

        String jdbcClassName = "com.ibm.as400.access.AS400JDBCDriver";
        String url = "jdbc:as400://192.200.9.190;";
        //String url = "jdbc:jtopenlite://192.200.9.190;data truncation=false;";

        Class.forName(jdbcClassName);
        return DriverManager.getConnection(url, "M3SRVICT", "ICT12345");
    }
    
    
      public static Connection RUNConnectionDB() throws Exception {

        String jdbcClassName = "com.ibm.jtopenlite.database.jdbc.JDBCDriver";
        String url = "jdbc:jtopenlite://192.200.9.190";
        //String url = "jdbc:jtopenlite://192.200.9.190;data truncation=false;";

        Class.forName(jdbcClassName);
        return DriverManager.getConnection(url, "M3SRVICT", "ICT12345");
    }

    public static Connection servers111() throws Exception {

        String jdbcClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://192.200.9.111:1433;database=XIQMAOWADB;";

        Class.forName(jdbcClassName);
        return DriverManager.getConnection(url, "sa", "sa");
    }

    public static Connection LoginDB(String user, String pass) throws Exception {
        try {
          String jdbcClassName = "com.ibm.jtopenlite.database.jdbc.JDBCDriver";
            String url = "jdbc:jtopenlite://192.200.9.190";
            Class.forName(jdbcClassName);
            return DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException e) {
            ErrorLogs = e.toString();
            return null;
        }

    }

}
