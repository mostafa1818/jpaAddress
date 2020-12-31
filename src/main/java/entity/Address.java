package entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.util.Set;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int      id;

    @Column
    long     postalCode;

    @Column
    String   postalAddress;

    @Column
    String   city;

    @ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="fk_Employee")
    private Employee employee;

    @ManyToMany( targetEntity = PhoneNumber.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_PhoneNumber", referencedColumnName = "id")
    private Set<PhoneNumber> phoneNumber;





}
