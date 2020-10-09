package com.philosophy.lesson4.service;

import com.philosophy.lesson4.dao.OrderDAO;
import com.philosophy.lesson4.dao.PersistException;
import com.philosophy.lesson4.domain.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
        public OrderDTO CreateOrder(OrderDTO orderDTO) throws PersistException {
            orderDAO.persist(orderDTO);
            return orderDTO;
        }
}
