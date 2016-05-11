package classes;

import conexao.conexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    public static Usuario consultar(String login, String senha) throws SQLException, ClassNotFoundException {
        Connection conexao = conexaoBanco.obterConexao();
        
        String sql = "SELECT codigoUnitario, codigoFilial, codigoPerfil, nome, login, senha, status"
                + " FROM Usuario WHERE login = ? AND senha = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setString(1, login);
        stmt.setString(2, senha);
        
        ResultSet result = stmt.executeQuery();
        
        String strResult = result.toString();
        
        int codUnitario = result.getInt("codigoUnitario");
        int codFilial = result.getInt("codigoFilial");
        int codPerfil = result.getInt("codigoPerfil");
        String nome = result.getString("nome");
        String loginRes = result.getString("login");
        String senhaRes = result.getString("senha");
        boolean status = result.getBoolean("status");
        List<Integer> funcionalidades = consultarFuncionalidadesDoPerfil(result.getInt("codigoPerfil"));
        
        Usuario usuario = new Usuario(nome, codUnitario, codFilial, codPerfil, loginRes, senhaRes, status, funcionalidades);
        
        stmt.close();
        
        return usuario;
    }
    
    public static List<Integer> consultarFuncionalidadesDoPerfil(int codigoPerfil) throws SQLException, ClassNotFoundException {
        Connection conexao = conexaoBanco.obterConexao();
        
        String sql = "SELECT codigoFuncionalidade FROM Perfil_Funcionalidade WHERE codigoPerfil = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, codigoPerfil);
        
        List<Integer> retorno = new ArrayList<Integer>();
        ResultSet result = stmt.executeQuery(sql);
        
        while(result.next()) {
            retorno.add(result.getInt("codigoFuncionalidade"));
        }
        
        stmt.close();
        
        return retorno;
    }
}


