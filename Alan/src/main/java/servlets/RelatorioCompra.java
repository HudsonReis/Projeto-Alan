/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.CompraDAO;
import DAO.FilialDAO;
import DAO.ProdutoDAO;
import classes.CompraListagem;
import classes.ProdutoListagem;
import classes.entidades.Filial;
import classes.entidades.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Arthur
 */
@WebServlet(name = "RelatorioCompra", urlPatterns = {"/RelatorioCompra"})
public class RelatorioCompra extends BaseServlet {

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

        List<CompraListagem> lista = new ArrayList<>();
        List<ProdutoListagem> produtos = new ArrayList<>();
        try {
            lista = CompraDAO.listar();
            produtos = ProdutoDAO.listar();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioCompra.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatorioCompra.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }

        
        request.setAttribute("Compras", lista);
        request.setAttribute("produtos", produtos);

        processRequest(request, response, "/WEB-INF/jsp/relatorioCompras.jspx");
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
        
        
        
        String dataInicial = request.getParameter("dataInicial");
        String dataFinal = request.getParameter("dataFinal");
        int codProduto = Integer.parseInt(request.getParameter("produtoId"));
        
        List<CompraListagem> lista = new ArrayList<>();
        List<ProdutoListagem> produtos = new ArrayList<>();
        
        try {
            if(codProduto==0){
                lista = CompraDAO.listar(dataInicial, dataFinal);
            }
            else{
                lista = CompraDAO.listar(dataInicial, dataFinal, codProduto);
            }
            produtos = ProdutoDAO.listar();
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioCompra.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RelatorioCompra.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
        }
        
        request.setAttribute("Compra", lista);
        request.setAttribute("produtos", produtos);

        processRequest(request, response, "/WEB-INF/jsp/relatorioCompras.jspx");
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
