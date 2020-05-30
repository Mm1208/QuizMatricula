
function loader_init() {
    loader_label_init();
}

let loader_text;
function loader_label_init(){
    loader_text = document.getElementById('loader_text');
    $(document).on('show.bs.modal', '.modal', function () {
        var zIndex = 1040 + (10 * $('.modal:visible').length);
        $(this).css('z-index', zIndex);
        setTimeout(function () {
            $('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
        }, 0);
    });
}

function loader_progress_bar(e) {
    if (e.lengthComputable) {
        var percentComplete = (e.loaded / e.total) * 100;
        loader_text.innerHTML = percentComplete + '%';
        $('.progress-bar').css('width', percentComplete + '%').attr('aria-valuenow', percentComplete);
    }
}

document.addEventListener("DOMContentLoaded", loader_init);