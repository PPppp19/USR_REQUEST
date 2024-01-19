/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.util.codec.binary.Base64;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

import com.br.api.app.connect.ConnectDB2;
import com.br.api.app.BlobGen;
import groovy.json.StringEscapeUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import static java.lang.System.in;

import java.sql.Blob;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.Statement;
import static java.sql.Types.CLOB;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import org.apache.tomcat.jni.File;
import org.apache.tomcat.util.http.fileupload.FileUtils;

/**
 *
 * @author ACHARD
 */
@WebServlet(name = "UploadStaff", urlPatterns = {"/UploadStaff"})
@MultipartConfig

public class UploadStaff extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("Add").equals("AddStaff")) {
            String EMPCODE = request.getParameter("EMPCODE");
            String ST_STDT = request.getParameter("ST_STDT");
            String EPNM = request.getParameter("EPNM");
            String ENAME = request.getParameter("ENAME").toUpperCase();
            String ELNAME = request.getParameter("ELNAME").toUpperCase();
            String COMPANY = request.getParameter("COMPANY");
            String COSTCENTER = request.getParameter("COSTCENTER");
            String TPNM = request.getParameter("TPNM");
            String TNAME = request.getParameter("TNAME");
            String TLNAME = request.getParameter("TLNAME");
            String EMAIL = request.getParameter("EMAIL");
            String POSITION = request.getParameter("POSITION").toUpperCase();
            String LEVEL = request.getParameter("LEVEL").toUpperCase();
            String STATUS = request.getParameter("STATUS");
            String ST_M3 = request.getParameter("ST_M3").toUpperCase().trim();

            String ST_N6L3 = "";
            ST_N6L3 = ENAME.substring(0, 6).toUpperCase() + "_" + ELNAME.substring(0, 3).toUpperCase();
            InputStream inputStream = null; // input stream of the upload file
            Part filePart = request.getPart("ST_SIGN");
            Connection conn = null;
//            System.out.println("FEW   " + filePart);

            if (filePart != null) {
                inputStream = filePart.getInputStream();
            } else {
                System.out.println("ERROR FILE");
                return;
            }
            try {
                conn = ConnectDB2.ConnectionDB();
                byte buf[] = new byte[(int) filePart.getSize()];
                inputStream.read(buf, 0, (int) filePart.getSize());

                String imageStr = Base64.encodeBase64String(buf);
                try (
                        BlobGen lob = new BlobGen(conn);
                        PreparedStatement stmt = conn.prepareStatement(
                                "INSERT INTO BRLDTA0100.STAFFLIST \n"
                                + "(ST_CONO, ST_N6L3, ST_EMAIL, ST_COSTC, ST_CODE,\n"
                                + "ST_EPNM, ST_ENAME, ST_ELNAME, ST_TPNM, ST_TNAME,\n"
                                + "ST_TLNAME, ST_POSITON, ST_LEVEL, ST_DISM3, ST_DISMAIL,\n"
                                + "ST_STDT, ST_REDT, ST_SIGN, ST_STS, ST_M3)\n"
                                + "VALUES(?, ?, ?, ?, ?,\n"
                                + "?, ?, ?, ?, ?,\n"
                                + "?, ?, ?, ?, ?,\n"
                                + "?, ?, ?, ?,?)")) {
                    stmt.setString(1, COMPANY.trim());
                    stmt.setString(2, ST_N6L3.trim());
                    stmt.setString(3, EMAIL.trim());
                    stmt.setString(4, COSTCENTER.trim());
                    stmt.setString(5, EMPCODE.trim());

                    stmt.setString(6, EPNM.trim());
                    stmt.setString(7, ENAME.trim());
                    stmt.setString(8, ELNAME.trim());
                    stmt.setString(9, TPNM.trim());
                    stmt.setString(10, TNAME.trim());

                    stmt.setString(11, TLNAME.trim());
                    stmt.setString(12, POSITION.trim());
                    stmt.setString(13, LEVEL.trim());
                    stmt.setString(14, "0");
                    stmt.setString(15, "0");

                    stmt.setInt(16, Integer.parseInt(ST_STDT.trim().replaceAll("-", "")));
                    stmt.setInt(17, 0);
                    stmt.setClob(18, lob.clob(imageStr));
                    stmt.setInt(19, Integer.parseInt(STATUS.trim()));
                    stmt.setString(20, ST_M3);
                    stmt.executeUpdate();
                    stmt.close();
                }

                conn.close();

            } catch (SQLException ex) {
                // message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                request.setAttribute("msg", "success");
                response.sendRedirect("./?page=./StaffManage");

            }
        } else if (request.getParameter("Add").equals("Update")) {

            String EMPCODE = request.getParameter("EMPCODE");
            String ST_STDT = request.getParameter("ST_STDT");
            String EPNM = request.getParameter("EPNM");
            String ENAME = request.getParameter("ENAME").toUpperCase();
            String ELNAME = request.getParameter("ELNAME").toUpperCase();
            String COMPANY = request.getParameter("COMPANY");
            String COSTCENTER = request.getParameter("COSTCENTER");
            String TPNM = request.getParameter("TPNM");
            String TNAME = request.getParameter("TNAME");
            String TLNAME = request.getParameter("TLNAME");
            String EMAIL = request.getParameter("EMAIL");
            String POSITION = request.getParameter("POSITION").toUpperCase();
            String LEVEL = request.getParameter("LEVEL").toUpperCase();
            String ST_REDT = request.getParameter("ST_REDT");
            String ST_DISM3 = request.getParameter("ST_DISM3");
            String ST_DISEMAIL = request.getParameter("ST_DISEMAIL");
            String ST_M3 = request.getParameter("ST_M3").toUpperCase().trim();
            ST_REDT = SetDate(ST_REDT);
            ST_DISM3 = SetDate(ST_DISM3);
            ST_DISEMAIL = SetDate(ST_DISEMAIL);

            String STATUS = request.getParameter("STATUS");
            String ST_N6L3 = "";

            if (ENAME.length() < 6) {
                ST_N6L3 = ENAME.trim().toUpperCase() + "_" + ELNAME.substring(0, 3).toUpperCase();
            } else {
                ST_N6L3 = ENAME.substring(0, 6).toUpperCase() + "_" + ELNAME.substring(0, 3).toUpperCase();
            }

            InputStream inputStream = null; // input stream of the upload file
            Part filePart = request.getPart("ST_SIGN");
            Connection conn = null;
            System.out.println("FEW   " + filePart);

            if (filePart != null) {
                inputStream = filePart.getInputStream();
            } else {
                System.out.println("ERROR FILE");
                return;
            }
            try {
                conn = ConnectDB2.ConnectionDB();
                byte buf[] = new byte[(int) filePart.getSize()];
                inputStream.read(buf, 0, (int) filePart.getSize());

                String imageStr = Base64.encodeBase64String(buf);

                if (!imageStr.equals("")) {
                    try (
                            BlobGen lob = new BlobGen(conn);
                            PreparedStatement stmt = conn.prepareStatement(
                                    "UPDATE BRLDTA0100.STAFFLIST \n"
                                    + "SET ST_CONO = ?, ST_N6L3= ?, ST_EMAIL=?, ST_COSTC=?,"
                                    + "ST_EPNM=?, ST_ENAME=?, ST_ELNAME=?, ST_TPNM=?, ST_TNAME=?,"
                                    + "ST_TLNAME=?, ST_POSITON=?, ST_LEVEL=?, ST_DISM3=?, ST_DISMAIL=?, "
                                    + "ST_STDT=?, ST_REDT=?, ST_SIGN=?, ST_STS=?, ST_M3 = ?\n"
                                    + "Where ST_CODE = ?")) {
                        stmt.setString(1, COMPANY.trim());
                        stmt.setString(2, ST_N6L3.trim());
                        stmt.setString(3, EMAIL.trim());
                        stmt.setString(4, COSTCENTER.trim());
                        stmt.setString(5, EPNM.trim());

                        stmt.setString(6, ENAME.trim());
                        stmt.setString(7, ELNAME.trim());
                        stmt.setString(8, TPNM.trim());
                        stmt.setString(9, TNAME.trim());
                        stmt.setString(10, TLNAME.trim());

                        stmt.setString(11, POSITION.trim());
                        stmt.setString(12, LEVEL.trim());
                        stmt.setInt(13, Integer.parseInt(ST_DISM3.trim().replaceAll("-", "")));
                        stmt.setInt(14, Integer.parseInt(ST_DISEMAIL.trim().replaceAll("-", "")));
                        stmt.setInt(15, Integer.parseInt(ST_STDT.trim().replaceAll("-", "")));

                        stmt.setInt(16, Integer.parseInt(ST_REDT.trim().replaceAll("-", "")));
                        stmt.setClob(17, lob.clob(imageStr));
                        stmt.setInt(18, Integer.parseInt(STATUS.trim()));
                        stmt.setString(19, ST_M3.trim());
                        stmt.setString(20, EMPCODE.trim());

                        stmt.executeUpdate();

                        stmt.close();
                    }
                } else {
                    try (
                            BlobGen lob = new BlobGen(conn);
                            PreparedStatement stmt = conn.prepareStatement(
                                    "UPDATE BRLDTA0100.STAFFLIST \n"
                                    + "SET ST_CONO = ?, ST_N6L3= ?, ST_EMAIL=?, ST_COSTC=?,"
                                    + "ST_EPNM=?, ST_ENAME=?, ST_ELNAME=?, ST_TPNM=?, ST_TNAME=?,"
                                    + "ST_TLNAME=?, ST_POSITON=?, ST_LEVEL=?, ST_DISM3=?, ST_DISMAIL=?, "
                                    + "ST_STDT=?, ST_REDT=?,  ST_STS=?, ST_M3 = ?\n"
                                    + "Where ST_CODE = ?")) {
                        stmt.setString(1, COMPANY.trim());
                        stmt.setString(2, ST_N6L3.trim());
                        stmt.setString(3, EMAIL.trim());
                        stmt.setString(4, COSTCENTER.trim());
                        stmt.setString(5, EPNM.trim());

                        stmt.setString(6, ENAME.trim());
                        stmt.setString(7, ELNAME.trim());
                        stmt.setString(8, TPNM.trim());
                        stmt.setString(9, TNAME.trim());
                        stmt.setString(10, TLNAME.trim());

                        stmt.setString(11, POSITION.trim());
                        stmt.setString(12, LEVEL.trim());
                        stmt.setInt(13, Integer.parseInt(ST_DISM3.trim().replaceAll("-", "")));
                        stmt.setInt(14, Integer.parseInt(ST_DISEMAIL.trim().replaceAll("-", "")));
                        stmt.setInt(15, Integer.parseInt(ST_STDT.trim().replaceAll("-", "")));

                        stmt.setInt(16, Integer.parseInt(ST_REDT.trim().replaceAll("-", "")));
                        stmt.setInt(17, Integer.parseInt(STATUS.trim()));
                        stmt.setString(18, ST_M3.trim());
                        stmt.setString(19, EMPCODE.trim());

                        stmt.executeUpdate();

                        stmt.close();

                    }
                    conn.close();
                }
            } catch (SQLException ex) {
                // message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                request.setAttribute("few", "success");
                response.sendRedirect("./?page=./StaffManage");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        try {
            // connects to the database
            // DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            PrintWriter wt = response.getWriter();
            Connection conn = ConnectDB2.ConnectionDB();
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM BRLDTA0100.STAFFLIST where ST_N6L3 = 'JILASA_SAM'");
            while (rs.next()) {
                wt.println("<html><img src='data:image/png;base64," + rs.getString("ST_SIGN") + "'></html>");
            }
            state.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(UploadStaff.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UploadStaff.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static String SetDate(String date) {

        String Newdate = "";
        String year = "";
        String month = "";
        String day = "";

        if (date.equals("0") || date.equals("")) {
            date = "00000000";
            year = date.substring(0, 4).trim();
            month = date.substring(4, 6).trim();
            day = date.substring(6, 8).trim();
            Newdate = year + "-" + month + "-" + day;
            return Newdate;
        } else {
            date = date.replaceAll("-", "");
            year = date.substring(0, 4).trim();
            month = date.substring(4, 6).trim();
            day = date.substring(6, 8).trim();
            Newdate = year + "-" + month + "-" + day;
            return Newdate;
        }

    }

}
