package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InventoryPK implements Serializable {
	
	@Column(name="PHARMACYID")
	protected Integer pharmacyId;
	
	@Column(name="PRODUCTID")
	protected Integer productId;
	
	public InventoryPK(){}
	public InventoryPK(Integer pharmacyId, Integer productId){
		this.pharmacyId = pharmacyId;
		this.productId = productId;
	}
	
	public Integer getPharmacyId(){
		return this.pharmacyId;
	}
	public void setPharmacyId(Integer id){
		this.pharmacyId = id;
	}
	
	public Integer getProductId(){
		return this.productId;
	}
	public void setProductId(Integer id){
		this.productId = id;
	}
	
	public boolean equals( Object obj ) {
		boolean result = false;
		InventoryPK i = (InventoryPK) obj;
		if( i.pharmacyId == this.pharmacyId && i.productId == this.productId)
			result = true;
		return result;
	}
}
