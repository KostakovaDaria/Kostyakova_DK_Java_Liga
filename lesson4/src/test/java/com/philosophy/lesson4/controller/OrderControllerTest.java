package com.philosophy.lesson4.controller;

import com.philosophy.lesson4.dao.PersistException;
import com.philosophy.lesson4.domain.Customer;
import com.philosophy.lesson4.domain.OrderDTO;
import com.philosophy.lesson4.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional

public class OrderControllerTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrderTest() throws PersistException {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setName("Order6");
        orderDTO.setPrice(754);

        Customer customer = new Customer();
        customer.setName("Anton");
        customer.setEmail_Address("Address1");
        orderDTO.setCustomer(customer);

        OrderDTO orderDTODB = new OrderDTO();
        orderDTODB = orderService.CreateOrder(orderDTO);
        assertEquals(orderDTO, orderDTODB);
    }

}
