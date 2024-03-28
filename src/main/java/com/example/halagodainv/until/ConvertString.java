package com.example.halagodainv.until;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class ConvertString {

    public static List<Integer> parseStringToListOfIntegers(String input) {
        List<Integer> integerList = new ArrayList<>();
        if (!StringUtils.isBlank(input)) {
            String[] numberStrings = input.split(",");
            for (String numberString : numberStrings) {
                int number = Integer.parseInt(numberString.trim());
                integerList.add(number);
            }
            return integerList;
        }
        return new ArrayList<>();
    }


    public static String parseListIntegerToString(List<Integer> inputs) {
        if (!inputs.isEmpty()) {
            StringJoiner joiner = new StringJoiner(", ");
            for (Integer integer : inputs) {
                joiner.add(String.valueOf(integer).trim());
            }
            return joiner.toString();
        }
        return "";
    }
}
