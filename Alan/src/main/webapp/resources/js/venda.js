var carrinhoCompra = [];

$(document).ready(function() {

    $(document).on("click", "button[id='btnIncluir']", function () {
        
        console.log("ae");
        var produtoId = $("#Produto").val();
        var produto = $("#Produto").text();
        var quantidade = $("#quantidade").val();
        var valor = $("#valor").val();
        
        var venda = {
            codigoProduto: produtoId,
            nome: produto,
            valor: valor,
            quantidade: quantidade
        };
        
        carrinhoCompra.push(venda);
        
        preencherTabela(venda);
    });
    
    var preencherTabela = function (venda) {
        var tbody = $("#vendas tbody");
        var htmlStr = "<tr><td>" + venda.nome + "</td><td>" + venda.quantidade + "</td><td>" + venda.valor + "</td></tr>";
        
        tbody.append(htmlStr);
    };
});