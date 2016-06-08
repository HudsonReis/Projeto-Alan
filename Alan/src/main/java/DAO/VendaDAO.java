package DAO;

import classes.VendaListagem;
import classes.entidades.Item;
import conexao.ConexaoBanco;
import classes.entidades.Venda;
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
 * @author caiqu
 */
public class VendaDAO {

    public static void adicionar(Venda venda) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "INSERT INTO VENDA "
                + "(codigoFilial, idUsuario, valorTotal, dataVenda)"
                + "VALUES(?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            
            stmt.setInt(1, venda.getCodigoFilial());
            stmt.setInt(2, venda.getCodigoUsuario());
            stmt.setDouble(3, venda.getValorTotal());
            stmt.setDate(4, new java.sql.Date(venda.getDataVenda().getTime()));

            stmt.execute();
        }
        
        List<Item> itens = venda.getItens();
        int idVenda = consultarIdVenda();

        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).setIdMovimentacao(idVenda);
        }

        adicionarItens(itens);
    }
    
    public static void adicionarItens(List<Item> itens) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "INSERT INTO VENDA_ITEM (idVenda, codigoProduto, quantidade, valorUnitario)"
                + " VALUES(?,?,?,?)";

        for (Item item : itens) {
            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

                stmt.setInt(1, item.getIdMovimentacao());
                stmt.setInt(2, item.getCodigoProduto());
                stmt.setInt(3, item.getQuantidade());
                stmt.setDouble(4, item.getValorUnitario());

                stmt.execute();
            }
            
            ProdutoDAO.atualizarEstoque(item.getCodigoProduto(), item.getQuantidade(), false);
        }
    }
    
    public static int consultarIdVenda() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT idVenda FROM VENDA ORDER BY idVenda desc FETCH FIRST 1 ROWS ONLY";

        int idVenda;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            ResultSet result = stmt.executeQuery();
            result.next();
            
            idVenda = result.getInt("idVenda");
        }

        return idVenda;
    }

    public static List<VendaListagem> listar() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql
                = " select "
                + " vi.IDVENDA, "
                + " v.DATAVENDA, "
                + " vi.CODIGOPRODUTO, "
                + " p.NOME as NOME_PRODUTO, "
                + " v.IDUSUARIO, "
                + " u.NOME as NOME_USUARIO, "
                + " vi.QUANTIDADE, "
                + " vi.VALORUNITARIO, "
                + " v.VALORTOTAL "
                + " from venda_item vi "
                + " inner join venda v  "
                + " on vi.IDVENDA = v.IDVENDA "
                + " inner join produto p on vi.CODIGOPRODUTO = p.CODIGOPRODUTO "
                + " inner join usuario u on u.CODIGOUSUARIO = v.IDUSUARIO ";


        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        List<VendaListagem> retorno = new ArrayList<>();
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

            VendaListagem venda = new VendaListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade,vrUnitario, valor);
            retorno.add(venda);

        }
        return retorno;
    }
    
    public static List<VendaListagem> listar(String dataInicial, String dataFinal) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql
                = " select "
                + " vi.IDVENDA, "
                + " v.DATAVENDA, "
                + " vi.CODIGOPRODUTO, "
                + " p.NOME as NOME_PRODUTO, "
                + " v.IDUSUARIO, "
                + " u.NOME as NOME_USUARIO, "
                + " vi.QUANTIDADE, "
                + " vi.VALORUNITARIO, "
                + " v.VALORTOTAL "
                + " from venda_item vi "
                + " inner join venda v  "
                + " on vi.IDVENDA = v.IDVENDA "
                + " inner join produto p on vi.CODIGOPRODUTO = p.CODIGOPRODUTO "
                + " inner join usuario u on u.CODIGOUSUARIO = v.IDUSUARIO "
                + " WHERE v.DATAVENDA BETWEEN ? AND ? ";


        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);
        
        List<VendaListagem> retorno = new ArrayList<>();
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

            VendaListagem venda = new VendaListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade,vrUnitario, valor);
            retorno.add(venda);

        }
        return retorno;
    }
    
    public static List<VendaListagem> listar(String dataInicial, String dataFinal, int codProduto) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql
                = " select "
                + " vi.IDVENDA, "
                + " v.DATAVENDA, "
                + " vi.CODIGOPRODUTO, "
                + " p.NOME as NOME_PRODUTO, "
                + " v.IDUSUARIO, "
                + " u.NOME as NOME_USUARIO, "
                + " vi.QUANTIDADE, "
                + " vi.VALORUNITARIO, "
                + " v.VALORTOTAL "
                + " from venda_item vi "
                + " inner join venda v  "
                + " on vi.IDVENDA = v.IDVENDA "
                + " inner join produto p on vi.CODIGOPRODUTO = p.CODIGOPRODUTO "
                + " inner join usuario u on u.CODIGOUSUARIO = v.IDUSUARIO "
                + " WHERE v.DATAVENDA BETWEEN ? AND ? "
                + " AND vi.CODIGOPRODUTO = ?";


        PreparedStatement stmt;
        stmt = conexao.prepareStatement(sql);
        stmt.setString(1, dataInicial);
        stmt.setString(2, dataFinal);
        stmt.setInt(3, codProduto);
        
        List<VendaListagem> retorno = new ArrayList<>();
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

            VendaListagem venda = new VendaListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade,vrUnitario, valor);
            retorno.add(venda);

        }
        return retorno;
    }

}
