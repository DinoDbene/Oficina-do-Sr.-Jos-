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
public class Carro {
    private int idcarro;
    private String placa;
    private String modelo;
    
    public boolean placaIsReady(boolean validarNoBanco) {
        if (this.placa.length() > 7  || this.placa.length() < 1) {
            System.out.println(" ERRO placa");
        return false;
        
        } else {
            
            if (!validarNoBanco) {
                return true;
            } else {

                ArrayList<String> placas = new ArrayList <String>();
                Connection con = ConnectionFactory.getConnection();

                PreparedStatement stmt = null;
                ResultSet rs = null;

                try {
                    stmt = con.prepareStatement("USE OFICINA");
                    stmt.executeQuery();

                    stmt = con.prepareStatement("SELECT PLACA FROM CARRO");

                    rs = stmt.executeQuery();
                    int i=0;
                    while(rs.next()){
                        placas.add(rs.getString("PLACA"));
                        i++;
                    }


                } catch (SQLException ex) {
                    Logger.getLogger(CarroDao.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    ConnectionFactory.closeConnection(con, stmt, rs);
                }


                for (int y = 0; y < placas.size(); y++) {            
                    if(this.placa.equalsIgnoreCase(placas.get(y))){
                        System.out.println(" ERRO placa");
                        return false;            
                    }
                }

                return true;
            }
        }        
    }

    public boolean modeloIsReady() {
        if (this.modelo.length() > 10 || this.modelo.length() < 1) {
            System.out.println(" ERRO modelo");
        return false;
        
        } else {
        return true;
        
        }    
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase();
    }


    public int getIdcarro() {
        return idcarro;
    }

    public void setIdcarro(int idcarro) {
        this.idcarro = idcarro;
    }
}
