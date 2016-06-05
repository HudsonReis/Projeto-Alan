var carrinhoCompra = [];
$(document).ready(function () {
    var remover = $('#remover').val();

    $(document).on("click", "button[id='btnIncluir']", function () {

        var codProduto = $("#codProduto").val();
        var nomeProduto = $("#Produto").val();
        var quantidade = $("#Quantidade").val();
        var preco = $("#Preco").val();
        

        //validação se os campos do form estão vazios
        if (codProduto == 0 || codProduto == null) {
            alert("Informe o codigo do produto de maneira correta");
            document.getElementById("codProduto").focus();
        } else if (nomeProduto == 0 || nomeProduto == null) {
            alert("Informe o produto de maneira correta");
            document.getElementById("Produto").focus();
        } else if (preco == 0 || preco == null) {
            alert("Informe o preço do produto de maneira correta");
            document.getElementById("Preco").focus();
        } else if (quantidade == 0 || quantidade == null) {
            alert("Informe a quantidade do produto de maneira correta");
            document.getElementById("Quantidade").focus();
        } else {
            var compra = {
                codProduto: codProduto,
                nomeProduto: nomeProduto,
                preco: preco,
                quantidade: quantidade
            };

            carrinhoCompra.push(compra);

            preencherTabela(compra);
        }
    });

    var preencherTabela = function (compra) {
        var tbody = $("#compras tbody");
        var htmlStr = "<tr><td>" + compra.codProduto + "</td><td>" + compra.nomeProduto + "</td><td>" + compra.preco + "</td><td>" + compra.quantidade + "</td>" + inserirRemover() + "</tr>";

        tbody.append(htmlStr);
    };

    function inserirRemover() {
       document.getElementById("remover").innerHTML = "<button type='button' class='btn btn-primary' id='btnRemover'>Remover</button>";
    }

});

