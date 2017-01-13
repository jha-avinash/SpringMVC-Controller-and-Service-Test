package edu.mum.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.domain.Category;
import edu.mum.domain.Product;
import edu.mum.repository.CategoryRepository;
import edu.mum.repository.ProductRepository;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
  	public List<Category> getAll() {
		return categoryRepository.getAll();
	}
	
	public Category getCategory(int id) {
		return categoryRepository.getCategory(id);
 	}
	
	
		   
}
 
