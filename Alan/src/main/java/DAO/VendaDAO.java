package DAO;

import classes.VendaListagem;
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

    public static void adicionar(Venda saida) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO VENDA  "
                //Nomes dos campos no banco
                + "(codigoProduto, produto, valor, idUsuario, quantidade)"
                + "VALUES(?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, saida.getCodProduto());
            stmt.setString(2, saida.getProduto());
            stmt.setFloat(2, saida.getValor());
            stmt.setInt(3, saida.getCodigoUsuario());
            stmt.setInt(4, saida.getQuantidade());

            stmt.execute();
        }
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

}
