package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.Test;

public class InvetoryTest
{

	@Test
	public void GetProductCountAndStockProduct()
	{
		Inventory inventory = new Inventory();
		assertEquals( 0, inventory.getProductCount(Product.CANDY));
		inventory.stockProduct( Product.CANDY, 2);
		assertEquals( 2, inventory.getProductCount(Product.CANDY));
	}
}
