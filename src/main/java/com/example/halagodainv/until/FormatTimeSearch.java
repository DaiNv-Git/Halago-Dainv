package com.example.halagodainv.until;

import org.apache.logging.log4j.util.Strings;

public class FormatTimeSearch {
    public static String getStart(String startDate) {
        String startDateFormat = "1000-01-01";
        if (!Strings.isEmpty(startDate)) {
            startDateFormat = startDate;
        }
        return startDateFormat + " 00:00:00";
    }

    public static String getEndDate(String endDate) {
        String endDateFormat = "9999-12-31";
        if (!Strings.isEmpty(endDate)) {
            endDateFormat = endDate;
        }
        return endDateFormat + " 23:59:59";
    }
}
