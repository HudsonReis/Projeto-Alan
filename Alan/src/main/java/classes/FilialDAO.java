package classes;

import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FilialDAO {

    
    
    public static void adicionar(Filial filial) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO FILIAL  "
                //Nomes dos campos no banco
                + "(nome , fantasia, rua, numero, bairro, estado, cidade, cnpj)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, filial.getNome());
        stmt.setString(2, filial.getNomeFantasia());
        stmt.setString(3, filial.getRua());
        stmt.setInt(4, filial.getNum());
        stmt.setString(5, filial.getBairro());
        stmt.setString(6, filial.getEstado());
        stmt.setString(7, filial.getCidade());
        stmt.setString(8, filial.getCnpj());
        stmt.execute();
    }
}


