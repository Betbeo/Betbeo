package pageobject.model;

import lombok.AllArgsConstructor;


public class RegisterAgent {
	public String name;
	public String storeName;
	public String address;
	public RegisterAgent(String name, String storeName, String address) {
		this.name = name;
		this.storeName = storeName;
		this.address = address;
	}
	
}
