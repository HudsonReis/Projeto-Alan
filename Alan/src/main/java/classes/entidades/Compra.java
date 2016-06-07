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
public class Compra {

    private int idCompra;
    private int codigoFilial;
    private int idUsuario;
    private double valorTotal;
    private Date dataCompra;
    private List<Item> itens;
    
    public Compra() {
        
    }

    public Compra(int codigoFilial, int idUsuario, double valorTotal, List<Item> itens) {
        this.codigoFilial = codigoFilial;
        this.idUsuario = idUsuario;
        this.valorTotal = valorTotal;
        this.dataCompra = new Date();
        this.itens = itens;
    }
    
    public int getIdCompra() {
        return this.idCompra;
    }
    
    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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

    public Date getDataCompra() {
        return this.dataCompra;
    }
    
    public List<Item> getItens() {
        return this.itens;
    }
    
    public void setItens(ArrayList<Item> itens) {
        this.itens = itens;
    }
}
