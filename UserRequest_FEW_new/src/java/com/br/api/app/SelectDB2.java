/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app;

import com.br.api.app.connect.ConnectDB2;
import com.lowagie.tools.split_pdf;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;

/**
 *
 * @author Wattana
 */
public class SelectDB2 {

    public static JSONArray GET_REQNO() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        Locale.setDefault(Locale.US);
        Date d = new Date();
        SimpleDateFormat A = new SimpleDateFormat("yyyyMMdd");
        String USREQDT = A.format(d);

        String year = USREQDT.substring(2, 4);
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT SUBSTR('" + USREQDT + "',3,2) || MAX(SUBSTR(USREQNO,3,7))+1 AS reqno \n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE SUBSTR(USREQDT,3,2) =  '" + year + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("reqno", mRes.getString(1).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    NumberFormat df = new DecimalFormat("#,##0.00");

    
    
            
            public static int checkExistUserbyempcode(String empcode) throws Exception {

        int userCount = 0;
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COUNT(TRIM(ST_N6L3)) AS usercount FROM BRLDTA0100.STAFFLIST s\n" +
"                       WHERE  ST_CODE = '"+empcode.trim()+"'\n" +
"                       AND ST_N6L3  != '#N/A'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    userCount = mRes.getInt("usercount");

                }

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

        return userCount;

    }

    
    public static int checkExistUser(String username) throws Exception {

        int userCount = 0;
        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COUNT(TRIM(ST_N6L3)) AS usercount FROM BRLDTA0100.STAFFLIST s\n"
                        + "WHERE  ST_N6L3 = '"+username.trim()+"'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

                    userCount = mRes.getInt("usercount");

                }

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

        return userCount;

    }

    public static JSONArray Company() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT CCCONO,CCDIVI,RTRIM(CCTX15) as CCTX15,'\"'|| TRIM(CCCONO) || ' : ' || TRIM(CCDIVI) || ' : ' || TRIM(CCCONM) || '\"' AS COMPANY\n"
                        + "FROM M3FDBPRD.CMNDIV\n"
                        + "WHERE CCDIVI NOT IN ('','120','130')\n"
                        + "ORDER BY CCCONO";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("CCCONO", mRes.getString(1).trim());
                    mMap.put("CCDIVI", mRes.getString(2).trim());
                    mMap.put("CCTX15", mRes.getString(3).trim());
                    mMap.put("COMPANY", mRes.getString(4).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray costcenter(String cono) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  distinct eaaitm,eatx15  \n"
                        + "FROM m3fdbprd.fchacc \n"
                        + "WHERE eaaitp =  '2' \n"
                        + "and eacono = '" + cono + "'\n"
                        + "and eadivi NOT IN ('','120','130')\n"
                        + "and ealccd = '0' ";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("eaaitm", mRes.getString(1).trim());
                    mMap.put("eatx15", mRes.getString(2).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray MailCreator() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "Select ST_ENAME,ST_EMAIL\n"
                        + "FROM BRLDTA0100.STAFFLIST"
                        + "\n Where ST_COSTC = 'S8'\n"
                        + "AND ST_STS = '20' ";
//                        + "\n and ST_CODE IN ('0103464','010','0102906','0100694','0100530')";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("ST_ENAME", mRes.getString(1).trim());
                    mMap.put("ST_EMAIL", mRes.getString(2).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Costcenter(String cono, String divi) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT TRIM(EAAITM) as EAAITM ,TRIM(EATX40) as EATX40\n"
                        + "FROM M3FDBTST.FCHACC\n"
                        + "WHERE EACONO = '" + cono + "'\n"
                        + "  AND EADIVI = '" + divi + "'\n"
                        + "  AND EALCCD = 0\n"
                        + "  AND EAAITP = 2\n"
                        + "  ORDER BY EAAITM";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("EAAITM", mRes.getString(1).trim());
                    mMap.put("EATX40", mRes.getString(2).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public ResultSet SearchEmail(String cono, String divi, String ename) {

        try {
            ResultSet RsEmailTemplate;
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String sql = "SELECT US_NAME,US_ENAME,US_CONO,US_DIVI\n"
                    + "FROM BRLDTA0100.M3_USERNEW \n"
                    + "WHERE US_LOGIN = '" + ename + "'\n";
//                    + "and US_CONO = '" + cono + "'\n"
//                    + "and US_DIVI = '" + divi + "'";
            RsEmailTemplate = sta.executeQuery(sql);
            return RsEmailTemplate;

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    public static JSONArray Table_USRREQ(String USREQNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT USREQNO, USLINE, USCOMP, USCODE ,USNAME1, USNAME2,"
                        + " USMAIL, USCOSTC, USGROUP, USTYPE, USLIKE,"
                        + " USAFUNC, USDFUNC,  USREQDT, USEMCRE\n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE USREQNO = '" + USREQNO.trim() + "'\n"
                        + "ORDER BY USLINE ASC";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("USREQNO", mRes.getString(1).trim());
                    mMap.put("USLINE", mRes.getString(2).trim());
                    mMap.put("USCOMP", mRes.getString(3).trim());
                    mMap.put("USCODE", mRes.getString(4).trim());
                    mMap.put("USNAME1", mRes.getString(5).trim());
                    mMap.put("USNAME2", mRes.getString(6).trim());
                    mMap.put("USMAIL", mRes.getString(7).trim());
                    mMap.put("USCOSTC", mRes.getString(8).trim());
                    mMap.put("USGROUP", mRes.getString(9).trim());
                    mMap.put("USTYPE", mRes.getString(10).trim());
                    mMap.put("USLIKE", mRes.getString(11).trim());
                    mMap.put("USAFUNC", mRes.getString(12).trim());
                    mMap.put("USDFUNC", mRes.getString(13).trim());
                    mMap.put("USREQDT", mRes.getString(14).trim());
                    mMap.put("USEMCRE", mRes.getString(15).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Request_List() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT USREQNO, USLINE, USCOMP, USNAME1, USNAME2,"
                        + " USMAIL, USCOSTC, USGROUP, USTYPE, USLIKE,"
                        + " USAFUNC, USDFUNC,  USREQDT, USCODE, USREMK, TRIM(SUBSTRING(USNAME1,1,6)) || '_' || TRIM(SUBSTRING(USNAME2,1,3)) AS USERNAME,USEMCRE\n"
                        + ",SUBSTRING(USREQDT,1,4) || '-' || SUBSTRING(USREQDT,5,2) || '-' || SUBSTRING(USREQDT,7,2) AS DATE,ST_N6L3,USUREQ\n"
                        + "FROM BRLDTA0100.USR_REQUEST,BRLDTA0100.STAFFLIST\n"
                        + "WHERE USSTAT = '10'\n"
                        + "AND USCODE = ST_CODE\n"
                        + "ORDER BY USREQNO,USLINE";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("USREQNO", mRes.getString(1).trim());
                    mMap.put("USLINE", mRes.getString(2).trim());
                    mMap.put("USCOMP", mRes.getString(3).trim());
                    mMap.put("USNAME1", mRes.getString(4).trim());
                    mMap.put("USNAME2", mRes.getString(5).trim());
                    mMap.put("USMAIL", mRes.getString(6).trim());
                    mMap.put("USCOSTC", mRes.getString(7).trim());
                    mMap.put("USGROUP", mRes.getString(8).trim());
                    mMap.put("USTYPE", mRes.getString(9).trim());
                    mMap.put("USLIKE", mRes.getString(10).trim());
                    mMap.put("USAFUNC", mRes.getString(11).trim());
                    mMap.put("USDFUNC", mRes.getString(12).trim());
                    mMap.put("USREQDT", mRes.getString(13).trim());
                    mMap.put("USCODE", mRes.getString(14).trim());
                    mMap.put("USREMK", mRes.getString(15).trim());
                    mMap.put("USERNAME", mRes.getString(16).trim());
                    mMap.put("USEMCRE", mRes.getString(17).trim());
                    mMap.put("DATE", mRes.getString(18).trim());
                    mMap.put("STN6L3", mRes.getString(19).trim());
                    mMap.put("USUREQ", mRes.getString(20).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Request_List2(String USUREQ) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT USREQNO, USLINE, USCOMP, USNAME1, USNAME2,"
                        + " USMAIL, USCOSTC, USGROUP, USTYPE, USLIKE,"
                        + " USAFUNC, USDFUNC,  USREQDT, USCODE, USREMK, TRIM(SUBSTRING(USNAME1,1,6)) || '_' || TRIM(SUBSTRING(USNAME2,1,3)) AS USERNAME,USEMCRE\n"
                        + ",SUBSTRING(USREQDT,1,4) || '-' || SUBSTRING(USREQDT,5,2) || '-' || SUBSTRING(USREQDT,7,2) AS DATE\n"
                        + ",CASE WHEN USSTAT = 0 THEN 'Not send' WHEN USSTAT = 10 THEN 'Wait for create' WHEN USSTAT = 15 THEN 'Wait for acknowledge' WHEN USSTAT = 20 THEN 'Complete' ELSE '-' END AS Status \n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE USSTAT IN  ('00','15')\n"
                        + "and USUREQ = '" + USUREQ + "'\n"
                        + "ORDER BY USREQNO desc,USLINE";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("USREQNO", mRes.getString(1).trim());
                    mMap.put("USLINE", mRes.getString(2).trim());
                    mMap.put("USCOMP", mRes.getString(3).trim());
                    mMap.put("USNAME1", mRes.getString(4).trim());
                    mMap.put("USNAME2", mRes.getString(5).trim());
                    mMap.put("USMAIL", mRes.getString(6).trim());
                    mMap.put("USCOSTC", mRes.getString(7).trim());
                    mMap.put("USGROUP", mRes.getString(8).trim());
                    mMap.put("USTYPE", mRes.getString(9).trim());
                    mMap.put("USLIKE", mRes.getString(10).trim());
                    mMap.put("USAFUNC", mRes.getString(11).trim());
                    mMap.put("USDFUNC", mRes.getString(12).trim());
                    mMap.put("USREQDT", mRes.getString(13).trim());
                    mMap.put("USCODE", mRes.getString(14).trim());
                    mMap.put("USREMK", mRes.getString(15).trim());
                    mMap.put("USERNAME", mRes.getString(16).trim());
                    mMap.put("USEMCRE", mRes.getString(17).trim());
                    mMap.put("DATE", mRes.getString(18).trim());
                    mMap.put("Status", mRes.getString(19).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray TrackStatus() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT USREQNO, USLINE, USCOMP, USNAME1, USNAME2,"
                        + " USMAIL, USCOSTC, USGROUP, USTYPE, USLIKE,"
                        + " USAFUNC, USUREQ,  SUBSTRING(USREQDT,1,4) || '-' || SUBSTRING(USREQDT,5,2) || '-' || SUBSTRING(USREQDT,7,2) AS USREQDT, USCODE, USREMK, USEMCRE\n"
                        + ",CASE WHEN USSTAT = 0 THEN 'Not send' WHEN USSTAT = 10 THEN 'Wait for create' WHEN USSTAT = 15 THEN 'Wait for acknowledge' WHEN USSTAT = 20 THEN 'Complete' ELSE '-' END AS USSTAT \n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE USSTAT != '99'\n"
                        + "ORDER BY USREQNO DESC,USLINE";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("USREQNO", mRes.getString(1).trim());
                    mMap.put("USLINE", mRes.getString(2).trim());
                    mMap.put("USCOMP", mRes.getString(3).trim());
                    mMap.put("USNAME1", mRes.getString(4).trim());
                    mMap.put("USNAME2", mRes.getString(5).trim());
                    mMap.put("USMAIL", mRes.getString(6).trim());
                    mMap.put("USCOSTC", mRes.getString(7).trim());
                    mMap.put("USGROUP", mRes.getString(8).trim());
                    mMap.put("USTYPE", mRes.getString(9).trim());
                    mMap.put("USLIKE", mRes.getString(10).trim());
                    mMap.put("USAFUNC", mRes.getString(11).trim());
                    mMap.put("USUREQ", mRes.getString(12).trim());
                    mMap.put("USREQDT", mRes.getString(13).trim());
                    mMap.put("USCODE", mRes.getString(14).trim());
                    mMap.put("USREMK", mRes.getString(15).trim());

                    String[] Creator = mRes.getString(16).trim().split("@");
                    mMap.put("USEMCRE", Creator[0].toUpperCase());
                    mMap.put("USSTAT", mRes.getString(17).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray GetMaxLine(String USREQNO) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT MAX(USLINE)+1 AS LINE\n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE USREQNO = '" + USREQNO + "'";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("LINE", mRes.getString(1).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Data_Acknowledge(String USREQNO, String USLINE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT USREQNO, USLINE, USCOMP, USCODE ,USNAME1, USNAME2, USMAIL, USGROUP, USTYPE,\n"
                        + "USLIKE, USAFUNC, USDFUNC,  USREQDT, USEMCRE,\n"
                        + "TRIM(SUBSTRING(USNAME1,1,6)) || '_' || TRIM(SUBSTRING(USNAME2,1,3)) AS username, CCCONO || ' : ' || CCTX15 AS COMPANY\n"
                        + ",USREMK,USCREBY\n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "LEFT JOIN M3FDBPRD.CMNDIV ON CCDIVI NOT IN ('120','130','') AND CCCONO = USCOMP\n"
                        + "LEFT JOIN m3fdbprd.fchacc ON eaaitp =  2 AND USCOSTC = EAAITM AND USCOMP = EACONO\n"
                        + "WHERE USREQNO = '" + USREQNO.trim() + "'\n"
                        + "AND USLINE = '" + USLINE.trim() + "'"
                        + "and USSTAT = '15'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("USREQNO", mRes.getString(1).trim());
                    mMap.put("USLINE", mRes.getString(2).trim());
                    mMap.put("USCOMP", mRes.getString(3).trim());
                    mMap.put("USCODE", mRes.getString(4).trim());
                    mMap.put("USNAME1", mRes.getString(5).trim());
                    mMap.put("USNAME2", mRes.getString(6).trim());
                    mMap.put("USMAIL", mRes.getString(7).trim());
                    mMap.put("USGROUP", mRes.getString(8).trim());
                    mMap.put("USTYPE", mRes.getString(9).trim());
                    mMap.put("USLIKE", mRes.getString(10).trim());
                    mMap.put("USAFUNC", mRes.getString(11).trim());
                    mMap.put("USDFUNC", mRes.getString(12).trim());
                    mMap.put("USREQDT", mRes.getString(13).trim());
                    mMap.put("USEMCRE", mRes.getString(14).trim());
                    mMap.put("username", mRes.getString(15).trim());
                    mMap.put("COMPANY", mRes.getString(16).trim());
                    mMap.put("USREMK", mRes.getString(17).trim());
                    mMap.put("USCREBY", mRes.getString(18).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray DetailUser(String ST_CODE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  ST_CONO,ST_N6L3,  ST_EMAIL, ST_COSTC,"
                        + "ST_CODE, ST_EPNM, ST_ENAME, ST_ELNAME, ST_TPNM, "
                        + "ST_TNAME, ST_TLNAME\n"
                        + "FROM BRLDTA0100.STAFFLIST\n"
                        + "where ST_CODE = '" + ST_CODE + "'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("ST_CONO", mRes.getString(1).trim());
                    mMap.put("ST_N6L3", mRes.getString(2).trim());
                    mMap.put("ST_EMAIL", mRes.getString(3).trim());
                    mMap.put("ST_COSTC", mRes.getString(4).trim());
                    mMap.put("ST_CODE", mRes.getString(5).trim());
                    mMap.put("ST_EPNM", mRes.getString(6).trim());
                    mMap.put("ST_ENAME", mRes.getString(7).trim());
                    mMap.put("ST_ELNAME", mRes.getString(8).trim());
                    mMap.put("ST_TPNM", mRes.getString(9).trim());
                    mMap.put("ST_TNAME", mRes.getString(10).trim());
                    mMap.put("ST_TLNAME", mRes.getString(11).trim());

                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray Search(String ST_CODE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {
                String dump = "ST_CODE";
                Statement stmt = conn.createStatement();
                String query = "SELECT ST_CONO, ST_N6L3, ST_M3, ST_EMAIL,"
                        + " ST_COSTC, ST_CODE, ST_EPNM, ST_ENAME, ST_ELNAME, "
                        + "ST_TPNM, ST_TNAME, ST_TLNAME, ST_POSITON, COALESCE(ST_LEVEL,'') AS ST_LEVEL,"
                        + "ST_DISM3, ST_DISMAIL, ST_STDT,ST_REDT, ST_SIGN, "
                        + "ST_STS\n"
                        + "FROM BRLDTA0100.STAFFLIST\n"
                        + "where ST_CODE =  '" + ST_CODE + "'";

//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();

//                    String year = ST_STDT.substring(0, 4).trim();
//                    String month = ST_STDT.substring(4, 6).trim();
//                    String day = ST_STDT.substring(6, 8).trim();
//                    ST_STDT = year + "-" + month + "-" + day;
                    String ST_STDT = SetDate(mRes.getString(17).trim());
                    String ST_DISM3 = SetDate(mRes.getString(15).trim());
                    String ST_DISMAIL = SetDate(mRes.getString(16).trim());
                    String ST_REDT = SetDate(mRes.getString(18).trim());

                    mMap.put("ST_CONO", mRes.getString(1).trim());
                    mMap.put("ST_N6L3", mRes.getString(2).trim());
                    mMap.put("ST_M3", mRes.getString(3).trim());
                    mMap.put("ST_EMAIL", mRes.getString(4).trim());
                    mMap.put("ST_COSTC", mRes.getString(5).trim());

                    mMap.put("ST_CODE", mRes.getString(6).trim());
                    mMap.put("ST_EPNM", mRes.getString(7).trim());
                    mMap.put("ST_ENAME", mRes.getString(8).trim());
                    mMap.put("ST_ELNAME", mRes.getString(9).trim());
                    mMap.put("ST_TPNM", mRes.getString(10).trim());
                    mMap.put("ST_TNAME", mRes.getString(11).trim());
                    mMap.put("ST_TLNAME", mRes.getString(12).trim());
                    mMap.put("ST_POSITON", mRes.getString(13).trim());
                    mMap.put("ST_LEVEL", mRes.getString(14).trim());
                    mMap.put("ST_DISM3", ST_DISM3);
                    mMap.put("ST_DISEMAIL", ST_DISMAIL);
                    mMap.put("ST_STDT", ST_STDT);
                    mMap.put("ST_REDT", ST_REDT);

//                    System.out.print(ST_DISM3 + ST_DISMAIL + ST_STDT + ST_REDT);
                    mMap.put("ST_SIGN", "data:image/png;base64," + mRes.getString(19));
                    mMap.put("ST_STS", mRes.getString(20).trim());
                    mJSonArr.put(mMap);
                }

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

        return mJSonArr;

    }

    public static JSONArray Check_Duplicate(String USREQNO, String USCOMP, String USCODE, String USGROUP, String USTYPE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT COUNT(*) AS Duplicate\n"
                        + "FROM BRLDTA0100.USR_REQUEST\n"
                        + "WHERE USREQNO = '" + USREQNO + "'\n"
                        + "AND USCOMP = '" + USCOMP + "'\n"
                        + "AND USCODE = '" + USCODE + "'\n"
                        + "AND USGROUP = '" + USGROUP + "'\n"
                        + "AND USTYPE = '" + USTYPE + "'\n";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("Duplicate", mRes.getString(1).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray EmpCodeList() throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "Select RTRIM(ST_CODE) as ST_CODE\n"
                        + "from BRLDTA0100.stafflist\n"
                        + "where ST_STS = '20'"
                        + "order by ST_CODE";
//                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    mMap.put("ST_CODE", mRes.getString(1).trim());
                    mJSonArr.put(mMap);

                }

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

        return mJSonArr;

    }

    public static JSONArray getuseremail(String username) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  ST_EMAIL FROM BRLDTA0100.STAFFLIST WHERE  ST_CONO = '10' AND  ST_N6L3 = '" + username.trim() + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    String[] File = mRes.getString(1).split("-");
                    mMap.put("USFILE", File[0]);
                    mMap.put("TYPE", File[1]);
                    mJSonArr.put(mMap);
                }
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
        return mJSonArr;
    }

    public static String getEncyptKey(String encrytcode) throws Exception {

        String prikey = "";
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT   EDPRIKEY FROM BRLDTA0100.M3_STORAGEEMAILSEND WHERE   EDPUBKEY = '" + encrytcode + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    prikey = mRes.getString("EDPRIKEY");
                }

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
        return prikey;
    }

    public static String getprikey(String encrytcode) throws Exception {

        String pkey = "";
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "        SELECT PRIKEY\n"
                        + "FROM M3FDBTST.LNGPIT\n"
                        + "\n"
                        + "WHERE  TSLGID  = '" + encrytcode + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    pkey = mRes.getString("PRIKEY");
                }
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
        return pkey;
    }

    public static String getprivatekey(String encrytcode) throws Exception {

        String pkey = "";
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "      SELECT EDPRIKEY\n"
                        + "FROM BRLDTA0100.M3_STORAGEEMAILSEND\n"
                        + "WHERE EDPUBKEY = '" + encrytcode + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    pkey = mRes.getString("EDPRIKEY");
                }
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
        return pkey;
    }

    public static int Checkprivatekey(String encrytcode) throws Exception {

        int hasKey = 0;
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT   COUNT(EDPRIKEY)AS HASKEY FROM BRLDTA0100.M3_STORAGEEMAILSEND WHERE   EDPUBKEY = '" + encrytcode + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    hasKey = mRes.getInt("HASKEY");
                }
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
        return hasKey;
    }

    public static JSONArray checkusername(String username) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        int num = 0;
        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT  COUNT(ST_N6L3) AS num  FROM BRLDTA0100.STAFFLIST WHERE  ST_STS = '20' AND  ST_N6L3 = '" + username.trim() + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    String[] File = mRes.getString(1).split("-");
                    mMap.put("num", File[0]);
                    mMap.put("username", username);
                    mJSonArr.put(mMap);

                }
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
        return mJSonArr;
    }

    public static JSONArray ShowFile(String USREQNO, String USLINE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();

        try {
            if (conn != null) {

                Statement stmt = conn.createStatement();
                String query = "SELECT USFILE FROM BRLDTA0100.USR_REQUEST WHERE USREQNO = '" + USREQNO.trim() + "' and USLINE = '" + USLINE.trim() + "'";
                System.out.println(query);
                ResultSet mRes = stmt.executeQuery(query);

                while (mRes.next()) {
                    Map<String, Object> mMap = new HashMap<>();
                    String[] File = mRes.getString(1).split("-");
                    mMap.put("USFILE", File[0]);
                    mMap.put("TYPE", File[1]);
                    mJSonArr.put(mMap);
                }
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
        return mJSonArr;
    }

    public static JSONArray CheckUserM3(String USCODE, String USCOMP, String USGROUP, String USTYPE, String USLIKE) throws Exception {

        JSONArray mJSonArr = new JSONArray();
        Connection conn = ConnectDB2.ConnectionDB();
        USGROUP = USGROUP.trim();
        try {
            if (conn != null) {
                if (USGROUP.equals("EPR") || USGROUP.equals("ESR")) {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT COUNT(*) AS CheckUserM3\n"
                            + "FROM BRLDTA0100.USR_REQUEST\n"
                            + "WHERE USCODE = '" + USCODE + "'\n"
                            + "AND USCOMP = '" + USCOMP + "'\n"
                            + "AND USGROUP = '" + USGROUP + "'\n"
                            + "AND USTYPE = 'New'\n"
                            + "AND USLIKE = '" + USLIKE + "'\n"
                            + "AND USSTAT IN ('10','15','20')";
                    System.out.println(query);
                    ResultSet mRes = stmt.executeQuery(query);
                    while (mRes.next()) {
                        Map<String, Object> mMap = new HashMap<>();
                        mMap.put("CheckUserM3", mRes.getString(1).trim());
                        mJSonArr.put(mMap);
                    }

                } else {
                    Statement stmt = conn.createStatement();
                    String query = "SELECT COUNT(*) AS CheckUserM3\n"
                            + "FROM BRLDTA0100.USR_REQUEST\n"
                            + "WHERE USCODE = '" + USCODE + "'\n"
                            + "AND USCOMP = '" + USCOMP + "'\n"
                            + "AND USGROUP = '" + USGROUP + "'\n"
                            + "AND USTYPE = 'New'\n"
                            + "AND USSTAT IN ('10','15','20')";
                    System.out.println(query);
                    ResultSet mRes = stmt.executeQuery(query);
                    while (mRes.next()) {
                        Map<String, Object> mMap = new HashMap<>();
                        mMap.put("CheckUserM3", mRes.getString(1).trim());
                        mJSonArr.put(mMap);
                    }
                }
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

        return mJSonArr;

    }

    public static String SetDate(String date) {

        String Newdate = "";
        String year = "";
        String month = "";
        String day = "";

        if (date.equals("0")) {
            date = "00000000";
            year = date.substring(0, 4).trim();
            month = date.substring(4, 6).trim();
            day = date.substring(6, 8).trim();
            Newdate = year + "-" + month + "-" + day;
            return Newdate;
        } else {
            date = date;
            year = date.substring(0, 4).trim();
            month = date.substring(4, 6).trim();
            day = date.substring(6, 8).trim();
            Newdate = year + "-" + month + "-" + day;
            return Newdate;
        }

    }

}
