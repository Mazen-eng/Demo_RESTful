package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.service.inventory.ItemService;

@Configuration
public class ApplicationStartupConfig {

	@Autowired
	ItemService itemService;
	/**
	 * Initialize the starting items list
	 */
	@Bean
	public void initItems(){
		itemService.createItem("Sandwich","Food",5);
		itemService.createItem("Shirt","Clothes",3);
		itemService.createItem("Iphone","Mobile",1);
	}
}
