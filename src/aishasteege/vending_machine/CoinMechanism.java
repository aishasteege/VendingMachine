package aishasteege.vending_machine;

import java.text.DecimalFormat;

// class for the coin mechanism of a vending machine
public class CoinMechanism
{
	int[] m_current_transaction_coins = new int[Coin.NUM_COINS];
	int[] m_bank_coins = new int[Coin.NUM_COINS];
	String m_coinReturn = new String();

	/***************************************************************************
	 * @return true if the no coins have been added
	 */
	public boolean isEmpty()
	{
		return GetCurrentTransactionValue() == 0.0f;
	}

	/***************************************************************************
	 * @return the string representation of the value of the current transaction
	 */
	public String GetCurrentTransactionString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(GetCurrentTransactionValue());
	}

	/***************************************************************************
	 * @return the string representation of the coin return
	 */
	public String getCoinReturnString()
	{
		return m_coinReturn;
	}

	/***************************************************************************
	 * @param coin - the coin to add to the coin mechanism
	 */
	public void addCoin(Coin coin)
	{
		m_current_transaction_coins[coin.getIdx()]++;
		returnCoin(Coin.PENNY);
	}

	/***************************************************************************
	 * @param transaction_price - the price of the item being purchased
	 * @return true if the transaction was completed or false if there was not
	 *         enough money to complete the transaction
	 */
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

	/***************************************************************************
	 * return all the coins to the user
	 */
	public void pressCoinReturn()
	{
		returnCoin(Coin.QUARTER);
		returnCoin(Coin.DIME);
		returnCoin(Coin.NICKEL);
	}

	/***************************************************************************
	 * user takes all the coins in the coin return
	 */
	public void emptyCoinReturn()
	{
		m_coinReturn = "";
	}

	/***************************************************************************
	 * @return get the value of all the coins in the current transaction
	 */
	private float GetCurrentTransactionValue()
	{
		float value = 0.0f;
		value += m_current_transaction_coins[Coin.NICKEL.getIdx()] * 0.05;
		value += m_current_transaction_coins[Coin.DIME.getIdx()] * 0.1;
		value += m_current_transaction_coins[Coin.QUARTER.getIdx()] * 0.25;
		return value;
	}

	/***************************************************************************
	 * clear the coin from the current transaction
	 */
	private void SaveCoin()
	{
		m_bank_coins[Coin.NICKEL.getIdx()] += m_current_transaction_coins[Coin.NICKEL.getIdx()];
		m_bank_coins[Coin.DIME.getIdx()] += m_current_transaction_coins[Coin.DIME.getIdx()];
		m_bank_coins[Coin.QUARTER.getIdx()] += m_current_transaction_coins[Coin.QUARTER.getIdx()];

		java.util.Arrays.fill(m_current_transaction_coins, 0);
	}

	/***************************************************************************
	 * @param change - the change to give back to the user
	 */
	private void MakeChange(float change)
	{
		for (; change >= 0.25; change -= 0.25)
		{
			m_current_transaction_coins[Coin.QUARTER.getIdx()]++;
		}
		for (; change >= 0.1; change -= 0.1)
		{
			m_current_transaction_coins[Coin.DIME.getIdx()]++;
		}
		for (; change >= 0.05; change -= 0.05)
		{
			m_current_transaction_coins[Coin.NICKEL.getIdx()]++;
		}
		pressCoinReturn();
	}

	/***************************************************************************
	 * @param coin - coin to return all of
	 */
	private void returnCoin(Coin coin)
	{
		int count = m_current_transaction_coins[coin.getIdx()];
		returnCoin(coin, count);
	}

	/***************************************************************************
	 * @param coin - coin to return
	 * @param count - how many of said coin to return
	 */
	private void returnCoin(Coin coin, int count)
	{
		if (count <= m_current_transaction_coins[coin.getIdx()])
		{
			m_coinReturn += new String(new char[count]).replace("\0", coin.getIcon());
			m_current_transaction_coins[coin.getIdx()] -= count;
		}
	}

	/***************************************************************************
	 * @return the combined value of the bank coins
	 */
	public float getBankValue()
	{
		float value = 0.0f;
		value += m_bank_coins[Coin.NICKEL.getIdx()] * 0.05;
		value += m_bank_coins[Coin.DIME.getIdx()] * 0.1;
		value += m_bank_coins[Coin.QUARTER.getIdx()] * 0.25;
		return value;
	}

	/***************************************************************************
	 * @return true if we do not have a nickel and a dime in the bank
	 */
	public boolean exactChangeRequired()
	{
		return (m_bank_coins[Coin.DIME.getIdx()] == 0) || (m_bank_coins[Coin.NICKEL.getIdx()] == 0);
	}
}
