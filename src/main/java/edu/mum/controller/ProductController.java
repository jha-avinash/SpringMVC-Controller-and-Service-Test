package edu.mum.controller;

import java.util.List;

import edu.mum.domain.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.domain.Product;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

@Controller
public class ProductController {
 
	@Autowired
	ProductService productService;
 	
	@Autowired
	CategoryService categoryService;
 	
     @RequestMapping(value={"/","/product"}, method = RequestMethod.GET)
    public String inputProduct(@ModelAttribute("newProduct") Product newProduct, Model model) {
 
         List<Category> categories = categoryService.getAll();
        model.addAttribute("categories", categories);
     
     
   	return "ProductForm";
    }

    @RequestMapping(value="/product", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("newProduct") Product product ) { 
        Category category = categoryService.getCategory(product.getCategory().getId());
        product.setCategory(category);

    	productService.save(product);
    	
        return "ProductDetails";
    }
    
    
    @RequestMapping(value="/listproducts", method = RequestMethod.GET)
    public String listProducts(Model model ) {
    	
    	
		List<Product> list1 = productService.getAll();
		model.addAttribute("products",  list1);
		//model.addAttribute("Santosh","santosh");
    	
        return "ListProducts";
    }
     
}
