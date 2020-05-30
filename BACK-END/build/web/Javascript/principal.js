
function principal_init() {
    principal_title_init('Menú Principal');
}

function principal_title_init(title) {
    document.title = title;
    document.getElementById('principal_title').innerHTML = title;
}

document.addEventListener("DOMContentLoaded", principal_init);
