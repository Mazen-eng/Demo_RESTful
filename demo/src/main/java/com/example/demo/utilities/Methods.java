package com.example.demo.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 
 * @author Mazen
 *
 */
@Component
public class Methods {

	private static ObjectMapper objMapper;
	
	public static <T> T mapToObject(Object sourceObject, Class<T> destinationClass) {
		return objMapper.convertValue(sourceObject, destinationClass);
	}
	
	@Autowired
	private Methods(ObjectMapper objMapper) {
		Methods.objMapper = objMapper;
	}
}
