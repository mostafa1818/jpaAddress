package dao;

import entity.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinFragment;
import sun.rmi.runtime.NewThreadAction;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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




    public  void findEmployeesBySupervisor() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();

        Root<Employee> posRoot = cq.from(Employee.class);
    //    Root<Address> supplierRoot = cq.from(Address.class);

      // CriteriaQuery<Employee> cq3 = cb.createQuery(Employee.class);


        cq.multiselect( posRoot.get(Employee_.ID),posRoot.get(Employee_.SALARY)  );


       TypedQuery<Tuple> query = entityManager.createQuery( cq);

     //   List<Double> Employees = query.getResultList();
      //  return Employees;

      //  List<Employee> Employees = query.getResultList();
        List<Tuple> resultList = entityManager.createQuery(cq).getResultList();

        resultList.forEach(row ->
                System.out.println("sdk  "+ row.get(0)+"  *   "+ row.get(1) ));


    }
    public  void practice10() {



        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //   CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        //    CriteriaQuery<Object[]> cq2 = cb.createQuery(Object[].class);
        //  CriteriaQuery<Address> cq4 = cb.createQuery(Address.class);

        //     CriteriaQuery<Double> cq3 = cb.createQuery(Double.class);
        //     Root<Employee> posRoot = cq2.from(Employee.class);
        CriteriaQuery<Object[]> c1 = cb.createQuery(Object[].class);
        Root<Employee> posRoot = c1.from(Employee.class);
        Root<Address> posRoot2 = c1.from(Address.class);
        Join <Employee,Address> join=posRoot.join("addressSet") ;
        Join <Employee,Address> join2=posRoot2.join("employee") ;
        c1.multiselect( join2.get("salary"));

//
//        Root<Employee> e3 = c1.from(Employee.class);
//        c1.multiselect(join.get("city"));
//
//        Subquery<Long> sq3= c1.subquery(Long.class);
//        Root<Employee> e6 = sq3.from(Employee.class);
//        sq3.select(cb.max(e6.<Long> get("salary")));

//
//
//        Subquery<Long> sq1 = c1.subquery(Long.class);
//        Root<Employee> e4 = sq1.from(Employee.class);
//        Root<Address> e8 = sq1.from(Address.class);
//        sq1.select(cb.max(e4.<Long> get("salary")));
//        sq1.where();
//
//
//
//        Subquery<Long> sq2 = sq1.subquery(Long.class);
//        Root<Employee> e5 = sq2.from(Employee.class);
//        sq2.select(cb.max(e5.<Long> get("salary")));


        // c1.where(cb.equal(e3.<Long> get("salary"), sq1));

        // sq1.where(cb.lessThan(e4.<Long> get("salary"), sq2));
        //  c1.where(cb.equal(e3.<Long> get("salary"), sq1));
        TypedQuery<Object[]> t=entityManager.createQuery(c1);
        //    List<Object[]> employees = entityManager.createQuery(c1).getResultList();
//
//        for (Object[] employee : employees) {
//            System.out.println(employee.getId() +" "+employee.getSalary());
//        }

        //   List<Double> Employees = query.getResultList();
        //  return Employees;



    }

    public  void practice1() {



        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
     //   CriteriaQuery<Tuple> cq = cb.createTupleQuery();
    //    CriteriaQuery<Object[]> cq2 = cb.createQuery(Object[].class);
      //  CriteriaQuery<Address> cq4 = cb.createQuery(Address.class);

   //     CriteriaQuery<Double> cq3 = cb.createQuery(Double.class);
   //     Root<Employee> posRoot = cq2.from(Employee.class);

        CriteriaQuery<Object[]> c1 = cb.createQuery(Object[].class);
      //  CriteriaQuery<Double[]> c3 = cb.createQuery(Double[].class);
        CriteriaQuery<Double> c2 = cb.createQuery(Double.class);
         Root<Employee> posRoot3 = c2.from(Employee.class);
        Root<Employee> posRoot = c1.from(Employee.class);
        Root<Address> posRoot2 = c1.from(Address.class);
      // Root<Address> posRoot4 = c1.from(Address.class);
        Join <Employee,Address> join=posRoot3.join("addressSet") ;
        Join <Employee,Address> join2=posRoot2.join("employee") ;

        c2.select( cb.max(posRoot3.get("salary")));
        c2.groupBy(join.get("city"));

//
//        Root<Employee> e3 = c1.from(Employee.class);
//        c1.multiselect(join.get("city"));
//
//        Subquery<Long> sq3= c1.subquery(Long.class);
//        Root<Employee> e6 = sq3.from(Employee.class);
//        sq3.select(cb.max(e6.<Long> get("salary")));

//
//
        Subquery<Long> sq1 = c1.subquery(Long.class);
        Root<Employee> e4 = sq1.from(Employee.class);
        Root<Address> e8 = sq1.from(Address.class);
        sq1.select(cb.max(e4.<Long> get("salary")));
 //       sq1.where();
//
//
//
//        Subquery<Long> sq2 = sq1.subquery(Long.class);
//        Root<Employee> e5 = sq2.from(Employee.class);
//        sq2.select(cb.max(e5.<Long> get("salary")));


    //   c2.where(cb.equal(join2.<Long> get("salary"), sq1));

       // sq1.where(cb.lessThan(e4.<Long> get("salary"), sq2));
      //  c1.where(cb.equal(e3.<Long> get("salary"), sq1));
    TypedQuery<Double> t=entityManager.createQuery(c2);
   //  List<Object[]> employees = entityManager.createQuery(c1).getResultList();
//
        System.out.println(t.getResultList().get(0));

        //   List<Double> Employees = query.getResultList();
        //  return Employees;



    }



    public  void practice2() {



        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //   CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        //    CriteriaQuery<Object[]> cq2 = cb.createQuery(Object[].class);
        //  CriteriaQuery<Address> cq4 = cb.createQuery(Address.class);

        //     CriteriaQuery<Double> cq3 = cb.createQuery(Double.class);
        //     Root<Employee> posRoot = cq2.from(Employee.class);

        CriteriaQuery<FullName> c1 = cb.createQuery(FullName.class);
        CriteriaQuery<Employee> c3 = cb.createQuery(Employee.class);
        CriteriaQuery<Double> c2 = cb.createQuery(Double.class);
        Root<Employee> posRoot3 = c1.from(Employee.class);
        Root<Employee> posRoot = c3.from(Employee.class);
        Root<Address> posRoot2 = c1.from(Address.class);
        // Root<Address> posRoot4 = c1.from(Address.class);
        Join <Employee,Address> join=posRoot3.join("addressSet") ;
        Join <Employee,Address> join2=posRoot2.join("employee") ;


        c1.select( join2.get("fullName"));


//
//        Root<Employee> e3 = c1.from(Employee.class);
//        c1.multiselect(join.get("city"));
//
//        Subquery<Long> sq3= c1.subquery(Long.class);
//        Root<Employee> e6 = sq3.from(Employee.class);
//        sq3.select(cb.max(e6.<Long> get("salary")));

//
//



        Subquery<Double> sq1 = c1.subquery(Double.class);
        Root<Employee> e4 = sq1.from(Employee.class);
        Root<Address> e8 = sq1.from(Address.class);
        sq1.select(cb.max(e4.<Double> get("salary")));
        sq1.where();
        sq1.groupBy(join.get("city"));
//
//
//
//        Subquery<Long> sq2 = sq1.subquery(Long.class);
//        Root<Employee> e5 = sq2.from(Employee.class);
//        sq2.select(cb.max(e5.<Long> get("salary")));

        Predicate predicate = cb.and(

                cb.equal(join2.<Double> get("salary"), sq1)
        );
        Predicate predicate2 = cb.and(predicate,
                cb.equal(join.<Integer> get("employee").get("id"), join2.<Integer> get("id"))

        );


        c1.where(predicate2);
        c1.distinct( true);
        //c1.where(cb.equal(posRoot3.<Integer> get("id"), 6));
        // sq1.where(cb.lessThan(e4.<Long> get("salary"), sq2));
        //  c1.where(cb.equal(e3.<Long> get("salary"), sq1));
            TypedQuery<FullName> t=entityManager.createQuery(c1);
        //  List<Object[]> employees = entityManager.createQuery(c1).getResultList();
//


    //    System.out.println(t.getResultList());
          List<FullName> employees = entityManager.createQuery(c1).getResultList();
     //   System.out.println(employees.getFirstName()+" "+employees.getLastName() );

       for (FullName employee : employees) {
           System.out.println(employee.getFirstName()+" "+employee.getLastName() );
       }
        //   List<Double> Employees = query.getResultList();
        //  return Employees;



    }








    public  void practice3( Long     postalCode) {



        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        //   CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        //    CriteriaQuery<Object[]> cq2 = cb.createQuery(Object[].class);
        //  CriteriaQuery<Address> cq4 = cb.createQuery(Address.class);

        //     CriteriaQuery<Double> cq3 = cb.createQuery(Double.class);
        //     Root<Employee> posRoot = cq2.from(Employee.class);

        CriteriaQuery<FullName> c1 = cb.createQuery(FullName.class);
        CriteriaQuery<Employee> c3 = cb.createQuery(Employee.class);
        CriteriaQuery<PhoneNumber> c2 = cb.createQuery(PhoneNumber.class);

        Root<Employee> posRoot3 = c1.from(Employee.class);
        Root<Employee> posRoot = c3.from(Employee.class);
        Root<Address> posRoot2 = c1.from(Address.class);

        // Root<Address> posRoot4 = c1.from(Address.class);
        Join <Employee,Address> join=posRoot3.join("addressSet") ;
        Join <Employee,Address> join2=posRoot2.join("employee") ;


        c1.select( join2.get("fullName"));


        Predicate predicate = cb.and(

                cb.equal(join.get(NewAddress_.PostalCode),postalCode)
        );
        Predicate predicate2 = cb.and(predicate,
                cb.equal(join.<Integer> get("employee").get("id"), join2.<Integer> get("id"))

        );
        c1.where(predicate2);
        c1.distinct( true);
        TypedQuery<FullName> t=entityManager.createQuery(c1);

        List<FullName> employees = entityManager.createQuery(c1).getResultList();


        for (FullName employee : employees) {
            System.out.println(employee.getFirstName()+" "+employee.getLastName() );
        }

    }

    public  void practice4( String telNumber) {



        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FullName > criteriaQuery = cb.createQuery(FullName .class);
        Root<Employee> fromEmployee = criteriaQuery.from(Employee.class);
        Join<Employee,Address> empAdd = fromEmployee.join("addressSet");
        Join<Address,PhoneNumber> addPhone = empAdd.join("phoneNumberset");

        criteriaQuery.select(fromEmployee.get("fullName"))
                .where(cb.equal(addPhone.get("telNumber"),telNumber))
                .distinct(true);
        TypedQuery<FullName > typedQuery = entityManager.createQuery(criteriaQuery);
        List<FullName > employees = entityManager.createQuery(criteriaQuery).getResultList();

        for (FullName employee : employees) {
            System.out.println(employee.getFirstName()+" "+employee.getLastName());
        }

    }

    public  void practice5( String telNumber) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<String> c1 = cb.createQuery(String.class);
        CriteriaQuery<PhoneNumber> c2 = cb.createQuery(PhoneNumber.class);

        Root<Address> posRoot1 = c1.from(Address.class);
        Root<PhoneNumber> posRoot2 = c1.from(PhoneNumber.class);

        Join <Address,PhoneNumber> join3=posRoot2.join("phoneNumber" ) ;
        Join <PhoneNumber,Address> join4=posRoot1.join("phoneNumber" ) ;

        c1.select(join3.get("postalCode"));
        TypedQuery<String> t=entityManager.createQuery(c1);

        String employees = entityManager.createQuery(c1).getResultList().get(0);
            System.out.println(employees );
    }

    public  List<Employee>  findEmployeesBySupervisor4() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> posRoot = cq.from(Employee.class);
        Root<Address> supplierRoot = cq.from(Address.class);
        cq.select(posRoot);
        Predicate predicate = cb.and(

             cb.like(supplierRoot.get("city"),"london"),
              cb.equal(supplierRoot.get("employee").get("empCode") ,posRoot.get("empCode"))

                   );

         cq.where(    predicate);
        TypedQuery<Employee> query = entityManager.createQuery(cq);
        System.out.println(cq.getParameters());
        List<Employee> Employees = query.getResultList();
        return Employees;

    }





    }
