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
@XmlRootElement(name="pharmacyProduct")
@XmlType(propOrder={
		"id",
		"pharmacyId",
		"productId",
		"price",
		"quantity"
})
public class PharmacyProduct {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="PHARMACY_ID")
	private int pharmacyId;

	@Column(name="PRODUCT_ID")
	private int productId;
	
	@Column(name="PRICE")
	private double price;
	
	@Column(name="QUANTITY")
	private int quantity;
	
	@XmlElement(required=true)
	public int getId(){
		return this.id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	@XmlElement(required=true)
	public int getPharmacyId(){
		return this.pharmacyId;
	}
	public void setPharmacyId(int id){
		this.pharmacyId = id;
	}
	
	@XmlElement(required=true)
	public int getProductId(){
		return this.productId;
	}
	public void setProductId(int id){
		this.productId = id;
	}
	
	@XmlElement(required=true)
	public double getPrice(){
		return this.price;
	}
	public void setId(double p){
		this.price = p;
	}
	
	@XmlElement(required=true)
	public int getQuantity(){
		return this.quantity;
	}
	public void setQuantity(int q){
		this.quantity = q;
	}
}
