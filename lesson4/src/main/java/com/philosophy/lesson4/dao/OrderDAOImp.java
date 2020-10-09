package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.*;

@Component
public class OrderDAOImp extends MySQLOrderDAOImp implements OrderDAO  {

    private String url = "jdbc:h2:mem:testdb";
    private String user = "sa";
    private String password = "";

    @Autowired
    private OrderDAOImp orderDAOImp;

    @Override
    public OrderDTO persist(OrderDTO orderDTO) throws PersistException {

        try {

            orderDAOImp.InsertOrder(orderDAOImp.getConnection(), orderDTO);
        }

        catch (SQLException e) {

            throw new PersistException(e);
        }
        return orderDTO;
    }
}
