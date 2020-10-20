package com.philosophy.lesson4.service;

import com.philosophy.lesson4.dao.*;
import com.philosophy.lesson4.domain.Order;
import com.philosophy.lesson4.domain.OrderDTO;
import org.springframework.stereotype.Service;
import java.sql.Connection;

@Service
public class OrderServiceImp implements OrderService {

    MySQLDAOFactory mySQLDAOFactory = new MySQLDAOFactory();

    Connection connection;

    {
        try {
            connection = mySQLDAOFactory.getContext();
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получаем объект DAO в соответствии с указанным шаблоном и в рамках указанного соединения
     */

    OrderMySQLDAO orderMySQLDAO = new OrderMySQLDAO(mySQLDAOFactory, connection);

    /**
     * Записи в Баззу Данных
     * @param order - Передаем данные, полученные по формату JSON для записи в Баззу Данных
     * @return - возвращаем объект типа OrderDTO, который содержит Id последней добавленной записи
     * @throws PersistException
     */

    @Override
        public OrderDTO CreateOrder(Order order) throws PersistException {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId((orderMySQLDAO.persist(order)).getId());
        return orderDTO;
        }
}
