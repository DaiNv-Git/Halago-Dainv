package com.example.halagodainv.model;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author Chidq
 * @project halago
 * @created 23/06/2021 - 6:45 PM
 */
public class Slug {
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    public static String makeSlug(String input) {
        if (input == null)
            throw new IllegalArgumentException();

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase(Locale.ENGLISH);
    }

//    public static void main(String[] args) {
//        String slug = makeSlug("HALAGO - INFLUENCER QUAY REVIEW NHẬN TRÊN DƯỚI  500.000 VNĐ VÀ ĐƯỢC TRỞ THÀNH THÀNH VIÊN CỦA HALAGO, THAM GIA NHIỀU DỰ ÁN CỦA HALAGO SẮP TỚI  ");
//        System.out.println("slug = " + slug);
//    }
}
