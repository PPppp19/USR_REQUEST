<%-- 
    Document   : TrackStatus
    Created on : May 26, 2020, 10:28:50 AM
    Author     : Jilasak
--%>

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

    .jsgrid-edit-row input, .jsgrid-edit-row select, .jsgrid-edit-row textarea, .jsgrid-filter-row input, .jsgrid-filter-row select, .jsgrid-filter-row textarea, .jsgrid-insert-row input, .jsgrid-insert-row select, .jsgrid-insert-row textarea {
        width: 100%;
        padding: .3em .5em;
        height: 20px;
    }


</style>


<body><br>
    <div class="container">
        <div id="jsGrid1" class="shadow"></div><br>
    </div>
</body>
<script>



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
                var data = $.Deferred();

                $.ajax({
                    type: 'GET',
                    url: './Sync',
                    dataType: 'json',
                    data: {
                        page: "TrackStatus"
                    },
                    async: false
                }).done(function (response) {
                    response = $.grep(response, function (item) {
                        return (!filter.USREQNO || (item.USREQNO.indexOf(filter.USREQNO) > -1))
                                && (!filter.USLINE.toUpperCase() || (item.USLINE.indexOf(filter.USLINE.toUpperCase()) > -1))
                                && (!filter.USCOMP.toUpperCase() || (item.USCOMP.indexOf(filter.USCOMP.toUpperCase()) > -1))
                                && (!filter.USNAME1.toUpperCase() || (item.USNAME1.indexOf(filter.USNAME1.toUpperCase()) > -1))
                                && (!filter.USNAME2.toUpperCase() || (item.USNAME2.indexOf(filter.USNAME2.toUpperCase()) > -1))
                                && (!filter.USGROUP || (item.USGROUP.indexOf(filter.USGROUP) > -1))
                                && (!filter.USTYPE || (item.USTYPE.indexOf(filter.USTYPE) > -1))
                                && (!filter.USREQDT.toUpperCase() || (item.USREQDT.indexOf(filter.USREQDT.toUpperCase()) > -1));

                    });
                    console.log(response);
                    data.resolve(response);
                });
                return data.promise();
            }
        },

        fields: [
            {title: "Req NO.", name: "USREQNO", type: "text", editing: false, align: "center", width: 75},
            {title: "Line", name: "USLINE", type: "text", align: "center", width: 50},
            {title: "Company", name: "USCOMP", type: "text", align: "center", width: 80, editing: true},
            {title: "Name", name: "USNAME1", type: "text", align: "left"},
            {title: "Last Name", name: "USNAME2", type: "text", align: "left", width: 125},
//                {title: "E-Mail", name: "USMAIL", type: "text", align: "left", width: 200},
            {title: "Group", name: "USGROUP", type: "text", align: "center", width: 90},
            {title: "Type", name: "USTYPE", type: "text", align: "center", width: 90},
            {title: "Date", name: "USREQDT", type: "text", align: "center", width: 95},
            {title: "Requester", name: "USUREQ", type: "text", align: "center", width: 100, filtering: false},
            {title: "STATUS", name: "USSTAT", type: "text", align: "center", width: 140, filtering: false},
            {title: "Creator", name: "USEMCRE", type: "text", align: "center", width: 100, filtering: false}
        ]
    });



</script>