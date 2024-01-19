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
                <form name="ChangePW" id="ChangePW" method="POST" action="./ChangePW">
                    <div class="form-group">
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">Username :</label>                   
                            <input maxlength="10" type="text" name="username" id="username" value="">
                        </div> 
                        <div class="col-md-12 ">
                            <label class="col-sm-5 text-right">Old Password :</label>                   
                            <input maxlength="20" type="password" name="txtOldPassword" id="txtOldPassword" value="">
                            <i class="far fa-eye" id="togglePassword1" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtOldPassword', 'togglePassword1')"></i>
                            <label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*เหมือน Username กรณีทาง ICT ปลดล็อคให้</label>                
                        </div>
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">New Password :</label>                   
                            <input maxlength="20" type="password" name="txtNewPassword" id="txtNewPassword" value="" minlength="8">
                            <i class="far fa-eye" id="togglePassword2" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtNewPassword', 'togglePassword2')"></i>
                            <!--<label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*ตัวอักษรผสมตัวเลข ขั้นต่ำ 8 ตัว</label>-->       
                        </div>
                        <div class="col-md-12">
                            <label class="col-sm-5 text-right">Confirm New Password :</label>                   
                            <input maxlength="20" type="password" name="txtConfirmPassword" id="txtConfirmPassword" value="" minlength="8">
                            <i class="far fa-eye" id="togglePassword3" style="margin-left: -30px;cursor: pointer" onclick="togglePassword('txtConfirmPassword', 'togglePassword3')"></i>
                            <!--<label class="col-sm-5 " style="font-size: 13px;color: red;font-weight: bold;width: 300px;">*ห้าม อักษรซ้ำ/ตัวเลขซ้ำ ติดกัน (เช่น AA,99)</label>-->       
                        </div> 
                        <br> 
                        <h6 id="error" style="color: #0050E1" class="text-center">                           
                             <%
                                 if(session.getAttribute("MSGError") != null){
                                out.println(session.getAttribute("MSGError"));
                                }
                                session.removeAttribute("MSGError");
                            %>
                        </h6>                             
                        <div class="col-md-12 text-center">
                            <button type="submit" class="btn btn-lg btn-danger shadow" id="changepw" name="changepw" disabled="true" value="ChangePW" >Change password</button>
                            <button type="submit" class="btn btn-lg btn-danger shadow " style=" background-color: white; color: red; border-color: red;" id="resetpw" name="resetpw" value="resetpw" formaction="?page=/ResetPW"  >Reset password</button>

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
        if ($("#error").text().trim() !== "null") {
            $("#error").show();
        } else {
            $("#error").hide();
        }
    });


    function checkPasswordMatch() {
        var password = $("#txtNewPassword").val();
        var confirmPassword = $("#txtConfirmPassword").val();
        if (password !== confirmPassword || confirmPassword === "") {
//            $("#divCheckPasswordMatch").html("Passwords do not match!");
            document.getElementById("changepw").disabled = true;
        } else {
//            $("#divCheckPasswordMatch").html("Passwords match.");
            document.getElementById("changepw").disabled = false;
        }
    }

    $('#username').on('input', function (evt) {
        $(this).val(function (_, val) {
            $("#error").hide();
            return val.toUpperCase();
        });
    });

</script>

