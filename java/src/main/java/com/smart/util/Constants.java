package com.smart.util;

public class Constants {
	
	public static final String DOMAIN_NAME = "smartiot";
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
    public static final Integer CODE_LENGTH = 10;
	public static final Integer ACTIVATION_CODE = 7;
	public static final Integer ACTIVATION_CODE_VALIDITY = 1*60*60;
	public static final Integer USER_ACTIVATION_TOKEN_LENGHT = 50;
	public static final Integer USER_TOKEN_VALIDITY = 2*60*60;
	
	// config for esp client
	public static final String DEFAULT_ESSID="smartiot";
	public static final String DEFAULT_ESSID_PASSWORD="smartioT";
	public static final String MICROPYTHON_APP_VERSION = "1.0.0";
	public static final String	GITHUB_PATH = "https://raw.githubusercontent.com/pawansankhle/my-iot/master/dev/";
	public static final String UPLOAD_PATH="upload";
	
	// roles for user
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";
	
	// JSCYPT config constants
	public static final String JSYPT_ALGORITHM = "PBEWithMD5AndDES";
	public static final String JSYPT_SECRET = "smartI@t";
	
	
	
	
	
}
