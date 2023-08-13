package com.volkan.constants;

public class ApiUrls {
    /**
     * Uygulamanız içinde kullanılan tüm erişim noktalarının listesi burada tutulur,
     * böylece farklı ortamlar i.in kullanılacak end pointler tek bir noktadan değiştirilebilir.
     */
    //public static final String VERSION = "/api/v1";
    public static final String USER= "/user";    //VERSION+

    //UserController
    public static final String REGISTER = "/register";
    public static final String CREATE_STANDARD_USER = "/create";
    public static final String LOGIN = "/login";
    public static final String UPDATE_PASSWORD = "/update";
    public static final String DELETE_BY_ID = "/deletebyid";
    public static final String GET_TOKEN_FIND_BY_ID = "/gettokenfindbyid";
    public static final String FIND_ALL = "/findall";

}
