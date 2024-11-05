package seminar4_Exceptions;


import java.util.List;

public class IteratorService<T> {

    public T iterator(List<T> objects){

        for (T object : objects) {
            return object;
        }
        return null;
    }

}
