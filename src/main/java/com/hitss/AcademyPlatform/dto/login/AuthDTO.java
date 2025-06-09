package com.hitss.AcademyPlatform.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

public class AuthDTO {
	@Data
	public static class LoginRequest {
		private String email;
		private String password;
	}

	@Data
    public static class RegisterRequest {
        private String nombre;
        private String email;
        private String password;
        private String rol;

        private String especialidad;    
        private String codigoMatricula;  
    }

	@Getter
	@AllArgsConstructor
	public static class AuthResponse {
		private String accessToken;
	}
}

