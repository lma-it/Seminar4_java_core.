package seminar4_homework.view;

import seminar4_homework.exceptions.CustomerException;
import seminar4_homework.exceptions.NotEnoughMoneyException;
import seminar4_homework.exceptions.NotNullCountOfProductsException;
import seminar4_homework.model.Product;
import seminar4_homework.model.impl.Customer;
import seminar4_homework.model.impl.Employee;
import seminar4_homework.service.ProductService;
import seminar4_homework.service.StoreService;
import seminar4_homework.service.UserService;
import seminar4_homework.service.InputService;
import seminar4_homework.util.Basket;
import seminar4_homework.util.Gender;
import seminar4_homework.util.Holydays;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class View {

    public void start(){
        System.out.println("Добро пожаловать в наш магазин.");
        final int error = 20;

        do {
            mainMenu();
            switch (Integer.parseInt(InputService.input("Введите команду."))) {
                case 1 -> {
                    if(StoreService.getProducts().isEmpty() &&
                            StoreService.getCustomers().isEmpty() &&
                            StoreService.getEmployees().isEmpty())
                    {

                        fillAutoOrHandleMenu();
                        switch (Integer.parseInt(InputService.input("Введите команду."))) {
                            case 1 -> {
                                StoreService.setProductsToStoreFromDB(Integer.parseInt(InputService.input(
                                        "Введите общее количество всех товаров в магазине: ")));
                                int count = Integer.parseInt(InputService.input(
                                        "Введите количество покупателей: "));
                                while (count > 0){
                                    StoreService.getCustomers().add(UserService.createCustomerFromDB());
                                    count--;
                                }
                                count = Integer.parseInt(InputService.input("Введите количество сотрудников: "));
                                while (count > 0){
                                    StoreService.getEmployees().add(UserService.createEmployeeFromDB());
                                    count--;
                                }
                            }
                            case 2 -> {
                                int capacity = Integer.parseInt(InputService.input("Введите общее количество товаров в магазине: "));
                                while(capacity > 0){
                                    int countOfProduct = Integer.parseInt(InputService.input("Введите количество товара, который хотите добавить."));
                                    if ((capacity - countOfProduct) == 0 || (capacity - countOfProduct) > 0){
                                        StoreService.setProductsToStore(countOfProduct,
                                                new Product(InputService.input("Введите название товара."),
                                                        Integer.parseInt(InputService.input("Введите его стоимость:"))));
                                        capacity -= countOfProduct;

                                    }else if(capacity - countOfProduct < 0){
                                        System.out.printf("Добавим товар в количестве %s потому что стеллажи переполнены%n",
                                                countOfProduct - (countOfProduct - capacity));
                                        StoreService.setProductsToStore(countOfProduct - (countOfProduct - capacity),
                                                new Product(InputService.input("Введите название товара."),
                                                        Integer.parseInt(InputService.input("Введите его стоимость:"))));
                                        capacity -= countOfProduct;
                                    }
                                }

                                int count = Integer.parseInt(InputService.input(
                                        "Введите количество покупателей: "));
                                while (count > 0){
                                    StoreService.getCustomers().add(UserService.createCustomer());
                                    count--;
                                }
                                count = Integer.parseInt(InputService.input("Введите количество сотрудников: "));
                                while (count > 0){
                                    StoreService.getEmployees().add(UserService.createEmployee());
                                    count--;
                                }
                            }
                            case 3 -> {
                            }
                        }
                    }else {
                        outputMenu();
                        switch (Integer.parseInt(InputService.input("Введите команду"))){
                            case 1 -> {
                                System.out.println("Товары в магазине: ");
                                ProductService.decorateOutput(StoreService.getProducts());
                                System.out.println("Покупатели в магазине: ");
                                Customer.decorateOutput(StoreService.getCustomers());
                                System.out.println("Сотрудники магазина: ");
                                Employee.decorateOutput(StoreService.getEmployees());
                            }
                            case 2 -> ProductService.decorateOutput(StoreService.getProducts());
                            case 3 -> Customer.decorateOutput(StoreService.getCustomers());
                            case 4 -> Employee.decorateOutput(StoreService.getEmployees());
                            case 0 -> {
                            }
                        }
                    }

                }
                case 2 -> {
                    // Второй пункт главного меню.
                    orderMainMenu();
                    switch (Integer.parseInt(InputService.input("Введите команду."))){
                        case 1 -> {
                            Customer customer = StoreService.getCustomers().get(new Random().nextInt(0, StoreService.getCustomers().size()));
                            System.out.println("Список продуктов.");
                            ProductService.decorateOutput(StoreService.getProducts());
                            System.out.printf("Покупатель %s может купить на сумму: %s\n",customer.getName(), customer.getMoney() - customer.getBasket().getTotalCostOfBasket());
                            customer = Customer.getOrder().order(customer,
                                    ProductService.findByName(InputService.input("Введите продукт")),
                                    Integer.parseInt(InputService.input("Введите количество товара.")));
                            if(customer != null){
                                for (Product product : customer.getBasket().getBasket()){
                                    System.out.println(product);
                                }
                                System.out.printf("Общая стоимость товаров в корзине %s руб.\n", customer.getBasket().getTotalCostOfBasket());
                            }
                        }
                        case 2 -> {
                            System.out.println("Покупатели в магазине: ");
                            Customer.decorateOutput(StoreService.getCustomers());
                            try{
                                var user = (Customer) UserService.findByName(StoreService.getCustomers(),
                                        InputService.input("Введите имя: "));
                                if (user == null){
                                    throw new CustomerException();
                                }
                                System.out.println("Список продуктов.");
                                ProductService.decorateOutput(StoreService.getProducts());
                                System.out.printf("%s может купить на сумму %s", user.getName(), user.getMoney() - user.getBasket().getTotalCostOfBasket());
                                user = Customer.getOrder().order(user,
                                        ProductService.findByName(InputService.input("Введите продукт")),
                                        Integer.parseInt(InputService.input("Введите количество товара.")));

                                if (user != null) {
                                    for (Product product : user.getBasket().getBasket()){
                                        System.out.println(product);
                                    }
                                    System.out.printf("Общая стоимость товаров в корзине %s руб.\n",
                                            user.getBasket().getTotalCostOfBasket());
                                }
                            }catch (CustomerException | NotEnoughMoneyException | NotNullCountOfProductsException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        case 3 -> {
                            try {
                                int income = 0;
                                Customer user = new Customer(Gender.valueFromString(InputService.input("Введите пол.")),
                                        InputService.input("Введите имя: "),
                                        InputService.input("Введите фамилию: "),
                                        InputService.input("Введите отчество: "),
                                        Integer.parseInt(InputService.input("Введите возраст: ")),
                                        InputService.input("Введите номер телефона: "),
                                        new Basket(),
                                        Integer.parseInt(InputService.input("Введите количество денег: ")));

                                System.out.println("Список продуктов.");
                                ProductService.decorateOutput(StoreService.getProducts());
                                System.out.printf("%s может купить на сумму %s", user.getName(), user.getMoney() - user.getBasket().getTotalCostOfBasket());
                                user = Customer.getOrder().order(user,
                                        ProductService.findByName(InputService.input("Введите продукт")),
                                        Integer.parseInt(InputService.input("Введите количество товара.")));

                                for (Product product : user.getBasket().getBasket()){
                                    System.out.println(product);
                                }
                                System.out.printf("Общая стоимость товаров в корзине %s руб.\n",
                                        user.getBasket().getTotalCostOfBasket());
                                StoreService.getCustomers().add(user);

                                if(StoreService.getIncome() == 0){
                                    StoreService.setIncome(income);
                                }else {
                                    int temp = StoreService.getIncome();
                                    temp += income;
                                    StoreService.setIncome(temp);
                                }
                            }catch (CustomerException e){
                                System.out.println(e.getMessage());
                            }

                        }
                        case 0 -> {
                        }
                    }
                }
                case 3 -> {
                    // Третий пункт главного меню.
                    buyMenu();
                    switch (Integer.parseInt(InputService.input("Введите команду"))){
                        case 1 -> {
                            int income = 0;
                            for (Customer customer : StoreService.getCustomers()){
                                if (!customer.getBasket().getBasket().isEmpty()){
                                    income += customer.getBasket().getTotalCostOfBasket();
                                    for (Product product : customer.getBasket().getBasket()){
                                        if (customer.getGender().equals(Gender.MALE))
                                            System.out.println(customer.getName() + " приобрел " + product);
                                        else
                                            System.out.println(customer.getName() + " приобрела " + product);
                                    }
                                    System.out.printf("Общая стоимость всей корзины %s\n", customer.getBasket().getTotalCostOfBasket());
                                }
                            }

                            List<Object> buyers = List.of(StoreService.getCustomers()
                                    .stream()
                                    .filter(customer -> !customer.getBasket().getBasket().isEmpty())
                                    .toArray()
                            );
                            StoreService.getCustomers().removeAll(buyers);

                            if(StoreService.getIncome() == 0){
                                StoreService.setIncome(income);
                            }else {
                                int temp = StoreService.getIncome();
                                temp += income;
                                StoreService.setIncome(temp);
                            }

                            System.out.printf("\nПрибыль магазина на сегодня: %s руб.\n", StoreService.getIncome());
                        }
                        case 2 -> {
                            try {
                                Customer.decorateOutput(StoreService.getCustomers());
                                int income = 0;
                                var customer = (Customer) UserService.findByName(StoreService.getCustomers(), InputService.input("введите имя: "));
                                if (customer != null){
                                    if (!customer.getBasket().getBasket().isEmpty()){
                                        income += customer.getBasket().getTotalCostOfBasket();
                                        for (Product product : customer.getBasket().getBasket()){
                                            if (customer.getGender().equals(Gender.MALE))
                                                System.out.println(customer.getName() + " приобрел " + product);
                                            else
                                                System.out.println(customer.getName() + " приобрела " + product);
                                        }
                                        System.out.printf("Общая стоимость всей корзины %s руб.\n", customer.getBasket().getTotalCostOfBasket());
                                    }else {
                                        System.out.println("У покупателя корзина пока пустая.");
                                    }
                                    StoreService.getCustomers().remove(customer);
                                    if(StoreService.getIncome() == 0){
                                        StoreService.setIncome(income);
                                    }else {
                                        int temp = StoreService.getIncome();
                                        temp += income;
                                        StoreService.setIncome(temp);
                                    }
                                    System.out.printf("\nПрибыль магазина на сегодня: %s руб.\n", StoreService.getIncome());
                                }else {
                                    throw new CustomerException();
                                }
                            }catch (CustomerException e){
                                System.out.println(e.getMessage());
                            }
                        }
                        case 0 -> {
                        }
                    }
                }
                case 4 -> {
                    //Четвертый пункт меню
                    holidayMenu();
                    switch (Integer.parseInt(InputService.input("Введите команду"))){
                        case 1 -> {
                            String date = InputService.input("Введите сегодняшнюю дату в формате (дд.мм).");
                            if (Holydays.HAPPY_NEW_YEAR.getDate().equals(date)){
                               for (Employee employee : StoreService.getEmployees()){
                                   System.out.printf("От лица администрации магазина, поздравляю %s с Новым %s Годом\n",
                                           employee.getName(),
                                           LocalDate.now().getYear()
                                   );
                               }
                            }else if (Holydays.MANS_DAY.getDate().equals(date)){
                                for (Employee employee : StoreService.getEmployees()){
                                    if(employee.getGender() == Gender.MALE){
                                        System.out.printf("От лица администрации магазина, поздравляю %s с Днем защитника отечества.\n",
                                                employee.getName()
                                        );
                                    }

                                }
                            }else if (Holydays.WOMANS_DAY.getDate().equals(date)){
                                for (Employee employee : StoreService.getEmployees()){
                                    if(employee.getGender() == Gender.FEMALE){
                                        System.out.printf("От лица администрации магазина, поздравляю %s с Международным Женским днем.\n",
                                                employee.getName()
                                        );
                                    }

                                }
                            } else if (Holydays.VICTORY_DAY.getDate().equals(date)) {
                                for (Employee employee : StoreService.getEmployees()){
                                    System.out.printf("От лица администрации магазина, поздравляю %s с Днем Победы. УРА! УРА! УРА!\n",
                                            employee.getName()
                                    );
                                }
                            }else {
                                System.out.println("Всем хорошего, продуктивного рабочего дня.");
                            }
                        }
                        case 2 -> {
                            String date = InputService.input("Введите сегодняшнюю дату в формате (дд.мм).");
                            if (Holydays.HAPPY_NEW_YEAR.getDate().equals(date)){
                                for (Employee employee : StoreService.getEmployees()){
                                    System.out.printf("От лица администрации магазина, поздравляю %s с Новым %s Годом\n",
                                            employee.getName(),
                                            LocalDate.now().getYear()
                                    );
                                }
                                for (Customer customer : StoreService.getCustomers()){
                                    System.out.printf("От лица администрации магазина, поздравляю %s с Новым %s Годом\n",
                                            customer.getName(),
                                            LocalDate.now().getYear()
                                    );
                                }
                            }else if (Holydays.MANS_DAY.getDate().equals(date)){
                                for (Employee employee : StoreService.getEmployees()){
                                    if(employee.getGender() == Gender.MALE){
                                        System.out.printf("От лица администрации магазина, поздравляю %s с Днем защитника отечества.\n",
                                                employee.getName()
                                        );
                                    }
                                }
                                for (Customer customer : StoreService.getCustomers()){
                                    if(customer.getGender() == Gender.MALE){
                                        System.out.printf("От лица администрации магазина, поздравляю %s с Днем защитника отечества.\n",
                                                customer.getName()
                                        );
                                    }
                                }
                            }else if (Holydays.WOMANS_DAY.getDate().equals(date)){
                                for (Employee employee : StoreService.getEmployees()){
                                    if(employee.getGender() == Gender.FEMALE){
                                        System.out.printf("От лица администрации магазина, поздравляю %s с Международным Женским днем.\n",
                                                employee.getName()
                                        );
                                    }
                                }
                                for (Customer customer : StoreService.getCustomers()){
                                    if(customer.getGender() == Gender.FEMALE){
                                        System.out.printf("От лица администрации магазина, поздравляю %s с Международным Женским днем.\n",
                                                customer.getName()
                                        );
                                    }

                                }
                            } else if (Holydays.VICTORY_DAY.getDate().equals(date)) {
                                for (Employee employee : StoreService.getEmployees()){
                                    System.out.printf("От лица администрации магазина, поздравляю %s с Днем Победы. УРА! УРА! УРА!\n",
                                            employee.getName()
                                    );
                                }
                                for (Customer customer : StoreService.getCustomers()){
                                    System.out.printf("От лица администрации магазина, поздравляю %s с Днем Победы. УРА! УРА! УРА!\n",
                                            customer.getName()
                                    );
                                }
                            }
                        }
                        case 0 -> {
                            break;
                        }
                    }
                }
                case 0 -> {
                    System.out.printf("Прибыль магазина на сегодня %s руб.\n", StoreService.getIncome());
                    return;
                }
                case error -> {

                }
            }

        } while (true);
    }

    private void mainMenu(){
        if(StoreService.getProducts().isEmpty() && StoreService.getCustomers().isEmpty() && StoreService.getEmployees().isEmpty()) {
            System.out.println("1. Заполнить магазин продуктами, покупателями и сотрудниками.");
        }else {
            System.out.println("1. Просмотреть все товары, всех покупателей, всех сотрудников.");
        }
        if(!StoreService.getProducts().isEmpty()){
            System.out.println("2. Совершить покупку.");
        }
        if (StoreService.isBuyOrNot(StoreService.getCustomers())){
            System.out.println("3. Заверишь покупку и покинуть магазин.");
        }
        if (!StoreService.getCustomers().isEmpty() || !StoreService.getEmployees().isEmpty()) {
            System.out.println("4. Поздравить с праздником.");
        }

        System.out.println("0. Выход.");

    }

    private void fillAutoOrHandleMenu(){
        System.out.println("1. Заполнить автоматически.");
        System.out.println("2. Заполнить вручную.");
        System.out.println("0. Выход.");
    }
    private void outputMenu(){
        System.out.println("1. Отобразить все, что есть в магазине (продукты, посетители, сотрудники).");
        System.out.println("2. Отобразить только продукты в магазине.");
        System.out.println("3. Отобразить только покупателей.");
        System.out.println("4. Отобразить только сотрудников.");
        System.out.println("0. ");
    }
    private void orderMainMenu(){
        System.out.println("1. Сделать заказ за любого покупателя.");
        System.out.println("2. Найти покупателя по имени и сделать заказ за него.");
        System.out.println("3. Стать новым покупателем и сделать заказ.");
        System.out.println("0. Выход");
    }
    private void buyMenu(){
        System.out.println("1. Завершить покупку всех покупателей у кого корзина не пуста.");
        System.out.println("2. Завершить покупку за конкретного покупателя.");
        System.out.println("0. ");
    }
    private void holidayMenu(){
        System.out.println("1. Поздравить только сотрудников.");
        System.out.println("2. Поздравить всех кто есть в магазине.");
        System.out.println("0. Выход.");
    }

}
