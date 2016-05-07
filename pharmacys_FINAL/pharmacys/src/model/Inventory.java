package model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="INVENTORY")
@XmlRootElement(name="inventory")
@XmlType(propOrder={
		"pharmacyId",
		"productId",
		"price",
		"stock",		
		"queryCount"
})
public class Inventory implements Serializable {
	
	@EmbeddedId
	private InventoryPK pharmacyProduct;
	
	private float price;
	private int stock;
	private int queryCount;
	
	@XmlElement(required=true)
	public int getPharmacyId(){
		return this.pharmacyProduct.getPharmacyId();
	}
	public void setPharmacyId(int id){
		this.pharmacyProduct.setPharmacyId(id);
	}
	
	@XmlElement(required=true)
	public int getProductId(){
		return this.pharmacyProduct.getProductId();
	}
	public void setProductId(int id){
		this.pharmacyProduct.setProductId(id);
	}
	
	@XmlElement(required=true)
	public float getPrice(){
		return this.price;
	}
	public void setPrice(float p){
		this.price = p;
	}
	
	@XmlElement(required=true)
	public int getStock(){
		return this.stock;
	}
	public void setStock(int n){
		this.stock = n;
	}
	
	@XmlElement(required=true)
	public int getQueryCount(){
		return this.queryCount;
	}
	public void setQueryCount(int n){
		this.queryCount = n;
	}
}
