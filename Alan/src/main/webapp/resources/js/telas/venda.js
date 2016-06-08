var carrinhoVenda = [],
contador = 0;

$(document).ready(function () {
    
    $(document).on("click", "#btnSalvar", function () {
       var valorTotalItens = $("#valorTotalVenda").text();       
       
       $("#valorTotalItens").val(retornarDecimal(valorTotalItens));
       $("#jsonItens").val(JSON.stringify(carrinhoVenda));
    });
    
    $(document).on("click", "button[id='btnIncluir']", function () {
        
        $("#itens").removeClass("hidden");
        
        var codigoProduto = $("#codigoProduto").val();
        var nomeProduto = $("#codigoProduto option:selected").text();
        var quantidade = $("#Quantidade").val();
        var valorUnitario = $("#valorUnitario").val();
        var valorTotal = $("#valorTotal").val();
        
        validarCampos(codigoProduto, "Informe o codigo do produto de maneira correta", "codigoProduto");
        validarCampos(quantidade, "Informe a quantidade do produto de maneira correta", "Quantidade");
        
        if (prosseguir) {
            
            var venda = {
                codigoProduto: codigoProduto,
                nomeProduto: nomeProduto,
                quantidade: parseInt(quantidade),
                valorUnitario: retornarDecimal(valorUnitario),
                valorTotal: retornarDecimal(valorTotal)
            };
            
            carrinhoVenda.push(venda);
            preencherTabela(venda);
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
        
        var tr = $(this).parent().parent();
        var indexTr = tr.attr("id");
        
        tr.remove();
        $("#valorTotalCompra").text(formatarValor(subtracao));
        var index = indexTr;
        var item = carrinhoVenda.splice(index, 1);
        
        contador--;
    });
    
    var preencherTabela = function (venda) {
        var tbody = $("#vendas tbody");
        var htmlStr = "<tr id='item" + contador + "'><td>" + venda.codigoProduto + 
                      "</td><td>" + venda.nomeProduto + 
                      "</td><td>" + venda.quantidade + 
                      "</td><td>" + formatarValor(venda.valorUnitario) + 
                      "</td><td>" + formatarValor(venda.valorTotal) + 
                      "</td><td><button type='button' class='btn btn-primary btn-danger btn-xs' name='btnRemoverItem'>Remover</button></td></tr>";

        tbody.append(htmlStr);
        contador++;
    };
    
    var preencherTabelaItens = function () {
        var jsonItens = $("#jsonItens").val();    
        if (jsonItens != "" && jsonItens != undefined) {
            
            $("#itens").removeClass("hidden");
            jsonItens = jsonItens.replaceAll("'", "\"");          
            var itens = JSON.parse(jsonItens);                        
            
            carrinhoVenda = itens;
            
            for(var x = 0; x < carrinhoVenda.length; x++) {
                preencherTabela(carrinhoVenda[x]);
            }
        }
    };
    
    var limparCampos = function () {
        $("#codigoProduto").val(0);
        $("#Quantidade").val(0);
        $("#valorUnitario").val("RS 0,00");
        $("#valorTotal").val("RS 0,00");
    };
    
    var atualizarTotal = function () {
        var total = 0;

        for(var i = 0; i < carrinhoVenda.length; i++){        
            total += carrinhoVenda[i].valorTotal;
        }
        
        $("#valorTotalVenda").text(formatarValor(total));
    };
    
    iniciarToastr();
    preencherTabelaItens();
    dispararResposta();
});

