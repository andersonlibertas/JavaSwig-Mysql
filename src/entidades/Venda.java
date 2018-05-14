/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author Anderson, Emile, Gustavo, Maira
 */
public class Venda {
    private int numNota;
    private Cliente cliente;
    private LocalDate data;
    private LinkedList<Produto> listaProdutos;
    private LinkedList<Integer> listaQuantidades;

    public LinkedList<Integer> getListaQuantidades() {
        return listaQuantidades;
    }

    public void setListaQuantidades(LinkedList<Integer> listaQuantidades) {
        this.listaQuantidades = listaQuantidades;
    }
       
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
       return data;
    }

   public void setData(LocalDate data) {
       this.data = data;
   }

    public int getNumNota() {
         return numNota;
    }
   
    public void setNumNota(int numNota) {
        this.numNota = numNota;
    }

    public LinkedList<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(LinkedList<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
    
    

    
    
    
}
