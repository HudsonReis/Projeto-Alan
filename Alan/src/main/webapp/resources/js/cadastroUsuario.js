$(document).ready(function () {
    $(document).on("click", "button[id='btnCancelar']", function () {
        var url = window.location.origin + "/Alan/Home";
        window.location.href = url;
    });
    
    var setarSelects = function(array, id) {
        for(var x = 0; x < array.length; x++) {
            
            if (array[x].value == id) {
                $(array[x]).attr("selected", "selected");
            }
        }
    };
    
    var mensagem = $("#mensagem").val();
    
    if(mensagem == "") {
        $("#ativo").attr("checked", "checked");
    } else {
        var status = $("#statusEdit").val();
        if(status == "true") {
            $("#ativo").attr("checked", "checked");
        } else if (status == "false"){
            $("#inativo").attr("checked", "checked");
        }
    }

    var perfilId = $("#hiddenPerfilId").val();
    var perfis = $("select[name='perfilId']").children();

    var filialId = $("#hiddenFilialId").val();
    var filiais = $("select[name='filialId']").children();

    setarSelects(perfis, perfilId);
    setarSelects(filiais, filialId);
    
    toastr.options = {
        "closeButton": false,
        "debug": false,
        "newestOnTop": false,
        "progressBar": false,
        "positionClass": "toast-top-right",
        "preventDuplicates": false,
        "onclick": null,
        "showDuration": "300",
        "hideDuration": "1000",
        "timeOut": "5000",
        "extendedTimeOut": "1000",
        "showEasing": "swing",
        "hideEasing": "linear",
        "showMethod": "fadeIn",
        "hideMethod": "fadeOut"
    };
    
    dispararResposta();
    $("#nome").focus();
});

var dispararResposta = function () {
    var sucesso = $("#sucesso").val();
    var mensagem = $("#mensagem").val();
    var campo = $("#campo").val();
    
    if (sucesso === "false") {
        $("#" + campo).addClass("has-error");
        toastr.error(mensagem, 'Erro!')
    } else if (sucesso === "true") {
        toastr.success(mensagem, 'Sucesso!')
    }
};
