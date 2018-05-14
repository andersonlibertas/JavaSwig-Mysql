/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entidades.Marca;
import entidades.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson, Emile, Gustavo, Maira
 */
public class CafeDAO {
    private Connection conexao;
    private String sql;
    private PreparedStatement pst;
    
    public CafeDAO(){
        conexao = Conecta.getConexao();
    }
    
    public int inserir(Produto produto){
        sql = "insert into cafe (cod_prod,descricao,preco, saldo, marca) "
                + "values(?,?,?,?,?)";
        try{
           pst = conexao.prepareStatement(sql);
           pst.setInt(1, produto.getCodProduto());
           pst.setString(2, produto.getDescricao());
           pst.setFloat(3, produto.getPreco());
           pst.setFloat(4, produto.getSaldo());
           Marca marca = produto.getMarca();
           int idMarca = marca.getCodMarca();
           pst.setInt(5,idMarca);
           return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }        
    }
    
    public LinkedList<Produto> busca(){
        LinkedList<Produto> lista = null;
        sql = "select * from cafe";
        try{
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                produto.setCodProduto(rs.getInt("cod_prod"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setSaldo(rs.getFloat("saldo"));
                MarcaDAO marcaDAO = new MarcaDAO();
                Marca marca = marcaDAO.busca(rs.getInt("marca"));
                produto.setMarca(marca);
               
                if(lista==null){
                    lista = new LinkedList();
                }
                
                lista.add(produto);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());            
        }
        return lista;
    }
    
    public int excluir(int codProduto){
        sql = "delete from produto where cod_prod = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,codProduto);
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
    
    public Produto busca(int codProduto){
        sql = "select * from cafe where cod_prod = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, codProduto);
            ResultSet rs = pst.executeQuery();
            Produto produto = null;
            if(rs.next()){
                produto = new Produto();
                produto.setCodProduto(rs.getInt("cod_prod"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getFloat("preco"));
                produto.setSaldo(rs.getFloat("saldo"));
                MarcaDAO marcaDAO = new MarcaDAO();
                Marca marca = marcaDAO.busca(rs.getInt("marca"));
                produto.setMarca(marca);
            }
            return produto;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
   
    public int alterar(Produto produto){
        sql = "update cafe set descricao = ?, preco = ?, saldo = ?, "
                + "marca = ? where cod_prod = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(5, produto.getCodProduto());
            pst.setString(1, produto.getDescricao());
            pst.setFloat(2, produto.getPreco());
            pst.setFloat(3, produto.getSaldo());
            Marca marca = produto.getMarca();
            int idMarca = marca.getCodMarca();
            pst.setInt(4,idMarca);
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
        
}
