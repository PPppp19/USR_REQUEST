/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;

import com.br.api.app.connect.ConnectDB2;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Wattana
 */
public class UpdateDB2 {

    
    
            
            
            
            public static void UpdateREQ(String username, String REQNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "  UPDATE BRLDTA0100.USR_REQUEST\n" +
"                                SET USUREQ = '"+username.trim()+"  '\n" +
"                                WHERE USUREQ = 'M3SRVICT'\n" +
"                                AND USREQNO = '"+REQNO.trim()+"'\n" +
"                                AND USGROUP = 'AddOnM3''";
                stmt.execute(query);

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

    }

    public static void Update_LineReq(String USREQNO, String USLINE, String USCOMP, String USNAME1, String USNAME2,
            String USMAIL, String USCODE,  String USGROUP, String USTYPE, String USLIKE, String USAFUNC, String USDFUNC, String USEMCRE) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.USR_REQUEST\n"
                        + "SET USCOMP = '" + USCOMP + "', USNAME1='" + USNAME1 + "', USNAME2='" + USNAME2 + "', USMAIL='" + USMAIL + "', USCODE = '" + USCODE + "',"
                        + "USGROUP='" + USGROUP + "', USTYPE='" + USTYPE + "', USLIKE='" + USLIKE + "', USAFUNC='" + USAFUNC + "', USDFUNC='" + USDFUNC + "', USEMCRE = '" + USEMCRE + "'\n"
                        + "WHERE USREQNO = '" + USREQNO + "' AND USLINE = '" + USLINE + "'";
                stmt.execute(query);

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

    }

    public static void Acknowledge(String USREQNO, String USLINE, String USACKBY) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        Locale.setDefault(Locale.US);
        Date d = new Date();
        SimpleDateFormat A = new SimpleDateFormat("yyyyMMdd");
        String USACKDT = A.format(d);

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.USR_REQUEST\n"
                        + "SET USSTAT = '20', USACKBY = '" + USACKBY + "' ,USACKDT = '" + USACKDT + "'\n"
                        + "WHERE USREQNO = '" + USREQNO + "'\n"
                        + "AND USLINE = '" + USLINE + "' \n"
                        + "AND USSTAT = '15'";
                System.out.print(query);
                stmt.execute(query);
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

    }

    public static void SendToCreate(String USREQNO) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.USR_REQUEST\n"
                        + "SET USSTAT = '10'\n"
                        + "WHERE USREQNO = '" + USREQNO + "'";
                stmt.execute(query);

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

    }

    public static void UpdateCreated(String USREQNO, String USLINE, String USREMK, String USCREBY) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();
        Locale.setDefault(Locale.US);
        Date d = new Date();
        SimpleDateFormat A = new SimpleDateFormat("yyyyMMdd");
        String USCREDT = A.format(d);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "UPDATE BRLDTA0100.USR_REQUEST\n"
                        + "SET   USREMK = '" + USREMK + "', USCREDT = '" + USCREDT + "', USCREBY = '" + USCREBY + "'\n"
                        + "WHERE USREQNO = '" + USREQNO + "'\n"
                        + "AND USLINE = '" + USLINE + "'";
                stmt.execute(query);
                System.out.println(query);

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

    }

}
