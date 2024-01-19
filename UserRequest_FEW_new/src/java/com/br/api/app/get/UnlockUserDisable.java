/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.RequestNotSupportedException;
import com.ibm.as400.access.UserList;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
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
@WebServlet(name = "UnlockUserDisable", urlPatterns = {"/UnlockUserDisable"})
public class UnlockUserDisable extends HttpServlet {

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
            out.println("<title>Servlet UnlockUserDisable</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnlockUserDisable at " + request.getContextPath() + "</h1>");
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

        String username = request.getParameter("username").trim();;

        if (request.getParameter("unlock").equals("unlockuser") && !username.equals("")) {

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
                as400.disconnectService(AS400.SIGNON);
                response.sendRedirect("./?page=./UndisAddon");

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

        } else if (request.getParameter("unlock").equals("resetpass") && !username.equals("")) {

            try {
                String HOST = "192.200.9.190";

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
                }

                as400.disconnectService(AS400.COMMAND);
                as400.disconnectService(AS400.SIGNON);
                response.sendRedirect("./?page=./UndisAddon");

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

        } else {
            session.setAttribute("MessageReturn", "Error");
            response.sendRedirect("./?page=./UndisAddon");
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
