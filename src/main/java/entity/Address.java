package entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.Set;


@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer      id;

    @Column
    Long     postalCode;

    @Column
    String   postalAddress;

    @Column
    String   city;

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="fk_Employee")
    public Employee employee;

    @ManyToMany( fetch=FetchType.EAGER,targetEntity = PhoneNumber.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_PhoneNumber", referencedColumnName = "id")
    private Set<PhoneNumber> phoneNumberset;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(long postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Set<PhoneNumber> getPhoneNumber() {
        return phoneNumberset;
    }

    public void setPhoneNumber(Set<PhoneNumber> phoneNumber) {
        this.phoneNumberset = phoneNumber;
    }
}
