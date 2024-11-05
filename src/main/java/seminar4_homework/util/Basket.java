package seminar4_homework.util;

import lombok.Getter;
import seminar4_homework.model.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Basket{

    private final List<Product> basket = new ArrayList<>();

    // Этот метод вызывается после того как все продукты будут добавлены в корзину.
    public int getTotalCostOfBasket() {

        int totalCost = 0;
        for (Product product : basket){
            totalCost += product.price();
        }
        return totalCost;
    }


    public String showBasket(){
        if(!getBasket().isEmpty()){
            StringBuilder basket = new StringBuilder();
            Product temp = null;
            for (Product product : getBasket()){
                int count = 0;
                if (!product.equals(temp)){
                    for ( int i = 0; i < getBasket().size(); i++){
                        if (product.equals(getBasket().get(i))){
                            count++;
                        }
                    }
                    temp = product;
                    basket.append(product).append(String.format(" шт. %s. ", count));
                }
            }
            return basket.toString();
        }
        return "пусто";
    }

}
