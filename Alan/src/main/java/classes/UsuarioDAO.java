package classes;

import conexao.conexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {
    public static void adicionar(Usuario usuario) throws SQLException, ClassNotFoundException {
//        Connection conexao = conexaoBanco.obterConexao();
//        //linguagem sql -> inserir no banco
//        String sql = "INSERT INTO Usuario  "
//                //Nomes dos campos no banco
//                + "(nome , fantasia, rua, numero, bairro, estado, cidade, cnpj)"
//                + "VALUES(?,?,?,?,?,?,?,?)";
//
//        PreparedStatement stmt = conexao.prepareStatement(sql);
//        stmt.setString(1, usuario.getNome());
//        stmt.setString(2, usuario.getNomeFantasia());
//        stmt.setString(3, usuario.getRua());
//        stmt.setInt(4, usuario.getNum());
//        stmt.setString(5, usuario.getBairro());
//        stmt.setString(6, usuario.getEstado());
//        stmt.setString(7, usuario.getCidade());
//        stmt.setString(8, usuario.getCnpj());
//        stmt.execute();
//        stmt.close();
    }
    
    public static void consultar(String nome) throws SQLException, ClassNotFoundException {
        Connection conexao = conexaoBanco.obterConexao();
        
        String sql = "SELECT ";
    }
}


