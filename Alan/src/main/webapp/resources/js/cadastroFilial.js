$(document).ready(function () {
    $(document).on("click", "button[id='btnCancelar']", function () {
        var url = window.location.origin + "/Alan/Home";
        window.location.href = url;
    });
});