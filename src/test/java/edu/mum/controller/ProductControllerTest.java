package edu.mum.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import edu.mum.builder.CategoryBuilder;
import edu.mum.builder.ProductListBuilder;
import edu.mum.domain.Category;
import edu.mum.service.CategoryService;
import edu.mum.service.ProductService;

public class ProductControllerTest {

	@Mock
	private ProductService productServiceMock;

	@Mock
	private CategoryService categoryServiceMock;

	private MockMvc mockMvc;

	@InjectMocks
	private ProductController productController;

	@Before
	public void setup() {

		// Process mock annotations
		MockitoAnnotations.initMocks(this);

		// Setup Spring test in standalone mode
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

	}

	@After
	public void setup2() {
		System.out.println("thanks for testign");
	}

	@Test
	public void ListProducts_getAll() throws Exception {

		ProductListBuilder listBuilder = new ProductListBuilder();

		when(productServiceMock.getAll()).thenReturn(listBuilder.build());
		try {
			mockMvc.perform(get("/listproducts"))
			.andExpect(status().isOk())
			.andExpect(view().name("ListProducts"))
			.andExpect(forwardedUrl("ListProducts"))
			.andExpect(model().attribute("products", hasSize(2)))
			.andExpect(model().attribute("products",
					hasItem(allOf(hasProperty("id", is(1L)),
							hasProperty("category", hasProperty("name", is("Sports"))),
							hasProperty("description", is("Two wheels")), hasProperty("name", is("Bicycle"))))))
			.andExpect(model().attribute("products",
					hasItem(allOf(hasProperty("id", is(2L)),
							hasProperty("category", hasProperty("name", is("Solids"))),
							hasProperty("description", is("6 sides")), hasProperty("name", is("Cube"))))));
		} catch (AssertionError e) {
			System.out.println("ListProduct Error Message: " + e.getMessage());
			throw e;
		}

		// verify EXACTLY 1 invocation ...
		verify(productServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(productServiceMock);
	}

	@Test
	public void saveProduct_Post() throws Exception {

		// Behavior of Mockito -- MOCKS the categoryService.GetCategory in
		// ProductController
		Category category = new CategoryBuilder().withId(1).withName("Food").build();
		when(categoryServiceMock.getCategory(1)).thenReturn(category);

		try {
			mockMvc.perform(post("/product")
					.param("name", "Ice Cream")
					.param("description", "Vanilla")
					.param("category.id", "1")
					.param("price", "3.99"))
					.andExpect(status().isOk())
					.andExpect(forwardedUrl("ProductDetails"))
					// validate that Data binding has worked...compare
					// ModelAttribute (NewProduct)
					// with "real" values....
					.andExpect(model().attribute("newProduct",
							allOf(hasProperty("name", is("Ice Cream")),
									hasProperty("category", hasProperty("name", is("Food"))),
									hasProperty("description", is("Vanilla")), hasProperty("price", is(3.99F)))));
		} catch (AssertionError e) {
			System.out.println("SaveProduct Error Message: " + e.getMessage());
			throw e;
		}

	}

}