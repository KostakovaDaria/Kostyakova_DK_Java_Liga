package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.OrderDTO;
import java.sql.*;

public class MySQLOrderDAOImp {

    private String url = "jdbc:h2:mem:testdb";
    private String user = "sa";
    private String password = "";

    /**
     * Метод getConnection обеспечивает подключение к БД H2
     *
     * @param
     * @return connection
     * @throws PersistException
     */

    public Connection getConnection() throws PersistException {

        {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
            }
            catch (SQLException e) {
                throw new PersistException(e);
            }
            return connection;
        }
    }

    /**
     * Метод getId возвращает Id пользователя, осуществляющего заказ
     *
     * @param connection, orderDTO
     * @return Integer
     * @throws PersistException, SQLException
     */

    public Integer getId(Connection connection, OrderDTO orderDTO) throws PersistException, SQLException {

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Id FROM Customer WHERE Name = ? AND Email_Address = ?");
            preparedStatement.setString(1, orderDTO.getCustomer().getName());
            preparedStatement.setString(2, orderDTO.getCustomer().getEmail_Address());

            resultSet = preparedStatement.executeQuery();
            resultSet.first();
        }
        catch (SQLException e) {
            throw new PersistException(e);
        }
        return resultSet.getInt("Id");
    }

    /**
     * Метод InsertOrder осуществляет запись заказа в БД
     *
     * @param connection, orderDTO
     * @return Boolean
     * @throws PersistException, SQLException
     */

    public Boolean InsertOrder(Connection connection, OrderDTO orderDTO) throws PersistException, SQLException {

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Order_Customer(Customer_Id, Name, Price) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, getId(connection, orderDTO));
            preparedStatement.setString(2,  orderDTO.getName());
            preparedStatement.setInt(3, orderDTO.getPrice());

            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new PersistException(e);
        }
        getIndex(connection, orderDTO);
        return true;
    }

    /**
     * Метод getIndex возвращает id заказа
     *
     * @param connection, orderDTO
     * @return void
     * @throws PersistException, SQLException
     */

    public void getIndex(Connection connection, OrderDTO orderDTO) throws PersistException, SQLException {

        ResultSet resultSet = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Id FROM Order_Customer ORDER BY Id DESC LIMIT 1");
            resultSet = preparedStatement.executeQuery();
            resultSet.first();
        }
        catch (SQLException e) {
            throw new PersistException(e);
        }
        orderDTO.setId(resultSet.getInt("Id"));
    }
}
