package com.Service;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omnicuris.OmniCurisException.OmniCurisException;
import com.omnicuris.project.Entity.OrderDetails;
import com.omnicuris.project.Entity.ProductAvailableDetails;
import com.omnicuris.project.Entity.ProductDetails;
import com.omnicuris.project.Entity.Users;
import com.omnicuris.project.Repository.OrderRepository;
import com.omnicuris.project.Repository.ProductAvailableDetailsRepository;
import com.omnicuris.project.Repository.ProductsRepository;
import com.omnicuris.project.Repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmniCurisServiceImpl implements OmniCurisService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private ProductsRepository productsRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductAvailableDetailsRepository productDetailsRepository;

	//UserDetails
	@Override
	public Users saveUsers(Users users) {

		Users userData = userRepository.save(users);

		return userData;
	}

	@Override
	public Users deleteUserById(Integer userId) throws OmniCurisException {
		Optional<Users> findUserById = userRepository.findById(userId);
		if (findUserById.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			throw new OmniCurisException("There is no User Found with this " + userId + " Id");
		}
		return null;
	}

	// Products Details
	@Override
	public ProductDetails saveproducts(ProductDetails products) {
		ProductAvailableDetails productAvailableDetails = null;
		ProductDetails productsData = productsRepository.save(products);
		Optional<ProductDetails> optionalproduct = productsRepository
				.findProductByProductName(products.getProductName());

		Integer productCount = productsRepository.getproductCountByproductName(products.getProductName());
		Optional<ProductAvailableDetails> optionalProduct = productDetailsRepository
				.findByProductName(products.getProductName());

		if (!optionalProduct.isPresent()) {
			productAvailableDetails = new ProductAvailableDetails();
			productAvailableDetails.setProduct(optionalproduct.get());
			Integer count = productAvailableDetails.setProductCount(productCount);

			if (count > 0) {
				productAvailableDetails.setAvailable(true);
			}
			productDetailsRepository.save(productAvailableDetails);

		}

		ProductAvailableDetails oldCountDetails = optionalProduct.get();
		oldCountDetails.setProductCount(productCount);
		productDetailsRepository.save(oldCountDetails);

		return productsData;
	}

	@Override
	public List<ProductDetails> getAllProductList() {
		List<ProductDetails> productsList = productsRepository.findAll();
		return productsList;
	}

	// OrderDetails
	@Override
	public Object saveOrderDetails(String orderDetailsList, String email)
			throws OmniCurisException, JsonMappingException, JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(orderDetailsList);
		List<OrderDetails> orderList = mapper.convertValue(node.get("orderDetailsList"),
				new TypeReference<List<OrderDetails>>() {
				});
		Optional<Users> optionalUser = userRepository.findUserByEmail(email);

		if (!optionalUser.isPresent()) {
			throw new OmniCurisException("There is no User Found by this email " + email);
		}

		for (OrderDetails orderDetails : orderList) {

			if (optionalUser.isPresent()) {
				Optional<ProductDetails> optionalProducts = productsRepository
						.findProductByProductName(orderDetails.getProductName());

				orderDetails.setUser(optionalUser.get());
				orderDetails.setProduct(optionalProducts.get());
				OrderDetails orders = orderRepository.save(orderDetails);
				return orders;
			}

		}

		return null;
	}

	@Override
	public OrderDetails orderDetailsByUserEmail(String email) {
		OrderDetails orderDetailsByEmail = orderRepository.findOrderDetailsByEmail(email);
		return orderDetailsByEmail;
	}

	@Override
	public List<OrderDetails> alllOrdersList() {
		List<OrderDetails> orderDetailsList = orderRepository.findAll();
		return orderDetailsList;
	}

}