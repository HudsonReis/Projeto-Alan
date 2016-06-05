var carrinhoCompra = [];

$(document).ready(function () {

    $(document).on("click", "button[id='btnIncluir']", function () {
        var produtoId = $("#produto").val();
        var quantidade = $("#quantidade").val();
        
        //validação se os campos do form estão vazios
        if (produtoId == 0) {
            alert("Selecione um produto");
        } else if (quantidade == 0 || quantidade == null) {
            alert("Informe uma quantidade para o produto");
        } else {

            var produto = $("#produto option:selected").text();
            var valor = $("#valor").val();

            var venda = {
                codigoProduto: produtoId,
                nome: produto,
                valor: valor,
                quantidade: quantidade
            }
        }
        ;

        $("#produto").val(0);
        $("#quantidade").val(0);

        carrinhoCompra.push(venda);
        preencherTabela(venda);


    });

    var preencherTabela = function (venda) {
        var tbody = $("#vendas tbody");
        var htmlStr = "<tr><td>" + venda.nome + "</td><td>" + venda.quantidade + "</td><td>" + venda.valor + "</td></tr>";

        tbody.append(htmlStr);
    };

    var atualizarTotal = function (venda) {
        var total = 0;

        //$("table tfoot").
        for(var i =0; i < carrinhoCompra.length; i++){
            total += carrinhoCompra[i].valor;
        }
        
        $("#valorTotal").val(total);
    };
});

