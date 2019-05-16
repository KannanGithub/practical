package co.uk.costcutter.practical.web;

import co.uk.costcutter.practical.core.dto.OrderDto;
import co.uk.costcutter.practical.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class OrdersController {

    private static final Logger LOG = Logger.getLogger(OrdersController.class);
    private static final String CUSTOMER_ID = "customerId";
    private static final String GET_ALL_ORDERS_FOR_CUSTOMER = "/orders/{customerId}";

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = GET_ALL_ORDERS_FOR_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<Object> getAllOrdersForCustomer(@PathVariable(CUSTOMER_ID) String customerId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Enter: getAllOrdersForCustomer(customerId = [" + customerId + "])");
        }

        Map<String, Object> response = new HashMap<>();

        try {
            List<OrderDto> allOrdersForCustomer = orderService.getAllOrdersForCustomer(customerId);
            response.put("Orders", allOrdersForCustomer);

        }catch(IllegalArgumentException iex) {
            String exceptionMessage = "Customer Id is invalid";
            return new ResponseEntity<>(exceptionMessage, HttpStatus.EXPECTATION_FAILED);
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Exit: getAllOrdersForCustomer(customerId = [" + customerId + "])");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

