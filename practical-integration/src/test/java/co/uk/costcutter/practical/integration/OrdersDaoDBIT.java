package co.uk.costcutter.practical.integration;

import co.uk.costcutter.practical.core.dao.OrdersDao;
import co.uk.costcutter.practical.core.domain.Orders;
import co.uk.costcutter.practical.core.exception.FatalException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        locations = {"classpath:unitApplicationContext.xml"}
)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class})

public class OrdersDaoDBIT {

    public static final Integer UNKNOWN_CUSTOMER = 100000;

    @Autowired
    private OrdersDao ordersDao;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Transactional
    @Test
    public void shouldGetAllOrdersForCustomer10() throws Exception {
        List<Orders> orders = ordersDao.fetchOrdersForCustomer(10);
        assertEquals(2, orders.size());
       //assert order 1
        Orders order1 = orders.get(0);
        assertEquals(10, order1.getCustomerNumber().getCustomerNumber().intValue());
        assertEquals(2714, order1.getOrderNumber().intValue());
        assertEquals(1, order1.getOrderedVehiclesList().size());
        assertEquals("Silver", order1.getOrderedVehiclesList().get(0).getColour());
        assertEquals("Sport", order1.getOrderedVehiclesList().get(0).getModelName().getModelName());
        assertEquals("3.0i", order1.getOrderedVehiclesList().get(0).getEngineDesignation().getEngineDesignation());
        assertEquals("Plus", order1.getOrderedVehiclesList().get(0).getTrimName().getTrimName());

        //assert order 2
        Orders order2 = orders.get(1);
        assertEquals(10, order2.getCustomerNumber().getCustomerNumber().intValue());
        assertEquals(16376, order2.getOrderNumber().intValue());
        assertEquals(1, order2.getOrderedVehiclesList().size());
        assertEquals("City", order2.getOrderedVehiclesList().get(0).getModelName().getModelName());
        assertEquals("White", order2.getOrderedVehiclesList().get(0).getColour());
        assertEquals("1.5ti", order2.getOrderedVehiclesList().get(0).getEngineDesignation().getEngineDesignation());
        assertEquals("Basic", order2.getOrderedVehiclesList().get(0).getTrimName().getTrimName());
    }

    @Transactional
    @Test
    public void shouldGetEmptyListForUNKNOWNCustomer() throws Exception {
        List<Orders> orders = ordersDao.fetchOrdersForCustomer(UNKNOWN_CUSTOMER);
        assertEquals(0, orders.size());
    }

    @Transactional
    @Test(expected = FatalException.class)
    public void shouldThrowFatalExceptionWhenCustomerIdIsNull() {
        ordersDao.fetchOrdersForCustomer(null);
    }
}