package seminar4_homework.util;

import lombok.Getter;

public enum Holydays {

    HAPPY_NEW_YEAR("01.01"),
    WOMANS_DAY("08.03"),
    MANS_DAY("23.02"),
    VICTORY_DAY("09.06");

    @Getter
    private final String date;

    Holydays(String date){
        this.date = date;
    }

}
