/* global ajax */

const parameters_button_logout = {
    GUARDAR: 'Guardar',
    CERRAR: 'Cerrar',
    SALIR: 'Salir',
    DARBAJA:'Dar Baja'
};

function logout() {
    ajax({
        type: "POST",
        url: "logout?"
    }).then(() => {
        window.location.href = "http://localhost:8080/SIAC/index.html";
    },
        (status) => {
            message('Error', status, parameters_button_logout.CERRAR);
        });
}