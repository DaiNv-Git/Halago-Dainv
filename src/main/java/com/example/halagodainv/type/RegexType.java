package com.example.halagodainv.type;

import java.util.regex.Pattern;

public class RegexType {
    public static final Pattern DATE_PATTERN =
            Pattern.compile("^((?:19|20)[0-9][0-9])([/-])(0?[1-9]|1[012])([/-])(0?[1-9]|[12][0-9]|3[01])");
    public static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PHONE_PATTERN  =  Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");

}