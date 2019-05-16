package co.uk.costcutter.practical.core.dto;

import co.uk.costcutter.practical.core.domain.OrderedVehicles;
import org.junit.Test;

import static co.uk.costcutter.practical.core.dto.OrderDtoTest.buildOrderedVehicles;
import static org.junit.Assert.*;

public class VehicleDtoTest {

    @Test
    public void shouldBuildVehicleDto(){
        //given
        //when
        VehicleDto vehicleDto = new VehicleDto(buildOrderedVehicles());
        //then
        assertEquals("Color", vehicleDto.getColor());
        assertEquals("Engine Name", vehicleDto.getEngine());
        assertEquals("Model Name", vehicleDto.getModel());
        assertEquals("Trim", vehicleDto.getTrim());
    }

    @Test
    public void equalsShouldReturnTrueForMatchingVehicleDtos() {
        OrderedVehicles orderedVehicles = buildOrderedVehicles();
        VehicleDto vehicleDto1 = new VehicleDto(orderedVehicles);
        VehicleDto vehicleDto2 = new VehicleDto(orderedVehicles);

        assertEquals(true, vehicleDto1.equals(vehicleDto2));
    }

    @Test
    public void shouldBuildToString() {
        VehicleDto vehicleDto1 = new VehicleDto();
        assertEquals("VehicleDto[model=<null>,color=<null>,engine=<null>,trim=<null>]", vehicleDto1.toString());
    }

    @Test
    public void equalsShouldReturnFalseForNonMatchingVehicleDto() {
        OrderedVehicles orderedVehicles = buildOrderedVehicles();
        VehicleDto vehicleDto1 = new VehicleDto(orderedVehicles);
        assertEquals(false, vehicleDto1.equals(orderedVehicles));
    }
}

