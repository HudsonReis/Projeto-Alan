package DAO;

import conexao.ConexaoBanco;
import classes.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caiqu
 */
public class ProdutoDAO {

    public static void adicionar(Produto produto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO MOVIMENTACAOENTRADA  "
                //Nomes dos campos no banco
                + "(CODIGOFILIAL, IDUSUARIO, NOME, QUANTIDADEPECA, VALOR, STATUS)"
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, produto.getCodigoFilial());
        stmt.setInt(2, produto.getCodUsuario());
        stmt.setString(3, produto.getNome());
        stmt.setInt(4, produto.getQtdPeca());
        stmt.setDouble(5, produto.getValor());
        stmt.setBoolean(6, true);
        stmt.execute();
        stmt.close();
    }

    public static int maxId() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT MAX(CODPECA)FROM PRODUTO";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        int prox=0;
        ResultSet rs = stmt.executeQuery();
        if(rs.next()==false) {
                prox = 1;
            } else {
                prox = rs.getInt(1) + 1;
            }
        return prox;
    }

}
