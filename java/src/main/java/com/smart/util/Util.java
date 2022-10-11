package com.smart.util;

import java.util.Optional;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	
	public static String getMessage(String msg,Boolean isSuccess) {
		JSONObject result = new JSONObject(); 
		
		try {
			if(isSuccess) {
				result.put("status", "success");
			}else {
				result.put("status", "failed");
			}
			
			result.put("msg", msg);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return result.toString();
		
	}
	
	public static String generateRandom(int len) {
		
        String SALTCHARS = "qwertyuiopasdfghjklmnbvcxzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < len) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
	}
	
	
	public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	public static Optional<Object> convertJsonToObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
             return Optional.of(mapper.readValue(json, Object.class));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
	}
	
    
}
