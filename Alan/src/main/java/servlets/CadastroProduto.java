/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.FilialDAO;
import DAO.PerfilDAO;
import DAO.ProdutoDAO;
import classes.Filial;
import classes.Perfil;
import classes.Produto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            filiais = FilialDAO.listar();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("filiais", filiais);
        
        int id=0;
        try {
            id  = ProdutoDAO.maxId();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("id", id);
        
        RequestDispatcher rd
            = request.getRequestDispatcher("/WEB-INF/jsp/cadastroProduto.jspx");
        
        
        
        rd.forward(request, response);

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
        
        
        
        String nome = request.getParameter("nomeProd");
        double valor = Double.parseDouble(request.getParameter("valorProd"));
        ArrayList lista = (ArrayList) request.getAttribute("lista");
        System.out.println(lista.get(0));
        System.out.println(valor);
        
        
        //Produto p = new Produto();
        
        processRequest(request, response, "/WEB-INF/jsp/cadastroProduto.jspx");
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
