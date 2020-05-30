<html>
    <head>
        <%@page contentType="text/html"%>
        <%@page pageEncoding="UTF-8"%>
        <%@ include file="Head.jspf" %>
        <script src="Javascript/login.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div class="card">
                        <div class="card-header text-center">
                            <h3 class="mb-0">Login</h3>
                        </div>
                        <div class="card-body">
                            <div class="form-group">
                                <label for="login_usuario">Usuario:</label>
                                <input type="text" class="form-control" id="login_usuario" placeholder="Cédula" required="">
                            </div>
                            <div class="form-group">
                                <label for="login_contrasenna">Contraseña:</label>
                                <input type="password" class="form-control" id="login_contrasenna" placeholder="Clave" required="">
                            </div>
                            <button type="button" class="form-control btn-primary" id="login_btn_primary">Ingresar</button>
                        </div>
                        <div class="card-footer text-right">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
