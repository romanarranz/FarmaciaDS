package dao;

import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Inventory;
import model.Pharmacy;
import model.Product;
import model.UserAbstraction;
import model.UserRefinedAbstraction;

public class DBConnector {
	private final UserDao userdao;
	private final PharmacyDao pharmacydao;
	private final CategoryDao categorydao;
	private final ProductDao productdao;
	private final InventoryDao inventorydao;
	
	public DBConnector(){
		userdao = new UserDao();
		pharmacydao = new PharmacyDao();
		categorydao = new CategoryDao();
		productdao = new ProductDao();
		inventorydao = new InventoryDao();
	}
	
	// CATEGORYDAO
	public List<Category> getAllCategories(){
		return this.categorydao.getAllCategories();
	}
	public Category getCategoryById(int id){
		return this.categorydao.getCategoryById(id);
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
	
	// INVENTORYDAO
	public List<Product> getProductsListByCif(String cif){
		List<Integer> productIds =  inventorydao.getAllProductsIdsByCif(cif);
		List<Product> products = new ArrayList<Product>();
	
		if(productIds != null){
			Product p;
			for(Integer i : productIds){
				p = this.getProductById(i);
				
				if(p != null){
					products.add(p);
				}
			}
		}
		
		return products;
	}
	public boolean insertInventory(Inventory i){
		return this.inventorydao.insertInventory(i);
	}
	public boolean updateInventory(Inventory i){
		return this.inventorydao.updateInventory(i);
	}
	public boolean deleteInventory(Inventory i){
		return this.inventorydao.deleteInventory(i);
	}
	public Inventory getInventoryById(String cif, Integer productId){
		return this.inventorydao.getInventoryById(cif, productId);
	}	
	public List<Product> getTopProducts(int n, String cif){
		List<Inventory> filterProducts = inventorydao.getTopProducts(n, cif);
		List<Product> products = new ArrayList<Product>();
		
		if(filterProducts != null){
		
			Product p;
			for(Inventory i : filterProducts){
				p = this.getProductById(i.getProductId());
				
				if(p != null){
					products.add(p);
				}
			}
		}
		
		return products;
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
	public UserRefinedAbstraction getUserByResetHash(String hash){
		return this.userdao.getUserByResetHash(hash);
	}
}