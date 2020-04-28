package com.omnicuris.project.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class ProductDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productsId;

	private String productType;
	private String productName;
	@Column(name = "descp", length = 2000)
	private String description;
	private double mrp;
	private String brand;
	@Column(name = "specs", length = 2000)
	private String specifications;

	public ProductDetails() {
	}

	public ProductDetails(int productsId, String productType, String productName, String description, double mrp,
			String brand, String specifications) {
		this.productsId = productsId;
		this.productType = productType;
		this.productName = productName;
		this.description = description;
		this.mrp = mrp;
		this.brand = brand;
		this.specifications = specifications;
	}

	public int getProductsId() {
		return productsId;
	}

	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

}