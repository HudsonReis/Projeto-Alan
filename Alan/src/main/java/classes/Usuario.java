/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Nicolas
 */
public abstract class Usuario {

    private String nome;
    private int codigoUnitario;
    private int codigoFilial;
    private String login;
    private String senha;
    private char tipo;
    private boolean status;

    //Inicia o usuario com status ativo
    public Usuario(String nome, int codUnitario, int codFilial, String login,
            String senha, char tipo) {
        this.nome = nome;
        codigoUnitario = codUnitario;
        codigoFilial = codFilial;
        this.login = login;
        this.senha = senha;
        this.tipo = tipo;
        status = true;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoUnitario() {
        return codigoUnitario;
    }

    public void setCodigoUnitario(int codigoUnitario) {
        this.codigoUnitario = codigoUnitario;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
