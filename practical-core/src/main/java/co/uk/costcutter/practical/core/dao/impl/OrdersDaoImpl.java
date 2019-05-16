package co.uk.costcutter.practical.core.dao.impl;

import co.uk.costcutter.practical.core.dao.BaseDao;
import co.uk.costcutter.practical.core.dao.OrdersDao;
import co.uk.costcutter.practical.core.domain.Orders;
import co.uk.costcutter.practical.core.exception.FatalException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrdersDaoImpl extends BaseDao implements OrdersDao {

    @Override
    public List<Orders> fetchOrdersForCustomer(Integer customerId) {
        if(customerId == null) {
            throw new FatalException("CustomerId cannot be null");
        }

        Map<String, Object> params = new HashMap<>();
        params.put("customerNumber", customerId);
        return namedSearch("Orders.findByCustomerId", params);
    }
}
