package aishasteege.vending_machine;

public enum Coin
{
	PENNY(0, "(1)"), NICKEL(1, "(5)"), DIME(2, "(10)"), QUARTER(3, "(25)");

	public static int NUM_COINS = 4;
	public static Coin[] COINS_INDEXED = new Coin[] { PENNY, NICKEL, DIME, QUARTER };

	private int m_idx;
	private String m_icon;

	private Coin(int idx, String string_icon)
	{
		m_idx = idx;
		m_icon = string_icon;
	}

	public int getIdx()
	{
		return m_idx;
	}
	
	public String getIcon()
	{
		return m_icon;
	}
}
