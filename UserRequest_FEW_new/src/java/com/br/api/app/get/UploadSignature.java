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
@WebServlet(name = "UploadSignature", urlPatterns = {"/UploadSignature"})
@MultipartConfig

public class UploadSignature extends HttpServlet {

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
     

        String ST_N6L3 = "";
        InputStream inputStream = null; // input stream of the upload file
        Part filePart = request.getPart("ST_SIGN");
        Connection conn = null;

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
                            + "ST_STDT, ST_REDT, ST_SIGN, ST_STS)\n"
                            + "VALUES(?, ?, ?, ?, ?,\n"
                            + "?, ?, ?, ?, ?,\n"
                            + "?, ?, ?, ?, ?,\n"
                            + "?, ?, ?, ?)")) {
                stmt.setClob(18, lob.clob(imageStr));
                stmt.executeUpdate();
                stmt.close();
            }

            conn.close();

        } catch (SQLException ex) {
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
            RequestDispatcher dispatcher = request.getRequestDispatcher("./?page=./StaffManage");
            dispatcher.forward(request, response);
            
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
            ResultSet rs = state.executeQuery("SELECT * FROM BRLDTA0100.STAFFLIST");
            while (rs.next()) {
                wt.println("<html><img src='data:image/png;base64," + rs.getString("ST_SIGN") + "'></html>");
            }
            state.close();
            conn.close();
        } catch (Exception ex) {
            Logger.getLogger(UploadSignature.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UploadSignature.class.getName()).log(Level.SEVERE, null, ex);
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

}
