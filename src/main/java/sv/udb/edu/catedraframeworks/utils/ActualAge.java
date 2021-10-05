package sv.udb.edu.catedraframeworks.utils;


import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.Date;

public class ActualAge {

    public int getActualDate (Date birthDate){
        LocalDate birthday = new LocalDate(birthDate);
        LocalDate now  = new LocalDate(now());
        Years age = Years.yearsBetween(birthday, now);

        return age.getYears();
    }

    protected Date now(){
        return new Date();
    }

}
