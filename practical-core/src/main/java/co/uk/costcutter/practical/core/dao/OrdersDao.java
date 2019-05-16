package co.uk.costcutter.practical.core.dao;


import co.uk.costcutter.practical.core.domain.Orders;

import java.util.List;

public interface OrdersDao {

    List<Orders> fetchOrdersForCustomer(Integer customerId);
}
