/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

import com.br.api.app.SelectDB2;
import com.br.api.app.connect.ConnectDB2;
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import static com.lowagie.text.pdf.PdfFileSpecification.url;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import static groovy.xml.Entity.gt;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jilasak
 */
@WebServlet(name = "ChangePW", urlPatterns = {"/ChangePW"})
public class ChangePW extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        processRequest(request, response);

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);
        if (request.getParameter("changepw").equals("ChangePW")) {
            String host = "192.200.9.190";
            String user = request.getParameter("username").toUpperCase().trim();
            String oldpw = request.getParameter("txtOldPassword").trim();
            String newpw = request.getParameter("txtNewPassword").trim();
            AS400 system = new AS400(host, user);

            try {
                system.changePassword(oldpw, newpw);
                session.setAttribute("MSGError", "Change password complete.");
                response.sendRedirect("./?page=./ChangePW");
            } catch (AS400SecurityException ex) {
                System.out.println(ex.toString());
                String error = ex.toString();
                if (error.equals("com.ibm.as400.access.AS400SecurityException: New password was previously used.:" + user + "")) {
                    session.setAttribute("MSGError", "Error : รหัสผ่านใหม่ต้องไม่ซ้ำกับก่อนหน้านี้.");
                    response.sendRedirect("./?page=./ChangePW");
                } else if (error.equals("com.ibm.as400.access.AS400SecurityException: User ID is not known.:" + user + "")) {
                    session.setAttribute("MSGError", "User ID is not known. : " + user + "");
                    response.sendRedirect("./?page=./ChangePW");
                } else if (error.equals("com.ibm.as400.access.AS400SecurityException: New password is not valid.")) {
                    session.setAttribute("MSGError", "New password is not valid.");
                    response.sendRedirect("./?page=./ChangePW");
                } else if (error.equals("com.ibm.as400.access.AS400SecurityException: New password must contain at least one numeric character.:" + user + "")) {
                    session.setAttribute("MSGError", "รหัสผ่านใหม่ต้องมีอักขระตัวเลขอย่างน้อยหนึ่งตัว");
                    response.sendRedirect("./?page=./ChangePW");
                } else {
                    String[] Ex = error.split(":");
                    String msgerror = Ex[1];
                    session.setAttribute("MSGError", msgerror);
                    response.sendRedirect("./?page=./ChangePW");
                }
            }
            system.disconnectService(AS400.SIGNON);
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
