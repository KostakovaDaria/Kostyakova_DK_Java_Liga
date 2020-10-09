package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.OrderDTO;

public interface OrderDAO {

    OrderDTO persist(OrderDTO orderDTO) throws PersistException;
}
