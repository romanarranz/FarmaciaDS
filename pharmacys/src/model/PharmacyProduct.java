package model;

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
@Table(name="PHARMACY_PRODUCT")
@XmlRootElement(name="product")
@XmlType(propOrder={
		"id",
		"pharmacyid",
		"productId",
		"price",
		"stock",
		"queryCount"
})
public class PharmacyProduct {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="PHARMACY_ID")
	private String pharmacyId;
	
	@Column(name="PRODUCT_ID")
	private int productId;
	
	@Column(name="PRICE")
	private float price;
	
	@Column(name="STOCK")
	private int stock;
	
	@Column(name="QUERY_COUNT")
	private int queryCount;
	
	// ID
	@XmlElement(required=true)
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	// PHARMACYID
	@XmlElement(required=true)
	public String getPharmacyId(){
		return this.pharmacyId;
	}
	public void setPharmacyId(String cif){
		this.pharmacyId = cif;
	}
	
	// PRODUCTID
	@XmlElement(required=true)
	public int getProductId(){
		return this.productId;
	}
	public void setProductId(int id){
		this.productId = id;
	}

	// PRICE
	@XmlElement(required=true)
	public float getPrice(){
		return this.price;
	}
	public void setPrice(float price){
		this.price = price;
	}
	
	// STOCK
	@XmlElement(required=true)
	public int getStock(){
		return this.stock;
	}
	public void setStock(int stock){
		this.stock = stock;
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
