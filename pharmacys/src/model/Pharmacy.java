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
@Table(name="PHARMACY")
@XmlRootElement(name="pharmacy")
@XmlType(propOrder={
		"cif",
		"name",
		"description",
		"phoneNumber",
		"startSchedule",
		"endSchedule"
})
public class Pharmacy {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CIF")
	private String cif;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PHONE_NUMBER")
	private int phoneNumber;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="START_SCHEDULE")
	private int startShedule; 
	
	@Column(name="END_SCHEDULE")
	private int endtShedule; 
	
	public Pharmacy(){}
	public Pharmacy(String cif, String name, int phoneNumber, String descr, int startSch, int endSch){
		this.cif = cif;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.description = descr;
		this.startShedule = startSch;
		this.endtShedule = endSch;
	}
	
	// CIF
	@XmlElement(required=true)
	public String getCif(){
		return this.cif;
	}	
	public void setCif(String cif){
		this.cif = cif;
	}
	
	// NAME
	@XmlElement(required=true)
	public String getName(){
		return this.name;
	}	
	public void setName(String name){
		this.name = name;
	}
	
	//PHONE_NUMBER
	@XmlElement(required=true)
	public int getPhoneNumber(){
		return this.phoneNumber;
	}
	public void setPhoneNumber(int phoneN){
		this.phoneNumber = phoneN;		
	}
	
	// DESCRIPTION
	@XmlElement(required=true)
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String descr){
		this.description = descr;
	}
	
	// START_SCHEDULE
	@XmlElement(required=true)
	public int getStartSchedule(){
		return this.startShedule;
	}
	public void setStartSchedule(int h){
		this.startShedule = h;
	}
	
	// END_SCHEDULE
	@XmlElement(required=true)
	public int getEndSchedule(){
		return this.endtShedule;
	}
	public void setEndSchedule(int h){
		this.endtShedule = h;
	}
	
}
