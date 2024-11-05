package seminar4_Exceptions;

import java.util.Scanner;

public class LoginAndPassword {

    public static boolean logIn() {

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите логин. Не более 20 символов.");
            String login = scanner.nextLine();
            int maxLength = 20;
            if(login.length() > maxLength){
                throw new WrongLoginException(String.format(
                        "Вы ввели недопустимое количество символов. Ожидалось %s, фактически %s",
                        maxLength,
                        login.length()));
            }
            System.out.println("Введите пароль. Не более 20 символов.");
            String password = scanner.nextLine();

            if(password.length() > maxLength){
                throw new WrongPasswordException(String.format(
                        "Вы ввели недопустимое количество символов. Ожидалось %s, фактически %s",
                        maxLength,
                        password.length()));
            }

            System.out.println("Повторите пароль.");
            String confirmPassword = scanner.nextLine();
            if(!confirmPassword.equals(password)){
                if(password.length() > maxLength){
                    throw new WrongPasswordException(String.format(
                            "Вы ввели недопустимое количество символов. Ожидалось %s, фактически %s",
                            maxLength,
                            password.length()));
                }else{
                    throw new WrongPasswordException(String.format(
                            "Пароли не совпадают. Ожидалось %s, фактически %s",
                            password,
                            confirmPassword));
                }
            }
            return true;

        }catch (WrongLoginException | WrongPasswordException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
