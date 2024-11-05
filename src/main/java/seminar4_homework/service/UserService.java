package seminar4_homework.service;


import seminar4_homework.model.User;
import seminar4_homework.model.impl.Customer;
import seminar4_homework.model.impl.Employee;
import seminar4_homework.util.Basket;
import seminar4_homework.util.Gender;

import java.util.List;


public class UserService {


    public static Customer createCustomerFromDB(){

        Gender gender = StoreDBService.getRandomGender();
        return new Customer(
                gender,
                StoreDBService.getRandomName(gender),
                StoreDBService.getRandomSecondName(),
                StoreDBService.getRandomLastName(),
                StoreDBService.getRandomAge(),
                StoreDBService.getRandomPhoneNumber(),
                new Basket(),
                StoreDBService.getRandomMoneyValue());
    }

    public static Customer createCustomer(){

        return new Customer(
                Gender.valueFromString(InputService.input("Введите пол (Мужчина, мужчина, Male, MALE, Женщина, женщина, Female, FEMALE): ")),
                InputService.input("Введите имя: "),
                InputService.input("Введите фамилию: "),
                InputService.input("Введите отчество: "),
                Integer.parseInt(InputService.input("Введите возраст: ")),
                InputService.input("Введите номер телефона: "),
                new Basket(),
                Integer.parseInt(InputService.input("Введите количество денег: ")));
    }

    public static Employee createEmployeeFromDB() {
        Gender gender = StoreDBService.getRandomGender();
        return new Employee(
                gender,
                StoreDBService.getRandomName(gender),
                StoreDBService.getRandomSecondName(),
                StoreDBService.getRandomLastName(),
                StoreDBService.getRandomAge(),
                StoreDBService.getRandomPhoneNumber(),
                StoreDBService.getRandomPosition()
        );
    }

    public static Employee createEmployee(){

        return new Employee(
                Gender.valueFromString(InputService.input("Введите пол (Мужчина, мужчина, Male, MALE, Женщина, женщина, Female, FEMALE): ")),
                InputService.input("Введите имя: "),
                InputService.input("Введите фамилию: "),
                InputService.input("Введите отчество: "),
                Integer.parseInt(InputService.input("Введите возраст: ")),
                InputService.input("Введите номер телефона: "),
                InputService.input("Введите должность в магазине.")
        );
    }

    public static User findByName(List<? extends User> users, String name){
        for (User user : users){
            if (user.getName().equals(name)){
                return user;
            }
        }
        return null;
    }

}
