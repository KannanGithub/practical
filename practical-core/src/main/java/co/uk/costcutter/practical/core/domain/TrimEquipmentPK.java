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
@Embeddable
public class TrimEquipmentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "trim_name")
    private String trimName;
    @Basic(optional = false)
    @Column(name = "effective_date")
    @Temporal(TemporalType.DATE)
    private Date effectiveDate;

    public TrimEquipmentPK() {
    }

    public TrimEquipmentPK(String trimName, Date effectiveDate) {
        this.trimName = trimName;
        this.effectiveDate = effectiveDate;
    }

    public String getTrimName() {
        return trimName;
    }

    public void setTrimName(String trimName) {
        this.trimName = trimName;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
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
