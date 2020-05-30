/* global parameters_classes, parameters_button, ajax */
let curso_type = sessionStorage.getItem('curso_type');

const curso_parameters = {
    TITLE: (curso_type === 1) ? 'Ingreso de Curso' : 'Mantenimiento de Curso',
    SECONDARY_BUTTON: (curso_type === 1) ? parameters_button.INGRESAR : parameters_button.GUARDAR
};

function curso_init() {
    curso_classes_init();
    curso_label_init();
    curso_button_init();
    curso_body_init();
}

function curso_classes_init() {
    document.getElementById('curso_header').className = parameters_classes.CLASS_HEADER;
    document.getElementById('curso_content').className = parameters_classes.CLASS_CONTENT;
    document.getElementById('curso_footer').className = parameters_classes.CLASS_FOOTER;
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function () {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });
}

let curso_title;
function curso_label_init() {
    curso_title = document.getElementById('curso_title');
    curso_title.className = parameters_classes.CLASS_TITLE;
    curso_title.innerHTML = curso_parameters.TITLE;
}

let curso_btn_primary;
let curso_btn_secondary;
function curso_button_init() {
    curso_btn_primary = document.getElementById("curso_btn_primary");
    curso_btn_secondary = document.getElementById("curso_btn_secondary");
    curso_btn_primary.className = parameters_classes.CLASS_BUTTON_PRIMARY;
    curso_btn_secondary.className = parameters_classes.CLASS_BUTTON_SECONDARY;
    curso_btn_primary.innerHTML = parameters_button.CERRAR;
    curso_btn_secondary.innerHTML = curso_parameters.SECONDARY_BUTTON;
}

let curso_body;
function curso_body_init() {
    curso_body = document.getElementById("curso_body");
    curso_body.className = parameters_classes.CLASS_BODY;
}

function curso_get_datas(id) {
    return 'json=' + encodeURIComponent(JSON.stringify({
        id: id,
        codigo: '',
        nombre: '',
        creditos: -1,
        hora_semana: -1,
        anno: -1,
        ciclo: undefined,
        carrera: undefined
    }));
}

function curso_load(id) {
    $('#confirmation_modal').modal('hide');
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "curso?opcion=query&" + curso_get_datas(id)
    }).then((curso) => {
        $('#loader').modal('hide');
        curso_show_curso(
                curso.codigo,
                curso.nombre,
                curso.creditos,
                curso.hora_semana,
                curso.anno,
                curso.ciclo.id,
                curso.carrera.id
                );
    },
            (status) => {
        $('#loader').modal('hide');
        error_message_show('Error', status);
    });
}

function curso_show_curso(codigo, nombre, creditos, hora_semana, anno, ciclo, carrera) {
    document.getElementById("curso_codigo").value = codigo;
    document.getElementById("curso_nombre").value = nombre;
    document.getElementById("curso_creditos").value = creditos;
    document.getElementById("curso_horas").value = hora_semana;
    document.getElementById("curso_anno").value = anno;
    document.getElementById("curso_ciclo").value = ciclo;
    document.getElementById("curso_carrera").value = carrera;
}
function curso_reset() {
    curso_show_curso('', '', '', '', '', '', '');
}


document.addEventListener("DOMContentLoaded", curso_init);