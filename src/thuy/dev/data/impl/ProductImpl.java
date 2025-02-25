package thuy.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import thuy.dev.data.dao.ProductDao;
import thuy.dev.data.driver.MySQLDriver;
import thuy.dev.data.model.Product;

public class ProductImpl implements ProductDao {
    Connection con = MySQLDriver.getInstance().getConnection();
    
    @Override
    public boolean insert(Product product) {
        // TODO Auto-generated method stub
        String sql = "INSERT INTO PRODUCTS(ID, NAME, DESCRIPTION, THUMBNAIL, PRICE, QUANTITY, CATEGORY_ID) VALUES(NULL, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName() );
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getThumbnail());
            stmt.setDouble(4, 100);
            stmt.setInt(5, 10);
            stmt.setInt(6, 1);
            
            stmt.execute();
            

            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Product product) {
        String sql = "UPDATE PRODUCTS SET name = ?, description = ?, thumbnail=?, price = ?, quantity = ?, category_id = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, product.getName() );
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getThumbnail());
            stmt.setDouble(4, 200);
            stmt.setInt(5, 20);
            stmt.setInt(6, 1);
            
            stmt.setInt(7, product.getId());
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM PRODUCTS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    
    public void find(int id) {
        // TODO Auto-generated method stub
        String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                String thumbnail = rs.getString("thumbnail");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                int view = rs.getInt("view");
                int categoryId = rs.getInt("category_id");
                Timestamp createdAt = rs.getTimestamp("created_at");
                System.out.format("%d:%s:%s:%s:%.2f:%d:%d:%d,:%s", id, name, description, thumbnail, price, quantity, view, categoryId, createdAt).println();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}