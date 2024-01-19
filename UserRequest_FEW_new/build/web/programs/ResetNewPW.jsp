<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">    

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
            <div class="content">
                <h5 class="center-block text-center" style="color: tomato">Change password (M3-Add on)</h5>
                <br>
<!--                <form name="unlockuser" id="unlockuser" method="POST" action="./UnlockUserDisable">
                      <input maxlength="10" type="hidden" name="unlockusername" id="unlockusername" value="" />
                </form>-->
                <form name="ResetNewPW" id="ResetNewPW" method="POST" form action="./ResetNewPW" >
                    <div class="form-group">
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">Username :</label>          
                            <label class="col-sm-5 text-left" id ="txtusername" name="txtusername"/>   
                            <input maxlength="10" type="hidden" name="username" id="username" value="" />
                            <input  type="hidden" name="url" id="url" value="" />

                        </div> 
<!--                        <div class="col-md-12 ">
                            <label class="col-sm-5 text-right">Old Password :</label>                   
                            <input maxlength="20" type="password" name="txtOldPassword" id="txtOldPassword" value="">
                            <i class="far fa-eye" id="togglePassword1" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtOldPassword', 'togglePassword1')"></i>
                            <label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*เหมือน Username กรณีทาง ICT ปลดล็อคให้</label>                
                        </div>-->
                        <div class="col-md-12 ">
                            <!--<i class="far fa-eye" id="togglePassword1" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtOldPassword', 'togglePassword1')"></i>-->
                            <!--                            <label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*เหมือน Username กรณีทาง ICT ปลดล็อคให้</label>                -->
                        </div>
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">New Password :</label>                   
                            <input maxlength="10" type="password" name="txtNewPassword" id="txtNewPassword" value="" minlength="8">
                            <i class="far fa-eye" id="togglePassword2" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtNewPassword', 'togglePassword2')"></i>
                            <!--<label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*ตัวอักษรผสมตัวเลข ขั้นต่ำ 8 ตัว</label>-->       
                        </div>
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">Confirm New Password :</label>                   
                            <input maxlength="10" type="password" name="txtConfirmPassword" id="txtConfirmPassword" value="" minlength="8">
                            <i class="far fa-eye" id="togglePassword3" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtConfirmPassword', 'togglePassword3')"></i>
                            <!--<label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*ห้าม อักษรซ้ำ/ตัวเลขซ้ำ ติดกัน (เช่น AA,99)</label>-->       
                        </div> 
                        <br> 
                        <h1 id="error" style="color: #0050E1" class="text-center" onafterprint="alert('File selected')" onchange="alert('File selected')" >                           
                            <%
                                out.println(session.getAttribute("MSGError"));
                                session.removeAttribute("MSGError");
                            %>
  
                        </h1>                             
                        <div class="col-md-12 text-center">
                            <button type="submit" class="btn btn-lg btn-danger shadow" id="resetnewpw" name="resetnewpw" disabled="true" value="ResetNewPW" >Change password</button>


                        </div><br>
                        <div class="col-md-12">
                            <label style="color: red;font-size: 14px; font-weight: bold">*Remark : ให้มีตัวอักษรและตัวเลข 8-10ตัว  ห้ามมี "@"หรืออักขระพิเศษ,ห้ามตัวอักษรและตัวเลขดับเบิ้ลเช่น AA,BB,22,55 เปลี่ยนสำเร็จจึงสามารถใช้เข้าโปรแกรมได้ครับ</label>                                
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>



    $(document).ready(function () {
 
        const queryString = window.location.search;
        const urlParams = new URLSearchParams(queryString);
        const Key = urlParams.get('pp');

        console.log(Key);
         document.getElementById('url').value =Key.toString();
         
        
                            

        $.ajax({
            url: "./Sync",
            type: "GET",
            dataType: 'json',

            data: {
                page: "Checkusername",
                Key: Key
            },
            success: function (getdata) {


                    console.log(getdata);
                                  
                if (getdata[0].num !== "1") {

                     console.log(Key);
                    alert("You not authorization this page !!!");
                    $(location).attr('href', '?page=./ChangePW');
                }
                else {
                    
                    document.getElementById("username").value = getdata[0].username;
                    document.getElementById('txtusername').innerHTML = getdata[0].username;
                   
         
                    

                }

            },
            error: function (e) {

                console.log(e);
                console.log("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeerrrrrrrrrrrr");


            }
        });


    });

    $(function () {
        $("#resetnewpw1").click(function (ev) {
            
               document.getElementById("unlockuser").submit();
               
             //  document.getElementById("ResetNewPW").submit();

//            if ($(this).attr("value") === "resetpw") {
//
//                $("#ChangePW").attr('action', '?page=/ResetPW');
//                $("#ChangePW").submit();
//            }



        });
    });




    function togglePassword(pass, eye) {
        var x = document.getElementById(pass + "");
        if (x.type === "password") {
            x.type = "text";
            document.getElementById("" + eye).className = "far fa-eye-slash";
        } else {
            x.type = "password";
            document.getElementById("" + eye).className = "far fa-eye";
        }
    }

    $(document).ready(function () {

        if ($("#txtConfirmPassword").val !== "") {
            $("#txtNewPassword, #txtConfirmPassword").keyup(checkPasswordMatch);

        }
        if ($("#username").val !== "") {
            $("#username, #username").keyup(checknullUsername);

        }
        if ($("#error").text().trim() !== "null") {
            $("#error").show();
        } else {
            $("#error").hide();
        }
    });


    function checknullUsername() {

        var username = $("#username").val();



        if (username === null || username === "") {
//            $("#divCheckPasswordMatch").html("Passwords do not match!");
            document.getElementById("resetnewpw").disabled = true;
        } else {
//            $("#divCheckPasswordMatch").html("Passwords match.");
            document.getElementById("resetnewpw").disabled = false;
        }
    }

    function checkPasswordMatch() {
        var password = $("#txtNewPassword").val();
        var confirmPassword = $("#txtConfirmPassword").val();
        if (password !== confirmPassword || confirmPassword === "") {
//            $("#divCheckPasswordMatch").html("Passwords do not match!");
            document.getElementById("resetnewpw").disabled = true;
        } else {
//            $("#divCheckPasswordMatch").html("Passwords match.");
            document.getElementById("resetnewpw").disabled = false;
        }
    }

    $('#username').on('input', function (evt) {
        $(this).val(function (_, val) {
            $("#error").hide();
            return val.toUpperCase();
        });
    });

</script>

