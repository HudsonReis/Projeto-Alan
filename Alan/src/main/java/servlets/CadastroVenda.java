/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DAO.FilialDAO;
import DAO.ProdutoDAO;
import DAO.VendaDAO;
import classes.entidades.Filial;
import classes.entidades.Item;
import classes.entidades.Produto;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.Resposta;

/**
 *
 * @author Arthur
 */
@WebServlet(name = "CadastroVenda", urlPatterns = {"/Venda"})
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
         
        preencherInformacoes(request);
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
        
        Resposta resposta = validar(request);
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
        
        if (resposta.getSucesso()){
            
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
        } else {
            request.setAttribute("resposta", resposta);
        }
        
        preencherInformacoes(request);
        processRequest(request, response, "/WEB-INF/jsp/venda.jspx");
    }
    
    public void preencherInformacoes(HttpServletRequest request) {
        
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
    }
    
    public Resposta validar(HttpServletRequest request) {
        Resposta resposta = new Resposta();
        
        String codigoFilialStr = request.getParameter("filialId");
        String valorTotalStr = request.getParameter("valorTotalItens");
        String json = request.getParameter("jsonItens");
        
        if(codigoFilialStr.equals("") || codigoFilialStr.equals("0")) {
            resposta.setErro("É necessário informar uma filial.", "codigoFilial");
        }
        
        if(valorTotalStr.equals("")) {
            resposta.setErro("Ocorreram erros e valor Total não foi preenchido.", "valorTotalItens");
        }
        
        if(json.equals("")) {
            resposta.setErro("Ocorreram erros e a lista de Itens não foi preenchida.", "");
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
