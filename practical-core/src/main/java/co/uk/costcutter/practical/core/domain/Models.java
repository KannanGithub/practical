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
@Table(name = "models")
@NamedQueries({
        @NamedQuery(name = "Models.findAll", query = "SELECT m FROM Models m"),
        @NamedQuery(name = "Models.findByModelName", query = "SELECT m FROM Models m WHERE m.modelName = :modelName"),
        @NamedQuery(name = "Models.findByBaseCost", query = "SELECT m FROM Models m WHERE m.baseCost = :baseCost"),
        @NamedQuery(name = "Models.findByAverageLeadTime", query = "SELECT m FROM Models m WHERE m.averageLeadTime = :averageLeadTime")})
public class Models implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "model_name")
    private String modelName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "base_cost")
    private BigDecimal baseCost;
    @Basic(optional = false)
    @Column(name = "average_lead_time")
    private int averageLeadTime;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modelName", fetch = FetchType.LAZY)
    private List<OrderedVehicles> orderedVehiclesList;

    public Models() {
    }

    public Models(String modelName) {
        this.modelName = modelName;
    }

    public Models(String modelName, BigDecimal baseCost, int averageLeadTime) {
        this.modelName = modelName;
        this.baseCost = baseCost;
        this.averageLeadTime = averageLeadTime;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(BigDecimal baseCost) {
        this.baseCost = baseCost;
    }

    public int getAverageLeadTime() {
        return averageLeadTime;
    }

    public void setAverageLeadTime(int averageLeadTime) {
        this.averageLeadTime = averageLeadTime;
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
