package seminar4_homework.service;

import lombok.Getter;
import seminar4_homework.model.Product;

import java.util.ArrayList;
import java.util.List;


@Getter
public class ProductService {

    private static final List<Product> products = new ArrayList<>();

    // Случайное заполнение продуктами из Базы Данных.
    // Автоматическое заполнение магазина продуктами.
    public static List<Product> fillProductsFromDB(int count){
        while (count > 0){
            products.add(StoreDBService.getRandomProductFromDB());
            count--;
        }
        return products;
    }

    public static void decorateOutput(Product object) {
        System.out.printf("Продукт: %s, Цена: %s руб.\n", object.name(), object.price());
    }


    public static void decorateOutput(List<Product> objects) {
        for (Product product : objects){
            decorateOutput(product);
        }

    }

    public static Product findByName(String name){
        for (Product product : products){
            if(product.name().contains(name)){
                return product;
            }
        }
        return null;
    }
}
