package com.hitss.AcademyPlatform.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGrantedAuthorityJsonCreator {
	
	@JsonCreator
	public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role) {

	}

}
