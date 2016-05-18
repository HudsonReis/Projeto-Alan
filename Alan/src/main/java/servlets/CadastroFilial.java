/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Filial;
import DAO.FilialDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
@WebServlet(name = "CadastroFilial", urlPatterns = {"/CadastroFilial"})
public class CadastroFilial extends BaseServlet {

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
        
        int id = 0;
        try {
            id = FilialDAO.maxId();
        } catch (SQLException ex) {
            Logger.getLogger(CadastroFilial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("id", id);
        processRequest(request, response, "/WEB-INF/jsp/cadastroFilial.jspx");

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

        int id = Integer.parseInt(request.getParameter("filialId")) ;
        String nome = request.getParameter("NomeFilial");
        String nomeFantasia = request.getParameter("NomeFantasia");
        String cnpj = request.getParameter("cnpj");
        String rua = request.getParameter("Rua");
        int num = Integer.parseInt(request.getParameter("Numero"));
        String bairro = request.getParameter("Bairro");
        String estado = request.getParameter("Estado");
        String cidade = request.getParameter("Cidade");
        
        //Criando objeto da filial
        Filial filial = new Filial(id, nome, nomeFantasia, rua, num, bairro, estado, cidade, cnpj);
        try {
            //tentando enviar filial para ser adicionadas
            FilialDAO.adicionar(filial);            
        } catch (SQLException ex) {
            Logger.getLogger(CadastroFilial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastroFilial.class.getName()).log(Level.SEVERE, null, ex);
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
