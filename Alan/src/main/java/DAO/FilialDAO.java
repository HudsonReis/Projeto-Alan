package DAO;

import classes.entidades.Filial;
import classes.entidades.Usuario;
import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilialDAO {

    public static void adicionar(Filial filial) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "INSERT INTO FILIAL  "
                //Nomes dos campos no banco
                + "(nome , fantasia, rua, numero, bairro, estado, cidade, cnpj)"
                + "VALUES(?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, filial.getNome());
            stmt.setString(2, filial.getNomeFantasia());
            stmt.setString(3, filial.getRua());
            stmt.setInt(4, filial.getNumero());
            stmt.setString(5, filial.getBairro());
            stmt.setString(6, filial.getEstado());
            stmt.setString(7, filial.getCidade());
            stmt.setString(8, filial.getCnpj());
            stmt.execute();
        }
    }

    public static void alterar(Filial filial) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        //linguagem sql -> inserir no banco
        String sql = "UPDATE FILIAL SET NOME = ?, FANTASIA = ?, RUA = ?, NUMERO = ?, "
                + "BAIRRO = ?, ESTADO = ?, CIDADE = ?, CNPJ = ? WHERE CODIGOFILIAL = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, filial.getNome());
            stmt.setString(2, filial.getNomeFantasia());
            stmt.setString(3, filial.getRua());
            stmt.setInt(4, filial.getNumero());
            stmt.setString(5, filial.getBairro());
            stmt.setString(6, filial.getEstado());
            stmt.setString(7, filial.getCidade());
            stmt.setString(8, filial.getCnpj());
            stmt.setInt(9, filial.getCodigoFilial());

            stmt.execute();
        }
    }

    public static ArrayList<Filial> listar() throws SQLException, ClassNotFoundException {

        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT * FROM FILIAL";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        ResultSet result = stmt.executeQuery();
        ArrayList<Filial> retorno = new ArrayList<>();

        while (result.next()) {
            //pego o retorno do banco e atribuo à variaveis
            int codigoFilial = result.getInt("codigofilial");
            String nome = result.getString("nome");
            String nomeFantasia = result.getString("fantasia");
            String rua = result.getString("rua");
            int num = result.getInt("numero");
            String bairro = result.getString("bairro");
            String estado = result.getString("estado");
            String cidade = result.getString("cidade");
            String cnpj = result.getString("cnpj");

            //crio um objeto filial
            Filial f = new Filial(codigoFilial, nome, nomeFantasia, rua, num, bairro, estado, cidade, cnpj);

            //e adiciono no arraylist para retorno
            retorno.add(f);

        }

        return retorno;
    }

    public static Filial consultarPorId(int id) throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT codigoFilial, nome, fantasia, rua, numero, bairro, estado, cidade, cnpj"
                + " FROM FILIAL WHERE codigoFilial = ?";

        Filial filial;
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            int codFilial = result.getInt("codigoFilial");
            String nome = result.getString("nome");
            String fantasia = result.getString("fantasia");
            String rua = result.getString("rua");
            int numero = result.getInt("numero");
            String bairro = result.getString("bairro");
            String estado = result.getString("estado");
            String cidade = result.getString("cidade");
            String cnpj = result.getString("cnpj");
            filial = new Filial(codFilial, nome, fantasia, rua, numero, bairro, estado, cidade, cnpj);
        }

        return filial;
    }

    public static int maxId() throws SQLException, ClassNotFoundException {
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT MAX(CODIGOFILIAL)FROM FILIAL";
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

    public static boolean cnpjJaCadastrado(String cnpj, int idTela) throws SQLException, ClassNotFoundException {
        boolean jaCadastrado = false;
        Connection conexao = ConexaoBanco.obterConexao();
        String sql = "SELECT * FROM FILIAL";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            //pego o retorno do banco e atribuo à variaveis
            String resultCnpj = result.getString("cnpj");

            int idBanco = result.getInt("codigofilial");

            if ((cnpj.equals(resultCnpj)) && (idTela != idBanco)) {
                jaCadastrado = true;
            }

        }

        return jaCadastrado;
    }

    public static ArrayList<Filial> listarRelatorio(Usuario usuario) throws SQLException, ClassNotFoundException {

        Connection conexao = ConexaoBanco.obterConexao();

        String sql = "SELECT * FROM FILIAL";
        PreparedStatement stmt = conexao.prepareStatement(sql);

        ResultSet result = stmt.executeQuery();
        ArrayList<Filial> retorno = new ArrayList<>();

        if ((usuario.getCodigoPerfil() == 1) || (usuario.getCodigoPerfil() == 2)) {
            Filial todos = new Filial(0, "Todos", "Todos", "Todos", 0, "Todos", "Todos", "Todos", "Todos");
            retorno.add(todos);
            while (result.next()) {
                int codigoFilial = result.getInt("codigofilial");
                String nome = result.getString("nome");
                String nomeFantasia = result.getString("fantasia");
                String rua = result.getString("rua");
                int num = result.getInt("numero");
                String bairro = result.getString("bairro");
                String estado = result.getString("estado");
                String cidade = result.getString("cidade");
                String cnpj = result.getString("cnpj");
                //crio um objeto filial
                Filial f = new Filial(codigoFilial, nome, nomeFantasia, rua, num, bairro, estado, cidade, cnpj);
                //e adiciono no arraylist para retorno
                retorno.add(f);
            }
        } else {

            while (result.next()) {
                if (result.getInt("codigofilial") == usuario.getCodigoFilial()) {
                    int codigoFilial = result.getInt("codigofilial");
                    String nome = result.getString("nome");
                    String nomeFantasia = result.getString("fantasia");
                    String rua = result.getString("rua");
                    int num = result.getInt("numero");
                    String bairro = result.getString("bairro");
                    String estado = result.getString("estado");
                    String cidade = result.getString("cidade");
                    String cnpj = result.getString("cnpj");

                    //crio um objeto filial
                    Filial f = new Filial(codigoFilial, nome, nomeFantasia, rua, num, bairro, estado, cidade, cnpj);

                    //e adiciono no arraylist para retorno
                    retorno.add(f);
                }
            }
        }

        return retorno;
    }

}
