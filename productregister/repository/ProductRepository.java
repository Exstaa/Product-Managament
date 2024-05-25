package az.developia.productregister.repository;

import java.sql.Connection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import az.developia.productregister.database.Database;
import az.developia.productregister.entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductRepository {
	private Connection conn;

	public void addProduct(Product p) {
		conn = Database.connect();
		PreparedStatement st;
		try {
			String query = "INSERT INTO products(productName, productCategory, productAddDate, productCount, productSellCount) VALUES(?,?,?,?,?)";
			st = conn.prepareStatement(query);
			st.setString(1, p.getProductName());
			st.setString(2, p.getProductCategory());
			st.setDate(3, Date.valueOf(p.getProductAddDate()));
			st.setInt(4, p.getProductCount());
			st.setInt(5, p.getProductSellCount());
			st.execute();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteProduct(int id) {
		conn = Database.connect();
		PreparedStatement st;
		try {
			String query = "DELETE FROM products WHERE id=?";
			st = conn.prepareStatement(query);
			st.setInt(1, id);
			st.execute();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateProduct(Integer id, Product p) {
		conn = Database.connect();
		try {
			PreparedStatement st = conn.prepareStatement(
					"UPDATE products SET productName = ?, productCategory = ?, productCount = ?, productSellCount = ? WHERE id = ?");
			st.setString(1, p.getProductName());
			st.setString(2, p.getProductCategory());
			st.setInt(3, p.getProductCount());
			st.setInt(4, p.getProductSellCount());
			st.setInt(5, id);
			st.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Product> getProducts() {
		ObservableList<Product> products = FXCollections.observableArrayList();

		conn = Database.connect();

		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM products");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Product p = new Product();

				p.setId(rs.getInt("id"));
				p.setProductName(rs.getString("productName"));
				p.setProductCategory(rs.getString("productCategory"));
				p.setProductAddDate(rs.getDate("productAddDate").toLocalDate());
				p.setProductCount(rs.getInt("productCount"));
				p.setProductSellCount(rs.getInt("productSellCount"));
				products.add(p);
			}
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	public ObservableList<Product> searchProducts(String search) {
		ObservableList<Product> products = FXCollections.observableArrayList();

		conn = Database.connect();
		String query = "SELECT * FROM products WHERE id LIKE '%" + search + "%' OR productName LIKE '%" + search
				+ "%' OR productCategory LIKE '%" + search + "%' OR productAddDate LIKE '%" + search
				+ "%' OR productCount LIKE '%" + search + "%' OR productSellCount LIKE '%" + search + "%'";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Product p = new Product();

				p.setId(rs.getInt("id"));
				p.setProductName(rs.getString("productName"));
				p.setProductCategory(rs.getString("productCategory"));
				p.setProductAddDate(rs.getDate("productAddDate").toLocalDate());
				p.setProductCount(rs.getInt("productCount"));
				p.setProductSellCount(rs.getInt("productSellCount"));
				products.add(p);
			}
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
