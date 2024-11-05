package seminar4_homework.service;

import java.util.Objects;
import java.util.Scanner;

public class InputService {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static String input(String message){
        String input = "";
        while (Objects.equals(input, "")){
            try {
                System.out.println(message);
                input =  SCANNER.nextLine();
                SCANNER.reset();
                if(Objects.equals(input, "")){
                    throw new RuntimeException("EXCEPTION: Вы ввели не верное значение. Попробуйте снова.");
                }
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

}
