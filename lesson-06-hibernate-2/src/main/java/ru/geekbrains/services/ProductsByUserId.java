package ru.geekbrains.services;

import ru.geekbrains.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductsByUserId {
    private EntityManagerFactory emFactory;
    private EntityManager em;

    public ProductsByUserId(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public void printUserOrders(long userId) {
        em = emFactory.createEntityManager();
        List<Order> orders = em.createQuery(
                        "select distinct o " +
                                "from Order o " +
                                "join fetch o.user " +
                                "where o.user.id = :user_id", Order.class)
                .setParameter("user_id", userId)
                .getResultList();
        orders.forEach(System.out::println);
        em.close();
    }
}
