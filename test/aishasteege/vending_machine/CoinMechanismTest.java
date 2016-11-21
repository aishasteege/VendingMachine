package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoinMechanismTest
{
	CoinMechanism coinMechanism;

	@Before
	public void setUp()
	{
		coinMechanism = new CoinMechanism();
	}

	@Test
	public void WhenQuarterIsAddedShowTwentyFive()
	{
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.25", coinMechanism.GetCurrentTransactionString());
	}

	@Test
	public void WhenValidCoinsAreAddedShowCombinedValue()
	{
		coinMechanism.addCoin(Coin.NICKEL);
		assertEquals("$0.05", coinMechanism.GetCurrentTransactionString());
		coinMechanism.addCoin(Coin.DIME);
		assertEquals("$0.15", coinMechanism.GetCurrentTransactionString());
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.40", coinMechanism.GetCurrentTransactionString());
	}

	@Test
	public void AddInvalidCoinsToCoinReturnAndClearWhenReturned()
	{
		coinMechanism.addCoin(Coin.PENNY);
		assertTrue(coinMechanism.isEmpty());
		assertEquals("(1)", coinMechanism.getCoinReturnString());
		coinMechanism.emptyCoinReturn();

		coinMechanism.addCoin(Coin.PENNY);
		coinMechanism.addCoin(Coin.PENNY);
		assertEquals("(1)(1)", coinMechanism.getCoinReturnString());
	}

	@Test
	public void ReturnCurrentTransactionAndClearTransactionWhenReturnCoinButtonPressed()
	{
		coinMechanism.addCoin(Coin.DIME);
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.35", coinMechanism.GetCurrentTransactionString());

		coinMechanism.pressCoinReturn();
		assertEquals("(10)(25)", coinMechanism.getCoinReturnString());
		assertTrue(coinMechanism.isEmpty());
		coinMechanism.emptyCoinReturn();
	}
	
	@Test
	public void ReturnFalseIfTransactionCannotBeCompleted()
	{
		assertFalse(coinMechanism.completeTransaction(.50f));
	}
	
	
}
