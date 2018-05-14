/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entidades.Marca;
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
public class MarcaDAO {
    private Connection conexao;
    private String sql;
    private PreparedStatement pst;
    
    public MarcaDAO(){
        conexao = Conecta.getConexao();
    }
    
    public int inserir(Marca marca){
      int retorno;
      sql = "insert into proprietario (cod_marca,nome_marca) values (?,?)";
      try{
          pst = conexao.prepareStatement(sql);
          pst.setInt(1,marca.getCodMarca());
          pst.setString(2, marca.getNomeMarca());
          retorno = pst.executeUpdate();
      }catch(SQLException e){
          JOptionPane.showMessageDialog(null, e.getMessage());
          retorno = -1;
      }
      return retorno;
    }
    
    public LinkedList<Marca> busca(){
        LinkedList<Marca> lista = null;
        sql = "select * from proprietario";
        try{
            pst = conexao.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Marca marca = new Marca();
                marca.setCodMarca(rs.getInt("cod_marca"));
                marca.setNomeMarca(rs.getString("nome_marca"));
               
                if(lista==null){
                    lista = new LinkedList();
                }
                
                lista.add(marca);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());            
        }
        return lista;
    }
    
    public int excluir(int codMarca){
        sql = "delete from proprietario where cod_marca = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1,codMarca);
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
    
    public Marca busca(int codMarca){
        sql = "select * from proprietario where cod_marca = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, codMarca);
            ResultSet rs = pst.executeQuery();
            Marca marca = null;
            if(rs.next()){
                marca = new Marca();
                marca.setCodMarca(rs.getInt("cod_marca"));
                marca.setNomeMarca(rs.getString("nome_marca"));
            }
            return marca;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return null;
        }
    }
    /**
     * Metodo que altera marca
     * @param marca
     * @return qtd de tuplas modificadas no BD, -1 se houve erro
     */
    public int alterar(Marca marca){
        sql = "update proprietario set nome_marca = ? where cod_marca = ?";
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, marca.getNomeMarca());
            pst.setInt(2, marca.getCodMarca());
            return pst.executeUpdate();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            return -1;
        }
    }
    
    
    
    
    
}
