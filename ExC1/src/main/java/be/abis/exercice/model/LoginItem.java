package be.abis.exercice.model;

public class LoginItem {

	public String email;
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
