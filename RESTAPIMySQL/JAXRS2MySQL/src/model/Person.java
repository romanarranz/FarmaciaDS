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
@Table(name="PERSON")
@XmlRootElement(name="person")
@XmlType(propOrder={"id","fullName","age"})
public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="FULL_NAME")
	private String fullname;
	@Column(name="AGE")
	private int age;
	
	@XmlElement
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	@XmlElement
	public String getFullName(){
		return this.fullname;
	}
	
	public void setFullName(String name){
		this.fullname = name;
	}
	
	@XmlElement
	public int getAge(){
		return this.age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
}
