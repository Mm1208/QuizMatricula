
/* global parameters_button, message_btn_primary, message_btn_secondary, LINKS, ajax, confirmation_btn_secondary, confirmation_show, carrera_btn_secondary, carrera_btn_primary */

function crud_carrera_init(event) {
    table_head_init(['Código', 'Nombre', 'Título', 'Acciones']);
    init_table();
    loadTitle('Mantenimiento de carreras');
    loadButton();
    list();
    setInterval(serch_change, 2000);
}

function table_head_init(parameters) {
    var row = document.getElementById("table_head").insertRow(0);
    row.innerHTML = '<th>' + parameters[0] + '</th>' +
            '<th>' + parameters[1] + '</th>' +
            '<th>' + parameters[2] + '</th>' +
            '<th>' + parameters[3] + '</th>';
}

function init_table() {
    var fecha = new Date();
    $('#table_data').DataTable({
        paging: false,
        info: false,
        filter: false,
        language: {
            "paginate": {
                "first": "Primera",
                "last": "Última",
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "sProcessing": "Procesando...",
            "sLoadingRecords": "Cargando...",
            "search": "Buscar:",
            "lengthMenu": "Mostrando _MENU_ registros por página",
            "zeroRecords": "Ningún dato disponible en esta tabla",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "No se encontraron datos",
            "infoFiltered": "(Filtrado de un total de _MAX_ registros)"
        },
        dom: '<"float-left"B> <"float-right"f>t<"row"<"col-sm-5"l><"col-sm-3"i><"col-sm-4"p>>',
        buttons: [
            {
                extend: 'pdf',
                text: '<i class="fa fa-file-pdf-o" title="Exportar a PDF"></i>',
                messageTop: 'Sistema de Matrícula',
                title: 'Reporte de cursos ' + fecha.getDate() + '-' + (1 + fecha.getMonth()) + '-' + fecha.getFullYear(),
                exportOptions: {
                    columns: [0, 1, 2, 3]
                }
            },
            {
                extend: 'excel',
                text: '<i class="fa fa-file-excel-o" title="Exportar a Excel"></i>',
                messageTop: 'Sistema de Matrícula',
                title: 'Reporte de cursos ' + fecha.getDate() + '-' + (1 + fecha.getMonth()) + '-' + fecha.getFullYear(),
                exportOptions: {
                    columns: [0, 1, 2, 3]
                }
            },
            {
                extend: 'print',
                text: '<i class="fa fa-print" title="Imprimir"></i>',
                messageTop: 'Sistema de Matrícula',
                title: 'Reporte de cursos ' + fecha.getDate() + '-' + (1 + fecha.getMonth()) + '-' + fecha.getFullYear(),
                exportOptions: {
                    columns: [0, 1, 2, 3]
                }
            }
        ],
        colReorder: true,
        columnDefs: [
            {
                targets: "_all",
                className: "dt-center"
            }]
    });
}

function loadTitle(title) {
    document.title = title;
    document.getElementById('table_title').innerHTML = title;
}

let crud_carrera_btn_primary;
function loadButton() {
    document.getElementById("table_button").onclick = insertar;
    crud_carrera_btn_primary = document.getElementById("button");
    crud_carrera_btn_primary.type = 'submit';
    crud_carrera_btn_primary.value = parameters_button.SALIR;
    crud_carrera_btn_primary.onclick = salir;
    message_btn_primary.innerHTML = parameters_button.GUARDAR;
    message_btn_primary.hidden = 'true';
    message_btn_secondary.innerHTML = parameters_button.CERRAR;
}

function salir(evt) {
    window.location.href = LINKS.PRINCIPAL;
}

function insertar() {
    sessionStorage.setItem('carrera_type', 1);
    carrera_reset();
    $('#carrera_modal').modal('toggle');
    confirmation_load_message('Confirmacíon de la Acción', '¿Esta seguro(a) que desea insertar esta carrera?');
    carrera_btn_secondary.onclick = crud_carrera_confirmation_show;
    confirmation_btn_secondary.onclick = insert;
}

function actualizar(carrera) {
    sessionStorage.setItem('carrera_type', 2);
    $('#carrera_modal').modal('toggle');
    confirmation_load_message('Confirmacíon de la Acción', '¿Esta seguro(a) que desea guardar los cambios en esta carrera?');
    carrera_btn_secondary.onclick = crud_carrera_confirmation_show;
    carrera_load(carrera.id);
    confirmation_btn_secondary.onclick = function () {
        update(carrera.id);
    };
}

function eliminar(carrera) {
    confirmation_load_message('Confirmacíon de la Acción', "¿Esta seguro(a) que desea eliminar " +
            "esta carrera?<br><br><div class='form-control'>" + carrera.nombre + '</div>');
    confirmation_show();
    confirmation_btn_secondary.onclick = function () {
        remove(carrera.id);
    };
}

function getCarrera(id) {
    return 'json=' + encodeURIComponent(JSON.stringify({
        id: ((id === undefined) ? -1 : id),
        codigo: document.getElementById('carrera_codigo').value,
        nombre: document.getElementById('carrera_nombre').value,
        titulo: document.getElementById('carrera_titulo').value
    }));
}

function insert() {
    $('#confirmation_modal').modal('hide');
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "carrera?opcion=insert&" + getCarrera()
    }).then((data) => {
        $('#loader').modal('hide');
        $('#carrera_modal').modal('hide');
        message_show('Notificación', 'Se ha guardado la carrera con éxito', parameters_button.CERRAR);
        crud_carrera_load_cursos(data);
    },
            (status) => {
        $('#loader').modal('hide');
        $('#carrera_modal').modal('toggle');
        error_message_show('Error', status);
    });
}

function update(id) {
    $('#confirmation_modal').modal('hide');
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "carrera?opcion=update&" + getCarrera(id)
    }).then((data) => {
        $('#loader').modal('hide');
        $('#carrera_modal').modal('hide');
        message_show('Notificación', 'Se ha guardado la modificación con éxito', parameters_button.CERRAR);
        crud_carrera_load_cursos(data);
    },
            (status) => {
        $('#loader').modal('hide');
        $('#carrera_modal').modal('toggle');
        error_message_show('Error', status);
    });
}

function remove(id) {
    $('#confirmation_modal').modal('hide');
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "carrera?opcion=delete&" + carrera_get_datas(id)
    }).then((data) => {
        $('#loader').modal('hide');
        message_show('Notificación', 'Se ha eliminado el curso con éxito', parameters_button.CERRAR);
        crud_carrera_load_cursos(data);
    },
            (status) => {
        $('#loader').modal('hide');
        error_message_show('Error', status);
    });
}

function list() {
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "carrera?opcion=list"
    }).then((data) => {
        $('#loader').modal('hide');
        crud_carrera_load_cursos(data);
    },
            (status) => {
        $('#loader').modal('hide');
        error_message_show('Error', status);
    });
}

function crud_carrera_load_cursos(data) {
    var fecha = new Date();
    $('#table_data').DataTable().destroy();
    $('#table_data').DataTable({
        language: {
            "paginate": {
                "first": "Primera",
                "last": "Última",
                "next": "Siguiente",
                "previous": "Anterior"
            },
            "search": "Buscar:",
            "lengthMenu": "Mostrando _MENU_ registros por página",
            "zeroRecords": "Ningún dato disponible en esta tabla",
            "info": "Página _PAGE_ de _PAGES_",
            "infoEmpty": "No se encontraron datos",
            "infoFiltered": "(Filtrado de un total de _MAX_ registros)",
            "EmptyTable": "Ningún dato disponible en esta tabla"
        },
        data: data,
        dom: '<"float-left"B> <"float-right"f>t<"row"<"col-sm-5"l><"col-sm-3"i><"col-sm-4"p>>',
        buttons: [
            {
                extend: 'pdf',
                text: '<i class="fa fa-file-pdf-o" title="Exportar a PDF"></i>',
                messageTop: 'Sistema de Matrícula',
                title: 'Reporte de cursos ' + fecha.getDate() + '-' + (1 + fecha.getMonth()) + '-' + fecha.getFullYear(),
                exportOptions: {
                    columns: [0, 1, 2, 3]
                }
            },
            {
                extend: 'excel',
                text: '<i class="fa fa-file-excel-o" title="Exportar a Excel"></i>',
                messageTop: 'Sistema de Matrícula',
                title: 'Reporte de cursos ' + fecha.getDate() + '-' + (1 + fecha.getMonth()) + '-' + fecha.getFullYear(),
                exportOptions: {
                    columns: [0, 1, 2, 3]
                }
            },
            {
                extend: 'print',
                text: '<i class="fa fa-print" title="Imprimir"></i>',
                messageTop: 'Sistema de Matrícula',
                title: 'Reporte de cursos ' + fecha.getDate() + '-' + (1 + fecha.getMonth()) + '-' + fecha.getFullYear(),
                exportOptions: {
                    columns: [0, 1, 2, 3]
                }
            }
        ],
        colReorder: true,
        columnDefs: [
            {
                targets: "_all",
                className: "dt-center"
            }],
        columns: [
            {"data": 'codigo'},
            {"data": 'nombre'},
            {"data": 'titulo'},
            {"defaultContent":
                        '\
                    <button data-toggle="modal" data-target="#carrera_modal" class="btn update" title="Editar">\n\
                        <i class="fa fa-edit"></i>\n\
                    </button>\n\
                    <button class="btn delete" title="Eliminar">\n\
                        <i class="fa fa-trash"></i>\n\
                    </button>'
            }
        ]
    });
    table_acciones();
}

function table_acciones() {
    $('#table_data tbody').on('click', 'button', function () {
        var action = this.className;
        var data = $('#table_data').DataTable().row($(this).parents('tr')).data();
        if (action === 'btn update')
            actualizar(data);
        if (action === 'btn delete')
            eliminar(data);
    });
}

function crud_carrera_confirmation_show() {
    $('#curso_modal').modal('hide');
    confirmation_show();
}

function serch_change() {
    ajax({
        type: "GET",
        url: "synchronizer"
    }).then((data) => {
        if (data)
            list();
    },
            (status) => {
        error_message_show('Error', status);
    });
}

document.addEventListener("DOMContentLoaded", crud_carrera_init);
