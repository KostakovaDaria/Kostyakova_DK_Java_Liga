package com.philosophy.lesson4.dao;

import com.philosophy.lesson4.domain.Customer;
import com.philosophy.lesson4.domain.OrderDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.transaction.Transactional;
import java.sql.SQLException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional

public class MySQLOrderDAOImpTest {

    MySQLOrderDAOImp mySQLOrderDAOImp = new MySQLOrderDAOImp();

    public OrderDTO getOrder()
    {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setName("Order6");
        orderDTO.setPrice(754);

        Customer customer = new Customer();
        customer.setName("Anton");
        customer.setEmail_Address("Address1");
        orderDTO.setCustomer(customer);

        return orderDTO;
    }

    @Test
    public void connectionTest() throws PersistException
    {
        Assert.assertNotNull(mySQLOrderDAOImp.getConnection());
    }

    @Test
    public void getIdTest() throws PersistException, SQLException {
        Assert.assertNotNull(mySQLOrderDAOImp.getId(mySQLOrderDAOImp.getConnection(), getOrder()));
    }

    @Test
    public void getIndexTest() throws PersistException, SQLException {
        OrderDTO orderDTO = getOrder();
        mySQLOrderDAOImp.getIndex(mySQLOrderDAOImp.getConnection(), orderDTO);
        Assert.assertNotNull(orderDTO.getId());
    }

    @Test
    public void InsertOrderTest() throws PersistException, SQLException {
        Assert.assertNotNull(mySQLOrderDAOImp.InsertOrder(mySQLOrderDAOImp.getConnection(), getOrder()));
    }
}
