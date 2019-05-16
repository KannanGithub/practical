package co.uk.costcutter.practical.core.domain;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Kannan Gnanasigamani
 */
@Entity
@Table(name = "ordered_vehicles")
@NamedQueries({
        @NamedQuery(name = "OrderedVehicles.findAll", query = "SELECT o FROM OrderedVehicles o"),
        @NamedQuery(name = "OrderedVehicles.findByVehicleNumber", query = "SELECT o FROM OrderedVehicles o WHERE o.vehicleNumber = :vehicleNumber"),
        @NamedQuery(name = "OrderedVehicles.findByColour", query = "SELECT o FROM OrderedVehicles o WHERE o.colour = :colour"),
        @NamedQuery(name = "OrderedVehicles.findByExpectedDeliveryDate", query = "SELECT o FROM OrderedVehicles o WHERE o.expectedDeliveryDate = :expectedDeliveryDate"),
        @NamedQuery(name = "OrderedVehicles.findByActualDeliveryDate", query = "SELECT o FROM OrderedVehicles o WHERE o.actualDeliveryDate = :actualDeliveryDate")})
public class OrderedVehicles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vehicle_number")
    private Integer vehicleNumber;
    @Basic(optional = false)
    @Column(name = "colour")
    private String colour;
    @Basic(optional = false)
    @Column(name = "expected_delivery_date")
    @Temporal(TemporalType.DATE)
    private Date expectedDeliveryDate;
    @Column(name = "actual_delivery_date")
    @Temporal(TemporalType.DATE)
    private Date actualDeliveryDate;
    @JoinColumn(name = "engine_designation", referencedColumnName = "engine_designation")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Engines engineDesignation;
    @JoinColumn(name = "model_name", referencedColumnName = "model_name")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Models modelName;
    @JoinColumn(name = "order_number", referencedColumnName = "order_number")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Orders orderNumber;
    @JoinColumn(name = "trim_name", referencedColumnName = "trim_name")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trims trimName;

    public OrderedVehicles() {
    }

    public OrderedVehicles(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public OrderedVehicles(Integer vehicleNumber, String colour, Date expectedDeliveryDate) {
        this.vehicleNumber = vehicleNumber;
        this.colour = colour;
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Integer getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(Integer vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public Date getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(Date actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public Engines getEngineDesignation() {
        return engineDesignation;
    }

    public void setEngineDesignation(Engines engineDesignation) {
        this.engineDesignation = engineDesignation;
    }

    public Models getModelName() {
        return modelName;
    }

    public void setModelName(Models modelName) {
        this.modelName = modelName;
    }

    public Orders getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Orders orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Trims getTrimName() {
        return trimName;
    }

    public void setTrimName(Trims trimName) {
        this.trimName = trimName;
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

