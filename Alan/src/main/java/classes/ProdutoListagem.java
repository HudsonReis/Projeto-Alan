/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Arthur
 */
public class ProdutoListagem {
    private int codigoPeca;
    private String filial;
    private String usuario;
    private String produto;
    private int qtdPeca;
    private boolean status;
    
    public ProdutoListagem(int codigoPeca, String filial, String usuario, String produto, int qtdPeca, boolean status)
    {
        this.codigoPeca = codigoPeca;
        this.filial = filial;
        this.usuario = usuario;
        this.produto = produto;
        this.qtdPeca = qtdPeca;
        this.status = status;
    }
    
    public int getCodigoPeca() {
        return this.codigoPeca;
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
    
    public String getStatus() {
        if (status) {
            return "Sim";
        } else {
            return "Não";
        }
    }
}
