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
        
        Resposta<Venda> resposta = validar(request);
        
        if (resposta.getSucesso()){

            try {
                VendaDAO.adicionar(resposta.getResultado());
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
        request.setAttribute("filial", filial);
    }
    
    public Resposta validar(HttpServletRequest request) {
        Resposta<Venda> resposta = new Resposta<Venda>();
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario)session.getAttribute("usuarioLogado");
        
        String codigoFilialStr = request.getParameter("filialId");
        String valorTotalStr = request.getParameter("valorTotalItens");
        String json = request.getParameter("jsonItens");
        ArrayList<Item> itens = new ArrayList<>();
        
        if(codigoFilialStr.equals("") || codigoFilialStr.equals("0")) {
            resposta.setErro("É necessário informar uma filial.", "codigoFilial");
        } else if(valorTotalStr.equals("")) {
            resposta.setErro("Ocorreram erros e valor Total não foi preenchido.", "valorTotalItens");
        } else if(json.equals("")) {
            resposta.setErro("Ocorreram erros e a lista de Itens não foi preenchida.", "");
        } else {
            itens = new Gson().fromJson(json, new TypeToken<List<Item>>(){}.getType());
            
            try {
                
                for (int x = 0; x < itens.size(); x++) {
                    Item item = itens.get(x);
                    if (ProdutoDAO.consultarEstoque(item.getCodigoProduto()) < item.getQuantidade()){
                        resposta.setErro("Não há quantidade suficiente de " + 
                                item.getNomeProduto() + " no estoque.", "item" + x);
                        break;
                    }
                }
                
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(CadastroVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (resposta.getSucesso()) {
            int codigoFilial = Integer.parseInt(codigoFilialStr);
            int idUsuario = usuario.getCodigoUsuario();
            double valorTotal = Double.parseDouble(valorTotalStr);
            
            resposta.setResultado(new Venda(codigoFilial, idUsuario, valorTotal, itens));
        } else {
            json = json.replace("\"", "'");
            request.setAttribute("jsonItens", json);
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
