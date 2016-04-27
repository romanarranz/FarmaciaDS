package model;

import java.util.HashMap;
import java.util.Map;

public class UserRefinedAbstraction implements UserAbstraction {
	
	private UserImplementator implementator;
	private String name;
	private String surname;
	private String email;
	private String password;	
	private boolean active;
	private String resetHash;
	private Map<Integer, Order> orders;
	
	public UserRefinedAbstraction(UserImplementator uimpl){
		this.implementator = uimpl;
		orders = new HashMap<Integer, Order>();
	}
	
	// NAME
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	// SURNAME
	public String getSurname(){
		return this.surname;
	}
	public void setSurname(String surname){
		this.surname = surname;
	}
	
	// EMAIL
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	// PASSWORD
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String passw){
		this.password = passw;
	}
	
	public boolean registration(String email, String name, String surname, String password){
		return false;
	}
	
	@Override
	public boolean login(String email, String password) {
		boolean result = false;
		
		if(this.implementator.login(email, password)){
			result = true;
			this.email = email;
			this.password = password;
		}
			
		return result;
	}
	
	public boolean logout(){
		return false;
	}

	@Override
	public boolean sendResetMail() {
		return this.implementator.sendResetMail();
	}

	@Override
	public boolean sendVerificationMail() {
		return this.implementator.sendVerificationMail();
	}
}
