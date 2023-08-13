package com.volkan.constants;

public class ApiUrls {
    /**
     * Uygulamanız içinde kullanılan tüm erişim noktalarının listesi burada tutulur,
     * böylece farklı ortamlar i.in kullanılacak end pointler tek bir noktadan değiştirilebilir.
     */
    //public static final String VERSION = "/api/v1";
    public static final String VEHICLE= "/vehicle"; //VERSION+

    //VehicleController
    public static final String CREATE_VEHICLE = "/create";
    public static final String UPDATE_VEHICLE = "/update";
    public static final String DELETE_BY_ID = "/deletebyid";
    public static final String FIND_BY_ID = "/findbyid";
    public static final String GET_TOKEN_FIND_BY_ID = "/gettokenfindbyid";
    public static final String FIND_ALL = "/findall";
}
