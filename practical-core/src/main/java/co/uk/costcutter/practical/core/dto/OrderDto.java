package co.uk.costcutter.practical.core.dto;

import co.uk.costcutter.practical.core.domain.Orders;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDto {

    private Integer orderNumber;
    private List<VehicleDto> orderedVehicles;

    public OrderDto() {}
    public OrderDto(Orders order) {
        this.orderNumber = order.getOrderNumber();
        this.orderedVehicles = buildVehicleDtos(order);
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public List<VehicleDto> getOrderedVehicles() {
        return orderedVehicles;
    }

    private List<VehicleDto> buildVehicleDtos(Orders order) {

        return order.getOrderedVehiclesList()
                .stream()
                .map(VehicleDto::new)
                .collect(Collectors.toList());
    }

    public int hashcode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
