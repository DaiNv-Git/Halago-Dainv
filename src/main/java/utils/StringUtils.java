package utils;

import com.example.halagodainv.config.Constant;
import com.fasterxml.jackson.databind.JsonNode;


import java.util.ArrayList;
import java.util.Random;

public class StringUtils {

    public static String setImgSeo(String img,String title){
        StringBuilder builder = new StringBuilder();
        builder.append(img).append("#").append(title);
        return builder.toString();
    }

//    public static SeoImg getSeoImg(String image) {
//        String[] img = image.split("#");
//        SeoImg seoImg = new SeoImg();
//        seoImg.setImg(img[0]);
//        seoImg.setTitle(img[1]);
//        return seoImg;
//    }
    public static String convertArraytoString(ArrayList<String> arrString) {
        if (arrString != null) {
            StringBuilder builder = new StringBuilder();
            for (String s : arrString) {
                builder.append(s.replaceAll(Constant.IMAGE_DOMAIN, "") + Constant.SPACE_ELEMENTS);
            }
            String str = builder.toString();
            return str;
        } else return null;
    }

    public static String convertArraytoInteger(ArrayList<Integer> arrString) {
        if (arrString != null) {
            StringBuilder builder = new StringBuilder();
            for (Integer s : arrString) {
                builder.append(s + Constant.SPECIAL_CHARACTERS);
            }
            String str = builder.toString();
//            String srt = str;
            return str;
        } else return null;
    }

    public static ArrayList<String> getArrayStringJson(JsonNode arrNode, String name) {
        ArrayList<String> list = new ArrayList<>();
        if (arrNode.has(name)) {
            if (arrNode.get(name).isArray()) {
                for (JsonNode objNode : arrNode.get(name)) {
                    list.add(objNode.asText());
                }
            }

        }
        return list;
    }

    public static ArrayList<String> stringToStringArray(String s) {
        ArrayList<String> sA = new ArrayList<String>();
        if (s != null) {
            String[] arr = s.split(Constant.SPACE_ELEMENTS);
            for (String text : arr) {
                if (!text.equals("")) {
                    sA.add(Constant.IMAGE_DOMAIN + text);
                }
            }
        }
        return sA;
    }

    public static ArrayList<String> stringToStringArray1(String s) {
        ArrayList<String> sA = new ArrayList<String>();
        if (s != null) {
            String[] arr = s.split(Constant.SPACE_ELEMENTS);
            for (String text : arr) {
                if (!text.equals("")) {
                    sA.add(text);
                }
            }
        }
        return sA;
    }

    public static boolean random() {
        Random rd = new Random();
        Boolean b = rd.nextBoolean();
        return b;
    }

    public static int Radom(int min, int max) {
        Random rn = new Random();
        int range = max - min + 1;
        int randomNum = min + rn.nextInt(range);
        return randomNum;
    }

    public static ArrayList<Integer> getArrayIntegerJson(JsonNode arrNode, String name) {
        ArrayList<Integer> list = new ArrayList<>();
        if (arrNode.has(name)) {
            if (arrNode.get(name).isArray()) {
                int i = 0;
                for (JsonNode objNode : arrNode.get(name)) {
                    list.add(objNode.asInt());
                }
            }
        }
        return list;
    }

    public static ArrayList<String> hashTagToStringArray(String s) {
        ArrayList<String> sA = new ArrayList<String>();
        if (s != null) {
            String[] arr = s.split(Constant.SPECIAL_CHARACTERS);
            for (String text : arr) {
                if (!text.equals("")) {
                    sA.add(text);
                }
            }
        }
        return sA;
    }

    public static ArrayList<Integer> hashTagToIntArray(String s) {
        ArrayList<Integer> sA = new ArrayList<Integer>();
        if (s != null) {
            String[] arr = s.split(Constant.SPECIAL_CHARACTERS);
            for (String text : arr) {
                if (!text.equals("")) {
                    sA.add(Integer.valueOf(text));
                }
            }
        }
        return sA;
    }

    public static ArrayList<String> getArrayHashTagJson(JsonNode arrNode, String name) {
        ArrayList<String> list = new ArrayList<>();
        if (arrNode.has(name)) {
            if (arrNode.get(name).isArray()) {
                for (JsonNode objNode : arrNode.get(name)) {
                    list.add(objNode.asText().replaceAll(".0",""));
                }
            }

        }
        return list;
    }

    public static String convertHashTagtoString(ArrayList<String> arrString) {
        if (arrString != null) {
            StringBuilder builder = new StringBuilder();
            for (String s : arrString) {
                builder.append(s + Constant.SPECIAL_CHARACTERS);
                System.out.println(s);
            }
            String str = builder.toString();
            return str;
        } else return null;
    }

    public static String convertHashTagtoStringSeoImg(ArrayList<String> arrString) {
        if (arrString != null) {
            StringBuilder builder = new StringBuilder();
            for (String s : arrString) {
                builder.append(s+"#a"+ Constant.SPECIAL_CHARACTERS);
                System.out.println(s);
            }
            String str = builder.toString();
            return str;
        } else return null;
    }
}