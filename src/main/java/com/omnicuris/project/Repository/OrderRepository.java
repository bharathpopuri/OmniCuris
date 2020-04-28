package com.omnicuris.project.Repository;

import com.omnicuris.project.Entity.OrderDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {

	@Query("select o from OrderDetails o where o.user.email=?1")
	public OrderDetails findOrderDetailsByEmail(String email);
}