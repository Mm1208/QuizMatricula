/* global parameters_classes, parameters_button, ajax */
let carrera_type = sessionStorage.getItem('carrera_type');

const carrera_parameters = {
    TITLE: (carrera_type === 1) ? 'Ingreso de Carrera' : 'Mantenimiento de Carrera',
    SECONDARY_BUTTON: (carrera_type === 1) ? parameters_button.INGRESAR : parameters_button.GUARDAR
};

function carrera_init() {
    carrera_classes_init();
    carrera_label_init();
    carrera_button_init();
    carrera_body_init();
}

function carrera_classes_init() {
    document.getElementById('carrera_header').className = parameters_classes.CLASS_HEADER;
    document.getElementById('carrera_content').className = parameters_classes.CLASS_CONTENT;
    document.getElementById('carrera_footer').className = parameters_classes.CLASS_FOOTER;
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function () {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });
}

let carrera_title;
function carrera_label_init() {
    carrera_title = document.getElementById('carrera_title');
    carrera_title.className = parameters_classes.CLASS_TITLE;
    carrera_title.innerHTML = carrera_parameters.TITLE;
}

let carrera_btn_primary;
let carrera_btn_secondary;
function carrera_button_init() {
    carrera_btn_primary = document.getElementById("carrera_btn_primary");
    carrera_btn_secondary = document.getElementById("carrera_btn_secondary");
    carrera_btn_primary.className = parameters_classes.CLASS_BUTTON_PRIMARY;
    carrera_btn_secondary.className = parameters_classes.CLASS_BUTTON_SECONDARY;
    carrera_btn_primary.innerHTML = parameters_button.CERRAR;
    carrera_btn_secondary.innerHTML = carrera_parameters.SECONDARY_BUTTON;
}

let carrera_body;
function carrera_body_init() {
    carrera_body = document.getElementById("carrera_body");
    carrera_body.className = parameters_classes.CLASS_BODY;
}

function carrera_get_datas(id) {
    return 'json=' + encodeURIComponent(JSON.stringify({
        id: id,
        codigo: undefined,
        nombre: undefined,
        titulo:undefined
    }));
}

function carrera_load(id) {
    $('#confirmation_modal').modal('hide');
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "carrera?opcion=query&" + carrera_get_datas(id)
    }).then((carrera) => {
        $('#loader').modal('hide');
        carrera_show_carrera(
                carrera.codigo,
                carrera.nombre,
                carrera.titulo
                );
    },
            (status) => {
        $('#loader').modal('hide');
        error_message_show('Error', status);
    });
}

function carrera_show_carrera(codigo, nombre, titulo) {
    document.getElementById("carrera_codigo").value = codigo;
    document.getElementById("carrera_nombre").value = nombre;
    document.getElementById("carrera_titulo").value = titulo;
}

function carrera_reset() {
    carrera_show_carrera('', '', '');
}

document.addEventListener("DOMContentLoaded", carrera_init);