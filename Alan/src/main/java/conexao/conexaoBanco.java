/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nicolas
 */
public class conexaoBanco {

    public static Connection obterConexao() throws SQLException, ClassNotFoundException {
        Connection conn = null;

        Class.forName("org.apache.derby.jdbc.ClientDataSource");

        conn = DriverManager.getConnection("jdbc:derby://localhost:1527/AlanBanco",
                "turing",
                "turing");

        return conn;
    }
    public static void main(String [] args) throws SQLException, ClassNotFoundException{
        Connection conn = null;
        conn = obterConexao();
        System.out.println("deu!");
    }
    
}
