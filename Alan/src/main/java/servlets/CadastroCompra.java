/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.CompraDAO;
import classes.entidades.Compra;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hudson.rsilva1
 */
@WebServlet(name = "Compra", urlPatterns = {"/Compra"})
public class CadastroCompra extends BaseServlet {

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
        processRequest(request, response, "/WEB-INF/jsp/compra.jspx");
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
        
        
        
        String produto = request.getParameter("Produto");
        int codProduto = Integer.parseInt(request.getParameter("CodigoProduto"));
        float preco = Float.parseFloat(request.getParameter("Preco"));
        int qtd = Integer.parseInt(request.getParameter("Quantidade"));
        
        Compra compra = new Compra(codProduto, produto, preco, qtd);
        
        try {
        CompraDAO.adicionar(compra);
        } catch (SQLException | ClassNotFoundException ex) {
            logar(CadastroCompra.class.getName(), ex);
        }
        
        processRequest(request, response, "/WEB-INF/jsp/compra.jspx");
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
