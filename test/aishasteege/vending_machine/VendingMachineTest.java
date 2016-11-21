package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest
{
	VendingMachine vendingMachine;

	@Before
	public void setUp()
	{
		vendingMachine = new VendingMachine();
	}

	@Test
	public void WhenVendingMachineDisplayStartsWithInsertCoin()
	{
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
	}

	@Test
	public void DisplayPriceOfProductWhenSelected()
	{
		vendingMachine.SelectProduct(Product.CANDY);
		assertEquals("PRICE $0.65", vendingMachine.getDisplayString());
		vendingMachine.SelectProduct(Product.CHIPS);
		assertEquals("PRICE $0.50", vendingMachine.getDisplayString());
		vendingMachine.SelectProduct(Product.COLA);
		assertEquals("PRICE $1.00", vendingMachine.getDisplayString());
	}
}
