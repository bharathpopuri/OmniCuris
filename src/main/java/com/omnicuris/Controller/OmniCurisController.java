package com.omnicuris.Controller;

import java.util.List;

import com.Service.OmniCurisService;
import com.omnicuris.project.Entity.OrderDetails;
import com.omnicuris.project.Entity.ProductDetails;
import com.omnicuris.project.Entity.Users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CommonUtils.ApiSuccess;
import CommonUtils.ApplicationConstants;
import CommonUtils.CommonUtils;

@RestController
@RequestMapping("/api")
public class OmniCurisController {

	private static final Logger LOGGER = LogManager.getLogger(OmniCurisController.class);
	@Autowired
	private OmniCurisService service;

	@PostMapping("/save-user")
	public ResponseEntity<Object> saveUserData(@RequestBody Users users) {
		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "saveUserData");

		Users usersData = service.saveUsers(users);
		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.CREATED, usersData));
	}

	@PostMapping("/save-Products")
	public ResponseEntity<Object> saveProductsData(@RequestBody ProductDetails products) {
		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "saveProductsData");

		ProductDetails productsData = service.saveproducts(products);
		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.CREATED, productsData));
	}

	@DeleteMapping("deleteuserby-Id")
	public ResponseEntity<Object> deleteUserById(@RequestParam Integer id) throws Exception {

		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "deleteUserById");

		Users deletedUser = service.deleteUserById(id);

		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.OK, deletedUser));
	}
	// Task 2

	@GetMapping("allItemsList")
	public ResponseEntity<Object> getAllItemsList() {

		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getAllItemsList");

		List<ProductDetails> productsList = service.getAllProductList();
		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.FOUND, productsList));
	}

	// Task 3

	@PostMapping("/save-orderDetails")
	public ResponseEntity<Object> saveOrderDetails(@RequestBody String orderDetails, @RequestParam String email)
			throws Exception {
		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "saveOrderDetails");

		Object orderDetailsData = service.saveOrderDetails(orderDetails, email);
		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.CREATED, orderDetailsData));
	}

	// Task 4

	@GetMapping("allOrdersList")
	public ResponseEntity<Object> getAllOrdersList() {

		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getAllOrdersList");

		List<OrderDetails> allOrderList = service.alllOrdersList();
		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.FOUND, allOrderList));
	}

	@GetMapping("customerOrders")
	public ResponseEntity<Object> getOrderDetailsByEmail(@RequestParam String email) {

		LOGGER.info(ApplicationConstants.CLASSNAME + this.getClass().getSimpleName() + ", "
				+ ApplicationConstants.METHODNAME + "getOrderDetailsByEmail");

		OrderDetails orderDetails = service.orderDetailsByUserEmail(email);
		return CommonUtils.buildResponseEntity(new ApiSuccess(HttpStatus.FOUND, orderDetails));
	}
}