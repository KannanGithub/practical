package co.uk.costcutter.practical.service;

import co.uk.costcutter.practical.core.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrdersForCustomer(String customerId);
}
