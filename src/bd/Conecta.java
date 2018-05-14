/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Anderson, Emile, Gustavo, Maira
 */
public class Conecta {
    private static Connection conexao;
    
    private static Connection criaConexao(){
        String url = "jdbc:mysql://127.0.0.1:3306/Vendas";
        //String urlOracle = "jdbc:oracle:thin:@localhost:1521:xe";
        String usuario = "root";
        String senha = "1234";
        try{
            Connection aux = DriverManager.getConnection(url,usuario,senha);
            System.out.println("OK");
            conexao = aux;
            return aux;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static Connection getConexao(){
        if(conexao != null) {
            return conexao;
        }else{
            return criaConexao();
        }
    }
    
    public static void fechaConexao(){
        conexao = null;
    }
}
