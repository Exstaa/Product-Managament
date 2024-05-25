package az.developia.productregister.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import az.developia.productregister.database.Database;
import az.developia.productregister.entity.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoryRepository {
	private Connection conn;

	public void createCategory(Category c) {
		conn = Database.connect();
		try {
			PreparedStatement st = conn
					.prepareStatement("INSERT INTO product_categories(category_name) VALUES(?)");
			st.setString(1, c.getName());
			st.execute();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public ObservableList<Category> readCategory() {
		ObservableList<Category> categoryList = FXCollections.observableArrayList();
		conn = Database.connect();
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM product_categories");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Category c = new Category();
				c.setName(rs.getString("category_name"));
				categoryList.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	public void deleteCategory(String categoryWhichMustBeDeleted) {
		conn = Database.connect();
		try {
			PreparedStatement st = conn
					.prepareStatement("DELETE FROM product_categories WHERE category_name = ?");
			st.setString(1, categoryWhichMustBeDeleted);
			st.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAllCategories() {
		conn = Database.connect();
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM product_categories");
			st.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void truncateCategories() {
		conn = Database.connect();
		try {
			PreparedStatement st = conn.prepareStatement("TRUNCATE product_categories");
			st.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
