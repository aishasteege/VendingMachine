package aishasteege.vending_machine;

public class VendingMachine
{
	CoinMechanism m_coin_mechanism;

	public VendingMachine()
	{
		m_coin_mechanism = new CoinMechanism();
	}

	public String getDisplayString()
	{
		if (m_coin_mechanism.isEmpty())
		{
			return "INSERT COIN";
		}
		else
		{
			return m_coin_mechanism.GetCurrentTransactionString();
		}
	}
	
	public String getCoinReturnString()
	{
		return m_coin_mechanism.getCoinReturnString();
	}

	public void addCoin(Coin coin)
	{
		m_coin_mechanism.addCoin(coin);
	}

	public void pressCoinReturn()
	{
		m_coin_mechanism.pressCoinReturn();
	}

	public void emptyCoinReturn()
	{
		m_coin_mechanism.emptyCoinReturn();
	}
}
