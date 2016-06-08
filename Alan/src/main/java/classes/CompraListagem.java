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
public class CompraListagem {

    private int idCompra;
    private int idFilial;
    private String dataCompra;
    private int codigoProduto;
    private String nomeProduto;
    private int idUsuario;
    private String nomeUsuario;
    private double quantidade;
    private double vrUnitario;
    private double valor;

    public CompraListagem(int idCompra,int idFilial, String dataCompra, int codigoProduto, String nomeProduto, int idUsuario, String nomeUsuario, double quantidade, double vrUnitario, double valor) {
        this.idCompra = idCompra;
        this.idFilial = idFilial;
        this.dataCompra = dataCompra;
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.quantidade = quantidade;
        this.vrUnitario = vrUnitario;
        this.valor = valor;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public double getVrUnitario() {
        return vrUnitario;
    }
    

    public double getValor() {
        return valor;
    }

    public int getIdFilial() {
        return idFilial;
    }
   

}
