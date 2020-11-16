package com.philosophy.lesson4.service;

import com.philosophy.lesson4.dao.PersistException;
import com.philosophy.lesson4.domain.Order;
import com.philosophy.lesson4.domain.OrderDTO;

public interface OrderService {

   OrderDTO CreateOrder(Order order) throws PersistException;

}
