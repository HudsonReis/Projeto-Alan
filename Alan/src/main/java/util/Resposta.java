/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author arthur.lrsouza1
 * @param <T>
 */
public class Resposta<T> {
    private boolean Sucesso;
    private String Mensagem;
    private String Campo;
    private T Resultado;
    
    public Resposta() {
        Sucesso = true;
    }
    
    public void setErro(String msg, String campo){
        Mensagem = msg;
        Campo = campo;
        Sucesso = false;
    }
    
    public String getMensagem() {
        return this.Mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.Mensagem = mensagem;
    }
    
    public boolean getSucesso() {
        return this.Sucesso;
    }
    
    public String getCampo() {
        return this.Campo;
    }
    
    public T getResultado() {
        return this.Resultado;
    }
    
    public void setResultado(T resultado) {
        this.Resultado = resultado;
    }
}
