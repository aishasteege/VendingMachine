package aishasteege.vending_machine;

public class VendingMachine
{
	CoinMechanism m_coin_mechanism;
	Product m_current_selection;

	public VendingMachine()
	{
		m_coin_mechanism = new CoinMechanism();
		m_current_selection = null;
	}

	public String getDisplayString()
	{
		if (m_current_selection != null)
		{

			String price = "PRICE " + m_current_selection.GetPriceString();
			m_current_selection = null;
			return price;
		}
		else if (m_coin_mechanism.isEmpty())
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

	public void SelectProduct(Product product)
	{
		m_current_selection = product;
	}
}
