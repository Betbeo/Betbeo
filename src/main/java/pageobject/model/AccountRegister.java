package pageobject.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


public class AccountRegister {

	public String lastName;
	public String firstName;
	public String email;
	public String password;
	public AccountRegister(String lastName, String firstName, String email, String password) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}
	
}
