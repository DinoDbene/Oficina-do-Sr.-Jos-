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

/**
 *
 * @author Denilson
 */
public class Cliente_Telefone_Dao {
    public static void create(int id_cliente, int id_telefone){        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();

            stmt = con.prepareStatement("INSERT INTO CLIENTE_TELEFONE (ID_CLIENTE, ID_TELEFONE) VALUES(?, ?)");
            System.out.println("id cliente: " + id_cliente + " // id telefone: " + id_telefone);
            stmt.setInt(1, id_cliente);
            stmt.setInt(2, id_telefone);           
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("ERRO Cliente_Telefone: " + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}
