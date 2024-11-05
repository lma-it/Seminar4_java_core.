package seminar4_homework.service;

import lombok.Getter;
import lombok.Setter;
import seminar4_homework.exceptions.NotNullCountOfProductsException;
import seminar4_homework.exceptions.ProductException;
import seminar4_homework.model.Product;
import seminar4_homework.model.impl.Customer;
import seminar4_homework.model.impl.Employee;

import java.util.*;


public class StoreService {

    @Getter
    private static List<Product> products = new ArrayList<>();
    @Getter
    private static List<Employee> employees = new ArrayList<>();
    @Getter
    private static List<Customer> customers = new ArrayList<>();
    @Getter
    @Setter
    public static int income = 0;

    public static void setProductsToStoreFromDB(int storeCapacity){
        try {
            getProducts().addAll(ProductService.fillProductsFromDB(storeCapacity));
        }catch (ProductException | NotNullCountOfProductsException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void setProductsToStore(int count, Product product){
        try {
            if (product != null && count > 0){
                List<Product> products = new ArrayList<>();
                for(int i = 0; i < count; i++){
                    products.add(product);
                }
                getProducts().addAll(products);
            }else if(product == null){
                throw  new ProductException("EXCEPTION: Продукт не может быть null, Данный продукт не будет добавлен.");
            }else {
                throw new NotNullCountOfProductsException(String.format("EXCEPTION: Количество продуктов не может быть %s. Товар не может быть добавлен.", count));
            }

        }catch (ProductException | NotNullCountOfProductsException e){
            System.out.println(e.getMessage());
        }

    }

    public static boolean isBuyOrNot(List<Customer> customers){
        for (Customer customer : customers){
            if (!customer.getBasket().getBasket().isEmpty()){
                return true;
            }
        }
        return false;
    }

}
