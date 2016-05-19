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
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO USUARIO  "
                + "(CODIGOFILIAL, CODIGOPERFIL, NOME, LOGIN, SENHA, STATUS)"
                + "VALUES(?,?,?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setInt(1, usuario.getCodigoFilial());
        stmt.setInt(2, usuario.getCodigoPerfil());
        stmt.setString(3, usuario.getNome());
        stmt.setString(4, usuario.getLogin());
        stmt.setString(5, usuario.getHashSenha().toString());
        stmt.setBoolean(6, usuario.getStatus());
        stmt.execute();
        stmt.close();
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


