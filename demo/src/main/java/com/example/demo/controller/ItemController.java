package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.inventory.Item;
import com.example.demo.service.inventory.ItemService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/items")
public class ItemController {

	private Logger logger = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	/**
	 * 
	 * @return The list of items
	 */
	@ApiOperation(value = "Display all the items in the item's list")
	@GetMapping
	public ResponseEntity<List<Item>> getItemsList() {

		List<Item> items = new ArrayList<Item>();

		items = itemService.getItemsList();
		if (items.isEmpty()) {
			logger.error("No items found!");
			return new ResponseEntity<List<Item>>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);

	}
	
	/**
	 * 
	 * @param name of the product to be created
	 * @param type of the product to be created
	 * @param amountAvailable of the product to be created
	 * @return a flag to indicate a successful or a failed operation
	 */
	@ApiOperation(value = "Creates an item and puts it in the item's list using the request parameters specified")
	@PostMapping("/create")
	public ResponseEntity<Boolean> createItem(@Valid @RequestBody Item item){
		boolean success = false;
		success = itemService.createItem(item);
		if(success) {
			return new ResponseEntity<Boolean>(success, HttpStatus.CREATED);
		}
		    logger.error("Product creation failed!");
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		
		
	}
	/**
	 * 
	 * @param item to be updated
	 * @param amountAvailable the new value
	 * @return a flag to indicate a successful or a failed operation
	 */
	@ApiOperation(value = "Updates the specified item(RequestBody) with the a new value of the amountAvailable(RequestParam)")
	@PutMapping("/update")
	public ResponseEntity<Boolean> updateItem(@RequestBody Object item, @RequestParam Integer amountAvailable){
		boolean success = false;
		success = itemService.updateAmountAvailable(item, amountAvailable);
		if(success) {
			return new ResponseEntity<Boolean>(success, HttpStatus.OK);
		}
		    logger.error("Amount available update failed!");
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		
		
	}
	/**
	 * 
	 * @param item to be deleted
	 * @return a flag to indicate a successful or a failed operation
	 */
	@ApiOperation(value = "Deletes an item(RequestBody) from the items list")
	@DeleteMapping("/delete")
	public ResponseEntity<Boolean> deleteItem(@RequestBody Item item){
		boolean success = false;
		success = itemService.removeItem(item);
		if(success) {
			return new ResponseEntity<Boolean>(success, HttpStatus.OK);
		}
		    logger.error("Item deletion failed");
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		
		
	}

}
