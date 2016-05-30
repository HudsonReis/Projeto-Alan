/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.FilialDAO;
import DAO.ProdutoDAO;
import classes.entidades.Filial;
import classes.entidades.Produto;
import classes.entidades.Usuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Resposta;

/**
 *
 * @author Nicolas
 */
@WebServlet(name = "CadastroProduto", urlPatterns = {"/CadastroProduto"})
public class CadastroProduto extends BaseServlet {

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

        //pego as filiais de usuario do banco de dados para preenchimento de campos no html
        ArrayList<Filial> filiais = new ArrayList<Filial>();
        
        Produto produto = new Produto();
        Integer id = identificarEdicao(request);
        boolean edicao = false;
        
        try {
            filiais = FilialDAO.listar();
            
            if (id != null) {
                produto = ProdutoDAO.consultarPorId(id);
                edicao = true;
            } else {
                id = ProdutoDAO.maxId();
                produto.setCodigoPeca(id);
            }
            
        } catch (SQLException ex) {
            logar(CadastroProduto.class.getName(), ex);
        } catch (ClassNotFoundException ex) {
            logar(CadastroProduto.class.getName(), ex);
        }
        request.setAttribute("filiais", filiais);
        request.setAttribute("produto", produto);
        request.setAttribute("edicao", edicao);
        
        processRequest(request, response, "/WEB-INF/jsp/cadastroProduto.jspx");

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
        
        Resposta resposta = new Resposta();
        
        HttpSession sessao = request.getSession(false);
        Usuario usuario = (Usuario)sessao.getAttribute("usuarioLogado");
        
        int codigoPeca = Integer.parseInt(request.getParameter("prodId"));
        int codFilial = Integer.parseInt(request.getParameter("filialId"));        
        int codUsuario = usuario.getCodigoUsuario();
        int qtdPeca = 0; 
        String nome = request.getParameter("nomeProd");        
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        boolean edicao = Boolean.parseBoolean(request.getParameter("edicao"));
        
        Produto produto = new Produto(codigoPeca, codFilial, codUsuario, nome, qtdPeca, status);
        
        try {
            
            if(resposta.getSucesso()) {
                if(edicao) {
                    ProdutoDAO.alterar(produto);
                } else {
                    ProdutoDAO.adicionar(produto);
                }
            }
            
            request.getSession().setAttribute("resposta", resposta);
            
        } catch (SQLException ex) {
            logar(CadastroProduto.class.getName(), ex);
        } catch (ClassNotFoundException ex) {
            logar(CadastroProduto.class.getName(), ex);
        }
        
        if(resposta.getSucesso()) {
            response.sendRedirect(request.getContextPath() + "/BuscaProdutos");
        } else {
            response.sendRedirect(request.getContextPath() + "/CadastroProduto");
        }
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
