package aishasteege.vending_machine;
import java.text.DecimalFormat;

public class CoinMechanism {
	int[] current_transaction_coins;
	float current_transaction;
	String coinReturn;

	
	public CoinMechanism() {
		current_transaction = 0.0f;
		coinReturn = "";

		current_transaction_coins = new int[Coin.NUM_COINS];
		for (int i = 0; i < Coin.NUM_COINS; i++) {
			current_transaction_coins[i] = 0;
		}
	}
	
	public boolean isEmpty(){
		return current_transaction == 0.0f ;
	}
	
	public String GetCurrentTransactionString()
	{
		DecimalFormat moneyFormat = new DecimalFormat("0.00");
		return "$" + moneyFormat.format(current_transaction);
	}

	public void addCoin(Coin coin) {
		switch (coin) {
		case PENNY:
			coinReturn += "(1)";
			break;
		case NICKEL:
			current_transaction += 0.05f;
			current_transaction_coins[Coin.NICKEL.getIdx()]++;
			break;
		case DIME:
			current_transaction += 0.10f;
			current_transaction_coins[Coin.DIME.getIdx()]++;
			break;
		case QUARTER:
			current_transaction += 0.25f;
			current_transaction_coins[Coin.QUARTER.getIdx()]++;
			break;
		}
	}

	public void pressCoinReturn() {
		while (current_transaction_coins[Coin.NICKEL.getIdx()] > 0) {
			coinReturn += "(5)";
			current_transaction_coins[Coin.NICKEL.getIdx()]--;
		}

		while (current_transaction_coins[Coin.DIME.getIdx()] > 0) {
			coinReturn += "(10)";
			current_transaction_coins[Coin.DIME.getIdx()]--;
		}

		while (current_transaction_coins[Coin.QUARTER.getIdx()] > 0) {
			coinReturn += "(25)";
			current_transaction_coins[Coin.QUARTER.getIdx()]--;
		}

		current_transaction = 0.0f;
	}

	public String getCoinReturnString() {
		return coinReturn;
	}

	public void emptyCoinReturn() {
		coinReturn = "";
	}
}
