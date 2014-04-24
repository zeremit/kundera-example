package com.khorevich;

import com.khorevich.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        User user = new User();
        user.setName("John");
        user.setSurname("Smith");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mongo_pu");
        EntityManager em = emf.createEntityManager();

        em.persist(user);

        user = new User();
        user.setName("Alex");
        user.setSurname("Smith");
        em.persist(user);

        user = new User();
        user.setName("Dane");
        user.setSurname("Smith");
        em.persist(user);

        em.close();
        em = emf.createEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from.alias("p"));
        query.orderBy(criteriaBuilder.desc(from.get("name")));
        TypedQuery<User> q = em.createQuery(query);
        for(User u: q.getResultList()){
            System.out.println(u);
        }
        System.out.println();
        String jpql = "Select u from User u order by p.name DESC ";
        q = em.createQuery(jpql,User.class);
        for(User u: q.getResultList()){
            System.out.println(u);
        }
        System.out.println();
        jpql = "Select u from User u order by NAME DESC ";
        q = em.createQuery(jpql,User.class);
        for(User u: q.getResultList()){
            System.out.println(u);
        }
        emf.close();
    }
}
