package com.example.demo.model.inventory;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Mazen
 *
 */
public class Item {
	public Item(String name, String type, Integer amountAvailable) {
		super();
		this.name = name;
		this.type = type;
		this.amountAvailable = amountAvailable;
	}
	
	/** Item's name */
	@NotNull
	@Size(min=1, message="Name cannot be empty")
	private String name;
	
	/** Item's type */
	@NotNull
	@Size(min=1, message="Type cannot be empty")
	private String type;
	
	/** Item's available amount */
	@Min(3)
	@Max(99)
	private Integer amountAvailable;
	
	/**
	 * 
	 * @return the item's name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name the product's name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @return the product's type
	 */
	public String getType() {
		return type;
	}
	/**
	 * 
	 * @param product's type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 
	 * @return product's available amount
	 */
	public Integer getAmountAvailable() {
		return amountAvailable;
	}
	/**
	 * 
	 * @param the product's available amount to set
	 */
	public void setAmountAvailable(Integer amountAvailable) {
		this.amountAvailable = amountAvailable;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item item = (Item) obj;
        if (!(name.equalsIgnoreCase(item.getName())))
            return false;
        if (!(type.equalsIgnoreCase(item.getType())))
            return false;
        if (amountAvailable != item.getAmountAvailable())
            return false;
        return true;
    }     

}
