package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    private Map<Product, Integer> products;

    @Autowired
    private ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
        products = new HashMap<>();
    }

    public void addProduct(long id, int count) {
        Product product = getProductId(id);
        products.merge(product, count, Integer::sum);
    }

    public void removeProduct(long id, int count) {
        Product product = getProductId(id);
        Integer currentQty = products.get(product);
        if (currentQty < count) {
            products.remove(product);
        } else {
            products.merge(product, -count, Integer::sum);
        }
    }

    public List<Product> getAll() {
        return new ArrayList<>(products.keySet());
    }

    private Product getProductId(long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new IllegalArgumentException("Такого продукта не существует!");
        }
        return product;
    }

    // TODO
}
