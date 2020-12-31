package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    public FullName fullName;

    @Column(nullable = true,unique = true)//problem
    private long    empCode;
   // @Check(constraints = "salary > 0")

    @Column( columnDefinition = " Double  CHECK ( salary>0)")
    private Double  salary;

    @ManyToMany(mappedBy = "employee")
    private  Set<Address> addressSet;




}
