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
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void WhenVendingMachineDisplayStartsWithInsertCoin() {
		VendingMachine VendingMachine = new VendingMachine() ;
		
		assertEquals( "", VendingMachine.PrintDisplay(), "INSERT COIN");
	}

}
