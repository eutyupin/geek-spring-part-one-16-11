package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        EntityManager em = emFactory.createEntityManager();
        ProductDao productOperations = new ProductDao(em);
        for (Product product : productOperations.findAll()) {
            System.out.println(product);
        }
        // some methods from productOperations

        em.close();
    }


}
