
/* global parameters, error_parameters, loader_progress_bar */

const error_parameters = {
    _404: 'Registro no encontrado',
    _405: 'Usuario no autorizado',
    _406: 'Registro duplicado'
};

const parameters = {
    POST: 'POST',
    PUT: 'PUT',
    HEADER: 'Accept',
    VALUE: 'application/json',
    CONTENT_TYPE: 'Content-Type',
    SERVER_NAME: 'Microsoft.XMLHTTP'
};

function ajax(config) {
    var promiseObj = new Promise((resolve, reject) => {
        //
        var request = (window.XMLHttpRequest) ? new XMLHttpRequest() : new ActiveXObject(parameters.SERVER_NAME);
        //
        request.open(config.type, config.url, true);
        if (config.type === parameters.POST || config.type === parameters.PUT) {
            request.setRequestHeader(parameters.CONTENT_TYPE, config.contentType);
        }
        //
        request.upload.onprogress = loader_progress_bar;
        request.onprogress = loader_progress_bar;
        //
        request.setRequestHeader(parameters.HEADER, parameters.VALUE);
        request.onreadystatechange = () => {
            if (request.readyState === 4) {
                switch (request.status) {
                    case 200:
                        resolve(JSON.parse(request.responseText));
                        break;
                    case 204:
                        resolve();
                        break;
                    default:
                        reject(errorMessage(request));
                        break;
                }
            }
        };
        if (config.data) {
            request.send(config.data);
        } else {
            request.send();
        }
    });
    return promiseObj;
}

function errorMessage(request) {
    switch (request.status) {
        case 404:
            return error_parameters._404;
        case 403:
        case 405:
            return error_parameters._405;
        case 406:
            return error_parameters._406;
        default:
            return request.responseText;
    }
}