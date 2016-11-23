package aishasteege.vending_machine;

import java.util.EnumMap;
import java.util.Map;

public class Inventory
{
	private Map<Product, Integer> m_inventory_map;

	public Inventory()
	{
		m_inventory_map = new EnumMap<Product, Integer>(Product.class);

		m_inventory_map.put(Product.CANDY, 0);
		m_inventory_map.put(Product.CHIPS, 0);
		m_inventory_map.put(Product.COLA, 0);
	}

	public void stockProduct(Product product, int count)
	{
		Integer current_count = m_inventory_map.get(product);

		current_count += count;

		m_inventory_map.put(product, current_count);
	}

	public int getProductCount(Product product)
	{
		return m_inventory_map.get(product);
	}
	
}
