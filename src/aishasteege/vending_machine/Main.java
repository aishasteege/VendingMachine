package aishasteege.vending_machine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		VendingMachine vendingMachine = new VendingMachine();

		vendingMachine.stockProduct(Product.COLA, 2);
		vendingMachine.stockProduct(Product.CHIPS, 2);
		vendingMachine.stockProduct(Product.CANDY, 2);
		
		while (true)
		{
			System.out.println("----------------------------------------------------");
			System.out.println("|" + center(vendingMachine.getDisplayString(), 50, ' ') + "|");
			System.out.println("|--------------------------------------------------|");
			System.out.printf("|%-50s|\n", "");
			System.out.println("|                                       .[]        |");
			System.out.printf("|%-50s|\n", "");
			System.out.printf("|%48s  |\n", "<COIN RETURN>");
			System.out.printf("|%48s  |\n", vendingMachine.getCoinReturnString());
			System.out.printf("|%-50s|\n", "");
			System.out.printf("|%-50s|\n", "");
			System.out.printf("|   %-47s|\n", "[1 - COLA ]");
			System.out.printf("|   %-47s|\n", "[2 - CHIPS]");
			System.out.printf("|   %-47s|\n", "[3 - CANDY]");
			System.out.printf("|%-50s|\n", "");
			System.out.printf("|%-50s|\n", "");
			System.out.println("|" + center("<PRODUCT DISPENSER>", 50, ' ') + "|");
			System.out.println(
					"|" + center(vendingMachine.getProductDispenseString(), 50, ' ') + "|");
			System.out.printf("|%-50s|\n", "");
			System.out.println("----------------------------------------------------");

			System.out.println("TO USE THE MACHINE - ENTER THE CODE FOR YOUR ACTION");
			System.out.println("    S# - Select item by number <S1, S2, S3>");
			System.out.println("    C# - Insert coin item by cents <C1,C5,C10,C25>");
			System.out.println("    CR - Press Coin Return Button");
			System.out.println("    P  - Take Your Product");
			System.out.println("    C  - Take Your Coins From the Coin Return");

			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			readInput(vendingMachine, input.readLine());
		}
	}

	/***************************************************************************
	 * @param machine vending machine being used
	 * @param command entered by the user
	 */
	private static void readInput(VendingMachine machine, String command)
	{
		switch (command.toUpperCase())
		{
		case "S1":
			machine.selectProduct(Product.COLA);
			break;
		case "S2":
			machine.selectProduct(Product.CHIPS);
			break;
		case "S3":
			machine.selectProduct(Product.CANDY);
			break;
		case "C1":
			machine.addCoin(Coin.PENNY);
			break;
		case "C5":
			machine.addCoin(Coin.NICKEL);
			break;
		case "C10":
			machine.addCoin(Coin.DIME);
			break;
		case "C25":
			machine.addCoin(Coin.QUARTER);
			break;
		case "CR":
			machine.pressCoinReturn();
			break;
		case "P":
			machine.takeProduct();
			break;
		case "C":
			machine.emptyCoinReturn();
			break;
		default:
			System.out.println("YOUR CODE WAS INVALID, PLEASE TRY AGAIN!!");
		}
	}

	public static String center(String string, int size, char pad)
	{
		if (string == null || size <= string.length())
			return string;

		StringBuilder sb = new StringBuilder(size);
		for (int i = 0; i < (size - string.length()) / 2; i++)
		{
			sb.append(pad);
		}
		sb.append(string);
		while (sb.length() < size)
		{
			sb.append(pad);
		}
		return sb.toString();
	}
}
