package co.uk.costcutter.practical.service.impl;

import co.uk.costcutter.practical.core.dao.OrdersDao;
import co.uk.costcutter.practical.core.domain.*;
import co.uk.costcutter.practical.core.dto.OrderDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrderServiceImplTest {

    public static final String UNKNOWN_CUSTOMER = "1000000";
    public static final String INVALID_CUSTOMER = "Invalid";

    @Mock
    private OrdersDao mockOrdersDao;

    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldGetAllOrdersForCustomer999() throws Exception {

        Orders order = new Orders();
        List<OrderedVehicles> orderedVehicleList = new ArrayList<>();
        orderedVehicleList.add(buildOrderedVehicles());
        order.setOrderNumber(100);
        order.setOrderedVehiclesList(orderedVehicleList);
        List<Orders> orders = new ArrayList<>();
        orders.add(order);

        //given
        given(mockOrdersDao.fetchOrdersForCustomer(999)).willReturn(orders);
        //when
        List<OrderDto> orderDtos = orderServiceImpl.getAllOrdersForCustomer("999");
        //then
        assertEquals(1, orderDtos.size());
        verify(mockOrdersDao).fetchOrdersForCustomer(999);
        verifyNoMoreInteractions(mockOrdersDao);
        assertOrderDtos(orderDtos);
    }

    @Test
    public void shouldFilterDuplicateOrdersForCustomer99() throws Exception {
        // Order 1
        Orders order1 = new Orders();
        List<OrderedVehicles> orderedVehicleList = new ArrayList<>();
        orderedVehicleList.add(buildOrderedVehicles());
        order1.setOrderNumber(100);
        order1.setOrderedVehiclesList(orderedVehicleList);

        //Duplicate Order
        Orders order2 = order1;
        List<Orders> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);

        //given
        given(mockOrdersDao.fetchOrdersForCustomer(99)).willReturn(orders);
        //when
        List<OrderDto> orderDtos = orderServiceImpl.getAllOrdersForCustomer("99");
        //then
        assertEquals(1, orderDtos.size());
        verify(mockOrdersDao).fetchOrdersForCustomer(99);
        verifyNoMoreInteractions(mockOrdersDao);
        assertOrderDtos(orderDtos);
    }


    @Test
    public void shouldReturnEmptyListForUNKNOWSCustomer() throws Exception {
        List<Orders> orders = new ArrayList<>();
        //given
        given(mockOrdersDao.fetchOrdersForCustomer(1000000)).willReturn(orders);
        //when
        List<OrderDto> orderDtos = orderServiceImpl.getAllOrdersForCustomer(UNKNOWN_CUSTOMER);
        //then
        assertEquals(0, orderDtos.size());
        verify(mockOrdersDao).fetchOrdersForCustomer(1000000);
        verifyNoMoreInteractions(mockOrdersDao);
    }

    private void assertOrderDtos(List<OrderDto> orderDtos) {
        assertEquals(orderDtos.size(), 1);
        assertEquals(100, orderDtos.get(0).getOrderNumber().intValue());
        assertEquals("Color", orderDtos.get(0).getOrderedVehicles().get(0).getColor());
        assertEquals("Engine", orderDtos.get(0).getOrderedVehicles().get(0).getEngine());
        assertEquals("Model", orderDtos.get(0).getOrderedVehicles().get(0).getModel());
        assertEquals("Trim", orderDtos.get(0).getOrderedVehicles().get(0).getTrim());
    }

    public static OrderedVehicles buildOrderedVehicles() {
        Models models = new Models("Model");
        Engines engines = new Engines("Engine");
        Trims trims = new Trims("Trim");
        OrderedVehicles orderedVehicles = new OrderedVehicles();
        orderedVehicles.setModelName(models);
        orderedVehicles.setEngineDesignation(engines);
        orderedVehicles.setTrimName(trims);
        orderedVehicles.setColour("Color");

        return orderedVehicles;
    }
}