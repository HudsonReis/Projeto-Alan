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
public class MovimentacaoEntrada {

    private int codigoProduto;
    private int codigoUsuario;
    private int quantidade;

    public MovimentacaoEntrada(int codProduto, int codUsuario, int qtd) {
        codigoProduto = codProduto;
        codigoUsuario = codUsuario;
        quantidade = qtd;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
