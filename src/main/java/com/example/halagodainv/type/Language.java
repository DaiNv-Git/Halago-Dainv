package com.example.halagodainv.type;

import org.apache.commons.lang3.StringUtils;

public enum Language {

    English(1, "english"),
    Vietnamese(2, "vietnamese");

    Integer id;
    String name;

    Language(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Boolean isExist(String name) {
        if(StringUtils.isBlank(name)) {
            return false;
        }

        for(Language item : Language.values()) {
            if(name.equalsIgnoreCase(item.name)) {
                return true;
            }
        }
        return false;
    }
}
