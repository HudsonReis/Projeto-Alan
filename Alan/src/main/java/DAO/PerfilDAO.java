/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.Perfil;
import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class PerfilDAO {

    public static ArrayList<Perfil> consultar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT * FROM PERFIL";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();
        
        ArrayList <Perfil> retorno = new ArrayList<Perfil>();
        
        while(result.next()){
            int codigoPerfil = result.getInt("codigoperfil");
            String nome = result.getString("nome");
            Perfil p = new Perfil(codigoPerfil, nome);
            retorno.add(p);
        }
        
        return retorno;
    }
}
