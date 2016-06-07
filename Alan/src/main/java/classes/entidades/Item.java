/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.entidades;

/**
 *
 * @author Arthur
 */
public class Item {
    private int idItem;
    private int idMovimentacao;
    private int codigoProduto;
    private int quantidade;
    private double valorUnitario;
    
    public Item() {
        
    }
    
    public Item(int idItem, int idMovimentacao, int codigoProduto, int quantidade, double valorUnitario) {
        this.idItem = idItem;
        this.idMovimentacao = idMovimentacao;
        this.codigoProduto = codigoProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }
    
    public int getIdItem() {
        return this.idItem;
    }
    
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    
    public int getIdMovimentacao() {
        return this.idMovimentacao;
    }
    
    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }
    
    public int getCodigoProduto() {
        return this.codigoProduto;
    }
    
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public double getValorUnitario() {
        return this.valorUnitario;
    }
    
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
