package az.developia.productregister.entity;

import java.time.LocalDate;

public class Product {
	private Integer id;
	private String productName;
	private String productCategory;
	private LocalDate productAddDate = LocalDate.now();
	private Integer productCount;
	private Integer productSellCount;

	public Product() {

	}

	public Product(Integer id, String productName, String productCategory,
			Integer productCount, Integer productSellCount) {
		this.id = id;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productCount = productCount;
		this.productSellCount = productSellCount;
	}

	public void setProductAddDate(LocalDate productAddDate) {
		this.productAddDate = productAddDate;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public LocalDate getProductAddDate() {
		return productAddDate;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Integer getProductSellCount() {
		return productSellCount;
	}

	public void setProductSellCount(Integer productSellCount) {
		this.productSellCount = productSellCount;
	}
	
	

}
