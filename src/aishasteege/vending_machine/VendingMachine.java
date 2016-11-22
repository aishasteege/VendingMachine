package aishasteege.vending_machine;

import java.util.EnumMap;
import java.util.Map;

public class VendingMachine
{
	private CoinMechanism m_coin_mechanism;
	private Product m_current_selection;
	private boolean m_transaction_complete;
	private String m_product_dispenser;
	private Map<Product, Integer> m_inventory_map;

	public VendingMachine()
	{
		m_coin_mechanism = new CoinMechanism();
		m_current_selection = null;
		m_transaction_complete = false;
		m_product_dispenser = new String();
		m_inventory_map = new EnumMap<Product, Integer>(Product.class);

		m_inventory_map.put(Product.CANDY, 0);
		m_inventory_map.put(Product.CHIPS, 0);
		m_inventory_map.put(Product.COLA, 0);
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
			if (m_inventory_map.get(m_current_selection) == 0)
			{
				return "SOLD OUT";
			}
			String price = "PRICE " + m_current_selection.GetPriceString();
			m_current_selection = null;
			return price;
		}

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
			StockProduct(product, -1);

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

	public void StockProduct(Product product, int num_items)
	{
		Integer current_count = m_inventory_map.get(product);

		current_count += num_items;

		m_inventory_map.put(product, current_count);
	}
}
