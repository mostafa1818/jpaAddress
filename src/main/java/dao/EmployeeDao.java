package dao;

import entity.Address;
import entity.Employee;
import entity.FullName;
import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDao extends AbstractJpaDao<Employee,Integer> {
    private Object Address_;

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
        FullName fullName=typedQuery.getSingleResult().getFullName();

        temp[0]=fullName.getFirstName();
        temp[1]=fullName.getLastName();
        return temp;
    }

    public List<Employee> loadBycriteria(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        criteriaQuery.select(criteriaQuery.from(Employee.class));
        Query query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }



    public  List<Employee>  findEmployeesBySupervisor() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> q = criteriaBuilder.createQuery(Employee.class);
        CriteriaQuery<Address> q2 = criteriaBuilder.createQuery(Address.class);
        Root<Employee> c1 = q.from(Employee.class);
        Root<Address> c2 = q2.from(Address.class);
        Root<Employee> fromEmployee = q.from(Employee.class);
         q.multiselect(c1, c2).where(criteriaBuilder.in(fromEmployee.get("salary")).value(1));
      //  q.select(c1).where(criteriaBuilder.in(fromEmployee.get("salary")).value(1));

        TypedQuery<Employee> typedQuery= entityManager.createQuery(q);
        return typedQuery.getResultList();

    }

    public  List<Employee>  findEmployeesBySupervisor1() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> q = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> c1 = q.from(Employee.class);



        Fetch<Employee, Address> productItemFetch = c1.fetch("addressSet", JoinType.LEFT);
        Join<Employee, Address> productItemJoin = (Join<Employee, Address>) productItemFetch;

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.in( productItemJoin.get( "postalCode" )).value(54545004));

        q.where(
                criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]))
        );

        TypedQuery<Employee> typedQuery= entityManager.createQuery(q);



        return typedQuery.getResultList();
    }
    public  List<Employee>  findEmployeesBySupervisor2() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Employee> cq = builder.createQuery(Employee.class);
        Root<Employee> root = cq.from(Employee.class);
//        Join<Employee, Address> join = root.join("addressSet");
//
//        join.on(builder.equal(root.get("id"), join.get("employee")));
//        cq.multiselect(join);
//        List<Employee> resultList = entityManager.createQuery(cq).getResultList();




        root.fetch("addressSet");
        cq.select(root);
        System.out.println(cq);
        List<Employee> resultList = entityManager.createQuery(cq).getResultList();
        return resultList;
    }

}
