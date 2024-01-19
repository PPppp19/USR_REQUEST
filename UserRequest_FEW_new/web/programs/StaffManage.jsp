<%-- 
    Document   : uploadimg
    Created on : Apr 22, 2020, 4:58:45 PM
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
    .error {
        color: red;

    }


</style>

<br>
<div class="container">
    <div class="card border-center-primary shadow h-100 py-2">
        <div class="card-body">
            <div class="content" id="Main">
                <h5 class="text-center">Staff Manage</h5>
                <div class="form-row" for="showdisplay" >
                    <div class="form-group col-md-2">
                        <label for="inputmaster">Staff code :</label>
                        <input type="text" class="form-control" id="Show_EMPCODE" name="Show_EMPCODE"  onblur="get_duplicatevalue();" value="">
                    </div>  
                    <div class="form-group col-md-3">
                        <label for="inputmaster">Start date :</label>
                        <input type="date" class="form-control" id="Show_STDT" name="Show_STDT" onchange="get_stdt();" value="0000-00-00" style="width: 185px;">
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputmaster">ST_M3 :</label>
                        <input type="text" class="form-control" maxlength="10" id="Show_STM3" name="Show_STM3"  value="" onblur="get_duplicatevalue();">
                    </div>

                </div>
                <form name="Myform" id="Myform" method="POST" action="./UploadStaff" enctype="multipart/form-data" acceptcharset="UTF-8">
                    <div class="form-row" hidden="true">
                        <div class="form-group col-md-2">
                            <label for="inputmaster">Employee Code :</label>
                            <input type="text" class="form-control" id="EMPCODE" name="EMPCODE" value="">
                        </div>  
                        <div class="form-group col-md-3">
                            <label for="inputmaster">Start date :</label>
                            <input type="date" class="form-control" id="ST_STDT" name="ST_STDT" style="width: 185px;">
                        </div>
                        <div class="form-group col-md-2">
                            <label for="inputmaster">ST_M3 :</label>
                            <input type="text" class="form-control" id="ST_M3" name="ST_M3"  value="" maxlength="10">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-2">
                            <label for="EPNM">Name perfix :</label>
                            <select id="EPNM" name="EPNM" class="form-control">
                                <option selected>Select name perfix</option>
                                <option value="Mr.">Mr.</option>
                                <option value="Miss">Miss</option>
                                <option value="Mrs.">Mrs.</option>
                                <option value="Sergeant">Sergeant</option>
                                <option value="ACTING SECOND LIEUTENANT">ACTING SECOND LIEUTENANT</option>
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputmaster">First Name :</label>
                            <input type="text" class="form-control" id="ENAME" name="ENAME" value="" onblur="SUB_M3();">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputmaster">Last Name :</label>
                            <input type="text" class="form-control" id="ELNAME" name="ELNAME" value="" onblur="SUB_M3();">
                        </div>
                        <div class="form-group col-sm-2">
                            <label for="disabledSelect">Company</label>
                            <select class="select2-search--dropdown form-control"  name="COMPANY" id="COMPANY" onchange="callcostc();">
                                <option value="" selected="selected">Select Company</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-2">
                            <label for="inputmaster">Cost Center :</label>
                            <select id="COSTCENTER" class="form-control" name="COSTCENTER">
                                <option selected>Select costc.</option>                               
                            </select>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-2">
                            <label for="TPNM">คำนำหน้าชื่อ :</label>
                            <select id="TPNM" class="form-control" name="TPNM">
                                <option selected>Please select</option>
                                <option value="นาย">นาย</option>
                                <option value="นาง">นาง</option>
                                <option value="นางสาว">นางสาว</option>
                                <option value="สิบเอก">สิบเอก</option>
                                <option value="ว่าที่ร้อยตรีหญิง">ว่าที่ร้อยตรีหญิง</option>
                                <option value="ว่าที่ร้อยตรีชาย">ว่าที่ร้อยตรีชาย</option>
                            </select>
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputmaster">ชื่อ :</label>
                            <input type="text" class="form-control" id="TNAME" name="TNAME" value="">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputmaster">นามสกุล :</label>
                            <input type="text" class="form-control" id="TLNAME" name="TLNAME" value="">
                        </div>
                        <div class="form-group col-md-4">
                            <label for="inputmaster">E-mail :</label>
                            <input  type="email" class="form-control" name="EMAIL" id="EMAIL" placeholder="br@br-bangkokranch.com"   maxlength="50">
                        </div>
                    </div> 

                    <div class="form-row">
                        <div class="form-group col-md-5">
                            <label for="inputmaster">Position :</label>
                            <input type="text" class="form-control" id="POSITION" name="POSITION" value="">
                        </div>
                        <div class="form-group col-md-3">
                            <label for="inputmaster">Level :</label>
                            <input type="text" class="form-control" id="LEVEL" name="LEVEL" value="">
                        </div>
                        <div class="form-group col-sm-3">
                            <label for="inputmaster">Signature :</label>
                            <input type="file" class="form-control2" id="ST_SIGN" name="ST_SIGN" >
                        </div>
                        <div class="form-group col-md-1">
                            <img id="img" src="assets/duck.png" height="100%" width="100%" hidden="true" class="center-block">
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-2">
                            <label for="status">Employee Status :</label>
                            <select id="STATUS" class="form-control" name="STATUS" onchange="DateStatus();">
                                <option value="" selected>Please select</option>
                                <option value="20">20 : Enable</option>
                                <option value="90">90 : Disable</option>                               
                            </select>
                        </div>

                        <div class="form-group col-sm-2" id="RTDT" hidden="true">
                            <label for="status">Retried Date :</label>
                            <input type="date" class="form-control" id="ST_REDT" name="ST_REDT" value="0000-00-00" >
                        </div>
                    </div>    
                    <div class="form-row" id="forupdate"  hidden="true">
                        <div class="form-group col-sm-2" id="DISM3" >
                            <label for="status">Disable M3 :</label>
                            <input type="date" class="form-control" id="ST_DISM3" name="ST_DISM3" value="0000-00-00">
                        </div>
                        <div class="form-group col-sm-2" id="DISEM">
                            <label for="status">Disable E-mail :</label>
                            <input type="date" class="form-control" id="ST_DISEMAIL" name="ST_DISEMAIL" value="0000-00-00">
                        </div> 
                    </div>
                    <button type="submit" id="Add" class="btn btn-primary" value="AddStaff" name="Add">Add Staff</button>
                    <button hidden="true" type="submit" id="Update" class="btn btn-success" value="Update" name="Add">Save data</button>
                </form>
            </div>
            <input  hidden="true" id="CheckAuth" type="text" value="<%out.print(session.getAttribute("ST_COSTC"));%>">
        </div>

    </div>   
</div>
<br><br>

<script>
    var search = document.getElementById("Show_EMPCODE");
    search.addEventListener("keyup", function (event) {
        if (event.keyCode === 13) {
//            alert(this.id);
            Search();
        }
    });


    $('#Myform').keydown(function (e) {
        if (e.keyCode === 13) {
            e.preventDefault();
            return false;
        }
    });

    $(document).ready(function () {

        var CheckAuth = document.getElementById("CheckAuth").value;

        if (CheckAuth !== "S8") {


            alert("You not authorization this page !!!");
            $(location).attr('href', './');
        }

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
                    $(div_data).appendTo('#COMPANY');
                });
            }
        });
    });

    function callcostc() {
        var cono = $("#COMPANY").val();
        $('#COSTCENTER').find('option').remove().end();
        $('#COSTCENTER').append('<option value="" selected="selected">Select Costcenter</option>');
        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "costcenter",
                cono: cono
            },
            async: false
        }).done(function (response) {
            $.each(response, function (i, obj) {
                var div_data = "<option value=" + obj.eaaitm + ">" + obj.eaaitm + " : " + obj.eatx15 + "</option>";
//                console.log(div_data);
                $(div_data).appendTo('#COSTCENTER');
            });
        }).fail(function (i, obj) {
            location.href = "./login.jsp";
        });
    }


    $("#Myform").validate({
        rules: {
            EPNM: "required",
            ENAME: "required",
            ELNAME: "required",
            COMPANY: "required",
            COSTCENTER: "required",
            TPNM: "required",
            TNAME: "required",
            TLNAME: "required",
            EMPCODE: "required",
            POSITION: "required",
//            ST_SIGN: "required",
            STATUS: "required",
            ST_STDT: "required"
        },
        messages: {
            EPNM: "required",
            ENAME: "required",
            ELNAME: "required",
            COMPANY: "required",
            COSTCENTER: "required",
            TPNM: "required",
            TNAME: "required",
            TLNAME: "required",
            EMPCODE: "required",
            POSITION: "required",
//            ST_SIGN: "required",
            STATUS: "required",
            ST_STDT: "required"
        }
    });

    function get_stdt() {

        var ST_STDT = document.getElementById("Show_STDT").value;
        document.getElementById("ST_STDT").value = ST_STDT;

    }

    function get_duplicatevalue() {
        var empcode = document.getElementById("Show_EMPCODE").value;
        document.getElementById("EMPCODE").value = empcode;

        var ST_M3 = document.getElementById("Show_STM3").value;
        document.getElementById("ST_M3").value = ST_M3;
    }

    function Search() {
        var empcode = document.getElementById("Show_EMPCODE").value;

        var st_sign;
        document.getElementById("Myform").reset();
        document.getElementById("Show_STDT").value = "0000-00-00";
        document.getElementById('img').setAttribute('src', '');
        document.getElementById("Show_STM3").value = "";
        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: "json",
            data: {
                page: "Search",
                ST_CODE: empcode
            },
            async: false
        }).done(function (response) {


            $.each(response, function (i, obj) {

                document.getElementById("EMPCODE").value = obj.ST_CONO;
                document.getElementById("Show_STDT").value = obj.ST_STDT;
                document.getElementById("ST_STDT").value = obj.ST_STDT;
                document.getElementById("Show_STM3").value = obj.ST_M3;
                document.getElementById("ST_M3").value = obj.ST_M3;
                document.getElementById("EPNM").value = obj.ST_EPNM;
                document.getElementById("ENAME").value = obj.ST_ENAME;
                document.getElementById("ELNAME").value = obj.ST_ELNAME;
                document.getElementById("COMPANY").value = obj.ST_CONO;
                callcostc();
                document.getElementById("COSTCENTER").value = obj.ST_COSTC;
                document.getElementById("TPNM").value = obj.ST_TPNM;
                document.getElementById("TNAME").value = obj.ST_TNAME;
                document.getElementById("TLNAME").value = obj.ST_TLNAME;
                document.getElementById("EMAIL").value = obj.ST_EMAIL;
                document.getElementById("POSITION").value = obj.ST_POSITON;
                document.getElementById("LEVEL").value = obj.ST_LEVEL;
                document.getElementById("STATUS").value = obj.ST_STS;
                DateStatus();
                document.getElementById("img").hidden = false;
                st_sign = obj.ST_SIGN;
                console.log(st_sign);
                document.getElementById('img').setAttribute('src', st_sign);
                document.getElementById("ST_REDT").value = obj.ST_REDT;
                document.getElementById("ST_DISEMAIL").value = obj.ST_DISEMAIL;
                document.getElementById("ST_DISM3").value = obj.ST_DISM3;
                document.getElementById("Add").hidden = true;
//            document.getElementById("Update").hidden = false;
//            document.getElementById("forupdate").hidden = false;
            });
        }).fail(function (i, obj) {
            location.href = "./login.jsp";
        });

        if ($("#ENAME").val() !== "") {
            document.getElementById("Update").hidden = false;
            document.getElementById("forupdate").hidden = false;

        } else {
            alert("Data not found. !!!");
            document.getElementById("Update").hidden = true;
            document.getElementById("forupdate").hidden = true;
            document.getElementById("Add").hidden = false;
        }
    }

    function DateStatus() {

        var x = document.getElementById("STATUS").value;

        if (x === "90") {
            document.getElementById("RTDT").hidden = false;
        } else {
            document.getElementById("RTDT").hidden = true;
            document.getElementById("ST_REDT").value = "0000-00-00";
        }

    }
    function SUB_M3() {

        var NAME = document.getElementById("ENAME").value;
        var LNAME = document.getElementById("ELNAME").value;

        var res = NAME.substring(0, 6).toUpperCase();
        var res1 = LNAME.substring(0, 3).toUpperCase();
        document.getElementById("Show_STM3").value = res + "_" + res1;
        document.getElementById("ST_M3").value = res + "_" + res1;
    }
    $('#ENAME').on('input', function (evt) {
        $(this).val(function (_, val) {
            return val.toUpperCase();
        });
    });
    $('#ELNAME').on('input', function (evt) {
        $(this).val(function (_, val) {
            return val.toUpperCase();
        });
    });


</script>