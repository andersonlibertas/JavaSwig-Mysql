/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entidades.Cidade;
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
public class CidadeDAO {
    private Connection conexao;
    private String sql;
    private PreparedStatement pst;
    
    public CidadeDAO(){
        conexao = Conecta.getConexao();
    }   
    
    public int inserir(Cidade cidade){
      int retorno;
      sql = "insert into cidade (cod_cidade,nome, estado) values (?,?,?)";
      try{
          pst = conexao.prepareStatement(sql);
          pst.setInt(1,cidade.getCodCidade());
          pst.setString(2, cidade.getNome());
          pst.setString(3, cidade.getEstado());
          retorno = pst.executeUpdate();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null, e.getMessage());
          retorno = -1;
      }
      return retorno;
    }
    
    public LinkedList<Cidade> busca(){
        LinkedList<Cidade> lista = null;
        sql = "select * from cidade";
        try{
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Cidade cidade = new Cidade();
                cidade.setCodCidade(rs.getInt("cod_cidade"));
                cidade.setNome(rs.getString("nome"));
                cidade.setEstado(rs.getString("estado"));
               
                if(lista==null){
                    lista = new LinkedList();
                }
                
                lista.add(cidade);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());            
        }
        return lista;
    }
    
    public int excluir(int codCidade){
        sql = "delete from cidade where cod_cidade = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,codCidade);
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
    
    public Cidade busca(int codCidade){
        sql = "select * from cidade where cod_cidade = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, codCidade);
            ResultSet rs = pst.executeQuery();
            Cidade cidade = null;
            if(rs.next()){
                cidade = new Cidade();
                   cidade.setCodCidade(rs.getInt("cod_cidade"));
                   cidade.setNome(rs.getString("nome"));
                   cidade.setEstado(rs.getString("estado"));
            }
            return cidade;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
   
    public int alterar(Cidade cidade){
        sql = "update cidade set nome = ?, estado = ? where cod_cidade = ?";
        try{
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1, cidade.getNome());
            pst.setString(2, cidade.getEstado());
            pst.setInt(3,cidade.getCodCidade());
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
}
