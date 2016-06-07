/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.CompraListagem;
import classes.VendaListagem;
import classes.entidades.Compra;
import classes.entidades.Item;
import com.google.gson.internal.LinkedTreeMap;
import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
            Item item = (Item) itens.get(i);
            item.setIdItem(consultarIdCompra(compra));
        }

        adicionarItens(compra.getItens());
    }

    public static void adicionarItens(List<Item> itens) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "INSERT INTO COMPRA_ITEM (idCompra, codigoProduto, quantidade, valorUnitario)"
                + " VALUES(?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            for (Item item : itens) {

                stmt.setInt(1, item.getIdMovimentacao());
                stmt.setInt(2, item.getCodigoProduto());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getValorUnitario());

                stmt.execute();
            }
        }
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

    public static List<CompraListagem> listar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql
                = " select "
                + " ci.IDCOMPRA, "
                + " c.DATACOMPRA, "
                + " ci.CODIGOPRODUTO, "
                + " p.NOME as NOME_PRODUTO, "
                + " c.IDUSUARIO, "
                + " u.NOME as NOME_USUARIO, "
                + " ci.QUANTIDADE, "
                + " ci.VALORUNITARIO, "
                + " c.VALORTOTAL "
                + " from compra_item ci "
                + " inner join compra c on ci.IDCOMPRA = c.IDCOMPRA "
                + " inner join produto p on ci.CODIGOPRODUTO = P.CODIGOPRODUTO "
                + " and c.CODIGOFILIAL = p.CODIGOFILIAL "
                + " inner join usuario u on u.CODIGOUSUARIO = C.IDUSUARIO " 
                + " and c.CODIGOFILIAL = u.CODIGOFILIAL";


        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        List<CompraListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int idVenda = result.getInt("IDCOMPRA");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data = result.getDate("DATACOMPRA");
            String dataVenda = format.format(data);
            int codigoProduto = result.getInt("CODIGOPRODUTO");
            String nomeProduto = result.getString("NOME_PRODUTO");
            int idUsuario = result.getInt("IDUSUARIO");
            String nomeUsuario = result.getString("NOME_USUARIO");
            double quantidade = result.getDouble("QUANTIDADE");
            double vrUnitario = result.getDouble("VALORUNITARIO");
            double valor = result.getDouble("VALORTOTAL");

            CompraListagem compra = new CompraListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade, vrUnitario, valor);
            retorno.add(compra);

        }
        return retorno;
    }

    public static List<CompraListagem> listar(String dataInicial, String dataFinal) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql=
                  " select "
                + " ci.IDCOMPRA, "
                + " c.DATACOMPRA, "
                + " ci.CODIGOPRODUTO, "
                + " p.NOME as NOME_PRODUTO, "
                + " c.IDUSUARIO, "
                + " u.NOME as NOME_USUARIO, "
                + " ci.QUANTIDADE, "
                + " ci.VALORUNITARIO, "
                + " c.VALORTOTAL "
                + " from compra_item ci "
                + " inner join compra c on ci.IDCOMPRA = c.IDCOMPRA "
                + " inner join produto p on ci.CODIGOPRODUTO = P.CODIGOPRODUTO "
                + " and c.CODIGOFILIAL = p.CODIGOFILIAL "
                + " inner join usuario u on u.CODIGOUSUARIO = C.IDUSUARIO " 
                + " and c.CODIGOFILIAL = u.CODIGOFILIAL"
                + " WHERE v.DATAVENDA BETWEEN ? AND ? ";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);

        List<CompraListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int idVenda = result.getInt("IDCOMPRA");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data = result.getDate("DATACOMPRA");
            String dataVenda = format.format(data);
            int codigoProduto = result.getInt("CODIGOPRODUTO");
            String nomeProduto = result.getString("NOME_PRODUTO");
            int idUsuario = result.getInt("IDUSUARIO");
            String nomeUsuario = result.getString("NOME_USUARIO");
            double quantidade = result.getDouble("QUANTIDADE");
            double vrUnitario = result.getDouble("VALORUNITARIO");
            double valor = result.getDouble("VALORTOTAL");

            CompraListagem compra = new CompraListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade, vrUnitario, valor);
            retorno.add(compra);

        }
        return retorno;
    }

    public static List<CompraListagem> listar(String dataInicial, String dataFinal, int codProduto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql =
                  " select "
                + " ci.IDCOMPRA, "
                + " c.DATACOMPRA, "
                + " ci.CODIGOPRODUTO, "
                + " p.NOME as NOME_PRODUTO, "
                + " c.IDUSUARIO, "
                + " u.NOME as NOME_USUARIO, "
                + " ci.QUANTIDADE, "
                + " ci.VALORUNITARIO, "
                + " c.VALORTOTAL "
                + " from compra_item ci "
                + " inner join compra c on ci.IDCOMPRA = c.IDCOMPRA "
                + " inner join produto p on ci.CODIGOPRODUTO = P.CODIGOPRODUTO "
                + " and c.CODIGOFILIAL = p.CODIGOFILIAL "
                + " inner join usuario u on u.CODIGOUSUARIO = C.IDUSUARIO " 
                + " and c.CODIGOFILIAL = u.CODIGOFILIAL"
                + " WHERE v.DATAVENDA BETWEEN ? AND ? "
                + " AND vi.CODIGOPRODUTO = ?";

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);
        stmt.setInt(3, codProduto);

        List<CompraListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int idVenda = result.getInt("IDVENDA");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data = result.getDate("DATAVENDA");
            String dataVenda = format.format(data);
            int codigoProduto = result.getInt("CODIGOPRODUTO");
            String nomeProduto = result.getString("NOME_PRODUTO");
            int idUsuario = result.getInt("IDUSUARIO");
            String nomeUsuario = result.getString("NOME_USUARIO");
            double quantidade = result.getDouble("QUANTIDADE");
            double vrUnitario = result.getDouble("VALORUNITARIO");
            double valor = result.getDouble("VALORTOTAL");

            CompraListagem compra = new CompraListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade, vrUnitario, valor);
            retorno.add(compra);

        }
        return retorno;
    }
}
