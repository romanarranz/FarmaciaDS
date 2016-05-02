package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="PRODUCT")
@XmlRootElement(name="product")
@XmlType(propOrder={
		"id",
		"category",
		"name",
		"description",
		"laboratory",		
		"units",
		"expirationDate",
		"size",
		"lot",
		"urlImg",
		"queryCount"
})
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="CATEGORY")
	private String category;

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
	
	@Column(name="URL_IMG")
	private String urlImg;
	
	@Column(name="QUERY_COUNT")
	private int queryCount;
	
	public Product(){}
	public Product(String name, String descr, String lab, String u, Date expDate, int size, String lot, String url){
		this.name = name;
		this.description = descr;
		this.laboratory = lab;
		this.units = u;
		this.expirationDate = expDate;
		this.size = size;
		this.lot = lot;
		this.urlImg = url;
		this.queryCount = 0;
	}
	
	// ID
	@XmlElement(required=true)
	public int getId(){
		return this.id;		
	}
	public void setId(int id){
		this.id = id;
	}
	
	// CATEGORY
	@XmlElement(required=true)
	public String getCategory(){
		return this.category;
	}
	public void setCategory(String newCategory){
		this.category = newCategory;
	}
		
	// NAME
	@XmlElement(required=true)
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	// DESCRIPTION
	@XmlElement(required=true)
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String descr){
		this.description = descr;
	}
	
	// LABORATORY
	@XmlElement(required=true)
	public String getLaboratory(){
		return this.laboratory;
	}
	public void setLaboratory(String lab){
		this.laboratory = lab;
	}
	
	// UNITS
	@XmlElement(required=true)
	public String getUnits(){
		return this.units;
	}
	public void setUnits(String u){
		this.units = u;
	}
	
	// EXPIRATION_DATE
	@XmlElement(required=true)
	public String getExpirationDate(){
		return this.expirationDate.toString();
	}
	public void setExpirationDate(Date d){
		this.expirationDate = d;
	}
	
	// SIZE
	@XmlElement(required=true)
	public int getSize(){
		return this.size;
	}
	public void setSize(int s){
		this.size = s;
	}
	
	// LOT
	@XmlElement(required=true)
	public String getLot(){
		return this.lot;		
	}
	public void setLot(String l){
		this.lot = l;
	}
	
	// URL_IMG
	@XmlElement(required=true)
	public String getUrlImg(){
		return this.urlImg;		
	}
	public void setUrlImg(String uri){
		this.urlImg = uri;
	}
	
	// QUERY_COUNT
	@XmlElement(required=true)
	public int getQueryCount(){
		return this.queryCount;
	}
	public void setQueryCount(int count){
		this.queryCount = count;
	}
}
