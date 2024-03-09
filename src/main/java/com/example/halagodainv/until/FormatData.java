package com.example.halagodainv.until;


public class FormatData {
    public static Long checkNull(Long value){
        return value == null ? 0 : value;
    }

    public static String checkNull(String value){
        return value == null ? "" : value;
    }

    public static Boolean checkNull(Boolean value){
        return value != null;
    }

    public static Integer checkNull(Integer value){
        return value == null ? 0 : value;

    }
}
