package aishasteege.vending_machine;

public class VendingMachine
{
	private CoinMechanism m_coin_mechanism;
	private Inventory m_product_inventory;
	private Product m_current_selection;
	private boolean m_transaction_complete;

	public VendingMachine()
	{
		m_coin_mechanism = new CoinMechanism();
		m_product_inventory = new Inventory();
		m_current_selection = null;
		m_transaction_complete = false;
	}

	/***************************************************************************
	 * @return
	 */
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
			String price = "PRICE " + m_current_selection.getPriceString();
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
			return m_coin_mechanism.getCurrentTransactionString();
		}
	}

	/***************************************************************************
	 * @return the string representation of the coin return
	 */
	public String getCoinReturnString()
	{
		return m_coin_mechanism.getCoinReturnString();
	}

	/***************************************************************************
	 * @return the string representation of the product dispenser
	 */
	public String getProductDispenseString()
	{
		return m_product_inventory.getProductDispenser();
	}

	/***************************************************************************
	 * @param coin - the coin to add to the coin mechanism
	 */
	public void addCoin(Coin coin)
	{
		m_coin_mechanism.addCoin(coin);
	}

	/***************************************************************************
	 * return all the coins to the user
	 */
	public void pressCoinReturn()
	{
		m_coin_mechanism.pressCoinReturn();
	}

	/***************************************************************************
	 * empty the coins in the coin return
	 */
	public void emptyCoinReturn()
	{
		m_coin_mechanism.emptyCoinReturn();
	}

	/***************************************************************************
	 * @param coin the coin to stock
	 * @param count the number of said coin to stock into the bank
	 */
	public void stockBank(Coin coin, int count)
	{
		m_coin_mechanism.stockBank(coin, count);
	}

	/***************************************************************************
	 * clear the bank of coins
	 */
	public void emptyBank()
	{
		m_coin_mechanism.emptyBank();
	}

	/***************************************************************************
	 * @param product the product selected by the user
	 */
	public void selectProduct(Product product)
	{
		if ((m_product_inventory.getProductCount(product) > 0) &&
				m_coin_mechanism.completeTransaction(product.getPrice()))
		{
			m_transaction_complete = m_product_inventory.dispenseProduct(product);
		}
		else
		{
			m_current_selection = product;
		}
	}

	/***************************************************************************
	 * clear the products from the dispenser
	 */
	public void takeProduct()
	{
		m_product_inventory.takeProduct();
	}

	/***************************************************************************
	 * @param product - the product to stock
	 * @param count - how many of the specified product are bring added
	 */
	public void stockProduct(Product product, int count)
	{
		m_product_inventory.stockProduct(product, count);
	}
}
