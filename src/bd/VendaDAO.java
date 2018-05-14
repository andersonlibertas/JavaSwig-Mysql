/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import entidades.Cliente;
import entidades.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson, Emile, Gustavo, Maira
 */

public class VendaDAO {
    private Connection conexao;
    private String sql;
    private PreparedStatement pst;
    
    public VendaDAO(){
        conexao = Conecta.getConexao();
    }
    
    
    public int inserir(Venda venda) {
        sql = "insert into Venda (num_nota, data_venda, cliente) "
                + " values (?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, venda.getNumNota());
            Cliente cli = venda.getCliente();
            pst.setInt(3, cli.getCodCliente());
            Date data = Date.valueOf(venda.getData());
            pst.setDate(2, data);
            int r=pst.executeUpdate();
            String sql2 = "insert into venda_produtos ("
                    + "num_nota,quantidade,produto) values (?,?,?)";
            pst = conexao.prepareStatement(sql2);
            pst.setInt(1, venda.getNumNota());
            
            for (int i = 0; i < venda.getListaProdutos().size(); i++) {
                pst.setInt(2, venda.getListaQuantidades().get(i));
                pst.setInt(3, venda.getListaProdutos().get(i).getCodProduto());
                pst.executeUpdate();
            }
            return r;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return -1;
        }
    }
    
}
