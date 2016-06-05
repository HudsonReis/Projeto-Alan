/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.entidades;

/**
 *
 * @author Nicolas
 */
public class Venda {

    private int quantidade;
    private String produto;
    private int codigoUsuario;
    private float valor;
    private int codProduto;

    public Venda(int qtd, float valor, String produto, int codUsuario, int codProduto) {
        this.quantidade = qtd;
        this.valor = valor;
        this.codigoUsuario = codUsuario;
        this.produto = produto;
        this.codProduto = codProduto;

    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public int getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(int codProduto) {
        this.codProduto = codProduto;
    }
}
