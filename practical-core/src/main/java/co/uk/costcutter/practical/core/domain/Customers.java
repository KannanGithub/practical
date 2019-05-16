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
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c"),
        @NamedQuery(name = "Customers.findByCustomerNumber", query = "SELECT c FROM Customers c WHERE c.customerNumber = :customerNumber"),
        @NamedQuery(name = "Customers.findByForename", query = "SELECT c FROM Customers c WHERE c.forename = :forename"),
        @NamedQuery(name = "Customers.findBySurname", query = "SELECT c FROM Customers c WHERE c.surname = :surname"),
        @NamedQuery(name = "Customers.findByTelephoneNumber", query = "SELECT c FROM Customers c WHERE c.telephoneNumber = :telephoneNumber")})
public class Customers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "customer_number")
    private Integer customerNumber;
    @Basic(optional = false)
    @Column(name = "forename")
    private String forename;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "telephone_number")
    private String telephoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerNumber", fetch = FetchType.LAZY)
    private List<Orders> ordersList;

    public Customers() {
    }

    public Customers(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Customers(Integer customerNumber, String forename, String surname, String telephoneNumber) {
        this.customerNumber = customerNumber;
        this.forename = forename;
        this.surname = surname;
        this.telephoneNumber = telephoneNumber;
    }

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
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

