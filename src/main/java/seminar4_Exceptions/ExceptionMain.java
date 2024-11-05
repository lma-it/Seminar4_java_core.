package seminar4_Exceptions;


public class ExceptionMain {
    public static void main(String[] args) {

//        Thread thread = new Thread(() -> System.out.printf("Run in thread %s", Thread.currentThread()));
//        Thread thread1 = new Thread(() -> System.out.printf("Run in thread %s", Thread.currentThread()));
//        Thread thread2 = new Thread(() -> System.out.printf("Run in thread %s", Thread.currentThread()));
//
//        thread.run();
//        thread1.run();
//        thread2.run();


        while (!LoginAndPassword.logIn()){
            if(LoginAndPassword.logIn()){
                System.out.println("Все отлично, Вы в системе!!!");
            }
        }


    }
}