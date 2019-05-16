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
@Table(name = "trim_equipment")
@NamedQueries({
        @NamedQuery(name = "TrimEquipment.findAll", query = "SELECT t FROM TrimEquipment t"),
        @NamedQuery(name = "TrimEquipment.findByTrimName", query = "SELECT t FROM TrimEquipment t WHERE t.trimEquipmentPK.trimName = :trimName"),
        @NamedQuery(name = "TrimEquipment.findByEffectiveDate", query = "SELECT t FROM TrimEquipment t WHERE t.trimEquipmentPK.effectiveDate = :effectiveDate"),
        @NamedQuery(name = "TrimEquipment.findByWheelType", query = "SELECT t FROM TrimEquipment t WHERE t.wheelType = :wheelType"),
        @NamedQuery(name = "TrimEquipment.findByInfotainmentType", query = "SELECT t FROM TrimEquipment t WHERE t.infotainmentType = :infotainmentType"),
        @NamedQuery(name = "TrimEquipment.findByHeadlightType", query = "SELECT t FROM TrimEquipment t WHERE t.headlightType = :headlightType"),
        @NamedQuery(name = "TrimEquipment.findByUpholsteryType", query = "SELECT t FROM TrimEquipment t WHERE t.upholsteryType = :upholsteryType")})
public class TrimEquipment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TrimEquipmentPK trimEquipmentPK;
    @Basic(optional = false)
    @Column(name = "wheel_type")
    private String wheelType;
    @Basic(optional = false)
    @Column(name = "infotainment_type")
    private String infotainmentType;
    @Basic(optional = false)
    @Column(name = "headlight_type")
    private String headlightType;
    @Basic(optional = false)
    @Column(name = "upholstery_type")
    private String upholsteryType;
    @JoinColumn(name = "trim_name", referencedColumnName = "trim_name", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trims trims;

    public TrimEquipment() {
    }

    public TrimEquipment(TrimEquipmentPK trimEquipmentPK) {
        this.trimEquipmentPK = trimEquipmentPK;
    }

    public TrimEquipment(TrimEquipmentPK trimEquipmentPK, String wheelType, String infotainmentType, String headlightType, String upholsteryType) {
        this.trimEquipmentPK = trimEquipmentPK;
        this.wheelType = wheelType;
        this.infotainmentType = infotainmentType;
        this.headlightType = headlightType;
        this.upholsteryType = upholsteryType;
    }

    public TrimEquipment(String trimName, Date effectiveDate) {
        this.trimEquipmentPK = new TrimEquipmentPK(trimName, effectiveDate);
    }

    public TrimEquipmentPK getTrimEquipmentPK() {
        return trimEquipmentPK;
    }

    public void setTrimEquipmentPK(TrimEquipmentPK trimEquipmentPK) {
        this.trimEquipmentPK = trimEquipmentPK;
    }

    public String getWheelType() {
        return wheelType;
    }

    public void setWheelType(String wheelType) {
        this.wheelType = wheelType;
    }

    public String getInfotainmentType() {
        return infotainmentType;
    }

    public void setInfotainmentType(String infotainmentType) {
        this.infotainmentType = infotainmentType;
    }

    public String getHeadlightType() {
        return headlightType;
    }

    public void setHeadlightType(String headlightType) {
        this.headlightType = headlightType;
    }

    public String getUpholsteryType() {
        return upholsteryType;
    }

    public void setUpholsteryType(String upholsteryType) {
        this.upholsteryType = upholsteryType;
    }

    public Trims getTrims() {
        return trims;
    }

    public void setTrims(Trims trims) {
        this.trims = trims;
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

