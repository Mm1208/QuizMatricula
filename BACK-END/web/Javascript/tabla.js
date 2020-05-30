/* global parameters_classes */

function labelTable(value) {
    return (value !== undefined) ? "<td><label class='" +
            parameters_classes.CLASS_LABEL_TABLE + "'>" + value +
            "</label></td>" : '<td></td>';
}

function thHeadTable(value) {
    return (value !== undefined) ? "<th>" + value + "</th>" : '';
}

function buttonTable(funcion, parameters, value) {
    return (value !== undefined) ? "<td><button type='button' data-toggle='modal' data-target='#model' class='" +
            parameters_classes.CLASS_BUTTON + "' onclick='" + funcion + "(" + parameters + ");'>" +
            value + "</button></td>" : '';
}

function buttonTableOnlyButton(funcion, parameters, value) {
    return (value !== undefined) ? "<button type='button' class='" +
            parameters_classes.CLASS_BUTTON_ICON + "' onclick='" + funcion + "(" + parameters + ");'>" +
            value + "</button>" : '';
}

function buttonTableToModal(funcion, parameters, value) {
    return (value !== undefined) ? "<td><button type='button' data-toggle='modal' data-target='#model' class='" +
            parameters_classes.CLASS_BUTTON + "' onclick='" + funcion + "(" + parameters + ");'>" +
            value + "</button></td>" : '';
}

function loadTableBody(list) {
    var table_body = document.getElementById("table_body");
    table_body.innerHTML = "";
    var tr = document.createElement("tr");
    tr.id = "sinResultado";
    tr.style.display = "none";
    tr.innerHTML = "<td>Sin resultados</td>";
    table_body.appendChild(tr);

    list.forEach((x) => row(table_body, x));
}