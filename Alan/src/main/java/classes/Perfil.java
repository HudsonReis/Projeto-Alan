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
public class Perfil {

    private int codigoPerfil;
    private String nome;

    public Perfil(int codigoPerfil, String nome) {
        this.codigoPerfil = codigoPerfil;
        this.nome = nome;
    }

    public int getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(int codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
