package aishasteege.vending_machine;

import java.util.EnumMap;
import java.util.Map;

public class Inventory
{
	private Map<Product, Integer> m_inventory_map;
	private String m_product_dispenser;

	public Inventory()
	{
		m_product_dispenser = new String();
		m_inventory_map = new EnumMap<Product, Integer>(Product.class);
	}

	public String getProductDispenser()
	{
		return m_product_dispenser;
	}
	
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

	public int getProductCount(Product product)
	{
		if( m_inventory_map.get(product) != null)
		{
			return m_inventory_map.get(product);
		}
		return 0;
	}

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

	public void takeProduct()
	{
		m_product_dispenser = "";
	}
}
