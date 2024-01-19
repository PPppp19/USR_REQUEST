<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<%
    if (session.getAttribute("namereq") == null) {
        response.sendRedirect("login.jsp");
    }
%>

<style>

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
    .select2-selection{
        text-align: center;

    } .jsgrid-edit-row input, .jsgrid-edit-row select, .jsgrid-edit-row textarea, .jsgrid-filter-row input, .jsgrid-filter-row select, .jsgrid-filter-row textarea, .jsgrid-insert-row input, .jsgrid-insert-row select, .jsgrid-insert-row textarea {
        width: 100%;
        padding: .3em .5em;
        height: 20px;
    }

</style>
<br>
<div class="container">
    <div id="jsGrid1" class="shadow"></div><br>
    <div class="card border-center-primary shadow h-100 py-2" id="detailform" hidden="true">
        <div class="card-body">
            <div class="content">
                <div class="row show-grid col-md-12">
                    <div class="col-md-9 ">
                        <a href="?page=Listrequest">Back</a> 
                    </div>
                    <div class="col-sm-2">
                        <lable for="reqno">Request No.</lable>
                        <input  type="text" id="reqno" class="form-control text-center" disabled="true" style="font-weight: 500" value="">  
                    </div>
                    <div class="col-sm-1">
                        <lable for="line">Line</lable>
                        <input type="text" id="line"  class="form-control text-center" disabled="true" value="1" style="font-weight: 500;width: 100%">                     
                    </div>
                </div>
                <form id="Myform" method="POST" action="./UploadFile" enctype="multipart/form-data" acceptcharset="UTF-8">
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
                            <label class="radio-inline"> <input type="radio" name="optionsRadio" id="M3INFO" value="M3INFO" onclick="selectgroup()"checked="">&nbsp;M3-Info </label>   <label style="color: #007bff;font-size: 12px; font-weight: bold;">(Request by user's head)</label>
                        </div>
                        <div class="col-md-2">
                            <label class="checkboxinline1"> <input  type="radio" name="checkboxinline" id="CheckboxNew" value="New" checked="" onclick="selecttype()">&nbsp;New &nbsp;</label> 
                        </div>
                        <div class="col-md-4" id="TYPEGROUP">
                            <label id="lbllike" class="form-row">Like user :&nbsp;&nbsp;
                                <input type="text" class="form-control" id="Like" value="" name="Like" maxlength="60" style="width: 230px;"></label>
                            <label id="lbltype" hidden="true" class="form-row">User type :&nbsp;
                                <select class="form-control select2-search--dropdown"  name="UserType" id="UserType" onchange="SelectEPR();"hidden="true" style="width: auto;">
                                    <option value="" selected="selected">Select Type</option>
                                    <option value="Requester">Requester</option>
                                    <option value="Approval">Approval</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-4">
                            <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <label class="radio-inline"> <input type="radio" name="optionsRadio" id="AddOnM3" value="AddOnM3" onclick="selectgroup()"> M3-Add on</label> <label style="color: #007bff;font-size: 12px; font-weight: bold;">(SRN,PRV,CAPEX,Incoming LD,..)</label>
                        </div>
                        <div class="col-md-2">
                            <label class="checkboxinline1"> <input  type="radio" name="checkboxinline" id="CheckboxAddF" value="AddFunc" onclick="selecttype()" >&nbsp;Add function &nbsp;</label> 
                        </div>
                        <div class="col-md-4" id="TYPEGROUP">
                            <label class="form-row">Function :&nbsp;&nbsp;
                                <input type="text" class="form-control" id="AddFun" name="AddFun" disabled="true" value="" maxlength="60" style="width: 230px;">
                            </label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-4">
                            <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <label class="radio-inline"> <input type="radio" name="optionsRadio" id="EPR" value="EPR" onclick="selectgroup()">&nbsp;EPR&nbsp;</label>&nbsp;&nbsp;
                            <label class="radio-inline"> <input type="radio" name="optionsRadio" id="ESR" value="ESR" onclick="selectgroup()">&nbsp;ESR</label> <label style="color: #007bff;font-size: 12px; font-weight: bold;">(Stationery)</label>
                        </div>
                        <div class="col-md-2" id="fordisable">
                            <label class="checkboxinline1"> <input  type="radio" name="checkboxinline" id="CheckboxDelF" value="DelFunc" onclick="selecttype()" > Delete function</label>                    
                        </div>
                        <div class="col-md-4" id="TYPEGROUP">
                            <label class="form-row">Function :&nbsp;&nbsp;
                                <input type="text" class="form-control" id="DelFun" name="DelFun" disabled="true" value="" maxlength="60" style="width: 230px;">
                            </label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-4">
                            <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <label class="radio-inline"> <input type="radio" name="optionsRadio" id="CRM" value="CRM" onclick="selectgroup()">&nbsp;CRM&nbsp;</label>
                        </div>
                        <div class="offset-md-2 col-md-4" id="TYPEGROUP">
                            <label class="form-row">File :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="file"id="File" name="File" disabled="true" hidden="true" style="width: 230px;" >
                            </label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-6">
                            <label for="disabledSelect" style="color:white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                            <label class="radio-inline"> <input type="radio" name="optionsRadio" id="Xiqma" value="Xiqma"   onclick="selectgroup()">&nbsp;Xiqma&nbsp;</label> <label style="color: #007bff;font-size: 12px; font-weight: bold;"> (Leave request, Material request,New Supplier, New Customer,....)</label>                            
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-4">
                            <label for="disabledSelect" class="form-row" style="font-weight: bold;">Description :</label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div style="width:230px;height:50px;padding-left: 10px;">
                            <label for="EMPCODE" class="form-row" style="width:230px;height:50px;padding-left: 15px;">Staff code :&nbsp;
                                <select class="select2-container--default"  name="empcode" id="empcode"  style="width: 126px; text-align: center" onchange="GetDetail()">
                                    <option  value="" selected="selected"></option>
                                </select>
                            </label>
                        </div>                        
                        <div style="width:210px;height:50px;padding-left: 12px;">
                            <label for="name" class="form-row">Name :&nbsp;
                                <input type="text" class="form-control" id="name" name="name" value="" style="width: 150px; background-color: #03a9f412;" readonly="true">
                            </label>
                        </div>
                        <div style="width:270px;height:50px;padding-left: 5px;">
                            <label for="surename" class="form-row">&nbsp;Last name :&nbsp;
                                <input type="text" class="form-control" id="lastname" name="lastname" value="" maxlength="30" style="width: 185px;background-color: #03a9f412;"readonly="true">
                            </label>
                        </div>
                        <div style="width:300px;height:50px;padding-left: 5px;">
                            <label for="email" class="form-row">&nbsp;Email :&nbsp;
                                <input  type="text" class="form-control" id="email"  name="email" maxlength="50" style="width: 250px;background-color: #03a9f412;" readonly="true">
                            </label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-4">
                            <label for="disabledSelect" class="form-row" style="font-weight: bold;">Request for :</label>
                        </div>
                    </div>
                    <div class="row show-grid">
                        <div class="col-md-4" >    
                            <label for="disabledSelect"class="form-row"style="padding-left: 15px">Company :&nbsp;
                                <select class="form-control select2-search--dropdown"  name="vCompany" id="vCompany" style="width: 200px">
                                    <option value="" >Select company</option>
                                </select>
                            </label>
                        </div>
                        <div class="col-md-4">
                            <label id="lblmail" class="form-row">To :&nbsp;
                                <select class="form-control select2-search--dropdown"  name="MailCreator" id="MailCreator" style="width: 250px">
                                    <option value="" selected="selected">Select Creator</option>
                                </select>
                            </label>
                        </div>
                    </div>
                    <br>
                    <div class="row show-grid">
                        <div class="col-md-12 text-center"> 
                            <button type="submit" id="Add" class="btn-success btn-lg">Add Request</button>                       
                            <button type="button" id="Save" class="btn-primary btn-lg" >&nbsp;&nbsp;Save&nbsp;&nbsp;</button>
                            <button type="button" id="Send" class="btn-danger btn-lg">Send Request</button>
                            <button type="reset"  id="reset" class="btn-success btn-lg" onclick="Reset();" hidden="true">Reset</button>
                            <button type="button" id="Acknowledge" name="Acknowledge" class="btn-danger btn-lg">Acknowledge</button>          
                        </div>  
                    </div>
                </form>
                <form name="Mail" id="Mail" method="GET" action="./sendmail">
                    <input type="text"  id="reqnoformail" name="reqnoformail" hidden="true">
                    <button type="submit" name="SendMail" id="SendMail" value="SendMail" hidden="true"></button>
                </form>
            </div>
        </div>
    </div>
    <br>
    <div id="jsGrid3"></div>
    <br>
    <br>
</div>

<script>

    $("#empcode").select2();

    $('#Myform').keydown(function (e) {
        if (e.keyCode === 13) {
            e.preventDefault();
            return false;
        }
    });

    var search = document.getElementById("empcode");
    search.addEventListener("keyup", function (event) {
        if (event.keyCode === 13) {
//            alert(this.id);
            GetDetail()();
        }
    });

    function GetDetail() {

        var ST_CODE = document.getElementById("empcode").value;
        document.getElementById("email").value = "";
        document.getElementById("vCompany").value = "";
        document.getElementById("name").value = "";
        document.getElementById("lastname").value = "";
        console.log(ST_CODE);

        $.ajax({
            url: './Sync',
            type: 'GET',
            dataType: 'json',
            data: {
                page: "DetailUser",
                ST_CODE: ST_CODE
            },
            async: false
        }).done(function (response) {

            $.each(response, function (i, obj) {
                var div_ST_CONO = obj.ST_CONO;
                var div_ST_N6L3 = obj.ST_N6L3;
                var div_ST_EMAIL = obj.ST_EMAIL;
                var div_ST_CODE = obj.ST_CODE;

                var div_ST_EPNM = obj.ST_EPNM;
                var div_ST_ENAME = obj.ST_ENAME;
                var div_ST_ELNAME = obj.ST_ELNAME;
                var div_ST_TPNM = obj.ST_TPNM;
                var div_ST_TNAME = obj.ST_TNAME;
                var div_ST_TLNAME = obj.ST_TLNAME;

                document.getElementById("email").value = div_ST_EMAIL;
                document.getElementById("vCompany").value = div_ST_CONO;

                document.getElementById("name").value = div_ST_ENAME;
                document.getElementById("lastname").value = div_ST_ELNAME;
            });
        });

    }

    $(document).ready(function () {

    <% System.out.println("Path : " + request.getContextPath());%>

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
                    //console.log(div_data)
                    $(div_data).appendTo('#vCompany');
                });
            }
        });

        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "MailCreator"
            },
            success: function (getdata) {
                $.each(getdata.data, function (i, obj) {
                    var div_data = "<option value=" + obj.ST_EMAIL + ">" + obj.ST_ENAME + " : " + obj.ST_EMAIL + "</option>";
//                    console.log(div_data);
                    $(div_data).appendTo('#MailCreator');
                });
            }
        });

        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "EmpCodeList"
            },
            success: function (getdata) {
                $.each(getdata.data, function (i, obj) {
                    var div_data = "<option value=" + obj.ST_CODE + ">" + obj.ST_CODE + "</option>";
//                    console.log(div_data);
                    $(div_data).appendTo('#empcode');
                });
            }
        });

    });

    $("#jsGrid1").jsGrid({
        width: "100%",
        height: "auto",
        sorting: true,
        paging: true,
        autoload: true,
        filtering: true,
        pageSize: 10,
        pageButtonCount: 5,
        controller: {
            loadData: function (filter) {
                var USUREQ = "<%out.print(session.getAttribute("namereq"));%>";
                var data = $.Deferred();

                $.ajax({
                    type: 'GET',
                    url: './Sync',
                    dataType: 'json',
                    data: {
                        page: "Request_List2",
                        USUREQ: USUREQ
                    },
                    async: false
                }).done(function (response) {
                    response = $.grep(response, function (item) {
                        return (!filter.USREQNO || (item.USREQNO.indexOf(filter.USREQNO) > -1))
                                && (!filter.USLINE.toUpperCase() || (item.USLINE.indexOf(filter.USLINE.toUpperCase()) > -1))
                                && (!filter.USCOMP.toUpperCase() || (item.USCOMP.indexOf(filter.USCOMP.toUpperCase()) > -1))
                                && (!filter.USNAME1.toUpperCase() || (item.USNAME1.indexOf(filter.USNAME1.toUpperCase()) > -1))
                                && (!filter.USNAME2.toUpperCase() || (item.USNAME2.indexOf(filter.USNAME2.toUpperCase()) > -1))
//                                && (!filter.USCOSTC.toUpperCase() || (item.USCOSTC.indexOf(filter.USCOSTC.toUpperCase()) > -1))
                                && (!filter.USGROUP || (item.USGROUP.indexOf(filter.USGROUP) > -1))
                                && (!filter.USTYPE || (item.USTYPE.indexOf(filter.USTYPE) > -1))
                                && (!filter.DATE.toUpperCase() || (item.DATE.indexOf(filter.DATE.toUpperCase()) > -1));

                    });
                    console.log(response);
                    data.resolve(response);
                });
                return data.promise();
            }
        },

        fields: [

            {title: "REQNO", name: "USREQNO", type: "text", editing: false, align: "center", width: 75},
            {title: "Line", name: "USLINE", type: "text", align: "center", width: 50},
            {title: "Company", name: "USCOMP", type: "text", align: "center", width: 80, editing: true},
            {title: "Name", name: "USNAME1", type: "text", align: "left"},
            {title: "Last Name", name: "USNAME2", type: "text", align: "left", width: 130},
            {title: "E-Mail", name: "USMAIL", type: "text", align: "left", width: 200},
//            {title: "CostC.", name: "USCOSTC", type: "text", align: "center", width: 90},
            {title: "Group", name: "USGROUP", type: "text", align: "center", width: 90},
            {title: "Type", name: "USTYPE", type: "text", align: "center", width: 65},
            {title: "Date", name: "DATE", type: "text", align: "center"},
            {title: "STATUS", name: "Status", type: "text", align: "center", filtering: false, width: 150}
        ],
        rowClick: function (args) {
//            console.log(args.item);
            showDetailsDialog1("GET", args.item);
        }

    });




    var showDetailsDialog1 = function (dialogType, client) {
        document.getElementById("detailform").hidden = false;
        $("#jsGrid1").hide();
        var input = "" + client.USTYPE;
        var fields = input.split(',');
        var Like = client.USLIKE;
        var AddFunc = client.USAFUNC;
        var DelFunc = client.USDFUNC;
        document.getElementById("Like").value = Like;
        document.getElementById("AddFun").value = AddFunc;
        document.getElementById("DelFun").value = DelFunc;



//        document.getElementById("Username").value = client.USERNAME;
        $("#reqno").val(client.USREQNO);
        document.getElementById("reqnoformail").value = "" + $("#reqno").val();
        $("#line").val(client.USLINE);
        $("#empcode").select2().val(client.USCODE).trigger("change");
        $("#MailCreator").val(client.USEMCRE);
        document.getElementById("name").value = client.USNAME1;
        document.getElementById("lastname").value = client.USNAME2;
        document.getElementById("email").value = client.USMAIL;


        if (client.Status === "Not send") {
            $("#Send").show();
            $("#Add").hide();
            $("#Save").show();
            $("#Acknowledge").hide();
            $("#TYPEGROUP :input").prop("disabled", true);
            $("#fordisable :input").prop("disabled", true);
        } else {
            $("#Send").hide();
            $("#Add").hide();
            $("#Save").hide();
            $("#Acknowledge").show();
            $("#jsGrid3").hide();
        }


//        $("#Send").show();
//        $("#Add").hide();
//        $("#Save").show();
//        $("#TYPEGROUP :input").prop("disabled", true);
//        $("#fordisable :input").prop("disabled", true);
        $("#vCompany").val(client.USCOMP);

        $('input[name="checkboxinline"]').each(function () {
            this.checked = false;
        });


        if (client.USGROUP === "Xiqma") {
            document.getElementById("Xiqma").checked = true;
        } else if (client.USGROUP === "AddOnM3") {
            document.getElementById("AddOnM3").checked = true;
        } else if (client.USGROUP === "EPR") {
            document.getElementById("EPR").checked = true;
            document.getElementById("lbllike").hidden = true;
            document.getElementById("lbltype").hidden = false;
        } else if (client.USGROUP === "CRM") {
            document.getElementById("CRM").checked = true;
        } else if (client.USGROUP === "M3INFO") {
            document.getElementById("M3INFO").checked = true;
        } else if (client.USGROUP === "ESR") {
            document.getElementById("ESR").checked = true;
        }
//        
        for (var i = 0; i < 3; i++) {
            var type = fields[i];

            if (type === "New" && client.USGROUP !== "M3INFO" && (client.USGROUP !== "ESR" && client.USGROUP !== "EPR")) {

                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;

                document.getElementById("CheckboxNew").checked = true;
                document.getElementById("Like").disabled = true;
                document.getElementById("CheckboxNew").disabled = false;
                document.getElementById("CheckboxAddF").disabled = true;
                document.getElementById("CheckboxDelF").disabled = true;

            } else if (type === "New" && client.USGROUP === "M3INFO") {

                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;

                document.getElementById("CheckboxNew").checked = true;
                document.getElementById("Like").disabled = false;
                document.getElementById("CheckboxNew").disabled = false;

            } else if (type === "AddFunc" && client.USGROUP === "M3INFO") {

                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;

                document.getElementById("CheckboxAddF").checked = true;
                document.getElementById("AddFun").disabled = false;
                document.getElementById("CheckboxAddF").disabled = false;

            } else if (type === "DelFunc" && client.USGROUP === "M3INFO") {

                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;

                document.getElementById("CheckboxDelF").checked = true;
                document.getElementById("DelFun").disabled = false;
                document.getElementById("CheckboxDelF").disabled = false;
                document.getElementById("File").disabled = false;
                document.getElementById("File").hidden = false;

            } else if (type === "New" && (client.USGROUP === "ESR" || client.USGROUP === "EPR")) {
                document.getElementById("CheckboxNew").checked = true;
                document.getElementById("CheckboxAddF").disabled = true;
                document.getElementById("CheckboxDelF").disabled = true;
                document.getElementById("lbltype").hidden = false;
                document.getElementById("UserType").hidden = false;
                document.getElementById("UserType").disabled = false;
                document.getElementById("UserType").value = Like;
                document.getElementById("Like").hidden = true;
                document.getElementById("lbllike").hidden = true;
                document.getElementById("Like").disabled = false;
                document.getElementById("Like").value = Like;
            }

        }

        $("#jsGrid3").jsGrid({
            width: "100%",
            height: "auto",
            sorting: true,
            paging: true,
            autoload: true,
            pageSize: 10,
            pageButtonCount: 5,
            deleteConfirm: function (item) {
                return "Requser No. " + item.USREQNO + " Line : " + item.USLINE + " will be removed. Are you sure?";
            },
            controller: {
                loadData: function (filter) {
                    var data = $.Deferred();
                    var cono = <%out.print(session.getAttribute("cono"));%>
                    var divi = <%out.print(session.getAttribute("divi"));%>
                    var reqno = document.getElementById("reqno").value;
                    $.ajax({
                        type: 'GET',
                        url: './Sync',
                        dataType: 'json',
                        data: {
                            page: "Table_USRREQ",
                            USREQNO: $("#reqno").val()
                        },
                        async: false
                    }).done(function (response) {
                        console.log(response);
                        data.resolve(response);
                    });
                    return data.promise();
                },
                deleteItem: function (item) {
                    console.log(item);
                    item.page = "DeleteLine";
                    $.ajax({
                        type: "POST",
                        url: "./Sync",
                        async: false,
                        cache: false,
                        data: item
                    });
                    $("#jsGrid3").jsGrid("loadData");
                    $("#reset").click();
                    GetMaxLine();
                    $("#Add").show();
                    $("#Send").show();
                    $("#Save").hide();
                    selectgroup();
                    document.getElementById("AddFun").disabled = true;
                    document.getElementById("DelFun").disabled = true;
                    document.getElementById("MailCreator").value = "";
                }
            },
            fields: [
                {type: "control", editButton: false, deleteButton: true, width: 50},
                {title: "REQNO", name: "USREQNO", type: "text", editing: false, align: "center", width: 75},
                {title: "Line", name: "USLINE", type: "text", align: "center", width: 50},
                {title: "Company", name: "USCOMP", type: "text", align: "center", width: 80, editing: true},
                {title: "Code", name: "USCODE", type: "text", editing: false, align: "center", width: 75},
                {title: "Name", name: "USNAME1", type: "text", align: "left"},
                {title: "Last Name", name: "USNAME2", type: "text", align: "left", width: 150},
                {title: "E-Mail", name: "USMAIL", type: "text", align: "left", width: 250},
//                    {title: "Costc.", name: "USCOSTC", type: "text", align: "center"},
                {title: "Group", name: "USGROUP", type: "text", align: "center"},
                {title: "Type", name: "USTYPE", type: "text", align: "center"},
                {title: "Like User", name: "USLIKE", type: "text", align: "left", width: 200},
                {title: "Add Function", name: "USAFUNC", type: "text", align: "left", width: 200},
                {title: "Delete Function", name: "USDFUNC", type: "text", align: "left", width: 200},
                {title: "Date", name: "USREQDT", type: "text", align: "center"},
                {title: "Creator", name: "USEMCRE", type: "text", align: "left", width: 250}
            ],
            rowClick: function (args) {
//            console.log(args.item);
                showDetailsDialog2("GET", args.item);
            }
        });


    };


    var showDetailsDialog2 = function (dialogType, client) {

        var input = "" + client.USTYPE;
        document.getElementById("Save").hidden = false;
        var fields = input.split(',');
        var Like = client.USLIKE;
        var AddFunc = client.USAFUNC;
        var DelFunc = client.USDFUNC;
        document.getElementById("Like").value = Like;
        document.getElementById("AddFun").value = AddFunc;
        document.getElementById("DelFun").value = DelFunc;

        $("#reqno").val(client.USREQNO);
        $("#line").val(client.USLINE);
        $("#empcode").select2().val(client.USCODE).trigger("change");
        $("#MailCreator").val(client.USEMCRE);
        document.getElementById("name").value = client.USNAME1;
        document.getElementById("lastname").value = client.USNAME2;
        document.getElementById("email").value = client.USMAIL;

        $("#Send").hide();
        $("#Add").hide();
        $("#Save").show();
        $("#TYPEGROUP :input").prop("disabled", true);
        $("#fordisable :input").prop("disabled", true);
        $("#vCompany").val(client.USCOMP);

        $('input[name="checkboxinline"]').each(function () {
            this.checked = false;
        });
        document.getElementById("UserType").disabled = false;

        if (client.USGROUP === "Xiqma") {
            document.getElementById("Xiqma").checked = true;
        } else if (client.USGROUP === "AddOnM3") {
            document.getElementById("AddOnM3").checked = true;
        } else if (client.USGROUP === "EPR") {
            document.getElementById("EPR").checked = true;
            document.getElementById("UserType").disabled = false;
            document.getElementById("UserType").value = client.USLIKE;
        } else if (client.USGROUP === "ESR") {
            document.getElementById("ESR").checked = true;
            document.getElementById("UserType").disabled = false;
            document.getElementById("UserType").value = client.USLIKE;
        } else if (client.USGROUP === "CRM") {
            document.getElementById("CRM").checked = true;
        } else if (client.USGROUP === "M3INFO") {
            document.getElementById("M3INFO").checked = true;
        }

        for (var i = 0; i < 3; i++) {
            var type = fields[i];

            if (type === "New" && client.USGROUP !== "M3INFO" && (client.USGROUP !== "ESR" && client.USGROUP !== "EPR")) {
                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;

                document.getElementById("CheckboxNew").checked = true;
                document.getElementById("Like").disabled = true;
                document.getElementById("CheckboxNew").disabled = false;
                document.getElementById("CheckboxAddF").disabled = true;
                document.getElementById("CheckboxDelF").disabled = true;
                document.getElementById("DelFun").disabled = true;
                document.getElementById("AddFun").disabled = true;

            } else if (type === "New" && client.USGROUP === "M3INFO") {
                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;
                document.getElementById("CheckboxAddF").disabled = false;
                document.getElementById("CheckboxDelF").disabled = false;
                document.getElementById("CheckboxNew").checked = true;
                document.getElementById("Like").disabled = false;
                document.getElementById("CheckboxNew").disabled = false;
                document.getElementById("AddFun").disabled = true;
                document.getElementById("DelFun").disabled = true;

            } else if (type === "AddFunc" && client.USGROUP === "M3INFO") {
                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;
                document.getElementById("CheckboxAddF").disabled = false;
                document.getElementById("CheckboxDelF").disabled = false;
                document.getElementById("CheckboxAddF").checked = true;
                document.getElementById("AddFun").disabled = false;
                document.getElementById("CheckboxAddF").disabled = false;
                document.getElementById("Like").disabled = true;
                document.getElementById("DelFun").disabled = true;

            } else if (type === "DelFunc" && client.USGROUP === "M3INFO") {
                document.getElementById("lbltype").hidden = true;
                document.getElementById("UserType").hidden = true;
                document.getElementById("Like").hidden = false;
                document.getElementById("lbllike").hidden = false;
                document.getElementById("File").hidden = false;
                document.getElementById("File").disabled = false;

                document.getElementById("CheckboxAddF").disabled = false;
                document.getElementById("CheckboxDelF").disabled = false;
                document.getElementById("CheckboxDelF").checked = true;
                document.getElementById("DelFun").disabled = false;
                document.getElementById("CheckboxDelF").disabled = false;
                document.getElementById("Like").disabled = true;
                document.getElementById("AddFun").disabled = true;

            } else if (type === "New" && (client.USGROUP === "ESR" || client.USGROUP === "EPR")) {
                document.getElementById("CheckboxNew").checked = true;
                document.getElementById("CheckboxAddF").disabled = true;
                document.getElementById("CheckboxDelF").disabled = true;
                document.getElementById("lbltype").hidden = false;
                document.getElementById("UserType").hidden = false;
                document.getElementById("UserType").disabled = false;
                document.getElementById("UserType").value = Like;
                document.getElementById("Like").hidden = true;
                document.getElementById("lbllike").hidden = true;
                document.getElementById("Like").disabled = false;
                document.getElementById("Like").value = Like;
                document.getElementById("DelFun").disabled = true;
                document.getElementById("AddFun").disabled = true;
            }

        }
    };

    function selectgroup() {

        var radioValue = $("input[name='optionsRadio']:checked").val();
        if (radioValue !== "M3INFO" && radioValue !== "EPR" && radioValue !== "ESR") {

            document.getElementById("CheckboxNew").checked = true;
            document.getElementById("CheckboxAddF").checked = false;
            document.getElementById("CheckboxDelF").checked = false;
            selecttype();
            document.getElementById("CheckboxAddF").disabled = true;
            document.getElementById("CheckboxDelF").disabled = true;
            SelectEPR();

        } else if (radioValue === "EPR" || radioValue === "ESR") {
            document.getElementById("CheckboxNew").checked = true;
            document.getElementById("CheckboxAddF").checked = false;
            document.getElementById("CheckboxDelF").checked = false;
            selecttype();
            document.getElementById("CheckboxAddF").disabled = true;
            document.getElementById("CheckboxDelF").disabled = true;
            SelectEPR();

        } else {

            document.getElementById("CheckboxNew").disabled = false;
            document.getElementById("CheckboxAddF").disabled = false;
            document.getElementById("CheckboxDelF").disabled = false;
            document.getElementById("File").hidden = true;
            SelectEPR();
        }
    }
    function selecttype() {
        // Get the checkbox
        var CheckboxNew = document.getElementById("CheckboxNew");
        var CheckboxAddF = document.getElementById("CheckboxAddF");
        var CheckboxDelF = document.getElementById("CheckboxDelF");

        // If the checkbox is checked, display the output text
        if (CheckboxNew.checked === true) {
            document.getElementById("Like").disabled = false;
            document.getElementById("UserType").disabled = false;
        } else {
            document.getElementById("Like").value = "";
            document.getElementById("Like").disabled = true;
            document.getElementById("UserType").disabled = true;
            document.getElementById("File").disabled = true;
            document.getElementById("File").hidden = true;
            document.getElementById("File").value = "";
        }

        if (CheckboxAddF.checked === true) {
            document.getElementById("AddFun").disabled = false;
        } else {
            document.getElementById("AddFun").value = "";
            document.getElementById("AddFun").disabled = true;
            document.getElementById("File").disabled = true;
            document.getElementById("File").hidden = true;
            document.getElementById("File").value = "";
        }

        if (CheckboxDelF.checked === true) {
            document.getElementById("DelFun").disabled = false;
            document.getElementById("File").disabled = false;
            document.getElementById("File").hidden = false;
        } else {
            document.getElementById("DelFun").value = "";
            document.getElementById("DelFun").disabled = true;
        }
    }


    function SelectEPR() {

        var radioValue = $("input[name='optionsRadio']:checked").val();
        if (radioValue === "EPR" || radioValue === "ESR") {

            document.getElementById("lbltype").hidden = false;
            document.getElementById("UserType").hidden = false;
            document.getElementById("Like").hidden = true;
            document.getElementById("lbllike").hidden = true;
            document.getElementById("Like").disabled = false;
            document.getElementById("Like").value = $("#UserType").val();
        } else if (radioValue === "M3INFO") {

            if (document.getElementById("Like").value === "Requester" || document.getElementById("Like").value === "Approval") {
                document.getElementById("Like").value = "";
            }
            document.getElementById("lbltype").hidden = true;
            document.getElementById("UserType").hidden = true;
            document.getElementById("Like").hidden = false;
            document.getElementById("lbllike").hidden = false;
            document.getElementById("Like").disabled = false;
        } else {
            document.getElementById("lbltype").hidden = true;
            document.getElementById("UserType").hidden = true;
            document.getElementById("Like").hidden = false;
            document.getElementById("lbllike").hidden = false;
            document.getElementById("Like").value = "";
            document.getElementById("Like").disabled = true;
        }
    }

    $("#Save").click(function (e) {

        var answer = window.confirm("Do you want to update detail request " + $("#reqno").val());
        var radioValue = $("input[name='optionsRadio']:checked").val();
        var ST_CODE = document.getElementById("empcode").value;
        var type = [];
        $.each($("input[name='checkboxinline']:checked"), function () {
            type.push($(this).val());
        });
        var Inserttype = type.join(",");
        if (answer === true) {
            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "Update_LineReq",
                    USREQNO: $("#reqno").val(),
                    USLINE: $("#line").val(),
                    USCOMP: $("#vCompany").val(),
                    USNAME1: $("#name").val(),
                    USNAME2: $("#lastname").val(),
                    USMAIL: $("#email").val(),
                    USCODE: ST_CODE,
                    USCOSTC: $("#Costcenter").val(),
                    USGROUP: radioValue,
                    USTYPE: Inserttype,
                    USLIKE: "" + $("#Like").val(),
                    USAFUNC: "" + $("#AddFun").val(),
                    USDFUNC: "" + $("#DelFun").val(),
                    USEMCRE: $("#MailCreator").val()

                },
                async: false
            }).done(function (response) {

            });
            
             if ($("#File").val() !== "") {
                    var form = $('#Myform')[0];
                    var data = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "./UploadFile",
                        enctype: 'multipart/form-data',
                        data: data,
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function (data) {
                            console.log("SUCCESS : ", data);
                        },
                        error: function (e) {
                            console.log("ERROR : ", e);
                        }
                    });
//
                    console.log(data);
                }
                
            $("#jsGrid3").jsGrid("loadData");
            alert("Update data complete.");
            $("#empcode").val('').trigger('change');
        } else {
            return false;
        }
        $("#reset").click();
        $("#Save").hide();
        $("#Add").show();
        document.getElementById("MailCreator").value = "";

        $("#Send").show();
        GetMaxLine();
        selectgroup();
        document.getElementById("AddFun").disabled = true;
        document.getElementById("DelFun").disabled = true;
    });

    function Reset() {
        GetMaxLine();
        $("#empcode").select2().val("").trigger("change");
        document.getElementById("lbltype").hidden = true;
        document.getElementById("UserType").hidden = true;
        document.getElementById("Like").hidden = false;
        document.getElementById("lbllike").hidden = false;
        document.getElementById("CheckboxNew").disabled = false;
        document.getElementById("CheckboxAddF").disabled = false;
        document.getElementById("CheckboxDelF").disabled = false;
        document.getElementById("MailCreator").value = "";
    }

    function GetMaxLine() {

        $.ajax({
            url: './Sync',
            type: 'GET',
            dataType: 'json',
            data: {
                page: "GetMaxLine",
                USREQNO: $("#reqno").val()
            },
            async: false
        }).done(function (response) {

            $.each(response, function (i, obj) {
                var div_LINE = obj.LINE;
                document.getElementById("line").value = div_LINE;
            });
        });
    }

    $("#Myform").validate({
        rules: {
            name: "required",
            lastname: "required",
            Company: "required",
//            Costcenter: "required",
//            empcode: "required",
            MailCreator: "required",
            Like: "required",
            AddFun: "required",
            DelFun: "required",
//            email: {
//                required: true,
//                email: true
//            },
            UserType: "required"
        },
        messages: {
            name: "Please specify your name",
            lastname: "Please specify your lastname",
            Company: " Please specify company",
//            Costcenter: " Please specify cost center",
//            empcode: "Please specify your staff code",
            MailCreator: "Please specify creator",
            Like: {
                required: function (element) {
                    if (document.getElementById("CheckboxNew").checked === true && document.getElementById("Like").value === "" && document.getElementById("M3INFO").checked === true) {
                        return  "Please specify your user reference";
                    }
                }
            },
            AddFun: {
                required: function (element) {
                    if (document.getElementById("CheckboxAddF").checked === true && document.getElementById("AddFun").value === "") {
                        return  "Please specify your function";
                    }
                }
            },
            DelFun: {
                required: function (element) {
                    if (document.getElementById("CheckboxDelF").checked === true && document.getElementById("DelFun").value === "") {
                        return  "Please specify your function";
                    }
                }
            },
            UserType: {
                required: function (element) {
                    if (document.getElementById("UserType").value === "" && (document.getElementById("EPR").checked === true || document.getElementById("ESR").checked === true)) {
                        return  "Please specify your type";
                    }
                }
            }
//            email: {
//                required: "Please specify your email"
//            }
        },
        submitHandler: function () {

            var reqno = document.getElementById("reqno").value;
            var line = document.getElementById("line").value;
            var USREQ = "<%out.print(session.getAttribute("namereq"));%>";
            var radioValue = $("input[name='optionsRadio']:checked").val();
            var type = [];
            $.each($("input[name='checkboxinline']:checked"), function () {
                type.push($(this).val());
            });

            if (reqno === "") {
                $.ajax({
                    url: './Sync',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        page: "GET_REQNO"
                    },
                    async: false
                }).done(function (response) {

                    $.each(response, function (i, obj) {
                        var div_REQNO = obj.reqno;
                        document.getElementById("reqno").value = div_REQNO;
                    });
                });
            }
            var Inserttype = type.join(",");
            var count = "";
            $.ajax({
                url: './Sync',
                type: 'GET',
                dataType: 'json',
                data: {
                    page: "Check_Duplicate",
                    USREQNO: $("#reqno").val(),
                    USCOMP: $("#vCompany").val(),
                    USCODE: $("#empcode").val(),
                    USGROUP: radioValue,
                    USTYPE: Inserttype
                },
                async: false
            }).done(function (response) {
                $.each(response, function (i, obj) {
                    var Duplicate = obj.Duplicate;
                    count = Duplicate;
                });
            });

            var CheckUserM3 = "0";

            if (Inserttype === "New") {
                $.ajax({
                    url: './Sync',
                    type: 'GET',
                    dataType: 'json',
                    data: {
                        page: "CheckUserM3",
                        USCODE: $("#empcode").val(),
                        USCOMP: $("#vCompany").val(),
                        USGROUP: radioValue,
                        USTYPE: Inserttype,
                        USLIKE: $("#Like").val()
                    },
                    async: false
                }).done(function (response) {
                    $.each(response, function (i, obj) {
                        var div_UserM3 = obj.CheckUserM3;
                        CheckUserM3 = div_UserM3;
                    });
                });
            }

            if (count !== "0") {
                alert("Duplicate detail !!");
                return false;
            } else if ((CheckUserM3 !== "0" && Inserttype === "New")) {
                alert("Can not request. You already have request in company " + $("#vCompany option:selected").text());
                return false;
            } else if ((USREQ === "M3SRVICT" && radioValue === "M3INFO")) {
                alert("Can not request. Please request by user's head.");
                return false;
            } else {

                $.ajax({
                    url: './Sync',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        page: "InsertRequest",
                        USREQNO: $("#reqno").val(),
                        USLINE: $("#line").val(),
                        USCOMP: $("#vCompany").val(),
                        USNAME1: $("#name").val(),
                        USNAME2: $("#lastname").val(),
                        USCODE: $("#empcode").val(),
                        USMAIL: $("#email").val(),
                        USCOSTC: $("#Costcenter").val(),
                        USGROUP: radioValue,
                        USTYPE: Inserttype,
                        USLIKE: $("#Like").val(),
                        USAFUNC: $("#AddFun").val(),
                        USDFUNC: $("#DelFun").val(),
                        USUREQ: USREQ,
                        USEMCRE: $("#MailCreator").val()
                    },
                    async: false
                }).done(function (response) {

                });

                if ($("#File").val() !== "") {
                    var form = $('#Myform')[0];
                    var data = new FormData(form);
                    $.ajax({
                        type: "POST",
                        url: "./UploadFile",
                        enctype: 'multipart/form-data',
                        data: data,
                        processData: false,
                        contentType: false,
                        cache: false,
                        success: function (data) {
                            console.log("SUCCESS : ", data);
                        },
                        error: function (e) {
                            console.log("ERROR : ", e);
                        }
                    });
//
                    console.log(data);
                }


//            $("#jsGrid3").jsGrid("loadData");
                $("#jsGrid3").jsGrid({
                    width: "100%",
                    height: "auto",
                    sorting: true,
                    paging: true,
                    autoload: true,
                    pageSize: 10,
                    pageButtonCount: 5,
                    deleteConfirm: function (item) {
                        return "Requser No. " + item.USREQNO + " Line : " + item.USLINE + " will be removed. Are you sure?";
                    },
                    controller: {
                        loadData: function (filter) {
                            var data = $.Deferred();
                            var cono = <%out.print(session.getAttribute("cono"));%>
                            var divi = <%out.print(session.getAttribute("divi"));%>
                            var reqno = document.getElementById("reqno").value;
                            $.ajax({
                                type: 'GET',
                                url: './Sync',
                                dataType: 'json',
                                data: {
                                    page: "Table_USRREQ",
                                    USREQNO: $("#reqno").val()
                                },
                                async: false
                            }).done(function (response) {
                                console.log(response);
                                data.resolve(response);
                            });
                            return data.promise();
                        },
                        deleteItem: function (item) {
                            console.log(item);
                            item.page = "DeleteLine";
                            $.ajax({
                                type: "POST",
                                url: "./Sync",
                                async: false,
                                cache: false,
                                data: item
                            });
                            $("#jsGrid3").jsGrid("loadData");
                            $("#reset").click();
                            GetMaxLine();
                            $("#Add").show();
                            $("#Send").show();
                            $("#Save").hide();
                            selectgroup();
                            document.getElementById("AddFun").disabled = true;
                            document.getElementById("DelFun").disabled = true;
                            document.getElementById("MailCreator").value = "";
                        }
                    },
                    fields: [
                        {type: "control", editButton: false, deleteButton: true, width: 50},
                        {title: "REQNO", name: "USREQNO", type: "text", editing: false, align: "center", width: 75},
                        {title: "Line", name: "USLINE", type: "text", align: "center", width: 50},
                        {title: "Company", name: "USCOMP", type: "text", align: "center", width: 80, editing: true},
                        {title: "Code", name: "USCODE", type: "text", editing: false, align: "center", width: 75},
                        {title: "Name", name: "USNAME1", type: "text", align: "left"},
                        {title: "Last Name", name: "USNAME2", type: "text", align: "left", width: 150},
                        {title: "E-Mail", name: "USMAIL", type: "text", align: "left", width: 250},
//                    {title: "Costc.", name: "USCOSTC", type: "text", align: "center"},
                        {title: "Group", name: "USGROUP", type: "text", align: "center"},
                        {title: "Type", name: "USTYPE", type: "text", align: "center"},
                        {title: "Like User", name: "USLIKE", type: "text", align: "left", width: 200},
                        {title: "Add Function", name: "USAFUNC", type: "text", align: "left", width: 200},
                        {title: "Delete Function", name: "USDFUNC", type: "text", align: "left", width: 200},
                        {title: "Date", name: "USREQDT", type: "text", align: "center"},
                        {title: "Creator", name: "USEMCRE", type: "text", align: "left", width: 250}
                    ],
                    rowClick: function (args) {
//            console.log(args.item);
                        showDetailsDialog2("GET", args.item);
                    }
                });
                alert("Add request complete.");
                $("#reset").click();
                $("#empcode").val('').trigger('change');
                line++;
                document.getElementById("line").value = line;
                document.getElementById("Send").hidden = false;
                document.getElementById("reqnoformail").value = "" + $("#reqno").val();
                document.getElementById("AddFun").disabled = true;
                document.getElementById("DelFun").disabled = true;
                selectgroup();
                document.getElementById("MailCreator").value = "";
//                
//            GetDetail();
            }
        }

    });


    $("#Send").click(function (e) {
        var answer = window.confirm("Do you want to send  request " + $("#reqno").val());
        if (answer === true) {
            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "SendToCreate",
                    USREQNO: $("#reqno").val()
                },
                async: false
            }).done(function (response) {

            });
            alert("Send request complete !!");
            $("#SendMail").click();
        } else {
            return false;
        }
//        location.reload();
    });

    $('#Like').on('input', function (evt) {
        $(this).val(function (_, val) {
            return val.toUpperCase();
        });
    });
    $('#AddFun').on('input', function (evt) {
        $(this).val(function (_, val) {
            return val.toUpperCase();
        });
    });
    $('#DelFun').on('input', function (evt) {
        $(this).val(function (_, val) {
            return val.toUpperCase();
        });
    });

    $("#Acknowledge").click(function (e) {

        var USACKBY = "<%out.print(session.getAttribute("namereq"));%>";
        var answer = window.confirm("Do you want to acknowledge request " + $("#reqno").val());
        if (answer === true) {
            $.ajax({
                url: './Sync',
                type: 'POST',
                dataType: 'json',
                data: {
                    page: "Acknowledge",
                    USREQNO: $("#reqno").val(),
                    USLINE: $("#line").val(),
                    USACKBY: USACKBY
                },
                async: false
            }).done(function (response) {

            });
        } else {
            return false;
        }
        alert("Acknowledge complete.");
        location.reload(0);
    });


</script>