package co.uk.costcutter.practical.core.dto;

import co.uk.costcutter.practical.core.domain.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDtoTest {

    @Test
    public void shouldBuildOrderDto(){
        Orders order = new Orders();
        List<OrderedVehicles> orderedVehicleList = new ArrayList<>();
        orderedVehicleList.add(buildOrderedVehicles());
        order.setOrderNumber(100);
        order.setOrderedVehiclesList(orderedVehicleList);
        VehicleDto expectedVehicleDto = new VehicleDto(buildOrderedVehicles());
        //given

        //when
        OrderDto orderDto = new OrderDto(order);
        //then
        assertEquals(expectedVehicleDto, orderDto.getOrderedVehicles().get(0));
        assertEquals(100, orderDto.getOrderNumber().intValue());
    }

    @Test
    public void equalsShouldReturnTrueForMatchingOrderDto() {
        Orders order = new Orders();
        List<OrderedVehicles> orderedVehicleList = new ArrayList<>();
        orderedVehicleList.add(buildOrderedVehicles());
        order.setOrderedVehiclesList(orderedVehicleList);
        OrderDto orderDto1 = new OrderDto(order);
        OrderDto orderDto2 = new OrderDto(order);
        assertEquals(true, orderDto1.equals(orderDto2));
    }

    @Test
    public void shouldGetToString() {
        OrderDto orderDto1 = new OrderDto();
        assertEquals("OrderDto[orderNumber=<null>,orderedVehicles=<null>]", orderDto1.toString());
    }

    @Test
    public void equalsShouldReturnFalseForNonMatchingVehicleDto() {
        OrderedVehicles orderedVehicles = buildOrderedVehicles();
        OrderDto orderDto1 = new OrderDto();
        assertEquals(false, orderDto1.equals(orderedVehicles));
    }

    public static OrderedVehicles buildOrderedVehicles() {

        Models models = new Models("Model Name");
        Engines engines = new Engines("Engine Name");
        Trims trims = new Trims("Trim");
        OrderedVehicles orderedVehicles = new OrderedVehicles();
        orderedVehicles.setModelName(models);
        orderedVehicles.setEngineDesignation(engines);
        orderedVehicles.setTrimName(trims);
        orderedVehicles.setColour("Color");

        return orderedVehicles;
    }
}