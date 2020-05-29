<html>
    <head>
        <%@page contentType="text/html"%>
        <%@page pageEncoding="UTF-8"%>
        <%@ include file="Head.jspf" %>
        <script src="Javascript/crud_curso.js"></script>
    </head>
    <body>
        <div class="container">
            <!-- Aréa de tabla -->
            <%@include file="Curso.jspf" %>
            <%@include file="Table.jspf" %>
            <div class="row">
                <div class="col-md">
                    <input class="form-control btn btn-dark" id="button">
                </div>
            </div>
        </div>
        <%@ include file="Footer.jspf" %>
    </body>
</html>
