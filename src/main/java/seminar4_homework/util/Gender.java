package seminar4_homework.util;

import lombok.Getter;

@Getter
public enum Gender {

    MALE("Мужчина"), FEMALE("Женщина");

    private final String gender;

    Gender(String gender){
        this.gender = gender;
    }

    public static Gender valueFromString(String gender){
        if (gender.contains("Ma") || gender.contains("MA") || gender.contains("муж") || gender.contains("Муж")){
            return MALE;
        }
        else{
            return FEMALE;
        }

    }
}
