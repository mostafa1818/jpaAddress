package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    public FullName fullName;

    @Column(nullable = true,unique = true)//problem
    public Long    empCode;
   // @Check(constraints = "salary > 0")

    @Column( columnDefinition = " Double  CHECK ( salary>0)")
    private Double  salary;

    @OneToMany(mappedBy = "employee")
    public  Set<Address> addressSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public long getEmpCode() {
        return empCode;
    }

    public void setEmpCode(long empCode) {
        this.empCode = empCode;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }
}
