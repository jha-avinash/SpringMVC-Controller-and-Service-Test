package edu.mum.builder;

import java.util.Arrays;
import java.util.List;

import edu.mum.domain.Product;

public class ProductListBuilder {

	public ProductBuilder productBuilderOne = new ProductBuilder()
            .withId(1L)
            .withCategory(new CategoryBuilder()
                    .withId(1)
                    .withName("Sports")
                   .build())
            .withDescription("Two wheels")
            .withName("Bicycle");

	public ProductBuilder productBuilderTwo = new ProductBuilder()
            .withId(2L)
            .withCategory(new CategoryBuilder()
            .withId(2)
            .withName("Solids")
           .build())
            .withDescription("6 sides")
            .withName("Cube");

	public List<Product> build() {
		
	    Product first = productBuilderOne.build();	
	    Product second = productBuilderTwo.build();
 	    return (List<Product>) Arrays.asList(first, second);
	}
	
	public  ProductBuilder getProductBuilderOne() {
		return productBuilderOne;
	}

}
