package com.dyejeekis.shopdemo;

import org.json.JSONException;
import org.junit.Test;

import static org.junit.Assert.*;

import com.dyejeekis.shopdemo.data.model.Order;
import com.dyejeekis.shopdemo.data.model.Product;
import com.dyejeekis.shopdemo.data.model.ProductList;
import com.dyejeekis.shopdemo.data.model.User;
import com.dyejeekis.shopdemo.data.remote.api.OrderResponse;
import com.dyejeekis.shopdemo.data.remote.api.ProductResponse;
import com.dyejeekis.shopdemo.data.remote.api.UserResponse;

import java.util.List;

public class TestApiResponses {

    @Test
    public void testUserResponse() {
        final String jsonStr = "{\"cart\":{\"items\":[]},\"_id\":\"613fabde6f55b8e2a8a42458\",\"username\":\"user4\",\"password\":\"$2b$10$OrJiaiYYAsVmQ0g5jttyy.WwpYdndda/WT5irLJgcfO1KAvyLqnVy\",\"__v\":0}";
        try {
            UserResponse response = new UserResponse(jsonStr);
            User user = response.getUser();
            assertEquals(user.getId(), "613fabde6f55b8e2a8a42458");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUsersResponse() {
        final String jsonStr = "[{\"cart\":{\"items\":[]},\"_id\":\"613f5e4a636f3fcf5aca32fe\",\"username\":\"user3\",\"password\":\"$2b$10$RQrWRgBuz5Dy.ht/t2xPC.AOUF.Lyx/PcNel5c9QVc1eVjTXzsESO\",\"__v\":0,\"token\":\"b24ceb6e75326c0ad2130cd6308271fa767b4c5ecbb9b1e75bf7162ad57fcd8d17a67b6e53f9971e4b605b1090bfa19de5f70d8eade6fef253b82400715f5b7d\"},{\"cart\":{\"items\":[]},\"_id\":\"613fabde6f55b8e2a8a42458\",\"username\":\"user4\",\"password\":\"$2b$10$OrJiaiYYAsVmQ0g5jttyy.WwpYdndda/WT5irLJgcfO1KAvyLqnVy\",\"__v\":0}]";
        try {
            UserResponse response = new UserResponse(jsonStr);
            List<User> users = response.getUsers();
            assertEquals(users.size(), 2);
            assertEquals(users.get(0).getUsername(), "user3");
            assertEquals(users.get(1).getUsername(), "user4");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProductResponse() {
        final String jsonStr = "{\"_id\":\"613f5ef6a5a651cb1fdb0378\",\"name\":\"product1\",\"stock\":5,\"price\":9.99,\"__v\":0}";
        try {
            ProductResponse response = new ProductResponse(jsonStr);
            Product product = response.getProduct();
            assertEquals(product.getId(), "613f5ef6a5a651cb1fdb0378");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProductsResponse() {
        final String jsonStr = "[{\"_id\":\"613f5ef6a5a651cb1fdb0378\",\"name\":\"product1\",\"stock\":5,\"price\":9.99,\"__v\":0},{\"_id\":\"613f5f03a5a651cb1fdb037a\",\"name\":\"product2\",\"stock\":100,\"price\":4.99,\"__v\":0}]";
        try {
            ProductResponse response = new ProductResponse(jsonStr);
            ProductList products = response.getProducts();
            assertEquals(products.size(), 2);
            assertEquals(products.get(0).getName(), "product1");
            assertEquals(products.get(1).getName(), "product2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrderResponse() {
        final String jsonStr = "[{\"productId\":\"6141aed5d1039fdd5e19c7ef\",\"quantity\":2,\"_id\":\"61432ed32badc9fd55b6ff62\"},{\"productId\":\"6141aed5d1039fdd5e19c7da\",\"quantity\":1,\"_id\":\"61432ede2badc9fd55b6ff6b\"}]";
        try {
            OrderResponse response = new OrderResponse(jsonStr);
            Order order = response.getOrder();
            assertEquals(order.getId(), "6141aed5d1039fdd5e19c7ef");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOrdersResponse() {
        // TODO: 9/16/2021
    }
}
