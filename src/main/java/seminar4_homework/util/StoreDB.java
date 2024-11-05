package seminar4_homework.util;

import lombok.Getter;
import seminar4_homework.model.Product;

import java.util.Random;

public class StoreDB {
    private static final Random RANDOM = new Random();

    @Getter
    private static String[] maleNames = {
            "Adam", "Michael", "Lion", "Freddy", "Lev", "Dmitriy",
            "Sergey",  "Alex", "Oleg", "Pavel", "Smith", "Omar", "Vlad",
            "Vadim", "Vyacheslav", "Chu", "Pu", "Yakov", "Sam"
    };

    @Getter
    private static String[] womanNames = {
            "Lola", "Anna", "Violetta", "Svetlana", "Naomi", "Inna", "Maria", "Sara", "Ann",
            "Mila", "Masha", "Olga", "Ola", "Viola", "Vera", "Sonya", "Bella"
    };

    @Getter
    private static String[] lastName = {
            "Anderson", "Bailey", "Bell", "Bennett", "Brooks", "Carter", "Collins", "Cooper",
            "Davis", "Edwards", "Evans", "Foster", "Gray", "Green", "Hall", "Harris", "Howard",
            "Hughes", "Jackson", "Johnson", "Kelly", "King", "Lewis", "Long", "Martin", "Miller",
            "Moore", "Morgan", "Murphy", "Parker", "Reed", "Roberts", "Scott", "Taylor", "Thomas",
            "Thompson", "Walker", "White", "Williams", "Young"
    };

    @Getter
    private static String[] secondNames = {
            "Adams", "Bailey", "Bennett", "Brooks", "Carter", "Clark",
            "Collins", "Cooper", "Cox", "Davis", "Edwards", "Evans", "Foster", "Gray", "Green", "Hall",
            "Harris", "Howard", "Hughes", "Jackson", "Johnson", "Kelly", "King", "Lewis", "Martin", "Miller",
            "Moore", "Morgan", "Murphy", "Parker", "Phillips", "Price", "Reed", "Roberts", "Russell", "Scott",
            "Stewart", "Taylor", "Thomas", "Williams"
    };

    @Getter
    private static String[] positions = {
            "Seller", "Manager", "Cashier", "Security"
    };

    private static final Product[] productsFromDB = {
            new Product("Bread 500gr", 15),
            new Product("Milk 1l", 10),
            new Product("Apple 1kg", 30),
            new Product("Orange 1kg", 45),
            new Product("Beer 0.5l", 30),
            new Product("Meat 1kg", 220),
            new Product("Chicken 1kg", 150),
            new Product("Soup", 17),
            new Product("Shampoo 0.7ml", 130),
            new Product("Oil 1l", 120),
            new Product("Coffee 120gr", 150),
            new Product("Fish 1kg", 170),
            new Product("Lollipop", 7),
            new Product("Strawberry 1kg", 200),
            new Product("Energy Drinks 0.4ml", 70),
            new Product("Water 0.5ml", 12),
            new Product("Olive oil 0.5ml", 500),
            new Product("Coca-Cola 1.5l", 80)
    };

    public static Product getProduct() {
        return productsFromDB[RANDOM.nextInt(0, productsFromDB.length)];
    }

    public static int getAgeFromDB() {
        return RANDOM.nextInt(18, 70);
    }

    public static String getRandomPhoneNumber() {

        StringBuilder phoneNumber = new StringBuilder(16);

        for (int i = 0; i < phoneNumber.capacity(); i++){
            if (i != 0 && i != 1 && i != 2 && i != 6 && i != 10 && i != 13){
                phoneNumber.append(RANDOM.nextInt(0,10));
            } else if (i == 1) {
                phoneNumber.append(RANDOM.nextInt(7,9));
            } else if(i == 0){
                phoneNumber.append("+");
            }else if(i == 2){
                phoneNumber.append("(");
            }else if(i == 6){
                phoneNumber.append(")");
            }else{
                phoneNumber.append("-");
            }
        }
        return phoneNumber.toString();
    }

    public static Gender getRandomGender() {
        Gender[] genders = new Gender[2];
        genders[0] = Gender.MALE;
        genders[1] = Gender.FEMALE;
        return genders[RANDOM.nextInt(0,2)];
    }

    public static int getRandomMoney() {
        return RANDOM.nextInt(100, 1000);
    }

    public static String getRandomPosition() {
        return positions[RANDOM.nextInt(0, positions.length)];
    }
}
