package com.example.halagodainv.config;

public interface Constant {
    String valiemail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    String IMAGE_DIRECTION = "/home/upload_file/";
    String IMAGE_CAMPAIGN_DIRECTION = IMAGE_DIRECTION + "campaign/";
    String IMAGE_DOMAIN = "https://img.halago.vn/";
    String IMAGE_CAMPAIGN_DOMAIN = IMAGE_DOMAIN + "campaign/";
    String SPACE_ELEMENTS = ",,";
    String SPECIAL_CHARACTERS = ",";
    String DATE_FORMAT = "dd-MM-yyyy";
    String UPPER_CASE = "QWERTYUIOPASDFGHJKLZXCVBNM";
    String LOWER_CASE = UPPER_CASE.toLowerCase();
    String NUMBER = "1234567890";
    int active = 1;
    int inactive = 0;
    int tontai = 1;
    int khongtontai = 0;
    int SUCCESS = 200;
    int FIRST_LOGIN_FB = 202;
    int NOT_REGISTER = 2;
    int FAILED = 0;
    String ADMIN = "1" ;
    String INFLUENCER = "2" ;
    String STAFF = "3" ;
    String BRAND = "4" ;

    int APPROVE = 1;
    int WAITING_APPROVE = 0;
    int REFUSE = 2;

    int HEADER=1;
    int BODY=2;
    int REASON=3;
    int FOOTER=4;
    int BRAND_DEPLOYMENT=5;
    int KOLS_MARKETING=3;
    int KOLS_STATISTICAL=5;

    String LANGUAGE_EN = "en" ;
    String LANGUAGE_VN = "vn" ;

    int HIGHLIGHT = 1;

    int INTERNAL_NEWS =  1;
    int FOREIGN_NEWS =  2;
    int NEWS_PAPERS =  3;
    int TYPE_ABOUT =  1;
    int TYPE_VISION =  2;



//    String GOOGLE_CLIENT_ID = "311546406630-ag3qepedv1is623p531q87kaqvgtvbgs.apps.googleusercontent.com";
//    String GOOGLE_CLIENT_SECRET = "j3c84fHOst9Tm_gMATpeJSl-";
//    String GOOGLE_REDIRECT_URI = "http://localhost:8083/login-google ";
//    String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
//    String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
//    String GOOGLE_GRANT_TYPE = "authorization_code";


}
