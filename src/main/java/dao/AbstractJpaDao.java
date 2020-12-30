package dao;

import javax.persistence.EntityManager;

public abstract class AbstractJpaDao <T,U>{

    EntityManager entityManager;
    public AbstractJpaDao (EntityManager entityManager)
    {
        this.entityManager=entityManager;
    }
    public void save(T entity )
    {entityManager.persist(entity);}
    public T load (U id )
    {
        return entityManager.find(getEntityClass(),id );
    }
    public void delete (T entity)
    {
        entityManager.remove(entity);
    }
    public void update(T entity)
    {
        entityManager.merge(entity);
    }



    public abstract Class <T> getEntityClass();
}
