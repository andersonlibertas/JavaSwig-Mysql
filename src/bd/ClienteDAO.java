/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entidades.Cidade;
import entidades.Cliente;
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
public class ClienteDAO {
    private Connection conexao;
    private String sql;
    private PreparedStatement pst;
    
    public ClienteDAO(){
        conexao = Conecta.getConexao();
    }
    
    public int inserir(Cliente cliente){
        sql = "insert into cliente (cod_cli,nome,telefone, cidade) "
                + "values(?,?,?,?)";
        try{
           pst = conexao.prepareStatement(sql);
           pst.setInt(1, cliente.getCodCliente());
           pst.setString(2, cliente.getNome());
           pst.setString(3, cliente.getTelefone());
           Cidade cidade = cliente.getCidade();
           pst.setInt(4,cidade.getCodCidade());
           return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }        
    }
    
    public LinkedList<Cliente> busca(){
        LinkedList<Cliente> lista = null;
        sql = "select * from cliente";
        try{
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("cod_cli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                CidadeDAO cidadeDAO = new CidadeDAO();
                Cidade cidade = cidadeDAO.busca(rs.getInt("cidade"));
                cliente.setCidade(cidade);
               
                if(lista==null){
                    lista = new LinkedList();
                }
                
                lista.add(cliente);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());            
        }
        return lista;
    }
    
    public int excluir(int codCliente){
        sql = "delete from cliente where cod_cli = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,codCliente);
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
    
    public Cliente busca(int codCliente){
        sql = "select * from cliente where cod_cli = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, codCliente);
            ResultSet rs = pst.executeQuery();
            Cliente cliente = null;
            if(rs.next()){
                cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("cod_cli"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                CidadeDAO cidadeDAO = new CidadeDAO();
                Cidade cidade = cidadeDAO.busca(rs.getInt("cidade"));
                cliente.setCidade(cidade);
            }
            return cliente;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
   
    public int alterar(Cliente cliente){
        sql = "update Cliente set nome = ?, telefone = ?, cidade = ?, "
                + " where cod_cli = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, cliente.getCodCliente());
            pst.setString(2, cliente.getNome());
            pst.setString(3, cliente.getTelefone());
            Cidade cidade = cliente.getCidade();
            pst.setInt(4,cidade.getCodCidade());
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
}
