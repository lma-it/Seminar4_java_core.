package seminar4_homework.model.impl;

import lombok.Getter;
import lombok.Setter;
import seminar4_homework.model.User;
import seminar4_homework.util.Gender;

import java.util.List;

@Getter
@Setter
public class Employee extends User {

    private String positionInMarket;

    public Employee(Gender gender,
                    String name,
                    String secondName,
                    String lastName,
                    int age,
                    String phoneNumber,
                    String position)
    {
        super(gender, name, secondName, lastName, age, phoneNumber);
        positionInMarket = position;
    }


    public static void decorateOutput(Employee object) {
        System.out.printf("Пол: %s, Имя: %s, Фамилия: %s, Отчество: %s, Возраст: %s, Номер телефона: %s, Должность: %s\n",
                object.getGender().getGender(),
                object.getName(),
                object.getSecondName(),
                object.getLastName(),
                object.getAge(),
                object.getPhoneNumber(),
                object.getPositionInMarket());
    }

    public static void decorateOutput(List<Employee> objects) {
        for (Employee employee : objects){
            decorateOutput(employee);
        }
    }
}
