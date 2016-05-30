/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Arthur
 */
public class UsuarioListagem {
    private int codigoUsuario;
    private String filial;
    private String perfil;
    private String nome;
    private String login;  
    private boolean status;
    
    public UsuarioListagem(int codigoUsuario, String filial, String perfil, String nome, String login,
            Boolean status) {
        
        this.codigoUsuario = codigoUsuario;
        this.filial = filial;
        this.perfil = perfil;
        this.nome = nome;
        this.login = login;
        this.status = status;
    }
    
    public int getCodigoUsuario() {
        return this.codigoUsuario;
    }
    
    public String getFilial() {
        return this.filial;
    }
    
    public String getPerfil() {
        return this.perfil;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public String getStatus() {
        if (status) {
            return "Sim";
        } else {
            return "Não";
        }
    }
}
