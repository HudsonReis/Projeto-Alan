/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nicolas
 */
public class ConexaoBanco {

    public static Connection obterConexao() {
        Connection conn = null;

        try {
            Class.forName("org.apache.derby.jdbc.ClientDataSource");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/AlanBanco",
                    "turing",
                    "turing");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ConexaoBanco.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

}
