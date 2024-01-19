<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="assets/jQuery-3.3.1/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="assets/Bootstrap-3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid.min.css" />
    <link rel="stylesheet" type="text/css" href="assets/jsgrid-1.5.3/dist/jsgrid-theme.min.css">

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


    <!--        <script src='dist/js/select2.min.js' type='text/javascript'></script>
    
    
            <link href='dist/css/select2.min.css' rel='stylesheet' type='text/css'>-->


    <title>Request User</title> 
    <link rel="icon" type="image/ico" href="images/user-icon-vector.jpg" />
</head>
<style>

    body {
        font-size: 16px;
    }

    .header1 {
        margin: 0;
        font-family: -apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,"Noto Sans",sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol","Noto Color Emoji";
        font-size: 1rem;
        font-weight: 400;
        line-height: 1.5;
        color: #212529;
        text-align: -webkit-center;
        background-color: whitesmoke;
    }
    ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        background-color: #dc3545;
        position: sticky;
        top: 0;
    }

    li {
        float: right;
    }

    li a {
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
    }

    li a:hover {
        background-color: #b3d7ff;
    }

    .active {
        background-color: #dc3545;
    }

    .form-control {
        display: block;
        width: 100%;
        height: 25px;
        padding: 0rem 0.75rem;
        font-size: 0.9rem;
        font-weight: 400;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ced4da;
        border-radius: 0.25rem;
        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    }
    .col-md-2 {
        -ms-flex: 0 0 16.666667%;
        flex: 0 0 16.666667%;
        max-width: 16.666667%;
    }

    .form-control2 {
        display: block;
        width: 100%;
        height: 27px;
        /*padding: 0.375rem 0.75rem;*/
        font-size: 0.9rem;
        font-weight: 400;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ced4da;
        border-radius: 0.25rem;
        transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
    }

    .btn-lg {
        padding:  auto;
        font-size: 13px;
        font-weight: bold;
        line-height: normal;
        border-radius: 28px;
    }
    .jsgrid {
        position: relative;
        overflow: hidden;
        font-size: 13px;
    }

</style>
<%

    String USREQNO = "";
    String USLINE = "";

    if (request.getParameter("USREQNO") != null) {
        USREQNO = request.getParameter("USREQNO");

    }
    if (request.getParameter("USLINE") != null) {
        USLINE = request.getParameter("USLINE");

    }

    session.setAttribute("USREQNO", USREQNO);
    session.setAttribute("USLINE", USLINE);


%>

<body style="background-color: whitesmoke">
    <div class="container">
        <div class="header1">
            <img src="assets/duck.png" class="center-block" width="80" height="80" alt="duck"/>
        </div><br>
        <nav class="navbar-new navbar-default navigation-clean">
            <ul class="shadow">
                <li>
                    <form action="Logout" method="POST">
                        <button type="submit" class="btn btn-lg" id="Logout" style="color: #ffffff">Logout</button>
                    </form>
                </li>
            </ul>
        </nav>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel='stylesheet' type='text/css'>
    </div>
    <br>
    <div class="container">
        <div class="card border-center-primary shadow h-100 py-2">
            <div class="card-body">
                <div class="content">
                    <div class="row show-grid col-md-12">
                        <div class="offset-sm-9">

                        </div>  
                        <div class="col-sm-2">
                            <lable for="reqno">Request No.</lable>
                            <input  type="text" id="USREQNO" class="form-control text-center" readonly="true" style="font-weight: 500" value="">  
                        </div>
                        <div class="col-sm-1">
                            <lable for="line">Line</lable>
                            <input type="text" id="USLINE"  class="form-control text-center" readonly="true" value="1" style="font-weight: 500;width: 100%">                     
                        </div>
                    </div>
                    <form id="Myform" >
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect"style="font-weight: bold;">User group : &nbsp;</label>
                            </div>
                            <div class="col-md-4" id="fordisable">
                                <label for="disabledSelect"style="font-weight: bold;">User type : &nbsp;</label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label class="radio-inline"> <input disabled="true" type="radio" name="optionsRadio" id="M3INFO" value="M3INFO" onclick="selectgroup()"checked="">&nbsp;M3-Info </label>   <label style="color: #007bff;font-size: 12px; font-weight: bold;">(Request by user's head)</label>
                            </div>
                            <div class="col-md-2">
                                <label class="checkboxinline1"> <input   disabled="true" type="radio" name="checkboxinline" id="CheckboxNew" value="New" checked="" onclick="selecttype()">&nbsp;New &nbsp;</label> 
                            </div>
                            <div class="col-md-6" id="TYPEGROUP">
                                <label id="lbllike" class="form-row">Like user :&nbsp;&nbsp;
                                    <input type="text" class="form-control" id="USLIKE" value="" name="Like" maxlength="60" style="width: 350px;background: #ffffff" readonly="true"></label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label class="radio-inline"> <input  disabled="true" type="radio" name="optionsRadio" id="AddOnM3" value="AddOnM3" onclick="selectgroup()">&nbsp;M3-Add on</label> <label style="color: #007bff;font-size: 12px; font-weight: bold;">(SRN,PRV,CAPEX,Incoming LD,..)</label>
                            </div>
                            <div class="col-md-2">
                                <label class="checkboxinline1"> <input  disabled="true" type="radio" name="checkboxinline" id="CheckboxAddF" value="AddFunc" onclick="selecttype()" >&nbsp;Add function &nbsp;</label> 
                            </div>
                            <div class="col-md-6" id="TYPEGROUP">
                                <label class="form-row">Function :&nbsp;&nbsp;
                                    <input type="text" class="form-control" id="USAFUNC" name="USAFUNC"  value="" maxlength="60" style="width: 350px;background: #ffffff" readonly="true">
                                </label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label class="radio-inline"> <input  disabled="true" type="radio" name="optionsRadio" id="EPR" value="EPR" onclick="selectgroup()">&nbsp;EPR&nbsp;</label>&nbsp;&nbsp;
                                <label class="radio-inline"> <input disabled="true" type="radio" name="optionsRadio" id="ESR" value="ESR" onclick="selectgroup()">&nbsp;ESR</label> <label style="color: #007bff;font-size: 12px; font-weight: bold;">(Stationery)</label>
                            </div>
                            <div class="col-md-2" id="fordisable">
                                <label class="checkboxinline1"> <input  disabled="true" type="radio" name="checkboxinline" id="CheckboxDelF" value="DelFunc" onclick="selecttype()" > Delete function</label>                    
                            </div>
                            <div class="col-md-5" id="TYPEGROUP">
                                <label class="form-row">Function :&nbsp;&nbsp;
                                    <input type="text" class="form-control" id="USDFUNC" name="USDFUNC" value="" maxlength="60" style="width: 350px;background: #ffffff"readonly="true">
                                </label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label class="radio-inline"> <input disabled="true" type="radio" name="optionsRadio" id="CRM" value="CRM" onclick="selectgroup()">&nbsp;CRM&nbsp;</label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">                               
                                <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                                <label class="radio-inline"> <input  disabled="true" type="radio" name="optionsRadio" id="Xiqma" value="Xiqma"   onclick="selectgroup()">&nbsp;Xiqma&nbsp;</label>                                                         
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect" class="form-row" style="font-weight: bold;">Description :</label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <!--<div class="col-md-3" >-->
                            <div style="width:230px;height:50px;padding-left: 21px;">
                                <label for="EMPCODE" class="form-row">&nbsp;&nbsp;Staff code :&nbsp;
                                    <input type="text" class="form-control text-center" id="USCODE" name="USCODE"  maxlength="10" style="width: 120px;background-color: #03a9f412;" onkeydown="" readonly="true" >  
                                </label>
                            </div>                        
                            <div style="width:210px;height:50px;padding-left: 10px;">
                                <label for="name" class="form-row">Name :&nbsp;
                                    <input type="text" class="form-control text-center" id="USNAME1" onkeyup='check_EN(this)' name="USNAME1" value="" style="width: 150px; background-color: #03a9f412;" readonly="true">
                                </label>
                            </div>
                            <div style="width:270px;height:50px;padding-left: 5px;">
                                <label for="surename" class="form-row">Last name :&nbsp;
                                    <input type="text" class="form-control text-center" id="USNAME2" onkeyup='check_EN(this)'name="USNAME2" value="" maxlength="30" style="width: 185px;background-color: #03a9f412;"readonly="true">
                                </label>
                            </div>
                            <div style="width:300px;height:50px;padding-left: 5px;">
                                <label for="email" class="form-row">&nbsp;Email :&nbsp;
                                    <input  type="text" class="form-control" id="USMAIL"  name="USMAIL" maxlength="30" style="width: 250px;background-color: #03a9f412;" readonly="true">
                                </label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div class="col-md-4">
                                <label for="disabledSelect" class="form-row" style="font-weight: bold;">Request for :</label>
                            </div>
                        </div>
                        <div class="row show-grid">
                            <div style="width:300px;height:50px;padding-left: 25px;">
                                <label for="email" class="form-row">&nbsp;Company :&nbsp;
                                    <input  type="text" class="form-control text-center" id="vCompany"  name="vCompany" maxlength="30" style="width: 170px; background: #ffffff" readonly="true">
                                </label>
                            </div>
                      
                            <div style="width:700px;height:50px;padding-left: 5px;">
                                <label class="form-row" style="padding-left: 0px;">Remark (ICT): &nbsp;
                                    <input type="text" class="form-control" id="Remark"  value="" maxlength="60" readonly="true" style="width: 550px; background: #ffffff" readonly="true">
                                </label>
                            </div>
                            <div class="col-md-2" hidden="true">
                                <label>User name :
                                    <input type="text" class="form-control" id="Username" name="Username" readonly="ture">           
                                </label>
                            </div>
                            <div class="col-md-4">
                                <label class="form-row" style="padding-left: 15px;">Created by : &nbsp;
                                    <input type="text" id="USCREBY" class="form-control text-center" readonly="true" style="width: 156px; background: #ffffff" >
                                </label>    
                            </div>
                        </div>
              
                        <div class="row show-grid">
                            <div class="col-md-12 text-center"> 
                                <br><button type="button" id="Acknowledge" class="btn-danger btn-lg">Acknowledge</button>                                                  
                            </div>  
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<br>   
<script>

    function Myfunction1() {
        $('#Logout').click();
//     
    }
    $(document).ready(function () {

        var USREQNO = <%out.print(session.getAttribute("USREQNO"));%>
        var USLINE = <%out.print(session.getAttribute("USLINE"));%>

        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "Company"
            },
            success: function (getdata) {
                $.each(getdata.data, function (i, obj) {
                    var div_data = "<option value=" + obj.CCCONO + ">" + obj.CCCONO + " : " + obj.CCTX15 + "</option>";
//                    console.log(div_data)
                    $(div_data).appendTo('#vCompany');
                });
            }
        });
        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "Data_Acknowledge",
                USREQNO: USREQNO,
                USLINE: USLINE
            },
            async: false
        }).done(function (response) {


            $("#fordisable :input").prop("disabled", true);
            $("#fordisable2 :input").prop("disabled", true);
            $.each(response, function (i, obj) {


                document.getElementById("USREQNO").value = obj.USREQNO;
                document.getElementById("USLINE").value = obj.USLINE;
                document.getElementById("vCompany").value = obj.COMPANY;
                document.getElementById("USCODE").value = obj.USCODE;
                document.getElementById("USNAME1").value = obj.USNAME1;
                document.getElementById("USNAME2").value = obj.USNAME2;
                document.getElementById("USMAIL").value = obj.USMAIL;
//                document.getElementById("USCOSTC").value = obj.Costc;
                document.getElementById("Remark").value = obj.USREMK;
                document.getElementById("USLIKE").value = obj.USLIKE;
                document.getElementById("USAFUNC").value = obj.USAFUNC;
                document.getElementById("USDFUNC").value = obj.USDFUNC;
                document.getElementById("Username").value = obj.username;
                document.getElementById("USCREBY").value = obj.USCREBY;
                if (obj.USGROUP === "Xiqma") {
                    document.getElementById("Xiqma").checked = true;
                } else if (obj.USGROUP === "AddOnM3") {
                    document.getElementById("AddOnM3").checked = true;
                } else if (obj.USGROUP === "EPR") {
                    document.getElementById("EPR").checked = true;
                } else if (obj.USGROUP === "CRM") {
                    document.getElementById("CRM").checked = true;
                } else if (obj.USGROUP === "M3INFO") {
                    document.getElementById("M3INFO").checked = true;
                }

                if (obj.USTYPE === "AddFunc") {
                    document.getElementById("CheckboxAddF").checked = true;
                    document.getElementById("USAFUNC").hidden = false;
                   
                } else if (obj.USTYPE === "DelFunc") {
                    document.getElementById("CheckboxDelF").checked = true;
                    document.getElementById("USDFUNC").hidden = false;
                    
                } else {
                    document.getElementById("CheckboxNew").checked = true;
                    document.getElementById("USLIKE").hidden = false;
                    document.getElementById("lbllike").hidden = false;
                }
            });
        });
    });

    $("#Acknowledge").click(function (e) {

        $.ajax({
            url: './Sync',
            type: 'POST',
            dataType: 'json',
            data: {
                page: "Acknowledge",
                USREQNO: $("#USREQNO").val(),
                USLINE: $("#USLINE").val(),
                USACKBY: $("#Username").val()
            },
            async: false
        }).done(function (response) {

        });

        alert("Update status complete.");
        $("#Logout").click();


    });



</script>
