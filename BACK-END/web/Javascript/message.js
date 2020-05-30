/* global parameters_classes */

function message_init() {
    message_classes_init();
    message_label_init();
    message_button_init();
    message_body_init();
}

function message_classes_init() {
    document.getElementById('message_header').className = parameters_classes.CLASS_HEADER;
    document.getElementById('message_content').className = parameters_classes.CLASS_CONTENT;
    document.getElementById('message_footer').className = parameters_classes.CLASS_FOOTER;
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function () {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });
}

let message_title;
function message_label_init() {
    message_title = document.getElementById('message_title');
    message_title.className = parameters_classes.CLASS_TITLE;
    message_title.innerHTML = '';
}

let message_btn_primary;
let message_btn_secondary;
function message_button_init() {
    message_btn_primary = document.getElementById("message_btn_primary");
    message_btn_secondary = document.getElementById("message_btn_secondary");
    message_btn_primary.className = parameters_classes.CLASS_BUTTON_PRIMARY;
    message_btn_secondary.className = parameters_classes.CLASS_BUTTON_SECONDARY;
    message_btn_primary.innerHTML = '';
    message_btn_secondary.innerHTML = '';
}

let message_body;
function message_body_init() {
    message_body = document.getElementById("message_body");
    message_body.className = parameters_classes.CLASS_BODY;
    message_body.innerHTML = '';
}

function message_label(property, value) {
    return (value !== undefined) ? "<label class='" + parameters_classes.CLASS_LABEL +
            "'>" + property + value + "</label>" : '';
}

function message_input(id, type, value) {
    value = (value === undefined) ? '' : value;
    return "<input id='" + id + "' type='" + type +
            "' class='" + parameters_classes.CLASS_INPUT + "' value='" + value + "'>";
}

function message_show(title, message, secondarybutton, primarybutton) {
    message_title.innerHTML = title;
    message_body.innerHTML = message_label('', message);
    message_btn_secondary.innerHTML = secondarybutton;
    if (primarybutton === undefined) {
        message_btn_primary.hidden = true;
    } else {
        message_btn_primary.innerHTML = secondarybutton;
    }
    $('#message_modal').modal('toggle');
}

document.addEventListener("DOMContentLoaded", message_init);