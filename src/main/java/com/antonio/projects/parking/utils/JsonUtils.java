package com.antonio.projects.parking.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	public static ObjectMapper mapper=new ObjectMapper();
	
	public static <T> T serialize(String s, Class<T> clazz) {
		if(StringUtils.isBlank(s)) throw new RuntimeException("Parking Lot no disponible");
		
		try {
			return mapper.readValue(s, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
