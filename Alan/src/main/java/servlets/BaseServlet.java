/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String pagina)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher rd
                    = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        }
    }
    
    protected void logar(String nameOfClass, Exception ex) {
        Logger.getLogger(nameOfClass).log(Level.SEVERE, null, ex);
    }
    
    protected Integer identificarEdicao(HttpServletRequest request) {
        Dictionary<String, String> dic = new Hashtable<>();
        
        if(request.getQueryString() != null) {
            String[] qs = request.getQueryString().split("&");
            
            for(String keyPair: qs) {
                String[] item = keyPair.split("=");
                dic.put(item[0], item[1]);
            }
        }
        
        if(dic != null && dic.get("id") != null) {
            request.setAttribute("edicao", true);
            return Integer.parseInt(dic.get("id"));
        } else {
            request.setAttribute("edicao", false);
            return null;
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
