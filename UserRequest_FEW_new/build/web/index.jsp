<%-- 
    Document   : index
    Created on : Jun 14, 2019, 9:32:52 AM
    Author     : Wattana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%

        System.out.println("Hello bar ");

        System.out.println(session.getAttribute("namereq"));
        System.out.println(session.getAttribute("Destination"));
        System.out.println(session.getAttribute("webpage"));
//                         System.out.println(session.getAttribute("Destination"));
//                         System.out.println(session.getAttribute("Destination"));
//                         System.out.println(session.getAttribute("Destination"));

        String Destination = request.getParameter("Destination");
        session.setAttribute("Destination", Destination);
        String code = request.getParameter("pp");

        String webpage = request.getParameter("webpage");
        session.setAttribute("webpage", webpage);

        if (session.getAttribute("namereq") == null)  {

            System.out.println("foonull");

            if (session.getAttribute("Destination") != null) {
                if (session.getAttribute("Destination").equals("ChangePW")) {
                    System.out.println("foo1");
                    session.setAttribute("namereq", "M3SRVICT");
                    response.sendRedirect("./?page=./ChangePW");
                } else if (session.getAttribute("Destination").equals("RequestUser")) {
                    System.out.println("foo2");
                    session.setAttribute("namereq", "M3SRVICT");

                    response.sendRedirect("./?page=./RequestUser");
                } else if (session.getAttribute("Destination").equals("ResetPW")) {
                    System.out.println("foo3");
                    session.setAttribute("namereq", "M3SRVICT");
                    response.sendRedirect("./?page=./ResetNewPW&pp=" + code);
                } else if (session.getAttribute("Destination").equals("PResetPW")) {
                    System.out.println("foo4");
                    session.setAttribute("namereq", "M3SRVICT");
                    response.sendRedirect("./?page=/ResetPW");

                }

            } else if (session.getAttribute("webpage") != null) {
                System.out.println("foo5");
                String CREATOR = request.getParameter("namereq");
                String ST_COSTC = request.getParameter("ST_COSTC");
                if (CREATOR.equals("null") || CREATOR.equals(null)) {
                    // response.sendRedirect("login.jsp");
                    response.sendRedirect("http://192.200.9.189:8080/UserRequest/login.jsp");

                } else {
                    System.out.println("foo6");

                    session.setAttribute("namereq", CREATOR);
                    session.setAttribute("ST_COSTC", ST_COSTC);
                    response.sendRedirect("./?page=./RequestList");
                }
            } //            else if (Destination.indexOf("UserRequest/?page=ResetNewPW") != 0) {
            //                    session.setAttribute("namereq", "M3SRVICT");
            //                   String url = "&pp="+request.getParameter("url").trim();            
            //                   response.sendRedirect("./?page=./ResetNewPW"+url);
            //                }
            else {
                System.out.println("foo7");

                response.sendRedirect("login.jsp");
//                response.sendRedirect("http://192.200.9.189:8080/UserRequest/login.jsp");
            }
        } else {

            System.out.println(session.getAttribute("namereq"));
            System.out.println(session.getAttribute("link"));

        }

    %>


    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="assets/jQuery-3.3.1/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid.min.css" />
        <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid-theme.min.css">

        <link rel="stylesheet" type="text/css" href="assets/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap-datepicker.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.css"/>


        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-3.3.1.min.js"></script>
        <script type="text/javascript" src="assets/Bootstrap-3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/jQuery-3.3.1/jquery-ui.min.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
        <script type="text/javascript" src="assets/jsgrid-1.5.3/dist/jsgrid.min.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.core.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.validation.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-indicator.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.load-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.sort-strategies.js"></script>
        <script src="assets/jsgrid-1.5.3/src/jsgrid.field.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.text.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.number.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.select.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.checkbox.js"></script>
        <script src="assets/jsgrid-1.5.3/src/fields/jsgrid.field.control.js"></script>

        <script src='assets/select2/select2.min.js' type='text/javascript'></script>
        <link href='assets/select2/select2.min.css' rel='stylesheet' type='text/css'>



        <title>Request User</title> 
        <link rel="icon" type="image/ico" href="images/user-icon-vector.jpg" />
    </head>
    <style>
        body {
            font-size: 16px;

        }

        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .navbar {
            overflow: hidden;
            background-color: #333;
        }

        .navbar a {
            float: left;
            font-size: 16px;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .dropdown2 {
            float: left;
            overflow: visible;
        }

        .dropdown2 .dropbtn {
            font-size: 16px;
            border: none;
            outline: none;
            color: white;
            padding: 14px 16px;
            background-color: inherit;
            font-family: inherit;
            margin: 0;
        }

        .navbar a:hover, .dropdown2:hover .dropbtn {
            background-color: red;
        }

        .dropdown2-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }

        .dropdown2-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown2-content a:hover {
            background-color: #ddd;
        }

        .dropdown2:hover .dropdown2-content {
            display: block;
        }



    </style>
    <body style="background-color: whitesmoke">
        <div class="container">

            <div class="header" style="text-align: center">
                <img src="assets/duck.png" class="center-block" width="80" height="80" alt="duck"/>
            </div><br>
            <nav class="navbar-new navigation-clean">
                <ul class="shadow" style="list-style-type:none;
                    margin: 0;
                    padding: 0px;
                    overflow: auto;
                    background-color: #dc3545;
                    top: 0;">
                    <form action="Logout" method="POST" hidden="true">
                        <button type="submit" class="btn btn-lg" id="Logout"> Logout</button>
                    </form>
                    <li  id="tabmenulog" style="float: right;"><a href="login.jsp" onclick="Myfunction1();"><h5>Logout</h5></a></li>                

                    <div class="dropdown2" style="float: right; "id="forS8" hidden="true">
                        <button class="dropbtn"><h5>ICT <i class="fa fa-caret-down"></i> </h5> 

                        </button>
                        <div class="dropdown2-content"  >
                            <a href="?page=RequestList" >User request list</a>
                            <a href="?page=StaffManage">Staff manage</a>
                            <a href="?page=ResignReport">Report resign</a>
                            <a href="?page=UndisAddon">Unlock user(M3-Add on)</a>
                        </div>
                    </div> 
                    <li style="float: right;" id="tabmenu1"><a href="?page=ChangePW"><h5>Change password(M3-Add on)</h5></a></li> 
                    <li style="float: right;" id="tabmenu2"><a href="?page=TrackStatus"><h5>Track status</h5></a></li> 
                    <li style="float: right;" id="tabmenu3"><a href="?page=Listrequest"><h5>List request</h5></a></li>   
                    <li style="float: right;" id="tabmenu4" ><a href="?page=RequestUser"><h5>New request</h5></a></li>   
                </ul>
            </nav>   
        </div>
    </body>

    <section id="programs">
        <div class='centerDiv' id="vSection"></div>
    </section>

</html>

<script type="text/javascript">


    <% if (request.getParameter("page") != null) {%>
    $("#vSection").load("./programs/<%=request.getParameter("page").toString()%>.jsp");
    <%  } else {  %>
    $("#vSection").load("./programs/RequestUser.jsp");
    <% }%>


    $('.dropdown-item').on('click', function () {
        console.log(this.name);
//        alert(this.name);
        $("#vSection").load("./programs/" + this.name + ".jsp");
    });

    $(document).ready(function () {
//        var costc = "S8";

        var username = "<%out.print(session.getAttribute("namereq"));%>";
        var menu1 = document.getElementById("tabmenu1");
        var menu2 = document.getElementById("tabmenu2");
        var menu3 = document.getElementById("tabmenu3");
        var menu4 = document.getElementById("tabmenu4");
        var menu5 = document.getElementById("forS8");
        var menu6 = document.getElementById("tabmenulog");


        var costc = "<%out.print(session.getAttribute("ST_COSTC"));%>";
        if (costc === "S8") {
            document.getElementById("forS8").hidden = false;
        } else {
            document.getElementById("forS8").hidden = true;
        }

        console.log(username);
        if (username === "M3SRVICT") {

            menu1.style.visibility = "Hidden";
            menu2.style.visibility = "Hidden";
            menu3.style.visibility = "Hidden";
            menu4.style.visibility = "Hidden";
            menu5.style.visibility = "Hidden";
//              menu6.style.visibility = "Hidden";


        } else {

            menu1.style.visibility = "Visible";
            menu2.style.visibility = "Visible";
            menu3.style.visibility = "Visible";
            menu4.style.visibility = "Visible";
            menu5.style.visibility = "Visible";
//                 menu6.style.visibility = "Hidden";


        }

    });

    function Myfunction1() {
        $('#Logout').click();
    }
</script>

