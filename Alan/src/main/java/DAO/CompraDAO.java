/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import classes.CompraListagem;
import classes.entidades.Compra;
import classes.entidades.Item;
import classes.entidades.Usuario;
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

    //saida do sql padrão
    private static String select
            = " select "
            + " ci.IDCOMPRA, "
            + " c.CODIGOFILIAL, "
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
        int idCompra = consultarIdCompra();

        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).setIdMovimentacao(idCompra);
        }

        adicionarItens(itens);
    }

    public static void adicionarItens(List<Item> itens) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "INSERT INTO COMPRA_ITEM (idCompra, codigoProduto, quantidade, valorUnitario)"
                + " VALUES(?,?,?,?)";

        for (Item item : itens) {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setInt(1, item.getIdMovimentacao());
                stmt.setInt(2, item.getCodigoProduto());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getValorUnitario());

                stmt.execute();
            }

            ProdutoDAO.atualizarEstoque(item.getCodigoProduto(), item.getQuantidade(), true);
        }
    }

    public static int consultarIdCompra() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT idCompra FROM COMPRA ORDER BY idCompra desc FETCH FIRST 1 ROWS ONLY";

        int idCompra;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            ResultSet result = stmt.executeQuery();
            result.next();

            idCompra = result.getInt("idCompra");
        }

        return idCompra;
    }

    public static List<CompraListagem> listar(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = select;
        if ((usuario.getCodigoPerfil() != 1) && (usuario.getCodigoPerfil() != 2)) {
            sql += " where u.codigofilial = ? ";
        }

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        if ((usuario.getCodigoPerfil() != 1) && (usuario.getCodigoPerfil() != 2)) {
            stmt.setInt(1, usuario.getCodigoFilial());
        }
        List<CompraListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();
        retorno = percorreResult(result);

        return retorno;
    }

    public static List<CompraListagem> listar(String dataInicial, String dataFinal, Usuario usuario, int codFilial) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = select;
        sql += " WHERE c.DATACOMPRA BETWEEN ? AND ? ";

        if (codFilial != 0) {
            sql += " and u.codigofilial = ? ";
        }

        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);

        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);
        if (codFilial != 0) {
            stmt.setInt(3, codFilial);
        }

        List<CompraListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();
        retorno = percorreResult(result);
        return retorno;
    }

    public static List<CompraListagem> listar(String dataInicial, String dataFinal, int codProduto, Usuario usuario, int codFilial) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = select;
        sql += " WHERE c.DATACOMPRA BETWEEN ? AND ? "
                + " AND ci.CODIGOPRODUTO = ? ";

        if (codFilial != 0) {
            sql += " and u.codigofilial = ? ";
        }
        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);
        stmt.setInt(3, codProduto);
        if (codFilial != 0) {
            stmt.setInt(4, codFilial);
        }

        List<CompraListagem> retorno = new ArrayList<>();
        ResultSet result = stmt.executeQuery();
        retorno = percorreResult(result);

        sql = "";
        return retorno;
    }

    public static List<CompraListagem> percorreResult(ResultSet result) throws SQLException, ClassNotFoundException {
        List<CompraListagem> retorno = new ArrayList<>();
        while (result.next()) {
            int idCompra = result.getInt("IDCOMPRA");
            int idFilial = result.getInt("CODIGOFILIAL");
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date data = result.getDate("DATACOMPRA");
            String dataCompra = format.format(data);
            int codigoProduto = result.getInt("CODIGOPRODUTO");
            String nomeProduto = result.getString("NOME_PRODUTO");
            int idUsuario = result.getInt("IDUSUARIO");
            String nomeUsuario = result.getString("NOME_USUARIO");
            double quantidade = result.getDouble("QUANTIDADE");
            double vrUnitario = result.getDouble("VALORUNITARIO");
            double valor = result.getDouble("VALORTOTAL");

            CompraListagem compra = new CompraListagem(idCompra,idFilial, dataCompra, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade, vrUnitario, valor);
            retorno.add(compra);
        }
        return retorno;
    }

}
