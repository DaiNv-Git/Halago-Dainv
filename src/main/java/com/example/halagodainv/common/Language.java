package com.example.halagodainv.common;

import lombok.Getter;

@Getter
public enum Language {
    EN("EN"),
    VN("VN");

    private final String language;

    Language(String language) {
        this.language = language;
    }
}
