package com.capgemini.onlinelenscart.DTO;

import jakarta.validation.constraints.NotEmpty;

public class LoginRequest{
		@NotEmpty(message = "username is required")
        String username;
        @NotEmpty(message = "password is required")
        String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public LoginRequest(@NotEmpty(message = "username is required") String username,
				@NotEmpty(message = "password is required") String password) {
			super();
			this.username = username;
			this.password = password;
		}
		public LoginRequest() {
			
		}
        

}

