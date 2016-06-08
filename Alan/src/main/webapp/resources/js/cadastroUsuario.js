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
    
    dispararResposta();
    $("#nome").focus();
});
