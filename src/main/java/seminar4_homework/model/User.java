package seminar4_homework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import seminar4_homework.util.Gender;


@Getter
@Setter
@AllArgsConstructor
public class User {
    protected Gender gender;
    protected String name;
    protected String secondName;
    protected String lastName;
    protected int age;
    protected String phoneNumber;

}
