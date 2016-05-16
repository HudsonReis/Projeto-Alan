
package DAO;

import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import classes.MovimentacaoEntrada;

/**
 *
 * @author caiqu
 */
public class MovimentacaoEntradaDAO {
    
    public static void adicionar(MovimentacaoEntrada entrada) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO MOVIMENTACAOENTRADA  "
                //Nomes dos campos no banco
                + "(codPeca, idUsuario, quantidade)"
                + "VALUES(?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, entrada.getCodigoProduto());
        stmt.setInt(2, entrada.getCodigoUsuario());
        stmt.setInt(3, entrada.getQuantidade());

        stmt.execute();
        stmt.close();
    }
    
    
}
