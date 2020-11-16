package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.Customer;
import com.philosophy.lesson4.domain.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class OrderMySQLDAO extends AbstractDAO<Order, Integer> {

    @Override
    public Order create(Order source) throws PersistException {
        Order order = new Order();
        order.setId(source.getId());
        order.setName(source.getName());
        order.setPrice(source.getPrice());
        order.setCustomer(source.getCustomer());
        return persist(order);
    }


    private class PersistOrder extends Order{

        public void setId(Integer Id)
        {
            super.setId(Id);
        }
    }

    public OrderMySQLDAO(DAOFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
        addRelation(Order.class, "customer");
    }

    @Override
    public String getSelectQuery() {
        return "SELECT Id, Name, Price, Customer_Id FROM Order_Customer ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Order_Customer(Customer_Id, Name, Price) VALUES (?, ?, ?);";
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Order> result = new LinkedList<Order>();
        try {
            while (rs.next())
            {
                PersistOrder order = new PersistOrder();
                order.setId(rs.getInt("Id"));
                order.setName(rs.getString("Name"));
                order.setPrice(rs.getInt("Price"));
                order.setCustomer((Customer)getDependence(Customer.class, rs.getInt("Customer_Id")));
                result.add(order);
            }
        }
        catch (Exception e)
        {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws PersistException {

        try {

            Integer customerId = (object.getCustomer() == null || object.getCustomer().getId() == null) ? -1
                 : object.getCustomer().getId();
            statement.setInt(1, customerId);
            statement.setString(2, object.getName());
            statement.setInt(3, object.getPrice());
        }
        catch (Exception e)
        {
            throw new PersistException(e);
        }
    }
}
