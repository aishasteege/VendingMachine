package aishasteege.vending_machine;

import java.text.DecimalFormat;

public enum Product
{
	COLA(1.0f, "[ICE COLD COLA]"), CHIPS(.5f,"[POTATO CHIPS]"), CANDY(0.65f,"[SWEET CANDY]");

	private float m_price;
	private String m_icon;
	
	/***************************************************************************
	 * @param price of the product
	 * @param icon is the string representation of the coin
	 */
	private Product(float price, String icon)
	{
		m_price = price;
		m_icon = icon;
	}

	/***************************************************************************
	 * @return the price of the product as a float
	 */
	public float getPrice()
	{
		return m_price;
	}

	/***************************************************************************
	 * @return the price of the product as a string
	 */
	public String getPriceString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(m_price);
	}
	
	/***************************************************************************
	 * @return the string representation of the coin 
	 */
	public String getIcon()
	{
		return m_icon;
	}
}
