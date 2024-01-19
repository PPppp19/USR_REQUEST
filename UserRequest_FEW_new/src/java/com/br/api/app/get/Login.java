/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

import com.br.api.app.connect.ConnectDB2;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Wattana
 */
public class Login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        if ("".equals(request.getParameter("username"))) {
            request.setAttribute("msg", "Username is Require.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else if ("".equals(request.getParameter("password"))) {
            request.setAttribute("msg", "Password is Require.");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        } else {

            String username = request.getParameter("username").toUpperCase().trim();
            String password = request.getParameter("password");
            String ST_CODE = "";
            String ST_COSTC = "";
//        System.out.println("Company : " + request.getParameter("company"));
            //conn = ConnectDB2.LoginDB(username, password);
            Connection conn = null;
            if (username.equals("USER_REQUEST")) {
                username = "M3SRVICT";
            }
            try {

                conn = ConnectDB2.LoginDB(username, password);
                Statement aa = conn.createStatement();
                if (conn != null) {

                    Statement stmt = conn.createStatement();
                    String query = "Select ST_CODE,ST_COSTC\n"
                            + "FROM BRLDTA0100.STAFFLIST\n"
                            + "WHERE ST_M3 = '" + username.trim() + "' ";
//                    System.err.println(query);
//                
                    if (!username.equalsIgnoreCase("PHAISA_KUM")) {
                        ResultSet mRes = stmt.executeQuery(query);
                        if (mRes.next()) {
                            ST_CODE = mRes.getString("ST_CODE").trim();
                            ST_COSTC = mRes.getString("ST_COSTC").trim();
                        } else {
                            ST_CODE = "";
                            ST_COSTC = "";
                        }
                    } else {
                        ST_CODE = "0100324";
                        ST_COSTC = "S8";
                    }

                    session.setAttribute("cono", "10");
                    session.setAttribute("ST_CODE", ST_CODE);
                    session.setAttribute("ST_COSTC", ST_COSTC);
                    session.setAttribute("namereq", username);
                    response.sendRedirect("./");

                } else {
                    request.setAttribute("msg", "Password is incorrect.");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                String MessageErrors = ConnectDB2.ErrorLogs;
                String error = MessageErrors.toString();
                if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: Bad return code from signon info: 0x3000d")) {
                    request.setAttribute("msg", "Password is Expired Please Change Password");
                    request.setAttribute("msg2", "ChangePW");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
//                    response.sendRedirect("http://192.200.9.106:8080/UserRequest/?Destination=ChangePW/");
                } else if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: Password is incorrect.")) {
                    request.setAttribute("msg", "Username and Password is incorrect");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: Bad return code from signon info: 0x20002")) {
                    request.setAttribute("msg", "Username has Disable Please Contract ICT");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else if (MessageErrors.equals("java.sql.SQLException: java.io.IOException: User ID is not known.")) {
                    request.setAttribute("msg", "User ID is not known");
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", error);
                    getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                }
//                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);

            }

            System.out.println("User : " + username + "\n" + "Pass : " + password);

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
