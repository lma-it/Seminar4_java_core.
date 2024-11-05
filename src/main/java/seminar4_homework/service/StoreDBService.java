package seminar4_homework.service;

import seminar4_homework.model.Product;
import seminar4_homework.util.Gender;
import seminar4_homework.util.StoreDB;

import java.util.Objects;
import java.util.Random;

public class StoreDBService {

    private static final Random RANDOM = new Random();

    public static String getRandomName(Gender gender){
        if (Objects.equals(gender.getGender(), Gender.MALE.getGender())){
            return StoreDB.getMaleNames()[RANDOM.nextInt(0, StoreDB.getMaleNames().length)];
        }else {
            return StoreDB.getWomanNames()[RANDOM.nextInt(0, StoreDB.getWomanNames().length)];
        }

    }

    public static String getRandomLastName(){
        return StoreDB.getLastName()[RANDOM.nextInt(0, StoreDB.getLastName().length)];
    }

    public static String getRandomSecondName(){
        return StoreDB.getSecondNames()[RANDOM.nextInt(0, StoreDB.getSecondNames().length)];
    }

    public static String getRandomPhoneNumber(){
        return StoreDB.getRandomPhoneNumber();
    }


    public static Gender getRandomGender(){
        return StoreDB.getRandomGender();
    }
    public static int getRandomAge(){
        return StoreDB.getAgeFromDB();
    }

    public static int getRandomMoneyValue(){
        return StoreDB.getRandomMoney();
    }

    public static Product getRandomProductFromDB(){
        return StoreDB.getProduct();
    }

    public static String getRandomPosition(){
        return StoreDB.getRandomPosition();
    }

}
