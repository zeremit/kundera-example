package com.khorevich;

import com.khorevich.entity.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.List;

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
        user.setDeleted(true);
        em.persist(user);

        user = new User();
        user.setName("Dane");
        user.setSurname("Smith");
        user.setDeleted(true);
        em.persist(user);

        em.close();
        em = emf.createEntityManager();


        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from.alias("p"));
        Predicate cr = criteriaBuilder.or(criteriaBuilder.equal(from.get("name"), "vivek"),
                criteriaBuilder.equal(from.get("surname"), "Smith1"));
        Predicate p1 = criteriaBuilder.equal(from.get("surname"), "Smith");
        Predicate p2 = criteriaBuilder.equal(from.get("name"), "Alex");
        query.where(criteriaBuilder.or(p1,p2 ));

        TypedQuery<User> q = em.createQuery(query);
        for(User u: q.getResultList()){
            System.out.println(u);
        }
        System.out.println();

        query.where(p1);
        q = em.createQuery(query);
        for(User u: q.getResultList()){
            System.out.println(u);
        }
        System.out.println();

        String querySql = "select u from User u where u.name = 'Alex' OR u.surname='Smith'";
        Query result = em.createQuery(querySql);
//        result.setParameter("name", "Alex");
//        result.setParameter("surname", "Smith");
        for(Object u : result.getResultList()){
            System.out.println(u);
        }
        System.out.println();
    }
}
