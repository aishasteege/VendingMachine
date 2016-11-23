package aishasteege.vending_machine;

public class VendingMachine
{
	private CoinMechanism m_coin_mechanism;
	private Inventory m_product_inventory;
	private Product m_current_selection;
	private boolean m_transaction_complete;
	private String m_product_dispenser;

	public VendingMachine()
	{
		m_coin_mechanism = new CoinMechanism();
		m_product_inventory = new Inventory();
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
			if (m_product_inventory.getProductCount(m_current_selection) == 0)
			{
				m_current_selection = null;
				return "SOLD OUT";
			}
			String price = "PRICE " + m_current_selection.GetPriceString();
			m_current_selection = null;
			return price;
		}

		if (m_coin_mechanism.isEmpty())
		{
			if (m_coin_mechanism.exactChangeRequired())
			{
				return "EXACT CHANGE ONLY";
			}

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

	public void stockBank(Coin coin, int count)
	{
		m_coin_mechanism.stockBank(coin, count);
	}
	
	public void emptyBank()
	{
		m_coin_mechanism.emptyBank();
	}

	public void SelectProduct(Product product)
	{
		if (m_coin_mechanism.completeTransaction(product.GetPrice()))
		{
			m_transaction_complete = true;
			stockProduct(product, -1);

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

	public void stockProduct(Product product, int count)
	{
		m_product_inventory.stockProduct( product, count);
	}
}
