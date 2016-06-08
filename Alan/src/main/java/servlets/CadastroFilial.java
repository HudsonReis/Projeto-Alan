/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import classes.entidades.Filial;
import DAO.FilialDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Funcoes;
import util.Resposta;

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

        Filial filial = new Filial();
        Integer id = identificarEdicao(request);
        boolean edicao = false;

        try {

            if (id != null) {
                filial = FilialDAO.consultarPorId(id);
                edicao = true;
            } else {
                id = FilialDAO.maxId();
                filial.setCodigoFilial(id);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            logar(CadastroFilial.class.getName(), ex);
        }

        request.setAttribute("filial", filial);
        request.setAttribute("edicao", edicao);

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

        Resposta resposta = new Resposta();

        boolean edicao = Boolean.parseBoolean(request.getParameter("edicao"));
        int id = Integer.parseInt(request.getParameter("filialId"));
        String nome = request.getParameter("NomeFilial");
        String nomeFantasia = request.getParameter("NomeFantasia");
        String cnpj = request.getParameter("cnpj");
        String rua = request.getParameter("Rua");
        int num = Integer.parseInt(request.getParameter("Numero"));
        String bairro = request.getParameter("Bairro");
        String estado = request.getParameter("Estado");
        String cidade = request.getParameter("Cidade");

        Filial filial = new Filial(id, nome, nomeFantasia, rua, num, bairro, estado, cidade, cnpj);
        try {

            resposta = validar(filial, id);

            if (resposta.getSucesso()) {
                if (edicao) {
                    FilialDAO.alterar(filial);
                } else {
                    FilialDAO.adicionar(filial);

                }

            }

            request.getSession().setAttribute("resposta", resposta);

        } catch (SQLException | ClassNotFoundException ex) {
            logar(CadastroFilial.class.getName(), ex);
        }

        if (resposta.getSucesso()) {
            response.sendRedirect(request.getContextPath() + "/BuscaFiliais");
        } else {
            processRequest(request, response, "/WEB-INF/jsp/cadastroFilial.jspx");
        }
    }

    public Resposta validar(Filial filial, int id) throws SQLException, ClassNotFoundException {
        Resposta resposta = new Resposta();

        if (!Funcoes.isCNPJ(filial.getCnpj())) {
            resposta.setErro("Este CNPJ é inválido.", "cnpj");
        }
        if (FilialDAO.cnpjJaCadastrado(filial.getCnpj(), id)) {
            resposta.setErro("Esse CNPJ já está cadastrado", "cnpj");
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
