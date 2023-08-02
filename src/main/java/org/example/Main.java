package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entities.Employee;
import org.example.entities.Product;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        try (
                EntityManagerFactory emf = new HibernatePersistenceProvider()
                        .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(), new HashMap<>());
//                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
                EntityManager em = emf.createEntityManager()
        ) {

            em.getTransaction().begin();

//            Employee e1 = em.find(Employee.class, 1);
//            e1.setName("Mary");

            Employee e1 = new Employee();
            e1.setId(1);
            e1.setName("Alice");
            e1.setAddress("Something");
            em.merge(e1);

//            em.persist();   -> Adding an entity in the context
//            em.find()       -> Finds by PK. Get from DB and add it to the context if it doesn't already exist
//            em.remove();    -> Marking entity for removal
//            em.merge();     -> Merges an entity from outside the context to the context.
//            em.refresh();   -> Mirror the context from the database
//            em.detach();    -> Taking the entity out of the context
//            em.getReference()

            em.getTransaction().commit(); // end of transaction

        }


    }
}