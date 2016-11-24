package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.Before;
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
		assertEquals("$0.25", coinMechanism.getCurrentTransactionString());
	}

	@Test
	public void WhenValidCoinsAreAddedShowCombinedValue()
	{
		coinMechanism.addCoin(Coin.NICKEL);
		assertEquals("$0.05", coinMechanism.getCurrentTransactionString());
		coinMechanism.addCoin(Coin.DIME);
		assertEquals("$0.15", coinMechanism.getCurrentTransactionString());
		coinMechanism.addCoin(Coin.QUARTER);
		assertEquals("$0.40", coinMechanism.getCurrentTransactionString());
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
		assertEquals("$0.35", coinMechanism.getCurrentTransactionString());

		coinMechanism.pressCoinReturn();
		assertEquals("(25)(10)", coinMechanism.getCoinReturnString());
		assertTrue(coinMechanism.isEmpty());
		coinMechanism.emptyCoinReturn();
	}

	@Test
	public void ReturnFalseIfTransactionCannotBeCompleted()
	{
		assertFalse(coinMechanism.completeTransaction(.50f));
	}

	@Test
	public void CompleteTransactionWhenEnoughMoneyHasBeenEnteredAndReturnTrue()
	{
		assertTrue(coinMechanism.isEmpty());
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);

		assertTrue(coinMechanism.completeTransaction(.50f));
		assertTrue(coinMechanism.isEmpty());
	}

	@Test
	public void CoinReturnGetsResetAfterTransation()
	{
		assertTrue(coinMechanism.isEmpty());
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);

		assertTrue(coinMechanism.completeTransaction(.50f));

		coinMechanism.pressCoinReturn();
		assertEquals("", coinMechanism.getCoinReturnString());
	}

	@Test
	public void MakeChangeForTransaction()
	{
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);

		assertTrue(coinMechanism.completeTransaction(0.50f));
		assertEquals("(25)", coinMechanism.getCoinReturnString());
		coinMechanism.emptyCoinReturn();

		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.DIME);
		coinMechanism.addCoin(Coin.DIME);
		coinMechanism.addCoin(Coin.DIME);

		assertTrue(coinMechanism.completeTransaction(.65f));
		assertEquals("(10)(5)", coinMechanism.getCoinReturnString());
	}

	@Test
	public void SaveCoinsToBankWhenTransactionIsCompleted()
	{
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);

		assertTrue(coinMechanism.completeTransaction(0.50f));
		assertEquals(0.5f, coinMechanism.getBankValue(), .0001f);

		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.DIME);
		coinMechanism.addCoin(Coin.NICKEL);

		assertTrue(coinMechanism.completeTransaction(.65f));
		assertEquals(1.15, coinMechanism.getBankValue(), .0001f);
	}

	@Test
	public void ExactChangeRequiredIfBankDoesNotHaveADimeAndNickel()
	{
		assertTrue(coinMechanism.exactChangeRequired());

		coinMechanism.addCoin(Coin.QUARTER);
		coinMechanism.addCoin(Coin.QUARTER);

		assertTrue(coinMechanism.completeTransaction(0.50f));
		assertTrue(coinMechanism.exactChangeRequired());

		coinMechanism.addCoin(Coin.DIME);
		assertTrue(coinMechanism.completeTransaction(0.10f));
		assertTrue(coinMechanism.exactChangeRequired());

		coinMechanism.addCoin(Coin.NICKEL);
		coinMechanism.addCoin(Coin.NICKEL);
		assertTrue(coinMechanism.completeTransaction(0.10f));
		assertFalse(coinMechanism.exactChangeRequired());
	}

	@Test
	public void AllowTheBankToBeStockedWithCoins()
	{
		assertEquals(0.0f, coinMechanism.getBankValue(), .0001f);
		coinMechanism.stockBank(Coin.NICKEL, 1);
		assertEquals(0.05f, coinMechanism.getBankValue(), .0001f);
		coinMechanism.stockBank(Coin.DIME, 1);
		assertEquals(0.15f, coinMechanism.getBankValue(), .0001f);
		coinMechanism.stockBank(Coin.QUARTER, 1);
		assertEquals(0.4f, coinMechanism.getBankValue(), .0001f);
	}
	
	@Test
	public void AllowTheBankToBeEmptied()
	{
		coinMechanism.stockBank(Coin.QUARTER, 1);
		coinMechanism.emptyBank();
		assertEquals(0.0f, coinMechanism.getBankValue(), .0001f);
	}
}
