/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.FilialDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import classes.entidades.Produto;
import classes.entidades.Filial;
import classes.entidades.Item;
import classes.entidades.Usuario;
import classes.entidades.Venda;
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
 * @author Nicolas
 */
@WebServlet(name = "Venda", urlPatterns = {"/Venda"})
public class CadastroVenda extends BaseServlet {

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
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
        
        List<Produto> lista = new ArrayList<>();
        Filial filial = new Filial();
        
        try {
            
            lista = ProdutoDAO.listarPorFilial(usuario.getCodigoFilial());
            filial = FilialDAO.consultarPorId(usuario.getCodigoFilial());
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("Produtos", lista);
        request.setAttribute("Filial", filial);
        
        processRequest(request, response, "/WEB-INF/jsp/venda.jspx");
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
        
        Venda venda = new Venda(codigoFilial, idUsuario, valorTotal, itens);
        
        try {
            VendaDAO.adicionar(venda);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CadastroCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        processRequest(request, response, "/WEB-INF/jsp/venda.jspx");
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
