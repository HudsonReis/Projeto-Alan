/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nicolas
 */
public class Venda {

    private int idVenda;
    private int codigoFilial;
    private int idUsuario;
    private double valorTotal;
    private Date dataVenda;
    private List<Item> itens;

    public Venda() {
        
    }
    
    public Venda(int codigoFilial, int idUsuario, double valorTotal, List<Item> itens) {
        this.codigoFilial = codigoFilial;
        this.idUsuario = idUsuario;
        this.valorTotal = valorTotal;
        this.dataVenda = new java.util.Date();
        this.itens = itens;
    }

    public int getIdVenda() {
        return this.idVenda;
    }
    
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
    public int getCodigoFilial() {
        return this.codigoFilial;
    }
    
    public void setCodigoFilial(int codigoFilial) {
        this.codigoFilial = codigoFilial;
    }

    public int getCodigoUsuario() {
        return idUsuario;
    }

    public void setCodigoUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getValorTotal() {
        return this.valorTotal;
    }
    
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Date getDataVenda() {
        return this.dataVenda;
    }
    
    public List<Item> getItens() {
        return this.itens;
    }
    
    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }
}
