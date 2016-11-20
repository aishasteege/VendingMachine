package aishasteege.vending_machine;

import java.text.DecimalFormat;

public class VendingMachine {
	int[] current_transaction_coins;

	float current_transaction;

	public VendingMachine() {
		current_transaction = 0.0f;

		current_transaction_coins = new int[Coin.NUM_COINS];
		for (int i = 0; i < Coin.NUM_COINS; i++) {
			current_transaction_coins[i] = 0;
		}
	}

	public String getDisplayString() {
		DecimalFormat moneyFormat = new DecimalFormat("0.00");

		if (current_transaction == 0) {
			return "INSERT COIN";
		} else {
			return "$" + moneyFormat.format(current_transaction);
		}
	}

	public void addCoin(Coin coin) {
		switch (coin) {
		case PENNY:
			current_transaction_coins[Coin.PENNY.getIdx()]++;
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

	public String returnCoin() {
		String coinReturn = "";

		while (current_transaction_coins[Coin.PENNY.getIdx()] > 0) {
			coinReturn += "(1)";
			current_transaction_coins[Coin.PENNY.getIdx()]--;
		}

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

		return coinReturn;
	}
}
