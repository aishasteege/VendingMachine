package aishasteege.vending_machine;

import static org.junit.Assert.assertEquals;

public class VendingMachine
{
	CoinMechanism m_coin_mechanism;
	Product m_current_selection;
	boolean m_transaction_complete;
	String m_product_dispenser;

	public VendingMachine()
	{
		m_coin_mechanism = new CoinMechanism();
		m_current_selection = null;
		m_transaction_complete = false;
		m_product_dispenser = new String();
	}

	public String getDisplayString()
	{
		if (m_transaction_complete)
		{
			m_transaction_complete = false;
			return "THANK YOU";
		}
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

	public String getProductDispenseString()
	{
		return m_product_dispenser;
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
		if (m_coin_mechanism.completeTransaction(product.GetPrice()))
		{
			m_transaction_complete = true;

			switch (product)
			{
			case COLA:
				m_product_dispenser = "[AN ICE COLD COLA]";
				break;
			case CHIPS:
				m_product_dispenser = "[A BIG BAG OF CHIPS]";
				break;
			case CANDY:
				m_product_dispenser = "[SWEET SWEET CANDY]";
				break;
			}
		}
		else
		{
			m_current_selection = product;
		}
	}

	public void takeProduct()
	{
		m_product_dispenser = "";
	}
}
