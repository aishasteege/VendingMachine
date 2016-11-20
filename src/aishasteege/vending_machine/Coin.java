package aishasteege.vending_machine;

public enum Coin {
	PENNY(0), NICKEL(1), DIME(2), QUARTER(3);

	int idx;

	private Coin(int i) {
		idx = i;
	}

	public int getIdx() {
		return idx;
	}
	
	public static int NUM_COINS = 4;
    public static Coin[] COINS_INDEXED = new Coin[] {PENNY, NICKEL, DIME, QUARTER };
}
