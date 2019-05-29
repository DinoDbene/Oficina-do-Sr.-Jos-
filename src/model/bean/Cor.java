/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.CarroDao;

/**
 *
 * @author Denilson
 */
public class Cor {
    private int idcor;
    private String cor;

    public boolean isReady() {
        
        if (this.cor.length() == 0) {
            return false;
        }
        
        ArrayList<String> cores = new ArrayList <String>();
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
 
        try {
            stmt = con.prepareStatement("USE OFICINA");
            stmt.executeQuery();
            
            stmt = con.prepareStatement("SELECT COR FROM COR");
            
            rs = stmt.executeQuery();
            int i=0;
            while(rs.next()){
                cores.add(rs.getString("COR"));
                i++;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        
        for (int y = 0; y < cores.size(); y++) {            
            if(this.cor.equalsIgnoreCase(cores.get(y))){
                System.out.println(" ERRO cor");
                return false;            
            }
        }
        
        return true;    
    }
    
    public int getIdcor() {
        return idcor;
    }

    public void setIdcor(int idcor) {
        this.idcor = idcor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor.toUpperCase();
    }
}
