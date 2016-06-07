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

        String sql = "SELECT"
                + " v.IDVENDA, "
                + " v.DATAVENDA, "
                + " v.CODIGOPRODUTO, "
                + " p.NOME AS NOME_PRODUTO, "
                + " v.IDUSUARIO, "
                + " u.NOME NOME_USUARIO, "
                + " v.QUANTIDADE, "
                + " v.VALOR "
                + " from venda v "
                + " inner join produto p on p.CODIGOPRODUTO = v.CODIGOPRODUTO "
                + " and p.CODIGOFILIAL = v.CODIGOFILIAL "
                + " inner join usuario u on u.CODIGOUSUARIO = v.IDUSUARIO "
                + " and u.CODIGOFILIAL = v.CODIGOFILIAL ";

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
            double valor = result.getDouble("VALOR");

            VendaListagem venda = new VendaListagem(idVenda, dataVenda, codigoProduto, nomeProduto,
                    idUsuario, nomeUsuario, quantidade, valor);
            retorno.add(venda);

        }
        return retorno;
    }

}
