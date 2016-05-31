/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.NumberFormat;

/**
 *
 * @author Arthur
 */
public class Formatacoes {
    public static String formatarMoeda(double valor) {
        NumberFormat formatador = NumberFormat.getCurrencyInstance();
        
        return formatador.format(valor);
    }
}
