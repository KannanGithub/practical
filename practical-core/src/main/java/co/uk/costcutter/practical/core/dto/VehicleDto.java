package co.uk.costcutter.practical.core.dto;

import co.uk.costcutter.practical.core.domain.OrderedVehicles;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class VehicleDto {

    private String model;
    private String color;
    private String engine;
    private String trim;

    public VehicleDto() {}
    public VehicleDto(OrderedVehicles vehicles) {
        this.model = vehicles.getModelName().getModelName();
        this.color = vehicles.getColour();
        this.engine = vehicles.getEngineDesignation().getEngineDesignation();
        this.trim = vehicles.getTrimName().getTrimName();
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getTrim() {
        return trim;
    }

    public String getEngine() {
        return engine;
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
