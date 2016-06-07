/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.entidades.Compra;
import classes.entidades.Item;
import com.google.gson.internal.LinkedTreeMap;
import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hudson Reis
 */
public class CompraDAO {
     public static void adicionar(Compra compra) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "INSERT INTO COMPRA  "
                + "(codigoFilial, idUsuario, valorTotal, dataCompra)"
                + "VALUES(?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, compra.getCodigoFilial());
            stmt.setInt(2, compra.getCodigoUsuario());
            stmt.setDouble(3, compra.getValorTotal());
            stmt.setDate(4, new java.sql.Date(compra.getDataCompra().getTime()));
            
            stmt.execute();
        }
        
        List<Item> itens = compra.getItens();
        
        for (int i = 0; i < itens.size(); i++) {
            Item item = (Item)itens.get(i);
            item.setIdItem(consultarIdCompra(compra));
        }
             
        adicionarItens(compra.getItens());
    }
     
    public static void adicionarItens(List<Item> itens) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "INSERT INTO COMPRA_ITEM (idCompra, codigoProduto, quantidade, valorUnitario)" +
                     " VALUES(?,?,?,?)";
        
         try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
             for (Item item: itens) {
                 
                 stmt.setInt(1, item.getIdMovimentacao());
                 stmt.setInt(2, item.getCodigoProduto());
                 stmt.setInt(3, item.getQuantidade());
                 stmt.setDouble(4, item.getValorUnitario());
                 
                 stmt.execute();
             }}
    }
    
    public static int consultarIdCompra(Compra compra) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT TOP 1 idCompra FROM COMPRA_ITEM ORDER BY idCompra desc";
        
        int idCompra = 0;
        
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            ResultSet result = stmt.executeQuery();
            result.next();
            
            idCompra = result.getInt("idCompra");
        }
        
        return idCompra;
    }
}
