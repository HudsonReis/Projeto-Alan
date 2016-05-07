/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.Filial;
import classes.FilialDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nicolas
 */
@WebServlet(name = "CadastroFilial", urlPatterns = {"/CadastroFilial"})
public class CadastroFilial extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CadastroFilial</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CadastroFilial at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
            RequestDispatcher rd
                    = request.getRequestDispatcher("/WEB-INF/jsp/cadastroFilial.jspx");
            rd.forward(request, response);
        }
    }

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
        processRequest(request, response);

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

        String nome = request.getParameter("NomeFilial");
        String nomeFantasia = request.getParameter("NomeFantasia");
        String cnpj = request.getParameter("cnpj");
        String rua = request.getParameter("Rua");
        int num = Integer.parseInt(request.getParameter("Numero"));
        String bairro = request.getParameter("Bairro");
        String estado = request.getParameter("Estado");
        String cidade = request.getParameter("Cidade");
        
        //Criando objeto da filial
        Filial filial = new Filial(nome, nomeFantasia, cnpj, rua, num, bairro, estado, cidade);
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
