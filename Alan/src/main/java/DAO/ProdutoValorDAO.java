/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.entidades.ProdutoValor;
import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class ProdutoValorDAO {
    public static void adicionar(ProdutoValor produtoValor) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "INSERT INTO PRODUTO_VALOR"
                + "(codigoProduto, dataInicioVigencia, dataTerminoVigencia, valorProduto)"
                + "VALUES(?,?,?,?)";

        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setInt(1, produtoValor.getCodigoProduto());
        stmt.setDate(2, produtoValor.getInicioVigencia());
        stmt.setDate(3, produtoValor.getTerminoVigencia());
        stmt.setDouble(4, produtoValor.getValor());

        stmt.execute();
        stmt.close();
    }
    
    public static List<ProdutoValor> listar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        
        String sql = "SELECT codigoProduto, dataInicioVigencia, dataTerminoVigencia, valorProduto FROM Produto_Valor";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        List<ProdutoValor> retorno = new ArrayList<ProdutoValor>();
        ResultSet result = stmt.executeQuery();
        
        while(result.next()) {
            
            int codigoProduto = result.getInt("codigoProduto");
            Date inicioVigencia = result.getDate("dataInicioVigencia");
            Date terminoVigencia = result.getDate("dataTerminoVigencia");
            double valor = result.getDouble("valorProduto");

            ProdutoValor produto = new ProdutoValor(codigoProduto, inicioVigencia, terminoVigencia, valor);
        
            retorno.add(produto);
        }
        
        return retorno;
    }
}
