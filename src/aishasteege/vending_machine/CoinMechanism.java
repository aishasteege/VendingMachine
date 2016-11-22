package aishasteege.vending_machine;

import java.text.DecimalFormat;

public class CoinMechanism
{
	int[] current_transaction_coins = new int[Coin.NUM_COINS];
	float current_transaction = 0.0f;
	String coinReturn = new String();

	public CoinMechanism()
	{
	}

	public boolean isEmpty()
	{
		return current_transaction == 0.0f;
	}

	public String GetCurrentTransactionString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(current_transaction);
	}

	public void addCoin(Coin coin)
	{
		switch (coin)
		{
		case PENNY:
			coinReturn += "(1)";
			break;
		case NICKEL:
			current_transaction += 0.05f;
			current_transaction_coins[Coin.NICKEL.getIdx()]++;
			break;
		case DIME:
			current_transaction += 0.10f;
			current_transaction_coins[Coin.DIME.getIdx()]++;
			break;
		case QUARTER:
			current_transaction += 0.25f;
			current_transaction_coins[Coin.QUARTER.getIdx()]++;
			break;
		}
	}

	public void pressCoinReturn()
	{
		returnCoin(Coin.NICKEL);
		returnCoin(Coin.DIME);
		returnCoin(Coin.QUARTER);
		current_transaction = 0.0f;
	}

	private void returnCoin(Coin coin)
	{
		int count = current_transaction_coins[coin.getIdx()];
		coinReturn += new String(new char[count]).replace("\0", coin.getIcon());
		current_transaction_coins[coin.getIdx()] = 0;
	}

	public String getCoinReturnString()
	{
		return coinReturn;
	}

	public void emptyCoinReturn()
	{
		coinReturn = "";
	}

	public boolean completeTransaction(float transaction_price)
	{
		if (transaction_price <= current_transaction)
		{
			current_transaction -= transaction_price;

			while (current_transaction >= 0.25f)
			{
				coinReturn += "(25)";
				current_transaction -= 0.25f;
			}
			while (current_transaction >= 0.1f)
			{
				coinReturn += "(10)";
				current_transaction -= 0.10f;
			}
			while (current_transaction >= 0.05f)
			{
				coinReturn += "(5)";
				current_transaction -= 0.05f;
			}

			java.util.Arrays.fill(current_transaction_coins, 0);

			return true;
		}
		return false;
	}
}
