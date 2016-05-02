package model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import dao.DBConnector;

@Entity
@Table(name="RESERVATION")
public class Reservation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="USEREMAIL")
	private String userEmail;
	
	@Column(name="LIST_PRODUCT")
	private String productList;
	
	@Column(name="AVIABLE")
	private int aviable;
	
	// ID
	public int getId(){
		return this.id;		
	}
	
	// DATE
	public String getDate(){
		return this.date.toString();
	}
	public void setDate(Date t){
		this.date = t;
	}
	
	// USEREMAIL
	public String getUserEmail(){
		return this.userEmail;
	}
	public void setUserEmail(String email){
		this.userEmail = email;
	}
	
	// LIST_PRODUCT
	private Map<Integer, Product> getProductList(){
		Map<Integer, Product> productList = new HashMap<Integer, Product>();
		DBConnector dbc = new DBConnector();
		
		String [] plistArray = this.productList.split(",");
		int key;
		for(int i = 0; i<plistArray.length; i++){
			key = Integer.parseInt(plistArray[i]);
			productList.put(key, dbc.getProductById(key));
		}
		
		return productList;
	}
	
	// AVIABLE
	public int getAviable(){
		return this.aviable;
	}
	public void setAviable(int a){
		this.aviable = a;
	}
}
