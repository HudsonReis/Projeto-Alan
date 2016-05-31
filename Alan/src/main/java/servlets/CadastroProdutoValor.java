/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.ProdutoDAO;
import DAO.ProdutoValorDAO;
import classes.entidades.Produto;
import classes.entidades.ProdutoValor;
import classes.entidades.Usuario;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Resposta;

/**
 *
 * @author Arthur
 */
@WebServlet(name = "CadastroProdutoValor", urlPatterns = {"/CadastroProdutoValor"})
public class CadastroProdutoValor extends BaseServlet {

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
        
        
        List<ProdutoValor> valores = new ArrayList<>();
        Produto produto = new Produto();
        Integer id = identificarEdicao(request);
        
        try {
            
            if (id != null) {
                produto = ProdutoDAO.consultarPorId(id);
                valores = ProdutoValorDAO.listar();
            } 
            
        } catch (SQLException | ClassNotFoundException ex) {
            logar(CadastroProdutoValor.class.getName(), ex);
        }
        
        request.setAttribute("nomeProduto", produto.getNome());
        request.setAttribute("codigoProduto", produto.getCodigoProduto());
        request.setAttribute("ProdutoValores", valores);
        
        processRequest(request, response, "/WEB-INF/jsp/cadastroProdutoValor.jspx");
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
        
        int codigoProduto = Integer.parseInt(request.getParameter("codigoProduto"));
        Date inicioVigencia = Date.valueOf(request.getParameter("inicioVigencia"));        
        Date terminoVigencia = Date.valueOf(request.getParameter("terminoVigencia")); 
        double valor = Double.parseDouble(request.getParameter("valor")); 
        
        ProdutoValor produtoValor = new ProdutoValor(codigoProduto, inicioVigencia, terminoVigencia, valor);
        
        try {
            
            if(resposta.getSucesso()) {
                ProdutoValorDAO.adicionar(produtoValor);
            }
            
            request.getSession().setAttribute("resposta", resposta);
            
        } catch (SQLException | ClassNotFoundException ex) {
            logar(CadastroProduto.class.getName(), ex);
        }
        
        if(resposta.getSucesso()) {
            response.sendRedirect(request.getContextPath() + "/BuscaProdutos");
        } else {
            response.sendRedirect(request.getContextPath() + "/CadastroProdutoValor");
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
