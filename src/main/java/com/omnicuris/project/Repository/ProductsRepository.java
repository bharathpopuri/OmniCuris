package com.omnicuris.project.Repository;

import java.util.Optional;

import com.omnicuris.project.Entity.ProductDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductsRepository extends JpaRepository<ProductDetails, Integer> {

	Optional<ProductDetails> findProductByProductName(String productName);

	@Query("select count(p.productName) from ProductDetails p where p.productName=?1")
	public Integer getproductCountByproductName(String productName);

}