package ru.rt.fsom.wfc.data.dict;

public final class SysParams {

    private SysParams() {
    }    
    public final static String LOGGER_NAME = "WFC";
    
    public final static String ITEM_ID = "ITEM_ID";
    public final static String OPEN_MODE = "OPEN_MODE";
    public final static String FORM_NAME = "FORM_NAME";
    public final static String USER_LOGIN = "UserLogin";
    public final static String USER_LOCALE = "UserLocale";
    public final static String JW_TOKEN = "Tocken";
                
    public static final String MAIN_PAGE   = "/views/monitor/monitor.xhtml";
    public static final String LOGIN_PAGE  = "/views/login.xhtml";
    public static final String LOGOUT_PAGE = "/views/logout.xhtml";
    public static final String EXPIRE_PAGE = "/views/expire.xhtml";
    
    public static final String PRIMEFACES_THEME = "primefaces_theme";
    
    public static final int LENGHT_NAME_ELIPSE = 60;
}