package co.uk.costcutter.practical.core.domain;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Kannan Gnanasigamani
 */
@Entity
@Table(name = "engines")
@NamedQueries({
        @NamedQuery(name = "Engines.findAll", query = "SELECT e FROM Engines e"),
        @NamedQuery(name = "Engines.findByEngineDesignation", query = "SELECT e FROM Engines e WHERE e.engineDesignation = :engineDesignation"),
        @NamedQuery(name = "Engines.findByCapacity", query = "SELECT e FROM Engines e WHERE e.capacity = :capacity"),
        @NamedQuery(name = "Engines.findByFuelType", query = "SELECT e FROM Engines e WHERE e.fuelType = :fuelType")})
public class Engines implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "engine_designation")
    private String engineDesignation;
    @Basic(optional = false)
    @Column(name = "capacity")
    private int capacity;
    @Basic(optional = false)
    @Column(name = "fuel_type")
    private String fuelType;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engineDesignation", fetch = FetchType.LAZY)
    private List<OrderedVehicles> orderedVehiclesList;

    public Engines() {
    }

    public Engines(String engineDesignation) {
        this.engineDesignation = engineDesignation;
    }

    public Engines(String engineDesignation, int capacity, String fuelType) {
        this.engineDesignation = engineDesignation;
        this.capacity = capacity;
        this.fuelType = fuelType;
    }

    public String getEngineDesignation() {
        return engineDesignation;
    }

    public void setEngineDesignation(String engineDesignation) {
        this.engineDesignation = engineDesignation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public List<OrderedVehicles> getOrderedVehiclesList() {
        return orderedVehiclesList;
    }

    public void setOrderedVehiclesList(List<OrderedVehicles> orderedVehiclesList) {
        this.orderedVehiclesList = orderedVehiclesList;
    }

//    public int hashcode() {
//        return HashCodeBuilder.reflectionHashCode(this);
//    }
//
//    public boolean equals(Object obj) {
//        return EqualsBuilder.reflectionEquals(this, obj);
//    }
//
//    public String toString() {
//        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
//    }
}

