
/* global LINKS, ajax */

function header_init() {
    header_load_user();
    header_load_link();
}

function header_load_link() {
    if (sessionStorage.getItem('user') !== 'undefined') {
        document.getElementById('header_link_logo').href = LINKS.PRINCIPAL;
        document.getElementById('header_link_nombre').href = LINKS.PRINCIPAL;
        document.getElementById('header_link_inicio').href = LINKS.PRINCIPAL;
        document.getElementById('header_link_acerca_de').href = '#';
        document.getElementById('header_link_acerca_de').onclick = header_acerca_de;
        document.getElementById('header_link_ayuda').href = '#';
        document.getElementById('header_link_ayuda').onclick = header_ayuda;
        document.getElementById('header_link_perfil').href = LINKS.PERFIL;
        document.getElementById('header_link_cerrar_sesion').href = '#';
        document.getElementById('header_link_cerrar_sesion').onclick = header_logout;
    } else {
        document.getElementById('header_link_logo').href = LINKS.INDEX;
        document.getElementById('header_link_nombre').href = LINKS.INDEX;
        document.getElementById('header_link_inicio').href = LINKS.INDEX;
        document.getElementById('header_link_acerca_de').onclick = header_acerca_de;
        document.getElementById('header_link_ayuda').onclick = header_ayuda;
        document.getElementById('navbarDropdown').style.visibility = "hidden";

    }
}

function header_load_user() {
    if (sessionStorage.getItem('user') === 'undefined' && window.location.href !== LINKS.LOGIN) {
        window.location.href = LINKS.LOGIN;
    } else if (window.location.href === LINKS.LOGIN) {
    } else {
        document.getElementById('header_nombre_usuario').innerHTML = (JSON.parse(sessionStorage.getItem('user'))).persona.nombre;
//        $('#loader').modal('toggle');
//        ajax({
//            type: "GET",
//            url: "login?opcion=query&id=" + sessionStorage.getItem('user')
//        }).then((user) => {
//            $('#loader').modal('hide');
//            document.getElementById('header_nombre_usuario').innerHTML = user.persona.nombre;
//            sessionStorage.setItem('user', user.id);
//        },
//                (status) => {
//            $('#loader').modal('hide');
//            error_message('Mensaje del Sistema', status);
//        });
    }
}

function header_acerca_de() {
    message_show('Información Sobre el Sistema',
            'Sistema de matrícula, SIMA',
            'Aceptar');
}

function header_ayuda() {
    message_show('Información de Ayuda',
            'Si necesita ayuda direccionece al siguiente link: www.helpme.com',
            'Aceptar');
}

function header_logout() {
    sessionStorage.setItem('user', undefined);
    window.location.href = LINKS.LOGIN;
}

document.addEventListener("DOMContentLoaded", header_init);