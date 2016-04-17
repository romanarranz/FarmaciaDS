package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="EMPLOYEE")
@XmlRootElement(name="employee")
@XmlType(propOrder={"id","name","email"})
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int empID;
	@Column(name="NAME")
	private String name;
	@Column(name="EMAIL")
	private String email;
	
	@XmlElement(required=true)
	public int getId(){
		return this.empID;
	}
	
	public void setId(int id){
		this.empID = id;
	}
	
	@XmlElement(required=true)
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	@XmlElement(required=true)
	public String getEmail(){
		return this.email;
	}
	
	public void setAge(String email){
		this.email = email;
	}
}
