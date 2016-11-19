package aishasteege.vending_machine;



public class VendingMachine {
	float current_transaction;
	
	VendingMachine(){
		current_transaction = 0;
	}

	String PrintDisplay()
	{
		if ( current_transaction == 0 )
		{
			return "INSERT COIN";
		}
		else 
		{
			return "$" + String.valueOf(current_transaction);
		}
	}

	public void AddCoin( Coin coin ) {
		current_transaction = 0.25f;
	}
}
