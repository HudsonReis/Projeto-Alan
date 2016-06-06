var carrinhoCompra = [];
var auxRemover = 0;

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

            $("#codProduto").val(0);
            $("#Produto").val(null);
            $("#Preco").val(0);
            $("#Quantidade").val(0);
            var compra = {
                codProduto: codProduto,
                nomeProduto: nomeProduto,
                preco: preco,
                quantidade: quantidade
            };

            carrinhoCompra.push(compra);
            preencherTabela(compra);
            atualizarTotal();
            inserirSalvar();
        }
    });


    var preencherTabela = function (compra) {
        var tbody = $("#compras tbody");
        var htmlStr = "<tr><td>" + compra.codProduto + "</td><td>" + compra.nomeProduto + "</td><td>" + compra.preco + "</td><td>" + compra.quantidade + "</td><td>" + inserirRemover();

        tbody.append(htmlStr);
    };

    function inserirSalvar() {
        document.getElementById("salvarCompra").innerHTML = "<button type='button' class='btn btn-success' id='btnSalvarCompra'>Salvar</button>";
    }
    
    function inserirRemover() {
        var tbody = $("#compras");
        var htmlStr = "<button type='button' class='btn btn-primary' id='btnRemoverCompra"+ auxRemover +"'"+">Remover</button></td></tr>";
        tbody.append(htmlStr);
        auxRemover++;
    }
    
    function remover(auxRemover){
        
    };
    
    var atualizarTotal = function () {
        var total = 0;

        //$("table tfoot").
        for(var i =0; i < carrinhoCompra.length; i++){
            total += carrinhoCompra[i].valor;
        }
        
        $("#valorTotal").val(total);
    };

});

