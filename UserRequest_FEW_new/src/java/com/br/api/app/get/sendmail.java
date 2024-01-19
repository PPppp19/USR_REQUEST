/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

import com.br.api.app.InsertDB2;
import com.br.api.app.SelectDB2;
import com.br.api.app.connect.ConnectDB2;
import com.br.api.as400.CreateUserAS400;
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
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "sendmail", urlPatterns = {"/sendmail"})
public class sendmail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
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
            out.println("<title>Servlet sendmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet sendmail at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Locale.setDefault(Locale.US);
        java.util.Date d = new java.util.Date();
        SimpleDateFormat A = new SimpleDateFormat("yyyy-MM-dd");

        HttpSession session = request.getSession(true);

        String editType = request.getParameter("SendMail");
        String ST_CODE = request.getParameter("empcode");
        String reqno = request.getParameter("reqno");
        String chkaddon = request.getParameter("creuser");
        
         System.out.println(chkaddon); 

        System.out.println("com.br.api.app.get.sendmail.doGet() " + editType);

        if (editType != null && !editType.isEmpty()) {
            if (request.getParameter("SendMail").equals("SendMail")) {

                System.out.println("SendMailSendMailSendMailSendMail");

                try {
                    SelectDB2 SDB2 = new SelectDB2();
                    Send_mail sm = new Send_mail();

                    String USREQNO = request.getParameter("reqnoformail");
                    ResultSet rsl = GetEmailCreater(USREQNO);
                    while (rsl.next()) {
                        String Appemail = rsl.getString("EMAIL").trim();
                        String COSTC = rsl.getString("ST_COSTC").trim();
                        String CREATOR = rsl.getString("ST_M3").trim();
                        String SubjectEmail = "New user request from request no. " + USREQNO.trim();
                        String LinkCreate = "Please check the details of the user request. : http://192.200.9.189:8080/UserRequest/?webpage=RequestList&ST_COSTC=" + COSTC + "&namereq=" + CREATOR;
                        sm.Sendmail_ICT(Appemail, LinkCreate, SubjectEmail);
                        System.out.println(Appemail + "\n" + SubjectEmail);

                    }

                } catch (Exception ex) {
                    Logger.getLogger(sendmail.class.getName()).log(Level.SEVERE, null, ex);
                }

//            out.print("Send Email Successful");
                System.out.print("UserSentMail");
                response.sendRedirect("./?page=./RequestUser");

            } else if (request.getParameter("SendMail").equals("SendMailUser")) {

                System.out.println("SendMailUserSendMailUserSendMailUserSendMailUserSendMailUser");

                SelectDB2 SDB2 = new SelectDB2();
                Send_mail sm = new Send_mail();
                String DATENOW = A.format(d);
                String Remark = request.getParameter("Remark");
                String EmailUser = request.getParameter("email").trim();
                String USREQNO = request.getParameter("reqno");
                String USLINE = request.getParameter("line");
                String USCREBY = session.getAttribute("namereq").toString().trim();

                String SubjectEmail = "Your request No. " + USREQNO + ".Line " + USLINE + " is complete";

                String LinkAcknow = Remark.trim() + " <br>  " + "Please click link for acknowledge : " + " \n  " + "http://192.200.9.189:8080/UserRequest/Acknowledge.jsp?USREQNO=" + USREQNO + "&USLINE=" + USLINE;
                if ((EmailUser.equals("#N/A") || EmailUser.equals("") || EmailUser == null) && USCREBY.equals("M3SRVICT")) {
                    UpdateAcknowledge(USREQNO, USLINE, DATENOW, USCREBY);
                } else if ((EmailUser.equals("#N/A") || EmailUser.equals("") || EmailUser == null) && !USCREBY.equals("M3SRVICT")) {
                    try {
                        ResultSet rsl = GetEmailUSREQ(USREQNO);
                        if (rsl.next()) {
                            EmailUser = rsl.getString("ST_EMAIL").trim();
                            sm.Sendmail_User(EmailUser, LinkAcknow, SubjectEmail, DATENOW, USREQNO, USLINE, USCREBY);
                        } else {
                            UpdateAcknowledge(USREQNO, USLINE, DATENOW, USCREBY);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(sendmail.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {

                    System.out.println("elseleselesleslelselsels");

                    sm.Sendmail_User(EmailUser, LinkAcknow, SubjectEmail, DATENOW, USREQNO, USLINE, USCREBY);
                    CreateUserAS400 CAS400 = new CreateUserAS400();
                    String username = request.getParameter("STN6L3");

                    try {

                        if (chkaddon == "addon") {
                            CAS400.CREATE_USER(username, username);
                            UPDATEREQ(USREQNO, username);
                            UPDATESTFLST(ST_CODE, username);
                        }

                    } catch (Exception e) {
                        System.out.println(e.toString());

                    }
                }
                System.out.print("UserSentMailFEW");
                response.sendRedirect("./?page=./RequestList");

            }

        } else {
            if (request.getParameter("SendVerifyMail").equals("SendVerifyMail")) {

                try {

                    response.sendRedirect("./?page=./ResetPW");

                    Send_mail sm = new Send_mail();

                    ResultSet rsl = Getuseremail(request.getParameter("username"));

                    while (rsl.next()) {
                        String Appemail = rsl.getString("ST_EMAIL").trim();

                        int ST_CONO = rsl.getInt("ST_CONO");

                        String SubjectEmail = "Please verify your email address";

                        String LinkCreate = com.br.api.app.Utility.getmaildetail(request.getParameter("username"), "RESETPW", "edrefno", Appemail, "esentcc", "ItCenter", SubjectEmail, "ItCenter", ST_CONO);

                        sm.Sendmail_ICT(Appemail, LinkCreate, SubjectEmail);
                        //Utility.addMailLog(Appemail, SubjectEmail, LinkCreate, 10,mimeusername); 
                        System.out.println(Appemail + "\n" + SubjectEmail);

                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxx sendmail xxxxxxxxxxxxxxxxxxxxxxxxxx");
                        session.setAttribute("MSGError", "An email has been sent. Please check your  Email");
                    }

                } catch (Exception ex) {
                    Logger.getLogger(sendmail.class.getName()).log(Level.SEVERE, null, ex);

                    session.setAttribute("MSGError", "Your Username is Incorrect.");
                    response.sendRedirect("./?page=./ResetPW");
                }

                System.out.print("UserSentMail");
                //response.sendRedirect("./?page=./RequestUser");

            }
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
        processRequest(request, response);
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

    public static ResultSet GetEmailCreater(String USREQNO) throws Exception {

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql = "SELECT  DISTINCT(USEMCRE) as USEMCRE,ST_COSTC,CASE WHEN USEMCRE = 'ictcenter@br-bangkokranch.com' THEN 'ictcenter@br-bangkokranch.com' ELSE USEMCRE END AS EMAIL,ST_M3\n"
                    + "FROM BRLDTA0100.USR_REQUEST\n"
                    + "LEFT JOIN BRLDTA0100.STAFFLIST ON USEMCRE = ST_EMAIL AND ST_COSTC = 'S8'\n"
                    + "WHERE USREQNO = '" + USREQNO + "'\n"
                    + "AND USSTAT = '10'";

            ResultSet rs1 = sta.executeQuery(Sql);
            return rs1;
        } catch (SQLException ex) {

        }
        return null;
    }

    private static void UpdateAcknowledge(String USREQNO, String USLINE, String Datenow, String USCREBY) {
        String Date = Datenow.replaceAll("-", "");

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement s = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.USR_REQUEST\n"
                    + "SET USCREBY = '" + USCREBY + "', USCREDT = '" + Date + "', USSTAT = '15'\n"
                    + "WHERE USREQNO = '" + USREQNO.trim() + "' \n"
                    + "AND USLINE = '" + USLINE.trim() + "'";

            s.executeUpdate(sql);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            out.println(e.getMessage());

        }

    }

    private static void UPDATEREQ(String reqno, String username) {

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement s = conn.createStatement();
            String sql = "  UPDATE BRLDTA0100.USR_REQUEST\n"
                    + "                                SET USUREQ = '" + username + "'\n"
                    + "                                WHERE USUREQ = 'M3SRVICT'\n"
                    + "                                AND USREQNO = '" + reqno + "'\n"
                    + "                                AND USGROUP = 'AddOnM3'";

            s.executeUpdate(sql);
            System.out.println(sql);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            out.println(e.getMessage());

        }

    }

    private static void UPDATESTFLST(String ST_CODE, String username) {

        try {

            Connection conn = ConnectDB2.ConnectionDB();
            Statement s = conn.createStatement();
            String sql = "UPDATE BRLDTA0100.STAFFLIST\n"
                    + "SET   ST_N6L3 = '" + username + "'\n"
                    + "WHERE ST_CODE = '" + ST_CODE + "'";

            s.executeUpdate(sql);
            System.out.println(sql);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            out.println(e.getMessage());

        }

    }

    public static ResultSet GetEmailUSREQ(String USREQNO) throws Exception {

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String Sql = "SELECT DISTINCT(USUREQ) AS USUREQ,ST_EMAIL \n"
                    + "FROM BRLDTA0100.USR_REQUEST,BRLDTA0100.STAFFLIST\n"
                    + "WHERE USREQNO = '" + USREQNO.trim() + "'\n"
                    + "AND  USUREQ = ST_N6L3";

            ResultSet rs1 = sta.executeQuery(Sql);
            return rs1;
        } catch (SQLException ex) {

        }
        return null;
    }

    public static ResultSet Getuseremail(String username) throws Exception {

        try {
            Connection conn = ConnectDB2.ConnectionDB();
            Statement sta = conn.createStatement();
            String query = "SELECT  * FROM BRLDTA0100.STAFFLIST WHERE  ST_STS = '20' AND  ST_N6L3 = '" + username.trim() + "'";

            ResultSet rs1 = sta.executeQuery(query);
            return rs1;
        } catch (SQLException ex) {

        }
        return null;
    }

}
