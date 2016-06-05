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
public class Compra {

    private int codigoProduto;
    private String produto;
    private float preco;
    private int codigoUsuario;
    private int quantidade;

    public Compra(int codProduto, String produto, float preco, int qtd) {
        this.codigoProduto = codProduto;
        this.produto = produto;
        this.preco = preco;
        //this.codigoUsuario = codUsuario;
        this.quantidade = qtd;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    
     public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
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
