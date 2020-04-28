package com.omnicuris.project.Repository;

import java.util.Optional;

import com.omnicuris.project.Entity.ProductAvailableDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductAvailableDetailsRepository extends JpaRepository<ProductAvailableDetails, Integer> {

	@Query("select p from ProductAvailableDetails p where p.product.productName=?1")
	public Optional<ProductAvailableDetails> findByProductName(String productName);

	@Query("select p.productCount from ProductAvailableDetails p where p.product.productName=?1")
	public Optional<ProductAvailableDetails> productCount(String productName);
}