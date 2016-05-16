/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Arthur
 */
public enum FuncionalidadesEnum {
    CadastroFiliais(1),
    CadastroProdutos(2),
    CadastroUsuarios(3),
    RegistroMovimentacaoCompras(4),
    RegistroMovimentacaoVendas(5),
    VisualizacaoRelatorios(6),
    BuscaUsuarios(7),
    BuscaProdutos(8),
    BuscaFiliais(9);
    
    public int value;
    
    FuncionalidadesEnum(int codigo) {
        value = codigo;
    }
}
