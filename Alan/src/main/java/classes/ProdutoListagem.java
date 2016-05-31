/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import util.Formatacoes;

/**
 *
 * @author Arthur
 */
public class ProdutoListagem {
    private int codigoProduto;
    private String filial;
    private String usuario;
    private String produto;
    private int qtdPeca;
    private double valor;
    private boolean status;
    
    public ProdutoListagem(int codigoProduto, String filial, String usuario, String produto, 
            double valor, int qtdPeca, boolean status)
    {
        this.codigoProduto = codigoProduto;
        this.filial = filial;
        this.usuario = usuario;
        this.produto = produto;
        this.qtdPeca = qtdPeca;
        this.valor = valor;
        this.status = status;
    }
    
    public int getCodigoProduto() {
        return this.codigoProduto;
    }
    
    public String getFilial() {
        return this.filial;
    }
    
    public String getUsuario() {
        return this.usuario;
    }
    
    public String getProduto() {
        return this.produto;
    }
    
    public int getQtdPeca() {
        return this.qtdPeca;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public String getValorFormatado() {
        return Formatacoes.formatarMoeda(this.valor);
    }
    
    public String getStatus() {
        if (status) {
            return "Sim";
        } else {
            return "Não";
        }
    }
}
