package seminar4_homework.model.impl;

import lombok.Getter;
import seminar4_homework.exceptions.CustomerException;
import seminar4_homework.exceptions.NotEnoughMoneyException;
import seminar4_homework.exceptions.NotNullCountOfProductsException;
import seminar4_homework.exceptions.ProductException;
import seminar4_homework.interfaces.Order;
import seminar4_homework.model.Product;
import seminar4_homework.model.User;
import seminar4_homework.util.Basket;
import seminar4_homework.util.Gender;

import java.util.List;

@Getter
public class Customer extends User {

    private final Basket basket;
    private final int money;


    public Customer(Gender gender, String name, String secondName, String lastName, int age, String phoneNumber, Basket basket, int money) {
        super(gender, name, secondName, lastName, age, phoneNumber);
        this.basket = basket;
        this.money = money;
    }

    public static void decorateOutput(Customer object) {
        System.out.printf("Пол: %s, Имя: %s, Фамилия: %s, Отчество: %s, Возраст: %s, Номер телефона: %s, Продуктовая корзина: %s Количество денег: %s руб.\n",
                object.getGender().getGender(),
                object.getName(),
                object.getSecondName(),
                object.getLastName(),
                object.getAge(),
                object.getPhoneNumber(),
                object.getBasket().showBasket(),
                object.getMoney());
    }

    public static void decorateOutput(List<Customer> objects) {
        for (Customer customer : objects){
            decorateOutput(customer);
        }
    }

    // Сам же по себе заказ не может существовать, поэтому он вложен в покупателя.
    @Getter
    public static Order order = (customer, product, count) -> {

        try {
            int minCount = 1;
            if(customer == null){
                throw new CustomerException(String.format(
                        "EXCEPTION: %s пользователя не существует.", "null"));
            } else if (product == null) {
                throw new ProductException(String.format(
                        "EXCEPTION: %s продукта нет в магазине. Невозможно его добавить в корзину.",
                        (Object) null));
            } else if (count < minCount) {
                throw new NotNullCountOfProductsException(String.format(
                        "EXCEPTION: Добавьте хотя бы один товар. Ожидается %s, фактически %s",
                        minCount,
                        count));
            } else if(Customer.totalCost(product, count) > customer.getMoney() - customer.getBasket().getTotalCostOfBasket()){
                throw new NotEnoughMoneyException(String.format(
                        "EXCEPTION: У вас недостаточно денег для покупки всего. Ожидается %s, фактически %s",
                        Customer.totalCost(product, count),
                        customer.getMoney()));
            }
            while (count != 0){
                customer.getBasket().getBasket().add(product);
                count--;
            }
            return customer;
        }catch (CustomerException | ProductException | NotNullCountOfProductsException | NotEnoughMoneyException e){
            System.out.println(e.getMessage());
            // Плохие манеры пробрасывать null из методов, но пока так.
            return null;
        }

    };

    private static int totalCost(Product product, int count){
        int totalCost = 0;
        while (count > 0){
            totalCost += product.price();
            count--;
        }
        return totalCost;
    }
}
