package co.uk.costcutter.practical.service.impl;

import co.uk.costcutter.practical.core.dao.OrdersDao;
import co.uk.costcutter.practical.core.domain.Orders;
import co.uk.costcutter.practical.core.dto.OrderDto;
import co.uk.costcutter.practical.service.OrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.cache.annotation.Cacheable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOG = Logger.getLogger(OrderServiceImpl.class);
    private static final String NULL_CUSTOMER_ID = "CustomerId cannot be null";

    @Autowired
    private OrdersDao ordersDao;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "co.uk.costcutter.practical.ordersByCustomerId")

    public List<OrderDto> getAllOrdersForCustomer(String customerId) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Enter: getAllOrdersForCustomer()");
        }

        Integer customer = Integer.valueOf(customerId);

        List<Orders> distinctOrders = new ArrayList<>();
        List<Integer> distinctOrderNumbers = new ArrayList<>();
        List<Orders> allOrders = ordersDao.fetchOrdersForCustomer(customer);

        for (Orders order : allOrders) {
            if (!distinctOrderNumbers.contains(order.getOrderNumber())) {
                distinctOrders.add(order);
                distinctOrderNumbers.add(order.getOrderNumber());
            }
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Exit: getAllOrdersForCustomer()");
        }

        return distinctOrders
                .stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
    }
}
