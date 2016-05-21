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
    
    var edicao = $("#edicao").val();
    if(edicao == "true") {
        var status = $("#statusEdit").val();
        
        if(status == "true") {
            $("#ativo").attr("checked", "checked");
        } else {
            $("#inativo").attr("checked", "checked");
        }

        var filialId = $("#hiddenFilialId").val();
        var filiais = $("select[name='filialId']").children();

        setarSelects(filiais, filialId);
    } else {
        $("#ativo").attr("checked", "checked");
    }

});

