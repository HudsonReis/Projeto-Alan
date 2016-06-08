var carrinhoCompra = [];
    
$(document).ready(function () {
    iniciarToastr();
    dispararResposta();
    
    $(document).on("click", "#btnSalvar", function () {
       var filialId = $("#codigoFilial").val();
       var valorTotalItens = $("#valorTotalCompra").text();       
       
       $("#filialId").val(filialId);
       $("#valorTotalItens").val(retornarDecimal(valorTotalItens));
       $("#jsonItens").val(JSON.stringify(carrinhoCompra));
    });

    $(document).on("click", "button[id='btnIncluir']", function () {

        $("#itens").removeClass("hidden");

        var codigoFilial = $("#codigoFilial").val();
        var codigoProduto = $("#codigoProduto").val();
        var nomeProduto = $("#codigoProduto option:selected").text();
        var quantidade = $("#Quantidade").val();
        var valorUnitario = $("#valorUnitario").val();
        var valorTotal = $("#valorTotal").val();
        
        validarCampos(codigoFilial, "Informe uma Filial.", "codigoFilial");
        validarCampos(codigoProduto, "Informe o codigo do produto de maneira correta", "codigoProduto");
        validarCampos(quantidade, "Informe a quantidade do produto de maneira correta", "Quantidade");        
   
        
   
        if (prosseguir) {
            
            var compra = {
                codigoProduto: codigoProduto,
                nomeProduto: nomeProduto,
                quantidade: parseInt(quantidade),
                valorUnitario: retornarDecimal(valorUnitario),
                valorTotal: retornarDecimal(valorTotal)
            };

            carrinhoCompra.push(compra);
            preencherTabela(compra);
            atualizarTotal();
            limparCampos();
        }
    });
    
    $(document).on("focus", "#Quantidade", function () {
       $(this).val(""); 
    });
    
    $(document).on("blur", "#Quantidade", function () {
        var quantidade = $("#Quantidade").val();
        
        if(quantidade != 0 || quantidade != null) {
            var valorProduto = $("#codigoProduto option:selected").data("valor");   
            var valorTotal = quantidade * valorProduto;
            
            $("#valorTotal").val(formatarValor(valorTotal));
        }
    });
    
    $(document).on("change", "#codigoProduto", function () {
        var valorProduto = $("#codigoProduto option:selected").data("valor");  
        
        $("#valorUnitario").val(formatarValor(valorProduto));
        $("#Quantidade").focus();
    });
    
    $(document).on("click", "button[name='btnRemoverItem']", function() {
        var valorTotalCompra = retornarDecimal($("#valorTotalCompra").text());
        var valorTotalItem = retornarDecimal($(this).parent().prev().text());
        var subtracao = valorTotalCompra - valorTotalItem;        

        $(this).parent().parent().remove();
        $("#valorTotalCompra").text(formatarValor(subtracao));
    });
    
    var limparCampos = function () {
        $("#codigoProduto").val(0);
        $("#Quantidade").val(0);
        $("#valorUnitario").val("RS 0,00");
        $("#valorTotal").val("RS 0,00");
    };

    var preencherTabela = function (compra) {
        var tbody = $("#compras tbody");
        var htmlStr = "<tr><td>" + compra.codigoProduto + 
                      "</td><td>" + compra.nomeProduto + 
                      "</td><td>" + compra.quantidade + 
                      "</td><td>" + formatarValor(compra.valorUnitario) + 
                      "</td><td>" + formatarValor(compra.valorTotal) + 
                      "</td><td><button type='button' class='btn btn-primary btn-danger btn-xs' name='btnRemoverItem'>Remover</button></td></tr>";

        tbody.append(htmlStr);
    };
    
    var atualizarTotal = function () {
        var total = 0;

        for(var i = 0; i < carrinhoCompra.length; i++){        
            total += carrinhoCompra[i].valorTotal;
        }
        
        $("#valorTotalCompra").text(formatarValor(total));
    };

});

