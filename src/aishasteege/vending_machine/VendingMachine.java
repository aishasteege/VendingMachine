package aishasteege.vending_machine;

import java.text.DecimalFormat;

public class VendingMachine {
	float current_transaction;

	VendingMachine() {
		current_transaction = 0;
	}

	String getDisplayString() {
		DecimalFormat moneyFormat = new DecimalFormat("0.00");

		if (current_transaction == 0) {
			return "INSERT COIN";
		} else {
			return "$" + moneyFormat.format(current_transaction);
		}
	}

	public void addCoin(Coin coin) {
		switch (coin) {
		case NICKEL:
			current_transaction += 0.05f;
			break;
		case DIME:
			current_transaction += 0.10f;
			break;
		case QUARTER:
			current_transaction += 0.25f;
			break;
		default:
			break;
		}
	}
}
