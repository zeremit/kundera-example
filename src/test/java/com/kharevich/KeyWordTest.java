package com.kharevich;

import com.kharevich.entity.UploadedObject;
import com.kharevich.entity.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * Created by zeremit on 17.11.14.
 */
public class KeyWordTest {

    private EntityManagerFactory emf;

    @BeforeClass
    public void setUpBeforeClass() {
        emf = Persistence.createEntityManagerFactory("mongo_pu");
    }

    @Test
    public void testKeyWordFail(){
        EntityManager emp = emf.createEntityManager();
        User user = new User();
        user.setName("Screenshot from 2013-10-30 17:43:51.png");
        user.setSurname("Smith");
        user.setDeleted(true);
        emp.persist(user);
        emp.close();
        emp = emf.createEntityManager();
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> from = query.from(User.class);
        query.select(from.alias("t"));
        query.where(criteriaBuilder.equal((Expression) from.get("name"), "Screenshot from 2013-10-30 17:43:51.png"));

        TypedQuery<User> typedQuery =emp.createQuery(query);
        List<User> result = typedQuery.getResultList();
    }
}
