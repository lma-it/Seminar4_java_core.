package seminar4_homework.model;

public record Product(String name, int price){

    @Override
    public String toString() {
        return String.format("Продукт: %s, Цена: %s руб.", name, price);
    }

}
