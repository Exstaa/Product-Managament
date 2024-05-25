package az.developia.productregister.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import az.developia.productregister.entity.Category;
import az.developia.productregister.entity.Product;
import az.developia.productregister.repository.CategoryRepository;
import az.developia.productregister.repository.ProductRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ProductRegisterController implements Initializable{

    @FXML
    private Button addButton;

    @FXML
    private Button addCategoryButton;

    @FXML
    private ComboBox<Category> categoryCB;

    @FXML
    private Button deleteButton;

    @FXML
    private Button deleteCategoryButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteAllCategoriesButton;
    
    @FXML
    private TextField productCountTF;

    @FXML
    private TextField productNameTF;

    @FXML
    private TextField productSellTF;

    @FXML
    private TextField searchTF;
    
    @FXML
    private TableView<Product> productTableView;
    
    @FXML
    private TableColumn<Product, Integer> soldCol;
    
    @FXML
    private TableColumn<Product, Integer> idCol;

    @FXML
    private TableColumn<Product, String> nameCol;
    
    @FXML
    private TableColumn<Product, String> categoryCol;

    @FXML
    private TableColumn<Product, Integer> countCol;

    @FXML
    private TableColumn<Product, LocalDate> dateCol;

    
    public ProductRepository repo = new ProductRepository();
    public CategoryRepository categoryRepo = new CategoryRepository();
    
    @FXML
    void addButtonClicked(ActionEvent event) {
    	Product p = new Product();
    	p.setProductName(productNameTF.getText());
    	p.setProductCategory(categoryCB.getValue().toString());
    	p.setProductCount( Integer.parseInt(productCountTF.getText()));
    	p.setProductSellCount( Integer.parseInt(productSellTF.getText()));
    	repo.addProduct(p);
    	loadProducts();
    }

    @FXML
    void addCategoryButtonClicked(ActionEvent event) {
    		String categoryName = JOptionPane.showInputDialog(null, "Kateqoriya qeyd edin");
    		Category c = new Category();
    		c.setName(categoryName);
    		if (categoryName != null) {
    			if (categoryName.trim().length() != 0) {
    				categoryRepo.createCategory(c);
    			}
    		}
    		loadCategories();
 
    }
    
    @FXML
    void deleteCategoryButtonClicked(ActionEvent event) {
    	if(categoryCB.getValue() != null) {
    	String deleteCategory = categoryCB.getValue().toString();
    	categoryRepo.deleteCategory(deleteCategory);
    	loadCategories();
    	}
    }
    
    @FXML
    void deleteAllCategoriesButtonClicked(ActionEvent event) {
		Integer warning = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all categories?",
				"WARNING", JOptionPane.YES_NO_OPTION);
		if (warning == JOptionPane.YES_OPTION) {
			categoryRepo.truncateCategories();
		}
		loadCategories();
    }
    
    @FXML
    void deleteButtonClicked(ActionEvent event) {
    	Product selectedItem = productTableView.getSelectionModel().getSelectedItem();
    	if(selectedItem != null) {
    		Integer id = selectedItem.getId();
    		repo.deleteProduct(id);
    	}
    	loadProducts();
    }
    
    @FXML
    void searchTFKeyReleased(KeyEvent event) {
    	if(searchTF.getText().trim().length() != 0) {
    		loadSearchedProducts(searchTF.getText());
    	}else {
    		loadProducts();
    	}
    }
    
    @FXML
    void tableViewMouseClicked(MouseEvent event) {
		Product selectedItem = productTableView.getSelectionModel().getSelectedItem();
		if (selectedItem != null) {
			productNameTF.setText(selectedItem.getProductName());
			productCountTF.setText(String.valueOf(selectedItem.getProductCount()));
			productSellTF.setText(String.valueOf(selectedItem.getProductSellCount()));
			categoryCB.setValue(new Category(selectedItem.getProductCategory()));
		}
    }
    
    @FXML
    void editButtonClicked(ActionEvent event) {
		Product selectedItem = productTableView.getSelectionModel().getSelectedItem();

		if (selectedItem != null) {
			String productName = productNameTF.getText();
			String productCategory = categoryCB.getValue().toString();
			Integer productCount = Integer.parseInt(productCountTF.getText());
			Integer productSellCount = Integer.parseInt(productSellTF.getText());

			Product p = new Product();
			p.setProductName(productName);
			p.setProductCategory(productCategory);
			p.setProductCount(productCount);
			p.setProductSellCount(productSellCount);
			
			repo.updateProduct(selectedItem.getId(), p);

		}
		loadProducts();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		loadProducts();
		loadCategories();
	}
	
	void loadProducts() {
		productTableView.setItems(repo.getProducts());
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("productAddDate"));
		countCol.setCellValueFactory(new PropertyValueFactory<>("productCount"));
		soldCol.setCellValueFactory(new PropertyValueFactory<>("productSellCount"));
	}
	
	void loadSearchedProducts(String search) {
		productTableView.setItems(repo.searchProducts(search));
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
		categoryCol.setCellValueFactory(new PropertyValueFactory<>("productCategory"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("productAddDate"));
		countCol.setCellValueFactory(new PropertyValueFactory<>("productCount"));
		soldCol.setCellValueFactory(new PropertyValueFactory<>("productSellCount"));
	}
	
	void loadCategories() {
		categoryCB.setItems(categoryRepo.readCategory());
	}
}
