package model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class UserRefinedAbstraction implements UserAbstraction {
	
	private UserImplementator implementator;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private String email;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ACTIVE")
	private int active;
	
	@Column(name="RESETHASH")
	private String resetHash;
	
	@Column(name="LIST_ORDERS")
	private Map<Integer, Order> orders;
	
	@Column(name="ROLE")
	private int role;
	
	public UserRefinedAbstraction(UserImplementator uimpl){
		this.implementator = uimpl;
		orders = new HashMap<Integer, Order>();
	}
	
	// EMAIL
	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
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
	
	// PASSWORD
	public String getPassword(){
		return this.password;
	}
	public void setPassword(String passw){
		this.password = passw;
	}
	
	// ACTIVE
	public int getActive(){
		return this.active;
	}
	public void setActive(){
		this.active = 1;
	}
	
	// RESET HASH
	public String getResetHash(){
		return this.resetHash;
	}
	public void setResetHash(String hash){
		this.resetHash = hash;
	}

	// ROLE
	public int getRole(){
		return this.role;
	}
	public void setRole(int r){
		this.role = r;
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
