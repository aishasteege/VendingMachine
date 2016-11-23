package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InvetoryTest
{
	Inventory inventory;
	@Before
	public void setUp()
	{
		inventory = new Inventory();
	}

	@Test
	public void GetProductCountAndStockProduct()
	{
		assertEquals( 0, inventory.getProductCount(Product.CANDY));
		inventory.stockProduct( Product.CANDY, 2);
		assertEquals( 2, inventory.getProductCount(Product.CANDY));
	}
	
	@Test
	public void DispenseProductPrintsTheSelectedProductsIconIfItIsInStock()
	{
		assertEquals( 0, inventory.getProductCount(Product.CANDY));
		inventory.stockProduct( Product.CANDY, 1);
		assertTrue(inventory.dispenseProduct(Product.CANDY));
		assertEquals( Product.CANDY.getIcon(), inventory.getProductDispenser());
		inventory.takeProduct();
		
		assertFalse(inventory.dispenseProduct(Product.CANDY));
		assertEquals( "", inventory.getProductDispenser());	
	}
}
