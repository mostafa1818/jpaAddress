package entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@StaticMetamodel(Address.class)
public   class NewAddress_ {
    public static volatile SingularAttribute<Address, String> postalAddress;
    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, Long> postalCode;
    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, Employee> employee;
    public static volatile SingularAttribute<Address, Set<PhoneNumber>> phoneNumber;

    public static final String PostalAddress = "postalAddress";
    public static final String PostalCode = "postalCode";
    public static final String ID = "id";
    public static final String City = "city";
    public static final String Employee = "employee";
    public static final String PhoneNumber = "phoneNumber";
    public NewAddress_() {
    }
}