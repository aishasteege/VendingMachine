package aishasteege.vending_machine;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest
{
	VendingMachine vendingMachine;

	@Before
	public void setUp()
	{
		vendingMachine = new VendingMachine();
		vendingMachine.StockProduct(Product.CANDY, 1);
		vendingMachine.StockProduct(Product.COLA, 1);
		vendingMachine.StockProduct(Product.CHIPS, 1);
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

	@Test
	public void DisplayInsertCoinAfterProductSelectedPriceIfNotEnoughMoney()
	{
		vendingMachine.SelectProduct(Product.CANDY);
		assertEquals("PRICE $0.65", vendingMachine.getDisplayString());
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
	}

	@Test
	public void DisplayThankYouAfterTransactionThenInsertCoin()
	{
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.SelectProduct(Product.CHIPS);
		assertEquals("THANK YOU", vendingMachine.getDisplayString());
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
	}

	@Test
	public void DispenseProductToUser()
	{
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.SelectProduct(Product.CHIPS);

		assertEquals("[A BIG BAG OF CHIPS]", vendingMachine.getProductDispenseString());
		vendingMachine.takeProduct();

		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.SelectProduct(Product.COLA);

		assertEquals("[AN ICE COLD COLA]", vendingMachine.getProductDispenseString());
		vendingMachine.takeProduct();

		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.SelectProduct(Product.CANDY);

		assertEquals("[SWEET SWEET CANDY]", vendingMachine.getProductDispenseString());
		vendingMachine.takeProduct();
	}

	@Test
	public void DisplayOutOfStockWhenProductIsSoldOut()
	{
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.addCoin(Coin.QUARTER);
		vendingMachine.SelectProduct(Product.CHIPS);
		assertEquals("THANK YOU", vendingMachine.getDisplayString());
		vendingMachine.takeProduct();

		vendingMachine.SelectProduct(Product.CHIPS);
		assertEquals("SOLD OUT", vendingMachine.getDisplayString());
	}
	
	@Test
	public void DisplayInsertCoinAfterSoldOutMessage()
	{
		vendingMachine.StockProduct(Product.CANDY, -1);
		
		vendingMachine.SelectProduct(Product.CANDY);
		assertEquals("SOLD OUT", vendingMachine.getDisplayString());
		assertEquals("INSERT COIN", vendingMachine.getDisplayString());
	}
}
