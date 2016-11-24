package aishasteege.vending_machine;

import java.util.EnumMap;
import java.util.Map;

public class Inventory
{
	private Map<Product, Integer> m_inventory_map;
	private String m_product_dispenser;

	/***************************************************************************
	 *  Constructor
	 */
	public Inventory()
	{
		m_product_dispenser = new String();
		m_inventory_map = new EnumMap<Product, Integer>(Product.class);
	}

	/***************************************************************************
	 * @return the string representation of the product dispenser
	 */
	public String getProductDispenser()
	{
		return m_product_dispenser;
	}
	
	/***************************************************************************
	 * @param product - the product to stock
	 * @param count - how many of the specified product are bring added
	 */
	public void stockProduct(Product product, int count)
	{
		Integer current_count = 0;
		if ( m_inventory_map.get(product) != null)
		{
			current_count = m_inventory_map.get(product);
		}

		current_count += count;
		m_inventory_map.put(product, current_count);
	}

	/***************************************************************************
	 * @param product - the product to check the count on
	 * @return the number of specified products in the inventory
	 */
	public int getProductCount(Product product)
	{
		if( m_inventory_map.get(product) != null)
		{
			return m_inventory_map.get(product);
		}
		return 0;
	}

	/***************************************************************************
	 * @param product - the product to be dispensed if it is in the inventory
	 * @return true if the product was succesfully dispensed
	 */
	public boolean dispenseProduct(Product product)
	{
		if (getProductCount(product) > 0)
		{
			stockProduct(product, -1);
			m_product_dispenser += product.getIcon();
			return true;
		}
		return false;
	}

	/***************************************************************************
	 * clear the products from the dispenser
	 */
	public void takeProduct()
	{
		m_product_dispenser = "";
	}
}
