package be.abis.exercice.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginItem {

	@NotBlank( message="Please enter the Email")
	@Email(message = "Email should be valid")	
	public String email;
	
	@NotBlank( message="Please enter the Password")
	@Size(min=6,  message="Password should be minimum 6 characters")
	public String password;
	
	
	public LoginItem() {
		super();
	}

	public LoginItem(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
