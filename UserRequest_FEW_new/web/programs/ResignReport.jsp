<%-- 
    Document   : ResignReport
    Created on : May 20, 2020, 11:26:28 AM
    Author     : Jilasak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String message = "";
    if (session.getAttribute("namereq") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <br>
        <div class="container">
            <div class="card border-center-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="content">
                        <h5 class="center-block text-center" style="color: tomato">Report Resign</h5>
                        <br>
                        <form method="GET" action="./Report">
                            <div class="row col-md-12">
                                <div class="offset-md-3 text-center">
                                    <label class="row" style="font-weight: bold;">From date : &nbsp;
                                        <input class="form-control text-center" type="date" id="Fromdate"  name="Fromdate" style="width: 175px;height: 35px;" onchange="alertx();">
                                    </label>
                                </div>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                                <div class="col-md-5">
                                    <label class="row" style="font-weight: bold;padding-left: 20px;">To date : &nbsp;
                                        <input class="form-control text-center" type="date" id="Todate"  name="Todate" style="width: 175px;height: 35px;">
                                    </label>
                                </div> 
                            </div>
                            <br>
                            <div class="row col-md-12">
                                <div class="col-md-12 text-center">
                                    <button type="submit" value="Resign" name="report" class="btn btn-danger centerDiv">GET REPORT</button>
                                </div>
                            </div>        
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
