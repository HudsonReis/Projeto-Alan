/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.FilialDAO;
import DAO.PerfilDAO;
import DAO.UsuarioDAO;
import classes.Filial;
import classes.Perfil;
import classes.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Resposta;

/**
 *
 * @author Nicolas
 */
@WebServlet(name = "CadastroUsuario", urlPatterns = {"/CadastroUsuario"})
public class CadastroUsuario extends BaseServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //pego os perfis de usuario do banco de dados para preenchimento de campos no html
        ArrayList<Perfil> perfis = new ArrayList<>();
        ArrayList<Filial> filiais = new ArrayList<Filial>();
        
        Usuario usuario = new Usuario();
        Integer id = identificarEdicao(request);
        boolean edicao = false;
        
        try {
            perfis  = PerfilDAO.consultar();
            filiais = FilialDAO.listar();
            
            if (id != null) {
                usuario = UsuarioDAO.consultarPorId(id);
                edicao = true;
            } else {
                id = UsuarioDAO.maxId();
                usuario.setCodigoUnitario(id);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("perfis", perfis);
        request.setAttribute("filiais", filiais);
        request.setAttribute("usuario", usuario);
        request.setAttribute("edicao", edicao);
        
        processRequest(request, response, "/WEB-INF/jsp/cadastroUsuario.jspx");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean edicao = Boolean.parseBoolean(request.getParameter("edicao"));
        boolean status = Boolean.parseBoolean(request.getParameter("Status"));
        String nome = request.getParameter("Nome");
        int id = Integer.parseInt(request.getParameter("usuarioId"));
        int codFilial = Integer.parseInt(request.getParameter("filialId"));        
        int codPerfil = Integer.parseInt(request.getParameter("perfilId"));        
        String login = request.getParameter("Login");
        String senha = request.getParameter("Senha");
        
        Usuario usuario = new Usuario(id, nome, codFilial, codPerfil, login, senha, status);
        
        try {
            if(edicao) {
                UsuarioDAO.alterar(usuario);
            } else {
                UsuarioDAO.adicionar(usuario);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(request.getContextPath() + "/Home");
    }
    
    public Resposta validar(Usuario usuario) {
        Resposta resposta = new Resposta();
        
        try {
            if(UsuarioDAO.consultarLoginExistente(usuario.getLogin())) {
                resposta.setErro("Esse login já existe, por favor, informe outro");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resposta;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
