package edu.mum.service;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.mum.builder.CategoryBuilder;
import edu.mum.builder.ProductBuilder;
import edu.mum.builder.ProductListBuilder;
import edu.mum.domain.Product;
import edu.mum.repository.ProductRepository;
import edu.mum.serviceimpl.ProductServiceImpl;

 
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepositoryMock;
     
    // Needs to be class NOT interface - how can you inject into an Interface?
    @InjectMocks
    private ProductServiceImpl productService;
 
    ProductBuilder bicycleProductBuilder;
	ProductBuilder cubeProductBuilder;
	
	List<Product> productList = new ArrayList<Product>();
	
    @Before
    public void setup() {
 
        // Process mock annotations
        MockitoAnnotations.initMocks(this);
  
    	bicycleProductBuilder = new ProductBuilder()
                .withId(1L)
                .withCategory(new CategoryBuilder()
                        .withId(1)
                        .withName("Sports")
                       .build())
                .withDescription("Two wheels")
                .withName("Bicycle");

    	cubeProductBuilder = new ProductBuilder()
                .withId(2L)
                .withCategory(new CategoryBuilder()
                .withId(2)
                .withName("Solids")
               .build())
                .withDescription("6 sides")
                .withName("Cube");
    	
    	productList.add(bicycleProductBuilder.build());
    	productList.add(cubeProductBuilder.build());
    }

    @Test
    public void getAll() throws Exception {
 
    	// Need data to mock repository
  	    when(productRepositoryMock.getAll()).thenReturn(productList);
	
	    // Invoke getAll
         List<Product> products = productService.getAll();
         
         // Validate results
         // Can use test data for testing results...
         Product product = bicycleProductBuilder.build();
         assertThat(products, hasItem(
                 allOf(
                         hasProperty("id", is(product.getId())),
                 		hasProperty("category", hasProperty("name", is(product.getCategory().getName()))),
                 		hasProperty("description", is(product.getDescription())),
                         hasProperty("name", is(product.getName()))
                 )
         ));


         assertThat(products, hasItem(
                 allOf(
                         hasProperty("id", is(2L)),
                 		hasProperty("category", hasProperty("name", is("Solids"))),
                         hasProperty("description", is("6 sides")),
                         hasProperty("name", is("Cube"))
                 )
         ));

 	}

    @Test
    public void findOne() throws Exception {
 
    	// Need data to mock repository
    	// Reuse bicycle but change name...
     	Product product = bicycleProductBuilder.withName("Tricycle").build();
	    when(productRepositoryMock.findOne(product.getId())).thenReturn(product);
	
	    // Invoke findOne
          Product productFound = productService.findOne(1L);
         
         // Validate results
         // Making sure that Service did NOT mess with data...
         // Can use test data for testing results...
		assertThat(productFound, allOf(
				hasProperty("id", is(product.getId())),
		 		hasProperty("category", hasProperty("name", is(product.getCategory().getName()))),
				hasProperty("description", is(product.getDescription())),
		        hasProperty("name", is(product.getName()))	
				));
 
 	}

}