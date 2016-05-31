/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.entidades;

import java.sql.Date;
import org.apache.derby.client.am.Decimal;
import util.Formatacoes;

/**
 *
 * @author Arthur
 */
public class ProdutoValor {
    private int codigoProduto;
    private Date inicioVigencia;
    private Date terminoVigencia;
    private double valor;
    
    public ProdutoValor() {
        
    }
    
    public ProdutoValor(int codigoProduto, Date inicioVigencia, Date terminoVigencia, double valor) {
        this.codigoProduto = codigoProduto;
        this.inicioVigencia = inicioVigencia;
        this.terminoVigencia = terminoVigencia;
        this.valor = valor;
    }
    
    public int getCodigoProduto() {
        return this.codigoProduto;
    }
    
    public Date getInicioVigencia() {
        return this.inicioVigencia;
    }
    
    public Date getTerminoVigencia() {
        return this.terminoVigencia;
    }
    
    public double getValor() {
        return this.valor;
    }
    
    public String getValorFormatado() {
        return Formatacoes.formatarMoeda(this.valor);
    }
    
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    
    public void setInicioVigencia(Date inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }
    
    public void setTerminoVigencia(Date terminoVigencia) {
        this.terminoVigencia = terminoVigencia;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
}
