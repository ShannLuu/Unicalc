package hw8;
/**
 * Name: Barbara Vargas and Shan Lu
 * Login:cs12foe            cs12fei
 * Id:   A13971081          A13361648
 * Date: Dec 2, 2017
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;


//This class tests the methods in AST.java for correctness in both values and
//units
public class ASTTester {

   /**This test tests that product in  AST works and the eval method in product 
    * work appropriatly produce correct product and units 
    * @param: none
    * @return: none
    */
	@Test
	public void testPro() {
      //create arrays with appropriate units
		List<String> m = new ArrayList<String>();
		m.add("meter");
		List<String> ss = new ArrayList<String>();
		ss.add("s");
		ss.add("s");

      //create quantity objects to multiply
		Quantity three = new Quantity(3.0, m, ss);
		Quantity five = new Quantity(5.0,m,ss);

      //multiply using the product method
		Product pro = new Product(new Value(three), new Value(five));
      //evalute
		Quantity test = pro.eval(QuantityDB.getDB());
		List<String> mm = new ArrayList<String>();
		mm.add("meter");
		mm.add("meter");
		List<String> ssss = new ArrayList<String>();
		ssss.add("s");
		ssss.add("s");
		ssss.add("s");
		ssss.add("s");
      //correct quantity
		Quantity shouldBe = new Quantity(15.0, mm, ssss);
      //test expected and resulting 
		assertEquals(test, shouldBe);
	}
	
   /**This method tests that Quotient in AST  and eval method in Quotient
    * produce the correct quotient with units
    * @param: none
    * @return: none
    */
	@Test
	public void testQuo() {
      //create arrays with appropriate units 
		List<String> emp = new ArrayList<String>();
		List<String> m = new ArrayList<String>();
		m.add("m");
		List<String> ss = new ArrayList<String>();
		ss.add("s");
		ss.add("s");
      //create quanities to divide
		Quantity three = new Quantity(3.0, m, ss);
		Quantity fifteen = new Quantity(15.0, m, ss);
      //correct output
	    Quantity shouldBe = new Quantity(5.0, emp, emp);
       //divide
	    Quotient quo = new Quotient(new Value(fifteen), new Value(three));
       //evaluate
	    Quantity test = quo.eval(QuantityDB.getDB());
       //test the expected and resulting
	    assertEquals(test, shouldBe);
	}
	
   /**This method tests that Sum in the AST class produces the
    * correct sum with units
    * @param: none
    * @return: none
    */
	@Test
	public void testSum() {
      //create arrays with units 
		List<String> m = new ArrayList<String>();
		m.add("m");
		List<String> ss = new ArrayList<String>();
		ss.add("s");
		ss.add("s");
      //create quanity objects to add
		Quantity three = new Quantity(3.0, m, ss);
		Quantity five = new Quantity(5.0, m, ss);
      //correct quantity
	    Quantity shouldBe = new Quantity(8.0, m, ss);
       //get the sum
	    Sum sum = new Sum(new Value(three), new Value(five));
       //evaluate 
	    Quantity test = sum.eval(QuantityDB.getDB());
       //test expected and resulting
	    assertEquals(test,shouldBe);
	}
	
   /**This method test that the Difference in AST class produces correct
    * difference with correct units
    * @param: none
    * @return: none
    */
	@Test
	public void testDiff() {
      //create arrays with units
		List<String> m = new ArrayList<String>();
		m.add("m");
		List<String> ss = new ArrayList<String>();
		ss.add("s");
		ss.add("s");

      //create quanity objects to subtract
		Quantity three = new Quantity(3.0, m, ss);
		Quantity five = new Quantity(5.0, m, ss);
      //corect sum
		Quantity shouldBe = new Quantity(2.0, m, ss);
      //calculate sum
		Difference diff = new Difference(new Value(five), new Value(three));
      //evaluate
		Quantity test = diff.eval(QuantityDB.getDB());
      //test expected and resulting
		assertEquals(test, shouldBe);
	}
	
   /**This method tests that Power in AST class produces correct power output
    * with correct units
    * @param: none
    * @return: none
    */
	@Test
	public void testPow() {
      //create arrays with units
      List<String> m = new ArrayList<String>();
      m.add("m");
      List<String> emp = new ArrayList<String>();
      List<String> mm = new ArrayList<String>();
      mm.add("m");
      mm.add("m");

      //create quanity 
      Quantity ten = new Quantity(10.0,m,emp);

      //correct power
      Quantity shouldBe = new Quantity(100.0,mm,emp);
     // System.out.println("This is what quantity should be  :" + shouldBe);

      //Calculate it with power
      Power pow = new Power(new Value(ten),2);

      //evaluate 
      Quantity test = pow.eval(QuantityDB.getDB());
     // System.out.println("This is what the eval test is : " + test);

      //test expected and resulting
      assertEquals(test, shouldBe);


		
	}

   /**This test checks to see if the Negation works in AST and if the output
    * units are correct
    * @param: none
    * @return: none
    */
	@Test
	public void testNeg() {
      //create arrays with units 
      List<String> m = new ArrayList<String>();
      m.add("m");
      List<String> ss = new ArrayList<String>();
      ss.add("s");
      ss.add("s");

      //create quantity object to negate 
      Quantity three = new Quantity(3.0,m,ss);

      //correct negation
      Quantity shouldBe = new Quantity(-3.0,m,ss);

      //calculate the negation
      Negation neg = new Negation(new Value(three));

      //evaluate 
      Quantity test = neg.eval(QuantityDB.getDB());

      //test expected result and resulting
      assertEquals(test, shouldBe);

		
	}
	
   /**This test checks to see if the normalization in AST gives the correct
    * output with units 
    * @param: none
    * @return: none
    */
	@Test
	public void testNor() {
      //create arrays with units
      List<String> emp = new ArrayList<String>();
      List<String> meter = new ArrayList<String>();
      meter.add("meter");
      List<String> km = new ArrayList<String>();
      km.add("km");

      //create quantity obbject to normalize
      Quantity one = new Quantity(1.0,km,emp);

      //add to data base
      Map data = QuantityDB.getDB();
      data.put("meter", 1);

      //correct normalization
      Quantity shouldBe =  new Quantity(1000.0,meter,emp);
      //System.out.println("This is what the quan should be : " + shouldBe);

      //calculate the normalization
      Normalize norm = new Normalize(new Value(one));

      //eval
      Quantity test = norm.eval(QuantityDB.getDB());
      //System.out.println("This is what test eval outputs :"+ test);

      //test expected result and resulting
      assertEquals(test, shouldBe);
		
	}
	
   /**This test checks to see if Define in AST evalutates correctly (includes
    * correct units
    * @param: none
    * @return: none
    */
	@Test
	public void testDef() {
      //create arrays with units
      List<String> smoot = new ArrayList<String>();
      smoot.add("smoot");
      List<String> emp = new ArrayList<String>();
      
      //create quanity object to define
      Quantity four = new Quantity(4.0,smoot,emp);

      //correct definition
      Quantity shouldBe = new Quantity(4.0,smoot,emp);

      //add to the dataBase
      Map data = QuantityDB.getDB();
      data.put("smoot",1);

      //calulate the new def
      Define def = new Define("smoot",new Value(four));

      //evaluate 
      Quantity test = def.eval(QuantityDB.getDB());
     // System.out.println("This is what eval produces: " + test);

      //test the expected result with resulting
      assertEquals(test, shouldBe);
		
	}
	
   /**This test checks to see if Value in AST evaluates and ouputs the correct
    * number and its associated units
    * @param: none
    * @return: none
    */
	@Test
	public void testVal() {
      //create arrays with units
      List<String> m = new ArrayList<String>();
      m.add("m");
      List<String> emp = new ArrayList<String>();

      //create quanity obbject to test val
      Quantity five = new Quantity(5.0,m,emp);

      //correct val
      Quantity shouldBe = new Quantity(5.0,m,emp);
      
      //calcaulate the value
      Value val = new Value(five);

      //eval
      Quantity test = val.eval(QuantityDB.getDB());

      //test the expected result and resulting
      assertEquals(test,shouldBe);

		
	}
}
