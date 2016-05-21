
package DAO;

import conexao.ConexaoBanco;
import classes.entidades.MovimentacaoSaida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author caiqu
 */
public class MovimentacaoSaidaDAO {
    
    public static void adicionar(MovimentacaoSaida saida) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO MOVIMENTACAOSAIDA  "
                //Nomes dos campos no banco
                + "(codPeca, codFilial, idUsuario, quantidade)"
                + "VALUES(?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, saida.getCodigoProduto());
        stmt.setInt(2, saida.getCodigoFilial());
        stmt.setInt(3, saida.getCodigoUsuario());
        stmt.setInt(4, saida.getQuantidade());

        stmt.execute();
        stmt.close();
    }
    
}
