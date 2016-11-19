package aishasteege.vending_machine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VendingMachineTest {

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//
//	@After
//	public void tearDown() throws Exception {
//	}

	VendingMachine VendingMachine;
	
	@Before
	public void setUp(){
		VendingMachine = new VendingMachine() ;
	}
	
	@Test
	public void WhenVendingMachineDisplayStartsWithInsertCoin() {
		assertEquals( VendingMachine.getDisplayString(), "INSERT COIN");
	}
	
	@Test
	public void WhenQuarterIsAddedShowTwentyFive(){
		VendingMachine.addCoin( Coin.QUARTER );		
		assertEquals( VendingMachine.getDisplayString(), "$0.25");
	}
	
	@Test
	public void WhenValidCoinsAreAddedShowCompinedValue(){
		VendingMachine.addCoin( Coin.NICKEL );	
		assertEquals( VendingMachine.getDisplayString(), "$0.05");
		VendingMachine.addCoin( Coin.DIME );	
		assertEquals( VendingMachine.getDisplayString(), "$0.15");
		VendingMachine.addCoin( Coin.QUARTER );		
		assertEquals( VendingMachine.getDisplayString(), "$0.40");
	}

	
	
}
