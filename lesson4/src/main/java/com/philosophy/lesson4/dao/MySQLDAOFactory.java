package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.Customer;
import com.philosophy.lesson4.domain.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MySQLDAOFactory implements DAOFactory<Connection> {

    private String url = "jdbc:h2:mem:testdb";
    private String user = "sa";
    private String password = "";
    private String driver = "org.h2.Driver";
    private Map<Class, DAOCreater> createrMap;

    public Connection getContext() throws PersistException {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return connection;
    }

    @Override
    public GenericDAO getDao(Connection connection, Class dtoClass) throws PersistException {
        DAOCreater creater = createrMap.get(dtoClass);
        if (creater == null) {
            throw new PersistException();
        }
        return creater.create(connection);
    }

    public MySQLDAOFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        createrMap = new HashMap<>();
        createrMap.put(Customer.class, (DAOCreater<Connection>) connection -> new CustomerMySQLDAO(MySQLDAOFactory.this, connection));
        createrMap.put(Order.class, (DAOCreater<Connection>) connection -> new OrderMySQLDAO(MySQLDAOFactory.this, connection));
    }
}

