package dao;

import entity.FullName;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

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


    public List<T> loadAll(T entity)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> c=   cb.createQuery(getEntityClass());
        TypedQuery<T> t=entityManager.createQuery(c);
       return t.getResultList();
    }
    public abstract Class <T> getEntityClass();
}
