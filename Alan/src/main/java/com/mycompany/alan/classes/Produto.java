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
public class Produto {

    private String nome;
    private int codigoPeca;
    private int qtdPeca;
    private double valor;
    private boolean status;
    private int codigoFilial;

    public Produto(String nome, int codigoPeca, int qtdPeca, double valor,
            boolean status, int codigoFilial) {
        this.nome = nome;
        this.codigoPeca = codigoPeca;
        this.qtdPeca = qtdPeca;
        this.valor = valor;
        this.status = status;
        this.codigoFilial = codigoFilial;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoPeca() {
        return codigoPeca;
    }

    public void setCodigoPeca(int codigoPeca) {
        this.codigoPeca = codigoPeca;
    }

    public int getQtdPeca() {
        return qtdPeca;
    }

    public void setQtdPeca(int qtdPeca) {
        this.qtdPeca = qtdPeca;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

}
