package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table(name="ORDER")
@XmlRootElement(name="order")
@XmlType(propOrder={"id","date","listProductPharmacy", "totalPrice", "userEmail"})
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int orderID;
	
	@Column(name="DATE", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name="LIST_PRODUCTPHARMACY")	
	private String listProductPharmacy;
	
	@Column(name="TOTALPRICE")
	private float totalPrice;
	
	@Column(name="USEREMAIL")
	private String userEmail;
	
	// DATE
	@XmlElement(required=true)
	public String getDate(){
		return this.date.toString();
	}
	
	// LIST_PRODUCTPHARMACY
	@XmlElement(required=true)
	public List<Integer> getListProductPharmacy(){
		
		List<Integer> result = new ArrayList<Integer>();
		String[] split = this.listProductPharmacy.split(",");
		for (int i = 0; i < split.length; i++) {
		    result.add(Integer.parseInt(split[i]));
		}
		
		return result;
	}
	
	// TOTALPRICE
	@XmlElement(required=true)
	public float getTotalPrice(){
		return this.totalPrice;
	}
	
	// USEREMAIL
	@XmlElement(required=true)
	public String getUserEmal(){
		return this.userEmail;
	}
	
}
