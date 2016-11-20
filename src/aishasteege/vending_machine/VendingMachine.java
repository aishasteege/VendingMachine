package aishasteege.vending_machine;

import java.text.DecimalFormat;

public class VendingMachine {
	float current_transaction;

	Integer coin_return_count;

	VendingMachine() {
		current_transaction = 0.0f;
		coin_return_count = 0;
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
			coin_return_count++;
			break;
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

	public String returnCoin() {
		String coinReturn = "";
		for (int i = 0; i < coin_return_count; i++) {
			coinReturn += "(1)";
		}
		coin_return_count = 0;
		return coinReturn;
	}
}
