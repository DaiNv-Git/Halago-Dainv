package com.example.halagodainv.until;


import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilFormat {

    public static String convertDateToString(Date dateTime, String dateType) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateType);
        String dateFormatString = formatter.format(dateTime);
        return dateFormatString;
    }
}
