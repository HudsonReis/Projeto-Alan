
package DAO;

import conexao.ConexaoBanco;
import classes.entidades.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author caiqu
 */
public class VendaDAO {
    
    public static void adicionar(Venda saida) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO VENDA  "
                //Nomes dos campos no banco
                + "(codigoPeca, codFilial, idUsuario, quantidade)"
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
