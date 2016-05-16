
package DAO;

import conexao.ConexaoBanco;
import classes.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                + "(nome, codPeca, qtdPeca, valor, status, codigoFilial)"
                + "VALUES(?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getCodigoPeca());
        stmt.setInt(3, produto.getQtdPeca());
        stmt.setDouble(4, produto.getValor());
        stmt.setBoolean(5, produto.isStatus());
        stmt.setInt(6, produto.getCodigoFilial());

        stmt.execute();
        stmt.close();
    }
}
