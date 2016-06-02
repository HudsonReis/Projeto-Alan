var carrinhoCompra = [];
$(document).ready(function () {

    $(document).on("click", "button[id='btnIncluir']", function () {

        if (valida_form() === false) {
            alert('Por favor, preencha todos os campos!');
        
        } else {    
            console.log("ae");
            var codProduto = $("#codProduto").val();
            var produto = $("#Produto").text();
            var quantidade = $("#Quantidade").val();
            var preco = $("#Preco").val();

            var compra = {
                codProduto: codProduto,
                produto: produto,
                preco: preco,
                quantidade: quantidade
            };

            carrinhoCompra.push(compra);

            preencherTabela(compra);
        }
    });

    var preencherTabela = function (compra) {
        var tbody = $("#compras tbody");
        var htmlStr = "<tr><td>" + compra.codProduto + "</td><td>" + compra.produto + "</td><td>" + compra.preco + "</td></tr>" + compra.quantidade + "</td></tr>";

        tbody.append(htmlStr);
    };
});

function valida_form() {
    if (document.getElementById("codProduto").value === "") {
        document.getElementById("codProduto").focus();
        return false;
    } else
    if (document.getElementById("Produto").value === "") {
        document.getElementById("Produto").focus();
        return false;
    } else
    if (document.getElementById("Preco").value === "") {
        document.getElementById("Preco").focus();
        return false;
    }else
    if (document.getElementById("Quantidade").value === "") {
        document.getElementById("Quantidade").focus();
        return false;
    }
}

