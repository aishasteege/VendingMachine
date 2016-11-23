package aishasteege.vending_machine;

public enum Coin
{
	PENNY(0, "(1)"), NICKEL(1, "(5)"), DIME(2, "(10)"), QUARTER(3, "(25)");

	private int m_idx;
	private String m_icon;

	/***************************************************************************
	 * @param idx of the coin in the enumeration
	 * @param icon is the string representation of the coin
	 */
	private Coin(int idx, String icon)
	{
		m_idx = idx;
		m_icon = icon;
	}

	/***************************************************************************
	 * @return the index in the enumeration
	 */
	public int getIdx()
	{
		return m_idx;
	}
	
	/***************************************************************************
	 * @return the string representation of the coin 
	 */
	public String getIcon()
	{
		return m_icon;
	}
}
