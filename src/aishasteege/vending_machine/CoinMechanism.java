package aishasteege.vending_machine;

import java.text.DecimalFormat;

public class CoinMechanism
{
	int[] current_transaction_coins = new int[Coin.NUM_COINS];
	String coinReturn = new String();

	public boolean isEmpty()
	{
		return GetCurrentTransactionValue() == 0.0f;
	}

	public String GetCurrentTransactionString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(GetCurrentTransactionValue());
	}

	public String getCoinReturnString()
	{
		return coinReturn;
	}

	public void addCoin(Coin coin)
	{
		current_transaction_coins[coin.getIdx()]++;
		returnCoin(Coin.PENNY);
	}

	public boolean completeTransaction(float transaction_price)
	{
		if (transaction_price <= GetCurrentTransactionValue())
		{
			float change = GetCurrentTransactionValue() - transaction_price;

			SaveCoin();
			MakeChange(change);

			return true;
		}
		return false;
	}

	public void pressCoinReturn()
	{
		returnCoin(Coin.QUARTER);
		returnCoin(Coin.DIME);
		returnCoin(Coin.NICKEL);
	}

	public void emptyCoinReturn()
	{
		coinReturn = "";
	}

	private float GetCurrentTransactionValue()
	{
		float value = 0.0f;
		value += current_transaction_coins[Coin.NICKEL.getIdx()] * 0.05;
		value += current_transaction_coins[Coin.DIME.getIdx()] * 0.1;
		value += current_transaction_coins[Coin.QUARTER.getIdx()] * 0.25;
		return value;
	}

	private void SaveCoin()
	{
		java.util.Arrays.fill(current_transaction_coins, 0);
	}

	private void MakeChange(float change)
	{
		for (; change >= 0.25; change -= 0.25)
		{
			current_transaction_coins[Coin.QUARTER.getIdx()]++;
		}
		for (; change >= 0.1; change -= 0.1)
		{
			current_transaction_coins[Coin.DIME.getIdx()]++;
		}
		for (; change >= 0.05; change -= 0.05)
		{
			current_transaction_coins[Coin.NICKEL.getIdx()]++;
		}
		pressCoinReturn();
	}

	private void returnCoin(Coin coin)
	{
		int count = current_transaction_coins[coin.getIdx()];
		returnCoin(coin, count);
	}

	private void returnCoin(Coin coin, int count)
	{
		if (count <= current_transaction_coins[coin.getIdx()])
		{
			coinReturn += new String(new char[count]).replace("\0", coin.getIcon());
			current_transaction_coins[coin.getIdx()] -= count;
		}
	}
}
