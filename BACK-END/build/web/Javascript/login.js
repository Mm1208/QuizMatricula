/* global ajax, LINKS */

function login_init() {
    login_title();
    login_button();
}

function login_title() {
    document.title = "Login";
}

function login_button() {
    document.getElementById("login_btn_primary").onclick = login_login;
}

function login_get_datas() {
    return 'json=' + encodeURIComponent(JSON.stringify({
        id: -1,
        usuario: document.getElementById('login_usuario').value,
        contrasenna: document.getElementById('login_contrasenna').value,
        persona: undefined
    }));
}

function login_login() {
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "login?opcion=login&" + login_get_datas()
    }).then((user) => {
        $('#loader').modal('hide');
        sessionStorage.setItem('user', JSON.stringify(user));
        window.location.href = LINKS.PRINCIPAL;
    },
            (status) => {
        $('#loader').modal('hide');
        message_show('Mensaje del Sistema', status, "Cerrar");
    });
}

document.addEventListener("DOMContentLoaded", login_init);
