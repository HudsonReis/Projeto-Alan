package DAO;

import classes.Usuario;
import conexao.ConexaoBanco;
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
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT u.codigoUnitario, u.codigoFilial, u.codigoPerfil, u.nome, u.login, u.senha"
                + ", u.status, p.nome as perfil"
                + " FROM Usuario u INNER JOIN Perfil p ON u.codigoPerfil = p.codigoPerfil "
                + "WHERE u.login = ? AND u.senha = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setString(1, login);
        stmt.setString(2, senha);
        
        ResultSet result = stmt.executeQuery();
        
        result.next();
        
        int codUnitario = result.getInt("codigoUnitario");
        int codFilial = result.getInt("codigoFilial");
        int codPerfil = result.getInt("codigoPerfil");
        String nome = result.getString("nome");
        String loginRes = result.getString("login");
        String senhaRes = result.getString("senha");
        String perfil = result.getString("perfil");
        boolean status = result.getBoolean("status");
        List<Integer> funcionalidades = consultarFuncionalidadesDoPerfil(result.getInt("codigoPerfil"));
        
        Usuario usuario = new Usuario(nome, codUnitario, codFilial, codPerfil, loginRes, senhaRes, status, funcionalidades, perfil);
        
        stmt.close();
        
        return usuario;
    }
    
    public static List<Integer> consultarFuncionalidadesDoPerfil(int codigoPerfil) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT codigoFuncionalidade FROM Perfil_Funcionalidade WHERE codigoPerfil = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, codigoPerfil);
        
        List<Integer> retorno = new ArrayList<Integer>();
        ResultSet result = stmt.executeQuery();
        
        while(result.next()) {
            retorno.add(result.getInt("codigoFuncionalidade"));
        }
        
        stmt.close();
        
        return retorno;
    }
    
    public static List<Usuario> listar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT u.codigoUnitario, u.codigoFilial, u.codigoPerfil, u.nome, u.login, u.senha"
                + ", u.status, p.nome as perfil"
                + " FROM Usuario u INNER JOIN Perfil p ON u.codigoPerfil = p.codigoPerfil ";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<Usuario> retorno = new ArrayList<Usuario>();
        ResultSet result = stmt.executeQuery();
        
        while(result.next()) {
            
            int codUnitario = result.getInt("codigoUnitario");
            int codFilial = result.getInt("codigoFilial");
            int codPerfil = result.getInt("codigoPerfil");
            String nome = result.getString("nome");
            String loginRes = result.getString("login");
            String senhaRes = result.getString("senha");
            String perfil = result.getString("perfil");
            boolean status = result.getBoolean("status");
            List<Integer> funcionalidades = consultarFuncionalidadesDoPerfil(result.getInt("codigoPerfil"));

            Usuario usuario = new Usuario(nome, codUnitario, codFilial, codPerfil, loginRes, senhaRes, status, funcionalidades, perfil);
        
            retorno.add(usuario);
        }
        
        return retorno;
    }
    
    public static Usuario consultarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT codigoUnitario, codigoFilial, codigoPerfil, nome, login, senha, status FROM Usuario" +
                "WHERE codigoUnitario = ?";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        result.next();
        
        int codUnitario = result.getInt("codigoUnitario");
        int codFilial = result.getInt("codigoFilial");
        int codPerfil = result.getInt("codigoPerfil");
        String nome = result.getString("nome");
        String loginRes = result.getString("login");
        String senhaRes = result.getString("senha");
        boolean status = result.getBoolean("status");
        
        Usuario usuario = new Usuario(nome, codUnitario, codFilial, codPerfil, loginRes, senhaRes, status, null, "");
        
        stmt.close();
        
        return usuario;
    }

    public static int maxId() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT MAX(CODIGOUNITARIO)FROM USUARIO";
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


