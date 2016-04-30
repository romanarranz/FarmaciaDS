package dao;

public class DBConnector {
	private final PharmacyDao pharmacydao;
	private final ProductDao productdao;
	
	public DBConnector(){
		pharmacydao = new PharmacyDao();
		productdao = new ProductDao();
	}
}
