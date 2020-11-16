package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class CustomerMySQLDAO extends AbstractDAO<Customer, Integer> {

    @Override
    public Customer create(Customer object) throws PersistException {
        return persist(object);
    }

    private class PersistCustomer extends Customer{

        public void setId(Integer Id)
        {
            super.setId(Id);
        }
    }

    public CustomerMySQLDAO(DAOFactory<Connection> parentFactory, Connection connection) {
        super(parentFactory, connection);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT Id FROM Customer ";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO Customer(Id, Name, Email_Address) VALUES (?, ?, ?);";
    }

    @Override
    protected List<Customer> parseResultSet(ResultSet rs) throws PersistException {
        LinkedList<Customer> result = new LinkedList<Customer>();
        try {
            while (rs.next())
            {
                CustomerMySQLDAO.PersistCustomer customer = new CustomerMySQLDAO.PersistCustomer();
                customer.setId(rs.getInt("Id"));
                result.add(customer);
            }
        }
        catch (Exception e)
        {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Customer object) throws PersistException {

        try {
            statement.setInt(1, object.getId());
        }
        catch (Exception e)
        {
            throw new PersistException(e);
        }
    }

}
