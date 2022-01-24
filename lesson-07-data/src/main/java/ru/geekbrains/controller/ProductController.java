package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("nameFilter") Optional<String> nameFilter,
                           @RequestParam("idFilter") Optional<String> idFilter) {
        logger.info("Product filter with name pattern {}", nameFilter.orElse(null));

        // TODO

        List<Product> products = null;
        if (nameFilter.isPresent() && !nameFilter.get().isBlank()) {
            products = productRepository.findAllByNameLike("%" + nameFilter.get() + "%");
        }
        if(idFilter.isPresent() && !idFilter.get().isBlank()) {
                products = productRepository.findAllById(Long.parseLong(idFilter.get()));
            }
        if (nameFilter.isPresent() && idFilter.isPresent() &&
                !nameFilter.get().isBlank() && !idFilter.get().isBlank()) {
            products = productRepository.findAllByNameLikeAndId(nameFilter.get(), Long.parseLong(idFilter.get()));
        }
        if ((!nameFilter.isPresent() && !idFilter.isPresent()) ||
                (nameFilter.isPresent() && idFilter.isPresent() &&
                        nameFilter.get().isBlank() && idFilter.get().isBlank())) {
            products = productRepository.findAll();
        }
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    // TODO добавить удаление продукта

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        productRepository.delete(productRepository.findById(id).get());
        return "redirect:/product";
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(NotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "not_found";
    }
}

