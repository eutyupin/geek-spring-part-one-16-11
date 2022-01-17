package ru.geekbrains;

import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> findAll() {
        List<Product> products;
        entityManager.getTransaction().begin();
        products = entityManager.createQuery("SELECT products FROM Product products", Product.class).getResultList();
        entityManager.getTransaction().commit();
        return products;
    }

    public Product findById(Long id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    public void saveOrUpdate(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
    }
}
