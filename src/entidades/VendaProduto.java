/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entidades;

import java.util.LinkedList;

/**
 *
 * @author Anderson, Emile, Gustavo, Maira
 */
public class VendaProduto {
    private Venda venda;
    private LinkedList<Produto> produtos;
    private LinkedList<Integer> quantidades;

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public LinkedList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(LinkedList<Produto> produtos) {
        this.produtos = produtos;
    }

    public LinkedList<Integer> getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(LinkedList<Integer> quantidades) {
        this.quantidades = quantidades;
    }
    
    
}
