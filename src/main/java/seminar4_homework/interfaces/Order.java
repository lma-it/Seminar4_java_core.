package seminar4_homework.interfaces;

import seminar4_homework.model.impl.Customer;
import seminar4_homework.model.Product;


public interface Order {
    Customer order(Customer customer, Product product, int count);
}
