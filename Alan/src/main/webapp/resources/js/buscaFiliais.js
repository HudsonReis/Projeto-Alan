$(document).ready(function(){
    $("#usuarios").DataTable({
        "language":{
            "url": "//cdn.datatables.net/plug-ins/1.10.11/i18n/Portuguese-Brasil.json"
        }
    });

    $(document).on("click", "tbody tr", function () {
        $("tr").removeAttr("class", "success");
        $(this).attr("class", "success"); 

        $("button[id='btnEditar']").removeAttr("disabled", "disabled");
    });

    $(document).on("click", "button[id='btnEditar']", function () {
        var id = $("tr[class='success']").children()[0].innerHTML;
        var url = window.location.origin + "/Alan/CadastroFilial?id=" + id;
        window.location.href = url;
    });
    
    $(document).on("click", "button[id='btnNovo']", function () {
        var url = window.location.origin + "/Alan/CadastroFilial";
        window.location.href = url;
    });
});