package hw8;

/**
 * Names: Barbara Vargas and Shan Lu
 * Date: Dec 4 2017
 */

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.*;

//This class is a tester for the unicalc class
public class UnicalcTester {
   private Unicalc calc;
   //Sets up a unicalc for each test
   @Before 
   public void setUp(){
      calc = new Unicalc();
    
   }
   

/**This method test the ability of the R() method to tokenize the input string 
 * according to R->V | V^2
 * @param:none
 * @return:none
 */
@Test
public void STest(){
   //write an R expression as a string + parse
   String input = "def smoot 50 in";
   //tokenize
   calc.tokenize(input);
   //call the s method
   AST  outputOfS = calc.S();
   //create arrays for quan obbjects
   List<String> emp1 = new ArrayList<String>();
   List<String> emp2 = new ArrayList<String>();
   List<String> inches = new ArrayList<String>();
   inches.add("in");

   //create a new Quantity 
   Quantity quan = new Quantity(50.0,emp1, emp2); 
   Quantity quan1 = new Quantity(1.0,inches,emp1);
   

   //check if the output is correct
   assertEquals(outputOfS, new Define("smoot", new Product(new Value(quan), new Value(quan1))));
   
}

/**This method tests the ability of L() method to tokenize the input string and
 * then using the given grammar convert it approprately
 * @param:none
 * @return: none
 */
@Test
public void LTest() {
   //string we will input 
	String input = "# 364.4 smoot";
   //tokenize
	calc.tokenize(input);
   //call the L method
	AST outputOfL = calc.L();
   //create arrays for quan objects
	List<String> emp = new ArrayList<String>();
	List<String> smoo = new ArrayList<String>();
	smoo.add("smoot");
   //create quan objects to use
	Quantity quan = new Quantity(1.0, smoo, emp);
	Quantity quan1 = new Quantity(364.4);
   //check if output is correct
	assertEquals(outputOfL,new Normalize(new Product(new Value(quan1),new Value(quan))));
}

/**This method test the ability of E() to tokenize the input string and then use
 * the given grammar to convert it appropriately
 * @param:none
 * @return:none
 */
@Test
public void ETest() {
   //string to inpur
	String input = "14m+9m";
   //tokenize
	calc.tokenize(input);
   //call E() method
	AST output = calc.E();
   //create lists needed for quan objects
	List<String> emp = new ArrayList<String>();
	List<String> m = new ArrayList<String>();
	m.add("m");
   //create quantities needed 
	Quantity quan = new Quantity(14.0, emp, emp);
	Quantity quan1 = new Quantity(1.0,m,emp);
	Quantity quan3 = new Quantity(9.0,emp,emp);
	Quantity quan4 = new Quantity(1.0,m,emp);
   //check if output is correct
	assertEquals(output, new Sum(new Product(new Value(quan), new Value(quan1)), new Product(new Value(quan3), new Value(quan4))));
}

/**This method tests the P() methods abbility to tokenize the input string then
 * use the given grammar to convert it appropriately
 * @param:none
 * @return:none
 */
@Test
public void PTest() {
   //input string
	String input = "60 Hz * 30s";
   //tokenize
	calc.tokenize(input);
   //call the P method
	AST output = calc.P();
   //create lists for quan objects
	List<String> emp = new ArrayList<String>();
	List<String> hz = new ArrayList<String>();
	hz.add("Hz");
	List<String> s = new ArrayList<String>();
	s.add("s");
   //create quan obbjects
	Quantity quan = new Quantity(60.0, emp, emp);
	Quantity quan1 = new Quantity(1.0, hz, emp);
	Quantity quan2 = new Quantity(30.0, emp, emp);
	Quantity quan3 = new Quantity(1.0, s, emp);
   //check if output is correct
	assertEquals(output, new Product(new Product(new Value(quan),new Value(quan1)),new Product(new Value(quan2), new Value(quan3))));
}

/**This method tests the K() ability to tokenize the input string and use the
 * given grammar to convert it approprately
 * @param:none
 * @return:None
 */
@Test
public void KTest() {
   //input string
	String input = "- 1";
   //tokenize
	calc.tokenize(input);
   //call k method
	AST output = calc.K();
   //create lists needed for quan
	List<String> emp = new ArrayList<String>();
   //create quan object needed 
	Quantity quan = new Quantity(1.0, emp, emp);
   //check if output is correct 
	assertEquals(output, new Negation(new Value(quan)));
}

/**This method tests the Q methods abbility to tokenize the input string and use
 * the given grammar to convert it appropriately
 * @param:none
 * @return:none
 */
@Test
public void QTest() {
   //input string
	String input = "60 s s";
   //tokenize
	calc.tokenize(input);
   //call P method
	AST output = calc.P();
   //create lists needed for quan objects
	List<String> emp = new ArrayList<String>();
	List<String> hz = new ArrayList<String>();
	hz.add("Hz");
	List<String> s = new ArrayList<String>();
	s.add("s");
   //create quantities
	Quantity quan = new Quantity(60.0, emp, emp);
	Quantity quan1 = new Quantity(1.0, s, emp);
	Quantity quan2 = new Quantity(1.0, s, emp);
   //check if output is correct 
    assertEquals(output, new Product(new Value(quan),new Product(new Value(quan1), new Value(quan1))));
}

/**This method tests the R tests ability to tokenize the input string anduse the
 * given grammar to convert it appropriately
 * @param:none
 * @return:none
 */
@Test
public void RTest() {
   //input string
    String input = "30 s ^ 2";
    //tokenize
    calc.tokenize(input);
    //call R method
    AST output = calc.R();
    //create lists needed for quan obj
    List<String> emp = new ArrayList<String>();
    //create quan object
    Quantity quan = new Quantity(30.0, emp, emp);
    //check if output is correct
    assertEquals(output, new Value(quan));
}

}//end class 
