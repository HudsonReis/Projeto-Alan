/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.CompraDAO;
import DAO.FilialDAO;
import DAO.ProdutoDAO;
import classes.entidades.Compra;
import classes.entidades.Filial;
import classes.entidades.Usuario;
import classes.entidades.Item;
import classes.ProdutoListagem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import javax.servlet.http.HttpSession;

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
        
        List<ProdutoListagem> produtos = new ArrayList<>();
        List<Filial> filiais = new ArrayList<>();
        
        try {
            produtos = ProdutoDAO.listar();
            filiais = FilialDAO.listar();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("Filiais", filiais);
        request.setAttribute("Produtos", produtos);
        
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

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
        
        int codigoFilial = Integer.parseInt(request.getParameter("filialId"));
        int idUsuario = usuario.getCodigoUsuario();
        double valorTotal = Double.parseDouble(request.getParameter("valorTotalItens"));
        String json = request.getParameter("jsonItens");
        
        ArrayList<Item> itens = new Gson().fromJson(json, new TypeToken<List<Item>>(){}.getType());
        
        Compra compra = new Compra(codigoFilial, idUsuario, valorTotal, itens);
  
        try {
            CompraDAO.adicionar(compra);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroCompra.class.getName()).log(Level.SEVERE, null, ex);
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
