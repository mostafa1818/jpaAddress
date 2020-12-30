package dao;

import entity.PhoneNumber;


import javax.persistence.EntityManager;

public class PhoneNumberDao extends AbstractJpaDao<PhoneNumber,Integer> {
    public  PhoneNumberDao (EntityManager entityManager)
    {
        super(entityManager);
    }

    @Override
    public Class<PhoneNumber> getEntityClass() {
        return PhoneNumber.class;
    }
}
