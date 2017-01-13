package edu.mum.service;

import java.util.List;

import edu.mum.domain.Product;

 public interface ProductService   {
	
	 // Returns a List of all Products
	public List<Product> getAll();
	// Save a Product
	public void save(Product product);
	// Find a Product by id
 	public Product findOne(Long id);

		   
}
 
