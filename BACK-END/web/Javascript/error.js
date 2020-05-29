/* global parameters_classes */

function error_init(event) {
    error_classes_init();
    error_label_init();
    error_button_init();
    error_body_init();
}

function error_classes_init() {
    document.getElementById('error_header').className = parameters_classes.CLASS_HEADER;
    document.getElementById('error_content').className = parameters_classes.CLASS_CONTENT;
    document.getElementById('error_footer').className = parameters_classes.CLASS_FOOTER;
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function () {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });
}

let error_body;
let error_title;
function error_label_init() {
    error_title = document.getElementById('error_title');
    error_title.className = parameters_classes.CLASS_TITLE;
    error_title.innerHTML = '';
}

function error_body_init() {
    error_body = document.getElementById("error_body");
    error_body.className = parameters_classes.CLASS_BODY;
    error_body.innerHTML = '';
}

let error_btn_primary;
let error_btn_secondary;
function error_button_init() {
    error_btn_secondary = document.getElementById("error_btn_primary");
    error_btn_secondary.className = parameters_classes.CLASS_BUTTON_SECONDARY;
    error_btn_secondary.innerHTML = 'Cerrar';
}

function error_message_show(title, message) {
    error_title.innerHTML = title;
    error_body.innerHTML = label_error_modal('', message);
    $('#error_modal').modal('toggle');
}

function label_error_modal(property, value) {
    return (value !== undefined) ? "<label class='" + parameters_classes.CLASS_LABEL +
            "'>" + property + value + "</label>" : '';
}

document.addEventListener("DOMContentLoaded", error_init);