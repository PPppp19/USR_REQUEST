<%-- 
    Document   : newimg
    Created on : Apr 27, 2020, 9:56:42 AM
    Author     : Jilasak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="col-md-12 text-center">
            <form name="UploadStaff" id="Mail" method="GET" action="./UploadStaff">          
                <button type="submit" class="btn btn-danger" id="few">IMG</button>
            </form>
            
        </div>
    </body>
</html>
