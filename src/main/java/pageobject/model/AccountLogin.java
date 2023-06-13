package pageobject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class AccountLogin {
	public String email;
	public String password;
	public AccountLogin(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
}
