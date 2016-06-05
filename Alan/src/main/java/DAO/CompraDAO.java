/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.entidades.Compra;
import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hudson Reis
 */
public class CompraDAO {
     public static void adicionar(Compra compra) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO COMPRA  "
                //Nomes dos campos no banco
                + "(codigoProduto, valor, idUsuario, quantidade)"
                + "VALUES(?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, compra.getCodigoProduto());
            stmt.setFloat(2, compra.getPreco());
            stmt.setInt(3, compra.getCodigoUsuario());
            stmt.setInt(4, compra.getQuantidade());
            
            stmt.execute();
        }
    }
    
}
