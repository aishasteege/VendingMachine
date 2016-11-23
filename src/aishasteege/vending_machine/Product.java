package aishasteege.vending_machine;

import java.text.DecimalFormat;

public enum Product
{
	COLA(1.0f, "[ICE COLD COLA]"), CHIPS(.5f,"[POTATO CHIPS]"), CANDY(0.65f,"[SWEET CANDY]");

	private float m_price;
	private String m_icon;
	
	private Product(float price, String icon)
	{
		m_price = price;
		m_icon = icon;
	}

	public float getPrice()
	{
		return m_price;
	}

	public String getPriceString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(m_price);
	}
	
	public String getIcon()
	{
		return m_icon;
	}
}
