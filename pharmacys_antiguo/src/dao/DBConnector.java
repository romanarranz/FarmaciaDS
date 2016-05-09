package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Pharmacy;
import model.PharmacyProduct;
import model.Product;
import model.UserAbstraction;
import model.UserRefinedAbstraction;

public class DBConnector {
	private final PharmacyDao pharmacydao;
	private final ProductDao productdao;
	private final UserDao userdao;
	private final PharmacyProductDao pharmacyproductdao;
	
	public DBConnector(){
		pharmacydao = new PharmacyDao();
		productdao = new ProductDao();
		userdao = new UserDao();
		pharmacyproductdao = new PharmacyProductDao();
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
	public Product getLastProductInserted(){
		return this.productdao.getLastInserted();
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
	
	//PHARMACYPRODUCTDAO
	public PharmacyProduct getById(int id){
		return this.pharmacyproductdao.getById(id);
	}
	public List<Product> getAllProductsByPharmacy(String cif){
		List<PharmacyProduct> pp = this.pharmacyproductdao.getAllProductsByPharmacy(cif);
		Long[] arrayIds = new Long[pp.size()];
					
		for(int i = 0; i<pp.size(); i++){
			arrayIds[i] = (long) pp.get(i).getProductId();
			System.out.println(arrayIds[i]);
		}		
		
		List<Long> listProductsIds = Arrays.asList(arrayIds);
		return this.productdao.getProductByIdsList(listProductsIds);
	}
	public List<Product> getTopProducts(int n, String cif){
		List<PharmacyProduct> pp = this.pharmacyproductdao.getTopProducts(n, cif);
		List<Product> products = new ArrayList<Product>();
		
		for(int i = 0; i<pp.size(); i++){
			products.add(this.productdao.getProductById(pp.get(i).getProductId()));
		}
		
		return products;
	}
	// por si el farmaceutico le quiere cambiar el precio al producto
	public PharmacyProduct getByPharmacyProduct(String pharmacyId, int productId){
		return this.pharmacyproductdao.getByPharmacyProduct(pharmacyId, productId);
	}
	public boolean insertPharmacyProduct(PharmacyProduct p){
		return this.pharmacyproductdao.insertPharmacyProduct(p);
	}
	public boolean updatePharmacyProduct(PharmacyProduct p){
		return this.pharmacyproductdao.updatePharmacyProduct(p);
	}	
	public boolean deletePharmacyProduct(PharmacyProduct p){
		return this.pharmacyproductdao.deletePharmacyProduct(p);
	}
}
