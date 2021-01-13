package entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Set;

@StaticMetamodel(Employee.class)
public abstract class Employee_ {
    public static volatile SingularAttribute<Employee, FullName>  fullName;
    public static volatile SingularAttribute<Employee, Integer> id;
    public static volatile SingularAttribute<Employee, Long> empCode;
    public static volatile SingularAttribute<Employee, Double> salary;
    public static volatile SingularAttribute<Employee, Set<Address>> addressSet;
    public static final String FullName = "fullName";
    public static final String ID = "id";
    public static final String EmpCode = "empCode";
    public static final String SALARY = "salary";
    public static final String AddressSet = "addressSet";
    public Employee_() {
    }
}