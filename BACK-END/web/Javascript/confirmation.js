
/* global parameters_classes */

function confirmation_init(event) {
    confirmation_classes_init();
    confirmation_label_init();
    confirmation_button_init();
    confirmation_modal_init();
}

function confirmation_classes_init() {
    document.getElementById('confirmation_header').className = parameters_classes.CLASS_HEADER;
    document.getElementById('confirmation_content').className = parameters_classes.CLASS_CONTENT;
    document.getElementById('confirmation_footer').className = parameters_classes.CLASS_FOOTER;
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function () {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });
}

let confirmation_body;
let confirmation_title;
function confirmation_label_init() {
    confirmation_title = document.getElementById('confirmation_title');
    confirmation_title.className = parameters_classes.CLASS_TITLE;
    confirmation_title.innerHTML = '';
}

function confirmation_modal_init() {
    confirmation_body = document.getElementById("confirmation_body");
    confirmation_body.className = parameters_classes.CLASS_BODY;
    confirmation_body.innerHTML = '';
}

let confirmation_btn_primary;
let confirmation_btn_secondary;
function confirmation_button_init() {
    confirmation_btn_primary = document.getElementById("confirmation_btn_primary");
    confirmation_btn_secondary = document.getElementById("confirmation_btn_secondary");
    confirmation_btn_primary.className = parameters_classes.CLASS_BUTTON_PRIMARY;
    confirmation_btn_secondary.className = parameters_classes.CLASS_BUTTON_SECONDARY;
    confirmation_btn_primary.innerHTML = parameters_button.RECHAZAR;
    confirmation_btn_secondary.innerHTML = parameters_button.ACEPTAR;
}

function confirmation_load_message(title, message) {
    confirmation_title.innerHTML = title;
    confirmation_body.innerHTML = confirmation_label('', message);
}

function confirmation_show() {
    $('#confirmation_modal').modal('toggle');
}

function confirmation_label(property, value) {
    return (value !== undefined) ? "<label class='" + parameters_classes.CLASS_LABEL +
            "'>" + property + value + "</label>" : '';
}

document.addEventListener("DOMContentLoaded", confirmation_init);