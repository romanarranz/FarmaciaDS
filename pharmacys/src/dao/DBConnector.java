package dao;

import java.util.List;

import model.Pharmacy;
import model.Product;
import model.UserAbstraction;
import model.UserRefinedAbstraction;

public class DBConnector {
	private final PharmacyDao pharmacydao;
	private final ProductDao productdao;
	private final UserDao userdao;
	
	public DBConnector(){
		pharmacydao = new PharmacyDao();
		productdao = new ProductDao();
		userdao = new UserDao();
	}
	
	// PHARMACYDAO
	public Pharmacy getPharmacyByCIF(String cif){
		return this.pharmacydao.getPharmacyByCIF(cif);
	}	
	public List<Pharmacy> getAllPharmacies(){
		return this.pharmacydao.getAllPharmacies();
	}
	public boolean insertPharmacy(Pharmacy p){
		return this.pharmacydao.insertPharmacy(p);
	}	
	public boolean updatePharmacy(Pharmacy p){
		return this.pharmacydao.updatePharmacy(p);
	}
	public boolean deletePharmacy(Pharmacy p){
		return this.pharmacydao.deletePharmacy(p);
	}
	
	// PRODUCTDAO
	public Product getProductById(int id){
		return this.productdao.getProductById(id);
	}
	public List<Product> getAllProducts(){
		return this.productdao.getAllProducts();
	}
	public boolean insertProduct(Product p){
		return this.productdao.insertProduct(p);
	}
	public boolean updateProduct(Product p){
		return this.productdao.updateProduct(p);
	}
	public boolean deleteProduct(Product p){
		return this.productdao.deleteProduct(p);
	}
	
	// USERDAO
	public UserAbstraction getUserFuncById(String email){
		return this.userdao.getUserFuncById(email);
	}
	public UserRefinedAbstraction getUserByEmailPassword(String email, String password){
		return this.userdao.getUserByEmailPassword(email, password);
	}
	public UserRefinedAbstraction getUserById(String email){
		return this.userdao.getUserById(email);
	}
	public List<UserRefinedAbstraction> getAllUsers(){
		return this.userdao.getAllUsers();
	}
	public boolean insertUser(UserRefinedAbstraction user){
		return this.userdao.insertUser(user);
	}
	public boolean updateUser(UserRefinedAbstraction user){
		return this.userdao.updateUser(user);
	}
}
