package pageobject.model;

import lombok.AllArgsConstructor;


public class Contact {
	public String name;
	public String email;
	public String comment;
	public Contact(String name, String email, String comment) {
		this.name = name;
		this.email = email;
		this.comment = comment;
	}
	
}
