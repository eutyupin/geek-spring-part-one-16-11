package ru.geekbrains;

import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {
    private EntityManagerFactory emFactory;
    private EntityManager em;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findAll() {
        em = emFactory.createEntityManager();
        List<Product> products;
        em.getTransaction().begin();
        products = em.createQuery("SELECT products FROM Product products", Product.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return products;
    }

    public Product findById(Long id) {
        em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public void deleteById(Long id) {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        em.remove(product);
        em.getTransaction().commit();
        em.close();
    }

    public void saveOrUpdate(Product product) {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }
}
