/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.entidades;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Criptografia;

/**
 *
 * @author Nicolas
 */
public class Usuario {

    private int codigoUsuario;
    private int codigoFilial;
    private int codigoPerfil;
    private String nome;
    private String login;
    private char[] hashSenha;  
    private boolean status;
    private Date dataUltimoLogin;
    private String saltHash;
    private List<Integer> funcionalidades;

    public Usuario()
    {
        
    }
    
    public Usuario(int codigoUsuario, String nome, int codigoFilial, int codigoPerfil, String login, 
            String senha, boolean status, String saltHash) {
        this.codigoUsuario = codigoUsuario;
        this.nome = nome;
        this.codigoFilial = codigoFilial;
        this.codigoPerfil = codigoPerfil;
        this.login = login;
        this.status = status;
        this.saltHash = saltHash; 
        
        try {
            if (senha != null) {
                this.hashSenha = Criptografia.gerarHashSenhaPBKDF2(senha, this.saltHash);
            }
            
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario(String nome, int codigoUsuario, int codFilial, int codPerfil, String login,
            String senha, Boolean status, List<Integer> funcionalidades, String perfil, 
            Date dataUltimoLogin, String saltHash) {
        
        this.nome = nome;
        this.codigoUsuario = codigoUsuario;
        this.codigoFilial = codFilial;
        this.login = login;
        this.codigoPerfil = codPerfil;
        this.status = status;
        this.funcionalidades = funcionalidades;
        this.hashSenha = senha.toCharArray();
        this.dataUltimoLogin = dataUltimoLogin;
        this.saltHash = saltHash;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getHashSenha() {
        return hashSenha;
    }

    public int getCodigoPerfil() {
        return this.codigoPerfil;
    }

    public void setCodigoPerfil(char tipo) {
        this.codigoPerfil = tipo;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public Date getDataUltimoLogin() {
        return this.dataUltimoLogin;
    }
    
    public String getSaltHash() {
        return this.saltHash;
    }
    
    public void setSaltHash(String saltHash) {
        this.saltHash = saltHash;
    }

    public boolean autenticar(String login, String senha) {
        if (this.nome != null) {
            try {
                return this.login.equals(login) && Arrays.equals(this.hashSenha, Criptografia.gerarHashSenhaPBKDF2(senha, this.saltHash));
            } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean autorizado(int perfilIdNecessario) {
        return this.funcionalidades.contains(perfilIdNecessario);
    }
}
