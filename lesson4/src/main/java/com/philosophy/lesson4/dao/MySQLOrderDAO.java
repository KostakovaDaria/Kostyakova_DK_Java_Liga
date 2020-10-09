package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.OrderDTO;
import java.sql.Connection;
import java.sql.SQLException;

public interface MySQLOrderDAO {

    Connection getConnection() throws PersistException;
    Integer getId(Connection connection, OrderDTO orderDTO) throws PersistException, SQLException;
    void InsertOrder(Connection connection, OrderDTO orderDTO) throws PersistException, SQLException;
    void getIndex(Connection connection, OrderDTO orderDTO) throws PersistException, SQLException;
}
