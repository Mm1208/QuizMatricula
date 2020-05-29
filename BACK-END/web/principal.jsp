<html>  
    <head>
        <%@page contentType="text/html"%>
        <%@page pageEncoding="UTF-8"%>
        <%@ include file="Head.jspf" %>
        <script src="Javascript/principal.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <div class="row">
                        <div class="col-md" style="text-align: center">
                            <h1 id="principal_title"></h1>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4" id="card-dashboard">
                            <div id="crud_curso">
                                <div class="card">
                                    <div class="card-header" style="text-align: center;">
                                        <a class="card-link" data-toggle="collapse"
                                           href="#collapseCursos">
                                            <h1>Cursos</h1>
                                        </a>
                                    </div>
                                    <div id="collapseCursos" class="collapse"
                                         data-parent="#crud_curso">
                                        <div class="card-body">
                                            <div class="row d-flex justify-content-center">
                                                <a href="crud_curso.jsp">
                                                    Lista de cursos&nbsp;
                                                    <i class="fa fa-desktop fa-lg"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4" id="card-dashboard">
                            <div id="crud_carrera">
                                <div class="card">
                                    <div class="card-header" style="text-align: center;">
                                        <a class="card-link" data-toggle="collapse"
                                           href="#collapseCarreras">
                                            <h1>Carreras</h1>
                                        </a>
                                    </div>
                                    <div id="collapseCarreras" class="collapse"
                                         data-parent="#crud_carrera">
                                        <div class="card-body">
                                            <div class="row d-flex justify-content-center">
                                                <a href="crud_carrera.jsp">
                                                    Lista de carreras&nbsp;
                                                    <i class="fa fa-desktop fa-lg"></i>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="Footer.jspf" %>
        </div>
    </body>
</html>

