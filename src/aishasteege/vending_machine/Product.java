package aishasteege.vending_machine;

import java.text.DecimalFormat;

public enum Product
{
	COLA(1.0f), CHIPS(.5f), CANDY(0.65f);

	private float m_price;

	private Product(float price)
	{
		m_price = price;
	}

	public float GetPrice()
	{
		return m_price;
	}

	public String GetPriceString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(m_price);
	}
}
