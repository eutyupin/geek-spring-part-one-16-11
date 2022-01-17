package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.services.ProductsByUserId;

import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        ProductsByUserId productsByUserId = new ProductsByUserId(emFactory);
        productsByUserId.printUserOrders(1L);
    }




}
