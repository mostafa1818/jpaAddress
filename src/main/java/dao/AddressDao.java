package dao;

import entity.Address;
import entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AddressDao extends AbstractJpaDao<Address,Integer> {
    public AddressDao (EntityManager entityManager)
    {
        super(entityManager);
    }

    @Override
    public Class<Address> getEntityClass() {
        return Address.class;
    }
    public void loadCity()
    {
        TypedQuery<Address> typedQuery=  super.entityManager.createQuery
                ("select  e from Address e  where e.city=:newcity",Address.class );
        typedQuery.setParameter("newcity","london");
        System.out.println(typedQuery.getResultList());


    }
}
