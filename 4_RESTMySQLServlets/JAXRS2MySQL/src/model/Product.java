package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="INVENTORY")
@XmlRootElement(name="pharmacy")
@XmlType(propOrder={
		"id",
		"name",
		"description",
		"laboratory",		
		"units",
		"expirationDate",
		"size",
		"lot"
})
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int productID;
	
	@Column(name="NAME")
	private String name;

	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="LABORATORY")
	private String laboratory;
	
	@Column(name="UNITS")
	private String units;
	
	@Column(name="EXPIRATION_DATE")
	private Date expirationDate;
	
	@Column(name="SIZE")
	private int size;
	
	@Column(name="LOT")
	private String lot;
	
	public Product(){}
	public Product(String name, String descr, String lab, String u, Date expDate, int size, String lot){
		this.name = name;
		this.description = descr;
		this.laboratory = lab;
		this.units = u;
		this.expirationDate = expDate;
		this.size = size;
		this.lot = lot;
	}
	
	// ID
	public int getId(){
		return this.productID;		
	}
	public void setId(int id){
		this.productID = id;
	}
		
	// NAME
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	// DESCRIPTION
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String descr){
		this.description = descr;
	}
	
	// UNITS
	public String getUnits(){
		return this.units;
	}
	public void setUnits(String u){
		this.units = u;
	}
	
	// EXPIRATION_DATE
	public String getExpirationDate(){
		return this.expirationDate.toString();
	}
	public void setExpirationDate(Date d){
		this.expirationDate = d;
	}
	
	// SIZE
	public int getSize(){
		return this.size;
	}
	public void setSize(int s){
		this.size = s;
	}
	
	// LOT
	public String getLot(){
		return this.lot;		
	}
	public void setLot(String l){
		this.lot = l;
	}
}
