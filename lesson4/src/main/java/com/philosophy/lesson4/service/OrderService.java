package com.philosophy.lesson4.service;

import com.philosophy.lesson4.dao.PersistException;
import com.philosophy.lesson4.domain.OrderDTO;

public interface OrderService {

   OrderDTO CreateOrder(OrderDTO orderDTO) throws PersistException;

}
