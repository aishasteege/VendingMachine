package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest {

	// @BeforeClass
	// public static void setUpBeforeClass() throws Exception {
	// }
	//
	// @AfterClass
	// public static void tearDownAfterClass() throws Exception {
	// }
	//
	//
	// @After
	// public void tearDown() throws Exception {
	// }

	VendingMachine VendingMachine;

	@Before
	public void setUp() {
		VendingMachine = new VendingMachine();
	}

	@Test
	public void WhenVendingMachineDisplayStartsWithInsertCoin() {
		assertEquals("INSERT COIN", VendingMachine.getDisplayString());
	}

	@Test
	public void WhenQuarterIsAddedShowTwentyFive() {
		VendingMachine.addCoin(Coin.QUARTER);
		assertEquals("$0.25", VendingMachine.getDisplayString());
	}

	@Test
	public void WhenValidCoinsAreAddedShowCombinedValue() {
		VendingMachine.addCoin(Coin.NICKEL);
		assertEquals("$0.05", VendingMachine.getDisplayString());
		VendingMachine.addCoin(Coin.DIME);
		assertEquals("$0.15", VendingMachine.getDisplayString());
		VendingMachine.addCoin(Coin.QUARTER);
		assertEquals("$0.40", VendingMachine.getDisplayString());
	}

	@Test
	public void AddInvalidCoinsToCoinReturnAndClearWhenReturned() {
		VendingMachine.addCoin(Coin.PENNY);
		assertEquals("INSERT COIN", VendingMachine.getDisplayString());
		assertEquals("(1)", VendingMachine.returnCoin());

		VendingMachine.addCoin(Coin.PENNY);
		VendingMachine.addCoin(Coin.PENNY);
		assertEquals("(1)(1)", VendingMachine.returnCoin());
	}

	@Test
	public void ReturnCurrentTransactionAndClearDisplayWhenReturnCoinButtonPressed() {
		VendingMachine.addCoin(Coin.DIME);
		VendingMachine.addCoin(Coin.QUARTER);
		assertEquals("$0.35", VendingMachine.getDisplayString());
		
		assertEquals("(10)(25)", VendingMachine.returnCoin());
		assertEquals("INSERT COIN", VendingMachine.getDisplayString());
	}
}
