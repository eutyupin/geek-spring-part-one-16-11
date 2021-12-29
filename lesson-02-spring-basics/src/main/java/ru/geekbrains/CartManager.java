package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;
import java.util.Scanner;

public class CartManager {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Scanner sc = new Scanner(System.in);
        CartService cart = null;
        while (true) {
            System.out.print("Введите комманду: /> ");
            String command = sc.nextLine().trim().toUpperCase();
            switch (command) {
                case "NEW":
                    cart = context.getBean(CartService.class);
                    break;
                case "ADD":
                    if (cart == null) {
                        System.out.println("Корзины не существует, создайте её коммандой new");
                        break;
                    } else {
                        System.out.print("Введите id: /> ");
                        long id = sc.nextLong();
                        System.out.print("Введите количество: /> ");
                        int count = sc.nextInt();
                        cart.addProduct(id, count);
                        break;
                    }
                case "SHOW":
                    cart.getAll().forEach(System.out::println);
                    break;
                case "EXIT":
                    return;
            }

        }
    }
}
