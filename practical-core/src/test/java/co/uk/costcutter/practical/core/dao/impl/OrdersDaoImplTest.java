package co.uk.costcutter.practical.core.dao.impl;

import co.uk.costcutter.practical.core.domain.Orders;
import co.uk.costcutter.practical.core.exception.FatalException;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrdersDaoImplTest {

    private EntityManager em;
    private OrdersDaoImpl ordersDaoImpl;

    @Before
    public void setUp() throws Exception {
        em = mock(EntityManager.class);
        ordersDaoImpl = new OrdersDaoImpl();
        ordersDaoImpl.setEntityManager(em);
    }

    @Test
    public void shouldGetAllOrdersForCustomer() throws Exception {

        List<Orders> expectedOrders = new ArrayList<>();
        Query query = mock(Query.class);
        //given
        given(em.createNamedQuery(anyString())).willReturn(query);
        given(query.getResultList()).willReturn(expectedOrders);

        //when
        ordersDaoImpl.fetchOrdersForCustomer(10);

        //then
        verify(em).createNamedQuery(anyString());
        verify(query).getResultList();
    }

    @Test(expected = FatalException.class)
    public void shouldThrowFatalExceptionWhenCustomerIdIsNull() throws Exception {
        ordersDaoImpl.fetchOrdersForCustomer(null);
    }
}