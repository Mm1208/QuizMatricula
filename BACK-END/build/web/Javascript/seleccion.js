/* global parameters_classes */

function option(select, nombre, value, funcion) {
    var option = document.createElement("option");
    option.innerHTML = labelTable(nombre);
    option.value = value;
    select.appendChild(option);
}