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
public class Produto {

    private int codigoProduto;
    private int codigoFilial;
    private int codUsuario;
    private String nome;
    private int qtdPeca;
    private boolean status;
    private ProdutoValor produtoValor;
    
    public Produto()
    {
        
    }

    public Produto(int codigoProduto, int codigoFilial, int codUsuario, String nome, int qtdPeca, boolean status) {
        this.codigoProduto = codigoProduto;
        this.codigoFilial = codigoFilial;
        this.codUsuario = codUsuario;
        this.nome = nome;
        this.qtdPeca = qtdPeca;
        this.status = status;
    }
    
    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public int getCodigoFilial() {
        return codigoFilial;
    }

    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdPeca() {
        return qtdPeca;
    }

    public void setQtdPeca(int qtdPeca) {
        this.qtdPeca = qtdPeca;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }        
    
    public ProdutoValor getProdutoValor() {
        return this.produtoValor;
    }
    
    public void setProdutoValor(ProdutoValor produtoValor) {
        this.produtoValor = produtoValor;
    }
}
