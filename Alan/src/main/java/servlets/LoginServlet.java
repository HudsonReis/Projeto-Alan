/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Usuario;
import DAO.UsuarioDAO;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Criptografia;


@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends BaseServlet {

  /**
   * Apresenta a tela de login caso usuário não esteja autenticado. Caso
   * contrário, redireciona para a página de cadastro de usuários.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
      
    HttpSession sessao = request.getSession(false);
    if (sessao == null || sessao.getAttribute("usuario") == null) {
      RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jspx");
      rd.forward(request, response);

      return;
    }
    // Usar o request.getContextPath() para corrigir o caminho da URL.
    response.sendRedirect(request.getContextPath() + "/Home");
  }

  /**
   * Efetua o login do usuário.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String login = request.getParameter("login");
    String senha = request.getParameter("senha");

    // Validar nome de usuário e senha.
    Usuario usuario = validar(login, senha);
    if (usuario != null) {
        HttpSession sessao = request.getSession(false);
        if (sessao != null) {
          // Força invalidação da sessão anterior.
          sessao.invalidate();
        }
        sessao = request.getSession(true);
        sessao.setAttribute("usuarioLogado", usuario);
        response.sendRedirect(request.getContextPath() + "/Home");
        return;
      // FIM CASO SUCESSO
    }
    response.sendRedirect(request.getContextPath() + "/erroLogin.jsp");

  }

  // Implementar aqui a validação do usuário com os dados
  // armazenados no banco de dados.
  private Usuario validar(String login, String senha) {
    try {
        Usuario usuario = UsuarioDAO.consultar(login, String.copyValueOf(Criptografia.gerarHashSenhaPBKDF2(senha)));
        if (usuario != null && usuario.autenticar(login, senha)) {
            return usuario;
        }
    } catch(SQLException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return null;
  }

}
