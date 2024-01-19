/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;

import com.br.api.app.connect.ConnectDB2;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Wattana
 */
public class InsertDB2 {

    public static void InsertRequest(String USREQNO, String USLINE, String USCOMP, String USNAME1, String USNAME2, String USCODE, String USMAIL, String USGROUP,
            String USTYPE, String USLIKE, String USAFUNC, String USDFUNC, String USUREQ, String USEMCRE) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        Locale.setDefault(Locale.US);
        Date d = new Date();
        SimpleDateFormat A = new SimpleDateFormat("yyyyMMdd");
        String USREQDT = A.format(d);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.USR_REQUEST\n"
                        + "(USREQNO, USLINE, USCOMP, USNAME1, USNAME2, USCODE,\n"
                        + "USMAIL, USCOSTC, USGROUP, USTYPE, USLIKE,\n"
                        + "USAFUNC, USDFUNC, USUREQ, USREQDT, USCREBY, \n"
                        + "USCREDT, USACKBY, USACKDT, USREMK, USSTAT, USEMCRE)\n"
                        + "VALUES('" + USREQNO + "', '" + USLINE + "', '" + USCOMP + "', '" + USNAME1.toUpperCase() + "', '" + USNAME2.toUpperCase() + "', '" + USCODE + "',\n"
                        + "'" + USMAIL + "', '-', '" + USGROUP + "', '" + USTYPE + "', '" + USLIKE + "',\n"
                        + "'" + USAFUNC + "', '" + USDFUNC + "', '" + USUREQ + "', '" + USREQDT + "', ' ',\n"
                        + "'0', ' ', '0', ' ', '00', '" + USEMCRE + "')";
//                System.out.print(query);
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


    /*
    
    EDDOCUMENT
    EDREFNO
    ESENTTO
    ESENTCC
    ESENTFROM
    EDSUBJECT
    EDDETAIL
    EDSTATUSNO
    CREATEBY
    ECONO
    EDIVISION
    SENTDATE
    SENTTIME
    
    
    
    */

     public static void InsertMailLog(String eddocument, String edrefno, String esentto, String esentcc, String esentfrom,
             String edsubject, String eddetail, String createby, int econo,
              String edpubkey, String edprikey, java.sql.Date d , java.sql.Time t) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();


        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO BRLDTA0100.M3_STORAGEEMAILSEND (EDDOCUMENT, EDREFNO, ESENTTO, ESENTCC, ESENTFROM, EDSUBJECT, EDDETAIL, CREATEBY, ECONO,EDPUBKEY, EDPRIKEY, SENTDATE , SENTTIME  )\n" +
                                "VALUES ('"+eddocument+"', '"+edrefno+"', '"+esentto+"','"+esentcc+"', '"+esentfrom+"', '"+edsubject+"', '"+eddetail+"', '"+createby+"', '"+econo+"',  '"+edpubkey+"', '"+edprikey+"','"+d+"','"+t+"')";
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
     
     public static void InsertPrikey(String encode, String prikey) throws Exception {

        Connection conn = ConnectDB2.ConnectionDB();

        Locale.setDefault(Locale.US);
         Date d = new Date();
         SimpleDateFormat A = new SimpleDateFormat("yyyyMMdd");
         SimpleDateFormat B = new SimpleDateFormat("HH:mm:ss");
//        sentdate = A.format(d);
//        senttime = B.format(d);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "INSERT INTO M3FDBTST.LNGPIT (TSLGID, PRIKEY)\n" +
                                "VALUES ('"+encode+"', '"+prikey+"')";
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

}
