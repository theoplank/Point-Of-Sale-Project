/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author garmin
 */
public class products {
    Connection connection;
    
    private int product_pk;
    private String product_name;
    private String product_description;
    private float product_sellprice;
    private float product_cost;
    private int createdby_fk;
    private Date createdon;
    private Date lastupdated;
    private boolean deletedflag;
    private String category;
    private int stock;
    
    public Integer getpk(){
        return product_pk;
    }
    
    public String getName(){
        return product_name;
    }
    
    public String getDescription(){
        return product_description;
    }
    
    public float getPrice(){
        return product_sellprice;
    }
    
    public float getCost(){
        return product_cost;
    }
    
    public int getFK(){
        return createdby_fk;
    }
    
    public Date getDateCreated(){
        return createdon;
    }
    
    public Date getLastUpdated(){
        return lastupdated;
    }
    
    public boolean getFlag(){
        return deletedflag;
    }
    
    public String getCat(){
        return category;
    }
    
    public int getStock(){
        return stock;
    }
    

    
    public void setName(String name){
        this.product_name = name;
    }
    
    public products(){}
    
    public products(Integer pk, String name, String description, float price, float cost,int fk, Date createdOn, Date updated, boolean deletedFlag,String categoryName, Integer stock)
    {
        this.product_pk = pk;
        this.product_name = name;
        this.product_description = description;
        this.product_sellprice = price;
        this.product_cost = cost;
        this.createdby_fk = fk;
        this.createdon = createdOn;
        this.lastupdated = updated;
        this.deletedflag = deletedFlag;
        this.category = categoryName;
        this.stock = stock;
    }
    
    public static void addProduct(products product){
        Connection connect = database.getConnection();
        PreparedStatement ps = null;
        try{
            ps = connect.prepareStatement("INSERT INTO products(product_name, product_description, product_sellprice, product_cost, category, stock, createdby_fk,createdon, lastupdated, deletedflag) VALUES(?,?,?,?,?,?,?, sysdate(), sysdate(), 0)");
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setFloat(3, product.getPrice());
            ps.setFloat(4, product.getCost());
            ps.setString(5, product.getCat());
            ps.setInt(6, product.getStock());
            ps.setInt(7, product.getFK());
            
            if(ps.executeUpdate() != 0){
                JOptionPane.showMessageDialog(null, "New Product Added");
            }
            else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(products.class.getName()).log(Level.SEVERE, null, ex);
        }
                finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {}
            }
            if (connect != null) {
                try {
                    connect.close();
                } 
                catch (SQLException e) {}
            }
        }
        
    }
    
    
}


