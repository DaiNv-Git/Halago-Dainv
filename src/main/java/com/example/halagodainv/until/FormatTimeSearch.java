package com.example.halagodainv.until;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class FormatTimeSearch {
    public static String getStart(String startDate) {
        String startDateFormat = "1000-01-01";
        if (!StringUtils.isEmpty(startDate)) {
            startDateFormat = startDate;
        }
        return startDateFormat + " 00:00:00";
    }

    public static String getEndDate(String endDate) {
        String endDateFormat = "9999-12-31";
        if (!StringUtils.isEmpty(endDate)) {
            endDateFormat = endDate;
        }
        return endDateFormat + " 23:59:59";
    }


        public static String getSiteURL(HttpServletRequest request) {
            String siteURL = request.getRequestURL().toString();
            return siteURL.replace(request.getServletPath(), "");
        }
}
