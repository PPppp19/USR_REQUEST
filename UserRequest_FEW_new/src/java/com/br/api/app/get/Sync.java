/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.api.app.get;

import com.br.api.app.DeleteDB2;
import com.br.api.app.InsertDB2;
import com.br.api.app.SelectDB2;
import com.br.api.app.UpdateDB2;
import com.br.api.app.Utility;
import com.br.api.app.connect.ConnectDB2;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author Wattana
 */
public class Sync extends HttpServlet {

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
            out.println("<title>Servlet Data</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Data at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
//        System.out.println("page = " + request.getParameter("page"));

        try {

            if (request.getParameter("page").equals("Company")) {
                try {
                    out.print(Utility.getCompany());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }

            } 
            else if (request.getParameter("page").equals("checkExistUserbyempcode")) {
                try {
                    out.print(SelectDB2.checkExistUserbyempcode(request.getParameter("empcode")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            
            else if (request.getParameter("page").equals("checkExistUser")) {
                try {
                    out.print(SelectDB2.checkExistUser(request.getParameter("username")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("costcenter")) {
                try {
                    out.print(SelectDB2.costcenter(request.getParameter("cono")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Table_USRREQ")) {
                try {
                    System.out.println("Table_USRREQ");
                    out.print(SelectDB2.Table_USRREQ(request.getParameter("USREQNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Request_List")) {
                try {
                    System.out.println("Request_List111");
                    out.print(SelectDB2.Request_List());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Request_List2")) {
                try {
                    System.out.println("Request_List2");
                    out.print(SelectDB2.Request_List2(request.getParameter("USUREQ")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("TrackStatus")) {
                try {
                    System.out.println("TrackStatus");
                    out.print(SelectDB2.TrackStatus());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GET_REQNO")) {
                try {
                    out.print(SelectDB2.GET_REQNO());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("GetMaxLine")) {
                try {
                    System.out.println("GetMaxLine");
                    out.print(SelectDB2.GetMaxLine(request.getParameter("USREQNO")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Data_Acknowledge")) {
                try {
                    System.out.println("Data_Acknowledge");
                    out.print(SelectDB2.Data_Acknowledge(request.getParameter("USREQNO"), request.getParameter("USLINE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("MailCreator")) {
                try {
//                    System.out.println("MailCreator");
                    out.print(Utility.MailCreator());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("DetailUser")) {
                try {
                    System.out.println("DetailUser");
                    out.print(SelectDB2.DetailUser(request.getParameter("ST_CODE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Search")) {
                try {
                    System.out.println("Search");
                    out.print(SelectDB2.Search(request.getParameter("ST_CODE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Check_Duplicate")) {
                try {
//                    System.out.println("Check_Duplicate");
                    out.print(SelectDB2.Check_Duplicate(request.getParameter("USREQNO"), request.getParameter("USCOMP"), request.getParameter("USCODE"), request.getParameter("USGROUP"), request.getParameter("USTYPE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("CheckUserM3")) {
                try {
                    System.out.println("CheckUserM3");
                    out.print(SelectDB2.CheckUserM3(request.getParameter("USCODE"), request.getParameter("USCOMP"), request.getParameter("USGROUP"), request.getParameter("USTYPE"), request.getParameter("USLIKE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("EmpCodeList")) {
                try {
//                    System.out.println("EmpCodeList");
                    out.print(Utility.EmpCodeList());
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("ShowFile")) {
                try {

                    out.print(SelectDB2.ShowFile(request.getParameter("USREQNO"), request.getParameter("USLINE")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("page").equals("Getdecode")) {
                try {

                    out.print(Utility.getdecodemime(request.getParameter("Key")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("page").equals("Checkusername")) {
                try {
                    System.out.println("p1ppppppppppppppppppppppppppppppp1");
                    String username = Utility.getdecodemime(request.getParameter("Key"));
                    System.out.println(username);
                    System.out.println("pppppppppppppppppppppppppppppppp");

                    out.print(SelectDB2.checkusername(username));

                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (request.getParameter("page").equals("Getuseremail")) {
                try {

                    out.print(SelectDB2.getuseremail(request.getParameter("username")));
                    out.flush();
                } catch (JSONException ex) {
                    Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        System.out.println("page = " + request.getParameter("page"));

        try {
            if (request.getParameter("page").equals("InsertRequest")) {
                try {
                    System.out.println("InsertHead");
                    InsertDB2.InsertRequest(request.getParameter("USREQNO"), request.getParameter("USLINE"), request.getParameter("USCOMP"), request.getParameter("USNAME1"), request.getParameter("USNAME2"),
                            request.getParameter("USCODE"), request.getParameter("USMAIL"), request.getParameter("USGROUP"), request.getParameter("USTYPE"), request.getParameter("USLIKE"),
                            request.getParameter("USAFUNC"), request.getParameter("USDFUNC"), request.getParameter("USUREQ"), request.getParameter("USEMCRE"));
                } catch (Exception e) {
                    System.out.println("ERROR INSERT");
                }
            } else if (request.getParameter("page").equals("Update_LineReq")) {
                System.out.println("Update_LineReq");
                UpdateDB2.Update_LineReq(request.getParameter("USREQNO"), request.getParameter("USLINE"), request.getParameter("USCOMP"), request.getParameter("USNAME1"),
                        request.getParameter("USNAME2"), request.getParameter("USMAIL"), request.getParameter("USCODE"), request.getParameter("USGROUP"), request.getParameter("USTYPE"),
                        request.getParameter("USLIKE"), request.getParameter("USAFUNC"), request.getParameter("USDFUNC"), request.getParameter("USEMCRE"));
            } else if (request.getParameter("page").equals("SendToCreate")) {
                System.out.println("SendToCreate");
                UpdateDB2.SendToCreate(request.getParameter("USREQNO"));
            } else if (request.getParameter("page").equals("UpdateREQ")) {
                System.out.println("UpdateREQ");
                UpdateDB2.UpdateREQ(request.getParameter("username"), request.getParameter("REQNO"));
            } else if (request.getParameter("page").equals("UpdateCreated")) {
                System.out.println("UpdateCreated");

                System.out.println(request.getParameter("USCREBY"));
                System.out.println(request.getParameter("USREQNO"));
                System.out.println(request.getParameter("USLINE"));
                System.out.println(request.getParameter("USREMK"));

                UpdateDB2.UpdateCreated(request.getParameter("USREQNO"), request.getParameter("USLINE"), request.getParameter("USREMK"), request.getParameter("USCREBY"));
            } else if (request.getParameter("page").equals("DeleteLine")) {
                System.out.println("DeleteLine");
                DeleteDB2.DeleteLine(request.getParameter("USREQNO"), request.getParameter("USLINE"));
            } else if (request.getParameter("page").equals("Acknowledge")) {
                System.out.println("Acknowledge");
                UpdateDB2.Acknowledge(request.getParameter("USREQNO"), request.getParameter("USLINE"), request.getParameter("USACKBY"));
            } else if (request.getParameter("page").equals("ChangePassword")) {
                System.out.println("ChangePassword");
                UpdateDB2.Acknowledge(request.getParameter("USREQNO"), request.getParameter("USLINE"), request.getParameter("USACKBY"));
            }

        } catch (Exception ex) {
            Logger.getLogger(Sync.class.getName()).log(Level.SEVERE, null, ex);
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
