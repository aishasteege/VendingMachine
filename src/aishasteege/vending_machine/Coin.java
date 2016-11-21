package aishasteege.vending_machine;

public enum Coin
{
	PENNY(0), NICKEL(1), DIME(2), QUARTER(3);

	public static int NUM_COINS = 4;
	public static Coin[] COINS_INDEXED = new Coin[] { PENNY, NICKEL, DIME, QUARTER };

	private int m_idx;

	private Coin(int idx)
	{
		m_idx = idx;
	}

	public int getIdx()
	{
		return m_idx;
	}
}
