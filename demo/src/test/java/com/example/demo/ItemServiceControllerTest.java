package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.model.inventory.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ItemServiceControllerTest extends DemoApplicationTests {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	Gson gson = new Gson();
	Type type = new TypeToken<ArrayList<Item>>() {}.getType();

	@Test
	public void getItemsList() throws Exception {
		String uri = "/items";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		List<Item> itemsList = gson.fromJson(content, type);;
		assertTrue(itemsList.size() > 0);
	}

	@Test
	public void createItem() throws Exception {
		String uri = "/items/create";
		Item item = new Item("Mock", "Test Item", 3);

		String inputJson = super.mapToJson(item);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);

	}

	@Test
	public void updateProduct() throws Exception {
		String uri = "/items/update";
		Item item = new Item("Mock2", "Test Item2", 7);
		String inputJson = super.mapToJson(item);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson).param("amountAvailable", "66"))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void deleteProduct() throws Exception {
		String uri = "/items/delete";
		Item item = new Item("Sandwich","Food",5);
		String inputJson = super.mapToJson(item);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
	}
}
