<%-- 
    Document   : UndisAddon
    Created on : May 26, 2020, 3:02:20 PM
    Author     : Jilasak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

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
                <h5 class="center-block text-center" style="color: tomato">Unlock user disable (M3-Add on)</h5>
                <br>
                <form name="unlockuser" id="unlockuser" method="POST" action="./UnlockUserDisable">
                    <div class="form-group">
                        <div class="col-md-12">
                            <label class="col-sm-6 text-right" style="padding-right: 40px;">Username :</label>                   
                            <input class="col-sm-6 text-center" maxlength="10" type="text" name="username" id="username" value="" maxlength="10" minlength="5" style="width: 140px;">
                        </div>                
                        <br> 
                        <h6 id="error" style="color: red" class="text-center">                           
                            <%
                                out.println(session.getAttribute("MessageReturn"));
                                session.removeAttribute("MessageReturn");
                            %>
                        </h6>                             
                        <div class="row col-md-12 ">
                            <div class="col-md-12 text-center">
                                <button type="submit" class="btn btn-lg btn-danger shadow" id="unlock" name="unlock" value="unlockuser" style="width: auto;">Unlock User / Reset Password</button>
                            </div>
<!--                            <div class="col-md-6">
                                <button type="submit" class="btn btn-lg btn-danger shadow" id="unlock" name="unlock" value="resetpass">Reset password</button>
                            </div>-->
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>

    $(document).ready(function () {

        if ($("#error").text().trim() !== "null") {
            $("#error").show();
        } else {
            $("#error").hide();
        }
    });


    $('#username').on('input', function (evt) {
        $(this).val(function (_, val) {
            $("#error").hide();
            return val.toUpperCase();
        });
    });

</script>