package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest {
	VendingMachine coinMechanism;

	@Before
	public void setUp() {
		coinMechanism = new VendingMachine();
	}

	@Test
	public void WhenVendingMachineDisplayStartsWithInsertCoin() {
		assertEquals("INSERT COIN", coinMechanism.getDisplayString());
	}

	@Test
	public void WhenQuarterIsAddedShowTwentyFive() {
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.25", coinMechanism.getDisplayString());
	}

	@Test
	public void WhenValidCoinsAreAddedShowCombinedValue() {
		coinMechanism.addCoin(Coin.NICKEL);
		assertEquals("$0.05", coinMechanism.getDisplayString());
		coinMechanism.addCoin(Coin.DIME);
		assertEquals("$0.15", coinMechanism.getDisplayString());
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.40", coinMechanism.getDisplayString());
	}

	@Test
	public void AddInvalidCoinsToCoinReturnAndClearWhenReturned() {
		coinMechanism.addCoin(Coin.PENNY);
		assertEquals("INSERT COIN", coinMechanism.getDisplayString());
		assertEquals("(1)", coinMechanism.getCoinReturnString());
		coinMechanism.emptyCoinReturn();

		coinMechanism.addCoin(Coin.PENNY);
		coinMechanism.addCoin(Coin.PENNY);
		assertEquals("(1)(1)", coinMechanism.getCoinReturnString());
	}

	@Test
	public void ReturnCurrentTransactionAndClearDisplayWhenReturnCoinButtonPressed() {
		coinMechanism.addCoin(Coin.DIME);
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.35", coinMechanism.getDisplayString());

		coinMechanism.pressCoinReturn();
		assertEquals("(10)(25)", coinMechanism.getCoinReturnString());
		assertEquals("INSERT COIN", coinMechanism.getDisplayString());
		coinMechanism.emptyCoinReturn();
	}
}
