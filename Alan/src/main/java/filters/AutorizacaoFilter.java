/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import classes.entidades.Usuario;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.FuncionalidadesEnum;

@WebFilter(filterName = "AutorizacaoFilter", 
        servletNames = {"CadastroFilial", "CadastroProduto", "CadastroUsuario", "BuscaUsuarios", "Home"},
        urlPatterns = {"/protegido/*"})
public class AutorizacaoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession sessao = httpRequest.getSession();
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioLogado");

        if (usuario == null) {
            String contextPath = httpRequest.getContextPath();
            httpResponse.sendRedirect(contextPath + "/Login");
            return;
        }

        try {
            if (verificarAcesso(usuario, httpRequest, httpResponse)) {
              chain.doFilter(request, response);
            } else {
              httpResponse.sendRedirect(httpRequest.getContextPath() + "/erroNaoAutorizado.jsp");
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /**
     * Método que verifica se o usuário possui o(s) papel(is) necessário(s) para
     * acessar a funcionalidade
     *
     * @param usuario
     * @param req
     * @param resp
     * @return
     */
    private static boolean verificarAcesso(Usuario usuario, HttpServletRequest req, HttpServletResponse resp) {
        String paginaCompleta = req.getRequestURI();
        String pagina = paginaCompleta.replace(req.getContextPath(), "");
        
        if (pagina.endsWith("CadastroFilial") 
                && usuario.autorizado(FuncionalidadesEnum.CadastroFiliais.value)) {
          return true;
        } else if (pagina.endsWith("CadastroProduto") 
                && usuario.autorizado(FuncionalidadesEnum.CadastroProdutos.value)) {
          return true;
        } else if (pagina.endsWith("CadastroUsuario") 
                && usuario.autorizado(FuncionalidadesEnum.CadastroUsuarios.value)) {
          return true;
        } else if (pagina.endsWith("BuscaUsuarios")
                && usuario.autorizado(FuncionalidadesEnum.BuscaUsuarios.value)) {
            return true;
        } else if (pagina.endsWith("Home")) {
            return true;
        } 
        
        return false;
    }

    /**
     * ROTINA PARA DESTRUIÇÃO DO FILTRO
     */
    @Override
    public void destroy() {
    }

    /**
     * ROTINA DE INICIALIZAÇÃO DO FILTRO
     */
    @Override
    public void init(FilterConfig filterConfig) {

    }
}