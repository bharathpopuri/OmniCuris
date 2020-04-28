package com.Service;

import java.util.List;

import com.omnicuris.project.Entity.OrderDetails;
import com.omnicuris.project.Entity.ProductDetails;
import com.omnicuris.project.Entity.Users;

public interface OmniCurisService {

    public Users saveUsers(Users users);

    public ProductDetails saveproducts(ProductDetails products);

    public Object saveOrderDetails(String orderDetailsList,String email)throws Exception;

    public List<ProductDetails> getAllProductList();

    public OrderDetails orderDetailsByUserEmail(String email);

    public Users deleteUserById(Integer userId) throws Exception;

    public List<OrderDetails> alllOrdersList();


}