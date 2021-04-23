package com.example.demo.service.inventory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.model.inventory.Item;
import com.example.demo.utilities.Methods;

/**
 * 
 * @author Mazen
 *
 */
@Service
public class ItemService {

	private Logger logger = LoggerFactory.getLogger(ItemService.class);

	/**
	 * The definition of items list which simulates the database
	 */
	List<Item> items = new ArrayList<Item>();

	/**
	 * 
	 * @param name            of the item to be created
	 * @param type            of the item to be created
	 * @param amountAvailable of the item to be created
	 * @return true if the item is created and added to the list of items
	 *         successfully
	 */
	public boolean createItem(String name, String type, Integer amountAvailable) {
		Item item = new Item(name, type, amountAvailable);
		boolean success = false;
		try {
			success = addItem(item);
		} catch (Exception e) {
			logger.error("Failed to add item to the items list" + e.getMessage());
		}

		return success;
	}
	
	/**
	 * 
	 * @param item to be add to the items' list
	 * @return a flag to indicate that the operation is successful or not
	 */
	public boolean createItem(Item item) {
		boolean success = false;
		try {
			success = addItem(item);
		} catch (Exception e) {
			logger.error("Failed to add item to the items list", e.getMessage());
		}
		
		return success;
	}

	/**
	 * 
	 * @param item to be added to the list of items
	 * @return true if operation is successful
	 */
	private boolean addItem(Item item) {
		return items.add(item);
	}

	/**
	 * 
	 * @return the list of items
	 */
	public List<Item> getItemsList() {

		return items;
	}

	/**
	 * 
	 * @param item
	 * @return true if the item is removed from the list of items
	 */
	public boolean removeItem(Item item) {
		// Convert the request object to item type
		//Item item = Methods.mapToObject(requestObject, Item.class);
		return items.remove(item);
	}

	/**
	 * 
	 * @param item to be updated
	 * @param the  new amountAvailable value
	 * @return true if success
	 */
	public boolean updateAmountAvailable(Object requestObject, Integer amountAvailable) {
		// Convert the request object to item type
		Item item = Methods.mapToObject(requestObject, Item.class);
		// locate the index of the item to be updated
		int index = items.indexOf(item);
		if (index < 0) {
			logger.error("Item not found");
			return false;
		}
		// if the item found in the list, configure the old item with the new
		// amountAvailable value
		item.setAmountAvailable(amountAvailable);
		// updates the item in the list with the new value
		items.set(index, item);
		return true;
	}

}
