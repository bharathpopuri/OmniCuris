package com.omnicuris.project.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class ProductAvailableDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stockId;

	private Integer productCount;
	private boolean isAvailable;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id")
	private ProductDetails product;

	public ProductAvailableDetails() {
	}

	public ProductAvailableDetails(int stockId, Integer productCount, boolean isAvailable, ProductDetails product) {
		this.stockId = stockId;
		this.productCount = productCount;
		this.isAvailable = isAvailable;
		this.product = product;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public int getProductCount() {
		return productCount;
	}

	public Integer setProductCount(Integer productCount) {
		return this.productCount = productCount;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public ProductDetails getProduct() {
		return product;
	}

	public void setProduct(ProductDetails product) {
		this.product = product;
	}

}