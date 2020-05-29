
/* global LINKS, ajax */

function perfil_init() {
    perfil_load_user();
    header_load_link();
}

function perfil_get_datas() {
    var id = JSON.parse(sessionStorage.getItem('user')).id;
    return 'json=' + encodeURIComponent(JSON.stringify({
        id: id,
        usuario: '',
        contrasenna: '',
        persona: undefined
    }));
}

function perfil_load_user() {
    $('#loader').modal('toggle');
    ajax({
        type: "GET",
        url: "usuario?opcion=query&" + perfil_get_datas()
    }).then((user) => {
        $('#loader').modal('hide');
        document.getElementById('perfil_cedula').value = user.persona.cedula;
        document.getElementById('perfil_nombre').value = user.persona.nombre + ' ' + user.persona.ape1 + ' ' + user.persona.pe2;
        document.getElementById('perfil_telefono').value = user.persona.tel;
        document.getElementById('perfil_correo').value = user.persona.correo;
        if (user.persona.type === 'A') {
            document.getElementById('perfil_fecha_nacimiento').value = user.persona.fecha_nacimiento;
            document.getElementById('perfil_carrera').value = user.persona.carrera.id;
        } else {
            var div = document.getElementById('home');
            div.removeChild(div.lastChild);
            div.removeChild(div.lastChild);
            div.removeChild(div.lastChild);
            div.removeChild(div.lastChild);
        }
    },
            (status) => {
        $('#loader').modal('hide');
        message_show('Error', status);
    });
}

document.addEventListener("DOMContentLoaded", perfil_init);