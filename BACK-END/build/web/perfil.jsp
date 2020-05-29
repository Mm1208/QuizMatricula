<html>
    <head>
        <title>Perfil</title>
        <%@page contentType="text/html"%>
        <%@ include file="Head.jspf" %>
        <script src="Javascript/perfil.js"></script>
        <%@page pageEncoding="UTF-8"%>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-md-2">
                            <div class="profile-img">
                                <img class="media-object dp img-circle" src="img/user.png" id="perfil_imagen">
                            </div>
                        </div>
                        <div class="col-md-10">
                            <div class="profile-head">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="perfil_cedula" disabled>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" id="perfil_nombre" disabled>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="class-body">
                    <div class="col-md">
                        <div class="tab-content profile-tab" id="myTabContent">
                            <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                <div class="form-group">
                                    <label for="perfil_telefono">Teléfono:</label>
                                    <input type="text" class="form-control" id="perfil_telefono" disabled>
                                </div>
                                <div class="form-group">
                                    <label for="perfil_correo">Correo electrónico:</label>
                                    <input type="text" class="form-control" id="perfil_correo" disabled>
                                </div>
                                <div class="form-group">
                                    <label for="perfil_fecha_nacimiento">Fecha de nacimiento:</label>
                                    <input type="text" class="form-control" id="perfil_fecha_nacimiento" disabled>
                                </div>
                                <div class="form-group">
                                    <label for="perfil_carrera">Carrera:</label>
                                    <input type="text" class="form-control" id="perfil_carrera" disabled>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <%@ include file="Footer.jspf" %>
    </body>
</html>