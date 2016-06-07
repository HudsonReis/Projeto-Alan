package DAO;

import conexao.ConexaoBanco;
import classes.entidades.Produto;
import classes.ProdutoListagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caiqu
 */
public class ProdutoDAO {

    public static void adicionar(Produto produto, boolean incluirProdutoValor) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "INSERT INTO PRODUTO"
                + " (CODIGOFILIAL, IDUSUARIO, NOME, QUANTIDADEPECA, STATUS, VALOR, PERCENTUALLUCRO)"
                + " VALUES(?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, produto.getCodigoFilial());
            stmt.setInt(2, produto.getCodUsuario());
            stmt.setString(3, produto.getNome());
            stmt.setString(4, "0");
            stmt.setBoolean(5, produto.getStatus());
            stmt.setDouble(6, produto.getValor());
            stmt.setDouble(7, produto.getPercentualLucro());
            
            stmt.execute();
        }
    }

    public static void alterar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "UPDATE PRODUTO SET CODIGOFILIAL = ?, IDUSUARIO = ?, NOME = ?, "
                + "STATUS = ?, VALOR = ?, PERCENTUALLUCRO = ? WHERE CODIGOPRODUTO = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, produto.getCodigoFilial());
            stmt.setInt(2, produto.getCodUsuario());
            stmt.setString(3, produto.getNome());
            stmt.setBoolean(4, produto.getStatus());
            stmt.setDouble(5, produto.getValor());
            stmt.setDouble(6, produto.getPercentualLucro());
            stmt.setInt(7, produto.getCodigoProduto());
            
            stmt.execute();
        }
    }

    public static Produto consultarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT codigoProduto, codigoFilial, idUsuario, nome, quantidadePeca, status," +
                     " valor, percentualLucro FROM PRODUTO WHERE codigoProduto = ?";

        Produto produto;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            
            int codigoProduto = result.getInt("codigoProduto");
            int codFilial = result.getInt("CODIGOFILIAL");
            int idUsuario = result.getInt("IDUSUARIO");
            String nome = result.getString("NOME");
            int qtdPeca = result.getInt("QUANTIDADEPECA");
            boolean status = result.getBoolean("STATUS");
            double valor = result.getDouble("VALOR");
            double percentualLucro = result.getDouble("PERCENTUALLUCRO");
            
            produto = new Produto(codigoProduto, codFilial, idUsuario, nome, qtdPeca, status, valor
            , percentualLucro);       
        }

        return produto;
    }

    public static List<ProdutoListagem> listar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT "
                +    " P.CODIGOPRODUTO, "
                +    " F.NOME AS FILIAL, "
                +    " U.NOME AS USUARIO, "
                +    " P.NOME AS PRODUTO,"
                +    " P.QUANTIDADEPECA, "
                +    " P.STATUS, "
                +    " P.VALOR"
                +    " FROM PRODUTO P "
                +    " INNER JOIN FILIAL F ON P.CODIGOFILIAL = F.CODIGOFILIAL "
                +    " INNER JOIN USUARIO U ON P.IDUSUARIO = U.CODIGOUSUARIO ";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<ProdutoListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();

        while (result.next()) {

            int codigoProduto = result.getInt("CODIGOPRODUTO");
            String filial = result.getString("FILIAL");
            String usuario = result.getString("USUARIO");
            String nomeProduto = result.getString("PRODUTO");
            int qtdPeca = result.getInt("QUANTIDADEPECA");
            double valor = result.getDouble("VALOR");
            boolean status = result.getBoolean("STATUS");

            ProdutoListagem produto = new ProdutoListagem(codigoProduto, filial, usuario, nomeProduto,
                    valor, qtdPeca, status);

            retorno.add(produto);
        }

        return retorno;
    }
    
    public static int maxId() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT MAX(CODIGOPRODUTO)FROM PRODUTO";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        int prox = 0;
        ResultSet rs = stmt.executeQuery();
        if (rs.next() == false) {
            prox = 1;
        } else {
            prox = rs.getInt(1) + 1;
        }
        return prox;
    }

}
