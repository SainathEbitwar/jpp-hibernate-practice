package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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
                EntityManager entityManager = emf.createEntityManager()
        ) {

            entityManager.getTransaction().begin();

            Product product = new Product();
            product.setId(4L);
            product.setName("Salad");

            entityManager.persist(product);

            entityManager.getTransaction().commit();

        }


    }
}