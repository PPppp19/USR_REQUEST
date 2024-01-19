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
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.RequestNotSupportedException;
import com.ibm.as400.access.UserList;
import static com.lowagie.text.pdf.PdfFileSpecification.url;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import static groovy.xml.Entity.gt;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
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
@WebServlet(name = "ResetNewPW", urlPatterns = {"/ResetNewPW"})
public class ResetNewPW extends HttpServlet {

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
            System.out.println("----------------------------------");
         System.out.println(request.getParameter("resetnewpw"));
          System.out.println(request.getParameter("resetnewpw"));
        HttpSession session = request.getSession(true);
        if (request.getParameter("resetnewpw").equals("ResetNewPW")) {


                  ///////////////////
             String username = request.getParameter("username").toUpperCase().trim();
             String url = "&pp="+request.getParameter("url").trim();
             
             
              try {
                String HOST = "192.200.9.190";
                String ENABLE = "*ENABLED";
                String MVXSECOFR = "MVXSECOFR";
                String PASSWORD = "lawson@90";
                String Command = "CHGUSRPRF USRPRF(" + username.trim() + ")  PASSWORD(" + username.trim() + ")";

                Set<String> as400Users = new HashSet();
                AS400 as400 = new AS400(HOST, MVXSECOFR, PASSWORD);
                UserList users = new UserList(as400);
                Enumeration io = users.getUsers();
                CommandCall cmd = new CommandCall(as400);

                while (io.hasMoreElements()) {
                    com.ibm.as400.access.User u = (com.ibm.as400.access.User) io.nextElement();
                    if (u.getName().toString().trim().equals(username) && u.getStatus().toString().trim().equalsIgnoreCase("*DISABLED")) {

                        if (cmd.run(Command)) {
                            u.setStatus(ENABLE);
                            u.setPasswordSetExpire(true);
                            System.out.println(Command + " Run success");
                            session.setAttribute("MessageReturn", "Complete");
                        } else {
                            session.setAttribute("MessageReturn", "Call command error");
                        }
                        break;
                    } else if (u.getName().toString().trim().equals(username) && u.getStatus().toString().trim().equalsIgnoreCase(ENABLE)) {
//                        session.setAttribute("MessageReturn", "User : " + username + " is still enabled.");
                        if (u.getName().toString().trim().equals(username)) {
                            if (cmd.run(Command)) {
                                u.setPasswordSetExpire(true);
                                System.out.println(Command + " Run success");
                                session.setAttribute("MessageReturn", "Complete");
                            } else {
                                session.setAttribute("MessageReturn", "Call command error");
                            }
                            break;
                        } else {
                            session.setAttribute("MessageReturn", "User : " + username + " not haved in system.");
                        }
                        break;
                    } else {
                        session.setAttribute("MessageReturn", "User : " + username + " not haved in system.");
                    }
                }

                as400.disconnectService(AS400.COMMAND);
//                as400.disconnectService(AS400.SIGNON);
//                response.sendRedirect("./?page=./UndisAddon");

            } catch (AS400SecurityException ex) {
                System.out.println(ex.toString());
            } catch (ErrorCompletingRequestException ex) {
                System.out.println(ex.toString());
            } catch (InterruptedException ex) {
                System.out.println(ex.toString());
            } catch (ObjectDoesNotExistException ex) {
                System.out.println(ex.toString());
            } catch (RequestNotSupportedException ex) {
                System.out.println(ex.toString());
            } catch (PropertyVetoException ex) {
                System.out.println(ex.toString());
            }
            
            
            
            
            
            
            
            //////////////////
            
            
            String host = "192.200.9.190";
            String user = request.getParameter("username").toUpperCase().trim();
            String oldpw = request.getParameter("username").toUpperCase().trim();
            String newpw = request.getParameter("txtNewPassword").trim();
            AS400 system = new AS400(host, user);
            
            

            try {
                
                
                system.changePassword(oldpw, newpw);
                session.setAttribute("MSGError", "Change password complete.");
                response.sendRedirect("./?page=./ResetNewPW"+url);
            } catch (AS400SecurityException ex) {
                System.out.println(ex.toString());
                String error = ex.toString();
                if (error.equals("com.ibm.as400.access.AS400SecurityException: New password was previously used.:" + user + "")) {
                    session.setAttribute("MSGError", "Error : รหัสผ่านใหม่ต้องไม่ซ้ำกับก่อนหน้านี้.");
                    response.sendRedirect("./?page=./ResetNewPW"+url);
                } else if (error.equals("com.ibm.as400.access.AS400SecurityException: User ID is not known.:" + user + "")) {
                    session.setAttribute("MSGError", "Error :User ID ที่ไม่รู้จัก. : " + user + "");
                    response.sendRedirect("./?page=./ResetNewPW"+url);
                } else if (error.equals("com.ibm.as400.access.AS400SecurityException: New password is not valid.")) {
                    session.setAttribute("MSGError", "Error : รหัสผ่านใหม่ไม่ถูกต้อง is not valid.");
                    response.sendRedirect("./?page=./");
                } else if (error.equals("com.ibm.as400.access.AS400SecurityException: New password must contain at least one numeric character.:" + user + "")) {
                    session.setAttribute("MSGError", "รหัสผ่านใหม่ต้องมีอักขระตัวเลขอย่างน้อยหนึ่งตัว");
                    response.sendRedirect("./?page=./ResetNewPW"+url);
                } else {
                        String[] Ex = error.split(":");
                    String msgerror = Ex[1];
                    session.setAttribute("MSGError", msgerror);
                    response.sendRedirect("./?page=./ResetNewPW"+url);
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
