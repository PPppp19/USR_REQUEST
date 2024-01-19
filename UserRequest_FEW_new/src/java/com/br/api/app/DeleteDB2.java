/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;

import com.br.api.app.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Wattana
 */
public class DeleteDB2 {

    public static void DeleteLine(String USREQNO, String USLINE) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                Statement stmt1 = conn.createStatement();
                String query1 = "DELETE FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE USREQNO = '" + USREQNO + "' AND USLINE = '" + USLINE + "' ";
                stmt1.execute(query1);

            } else {
                System.out.println("Server can't connect.");
            }

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) {
                conn.close();
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        updateline(USREQNO, USLINE);

    }

    public static void updateline(String USREQNO, String USLINE) {

        try {
            Connection conn = ConnectDB2.RUNConnectionDB();
            PreparedStatement PreUpdateLine = null;

            String StringUpdateLineNo = "SELECT USREQNO,USLINE AS num\n"
                    + "FROM  BRLDTA0100.USR_REQUEST\n"
                    + "WHERE USREQNO = '" + USREQNO + "'\n"
                    + "ORDER BY USLINE";

            PreUpdateLine = conn.prepareStatement(StringUpdateLineNo);

            ResultSet rsupdateLine = PreUpdateLine.executeQuery(StringUpdateLineNo);

            int Noline = 1;
            while (rsupdateLine.next()) {

                try {
                    PreparedStatement PreUpdateLines = null;
                    String LinePresent = rsupdateLine.getString("num");
                    String UpdateLineString = "UPDATE BRLDTA0100.USR_REQUEST \n"
                            + "SET USLINE ='" + Noline + "'\n"
                            + "WHERE  USREQNO = '" + USREQNO + "'\n"
                            + "AND USLINE = '" + LinePresent + "'";
                    PreUpdateLines = conn.prepareStatement(UpdateLineString);
                    PreUpdateLines.execute(UpdateLineString);
                    Noline++;
                } catch (Exception e) {
                  System.out.println(e.toString());
                }

//                LineNumbers = rs1.getString("LINENUM");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

}
