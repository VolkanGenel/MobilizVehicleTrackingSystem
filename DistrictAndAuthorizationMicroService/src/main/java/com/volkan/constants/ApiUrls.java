package com.volkan.constants;

public class ApiUrls {
    /**
     * Uygulamanız içinde kullanılan tüm erişim noktalarının listesi burada tutulur,
     * böylece farklı ortamlar i.in kullanılacak end pointler tek bir noktadan değiştirilebilir.
     */
    //public static final String VERSION = "/api/v1";
    public static final String AUTHORIZATION = "/authorization"; //VERSION+
    public static final String ZONE= "/zone"; //VERSION+
    public static final String SECTOR = "/sector"; //VERSION+
    public static final String CREATE = "/create";
    public static final String UPDATE= "/update";
    public static final String DELETE_BY_ID = "/deletebyid";
    public static final String FIND_BY_ID = "/findbyid";
    public static final String GET_TOKEN_FIND_BY_ID = "/gettokenfindbyid";
    public static final String AUTHORIZE_VEHICLE = "/authorizevehicle";
    public static final String AUTHORIZE_USER = "/authorizeuser";
    public static final String FIND_ALL = "/findall";
}
