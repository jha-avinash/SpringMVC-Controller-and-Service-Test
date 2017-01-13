package edu.mum.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

 import edu.mum.domain.Category;

import org.springframework.stereotype.Repository;

import edu.mum.domain.Product;
import edu.mum.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	
		private List<Product> listOfProducts = new ArrayList<Product>();
		 
		public List<Product> getAll() {
		return listOfProducts;
	}
	 
	public void save(Product product) {
		listOfProducts.add(product);
		return ;
	}
	
	public Product findOne(Long id) {
		return null;
	}

		   
}
 
