package dao;

import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.List;

public class EmployeeDao extends AbstractJpaDao<Employee,Integer> {
    public  EmployeeDao (EntityManager entityManager)
    {
        super(entityManager);
    }

    @Override
    public Class<Employee> getEntityClass() {
        return Employee.class;
    }
    public Double loadSalary()
    {
        TypedQuery<Employee> typedQuery=  super.entityManager.createQuery
                ("select    e from Employee e  where e.salary in (" +
                        "select    MAX (e1.salary) from Employee e1  )",Employee.class ).setFirstResult(1);

        System.out.println();
        return typedQuery.getSingleResult().getSalary();

    }
    public String[] loadFirstSalary()
    {
        TypedQuery<Employee> typedQuery=  super.entityManager.createQuery
                ("select    e from Employee e  where e.salary in (" +
                        "select    MAX (e1.salary) from Employee e1  )",Employee.class ).setFirstResult(1);

        System.out.println();
        String[] temp=new String[2];

        temp[0]=typedQuery.getSingleResult().getFullName().getFirstName();
        temp[1]=typedQuery.getSingleResult().getFullName().getLastName();
        return temp;
    }



}
