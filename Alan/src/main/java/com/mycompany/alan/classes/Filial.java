/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.alan.classes;

/**
 *
 * @author Nicolas
 */
public class Filial {
    private String nome;
    private String cnpj;
    private int codigoFilial;
    
    public Filial(String nome, String cnpj, int codigoFilial){
        this.nome = nome;
        this.cnpj = cnpj;
        this.codigoFilial = codigoFilial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }
    
}
