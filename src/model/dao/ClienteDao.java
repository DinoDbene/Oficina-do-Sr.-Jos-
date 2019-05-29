/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Carro;
import model.bean.Cliente;

/**
 *
 * @author Denilson
 */
public class ClienteDao {
    public static int create(Cliente c){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO CLIENTE (NOME, SEXO, ID_CARRO) VALUES(?, ?, ?)");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getSexo());
            stmt.setInt(3, c.getCarro());
            
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("SELECT IDCLIENTE FROM CLIENTE WHERE NOME = ? AND SEXO = ? AND ID_CARRO = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getSexo()); 
            stmt.setInt(3, c.getCarro());
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCLIENTE");
            }
            System.out.println("id cliente: " + id);
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    return id;    
    }
    
    public static int update(String placa , Cliente cl){        
        Connection con = ConnectionFactory.getConnection();
        
        int id = 0;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
           
        // ENCONTRAR O CLIENTE QUE VAI SER ALTERADO NO BANCO
        
            stmt = con.prepareStatement("SELECT IDCLIENTE FROM CLIENTE WHERE ID_CARRO = (SELECT IDCARRO FROM CARRO WHERE PLACA = ?)");
            stmt.setString(1, placa);
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {                
                id = rs.getInt("IDCLIENTE");
            }
            System.out.println("Placa: " + placa);
            System.out.println("id cliente: " + id);
        
        // EFETUAR ALTERAÇÕES EM CLIENTE
            
            stmt = con.prepareStatement("UPDATE CLIENTE SET NOME = ? WHERE IDCLIENTE = ?");
            stmt.setString(1, cl.getNome());
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
            
            stmt = con.prepareStatement("UPDATE CLIENTE SET SEXO = ? WHERE IDCLIENTE = ?");
            stmt.setString(1, cl.getSexo());
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
        
        } catch (SQLException ex) {
            System.out.println("ERRO ao alterar: " + ex);
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    return id;    
    }
    
}
