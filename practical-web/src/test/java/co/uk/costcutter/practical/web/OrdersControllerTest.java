package co.uk.costcutter.practical.web;

import co.uk.costcutter.practical.core.dto.OrderDto;
import co.uk.costcutter.practical.core.exception.FatalException;
import co.uk.costcutter.practical.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrdersControllerTest {

    private static final HttpStatus STATUS_OK = HttpStatus.OK;
    private static final HttpStatus STATUS_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final HttpStatus STATUS_EXPECTATION_FAILED = HttpStatus.EXPECTATION_FAILED;

    @Mock
    private OrderService mockOrderService;

    @InjectMocks
    private OrdersController ordersController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldFetchAllOrdersForCustomer() throws Exception {

       OrderDto orderDto = new OrderDto();
       List<OrderDto> orderDtos = Arrays.asList(orderDto);
       //given
       given(mockOrderService.getAllOrdersForCustomer("999")).willReturn(orderDtos);

       //when
       ResponseEntity<Object> response = ordersController.getAllOrdersForCustomer("999");
       Map<String, Object> responseMap = (Map<String, Object>) response.getBody();

       //then
       assertEquals(STATUS_OK, response.getStatusCode());
       assertTrue(orderDtos.size() == 1);
       assertEquals(orderDtos, responseMap.get("Orders"));
       verify(mockOrderService).getAllOrdersForCustomer("999");
       verifyNoMoreInteractions(mockOrderService);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhileFetchingOrdersForCustomer() throws Exception {
        //given
        given(mockOrderService.getAllOrdersForCustomer("Illegal Argument")).willThrow(IllegalArgumentException.class);

        //when
        ResponseEntity<Object> response = ordersController.getAllOrdersForCustomer("Illegal Argument");
        //then
        assertEquals(STATUS_EXPECTATION_FAILED, response.getStatusCode());
        verify(mockOrderService).getAllOrdersForCustomer("Illegal Argument");
        verifyNoMoreInteractions(mockOrderService);
    }

    @Test
    public void shouldVerifyIllegalArgumentExceptionMessageWhileFetchingOrdersForCustomer() throws Exception {
        //given
        given(mockOrderService.getAllOrdersForCustomer("Illegal Argument")).willThrow(IllegalArgumentException.class);

        //when
        ResponseEntity<Object> response = ordersController.getAllOrdersForCustomer("Illegal Argument");
        //then
        assertEquals(STATUS_EXPECTATION_FAILED, response.getStatusCode());
        assertEquals("Customer Id is invalid", response.getBody());
        verify(mockOrderService).getAllOrdersForCustomer("Illegal Argument");
        verifyNoMoreInteractions(mockOrderService);
    }

    @Test
    public void shouldThrowExceptionWhileFetchingOrdersForCustomer() throws Exception {
        //given
        given(mockOrderService.getAllOrdersForCustomer(null)).willThrow(FatalException.class);

        //when
        ResponseEntity<Object> response = ordersController.getAllOrdersForCustomer(null);
        //then
        assertEquals(STATUS_ERROR, response.getStatusCode());
        verify(mockOrderService).getAllOrdersForCustomer(null);
        verifyNoMoreInteractions(mockOrderService);
    }
}