package co.uk.costcutter.practical.core.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Kannan Gnanasigamani
 */
@Entity
@Table(name = "trims")
@NamedQueries({
        @NamedQuery(name = "Trims.findAll", query = "SELECT t FROM Trims t"),
        @NamedQuery(name = "Trims.findByTrimName", query = "SELECT t FROM Trims t WHERE t.trimName = :trimName"),
        @NamedQuery(name = "Trims.findByAdditionalCost", query = "SELECT t FROM Trims t WHERE t.additionalCost = :additionalCost")})
public class Trims implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trim_name")
    private String trimName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "additional_cost")
    private BigDecimal additionalCost;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trimName", fetch = FetchType.LAZY)
    private List<OrderedVehicles> orderedVehiclesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trims", fetch = FetchType.LAZY)
    private List<TrimEquipment> trimEquipmentList;

    public Trims() {
    }

    public Trims(String trimName) {
        this.trimName = trimName;
    }

    public Trims(String trimName, BigDecimal additionalCost) {
        this.trimName = trimName;
        this.additionalCost = additionalCost;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public BigDecimal getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(BigDecimal additionalCost) {
        this.additionalCost = additionalCost;
    }

    public List<OrderedVehicles> getOrderedVehiclesList() {
        return orderedVehiclesList;
    }

    public void setOrderedVehiclesList(List<OrderedVehicles> orderedVehiclesList) {
        this.orderedVehiclesList = orderedVehiclesList;
    }

    public List<TrimEquipment> getTrimEquipmentList() {
        return trimEquipmentList;
    }

    public void setTrimEquipmentList(List<TrimEquipment> trimEquipmentList) {
        this.trimEquipmentList = trimEquipmentList;
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

