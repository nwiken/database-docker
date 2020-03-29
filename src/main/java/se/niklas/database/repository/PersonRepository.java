package se.niklas.database.repository;

import org.springframework.stereotype.Repository;
import se.niklas.database.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> findAll() {
        return entityManager.createNativeQuery("Select * from smart.person", Person.class)
                .getResultList();
    }

}
