package hw8;

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
   

//This method test the ability of the R() method to tokenize the input string 
//according to R->V | V^2
@Test
public void sTest(){
   //write an R expression as a string + parse
   String input = "def smoot 50 in";
   //tokenize
   calc.tokenize(input);
   //call the s method
   AST  outputOfS = calc.S();
   List<String> emp1 = new ArrayList<String>();
   List<String> emp2 = new ArrayList<String>();
   List<String> inches = new ArrayList<String>();
   inches.add("in");
   //create a new Quantity 
   Quantity quan = new Quantity(50.0,emp1, emp2); 
   Quantity quan1 = new Quantity(1.0,inches,emp1);
   //System.out.println("This is what calc S() Prints " + outOfS.toString());
  
    //Define shou
   // String string = shouldBe.toString();
    //System.out.println("This is the toString output " + string);
   //check if the output is correct
   assertEquals(outputOfS, new Define("smoot", new Product(new Value(quan), new Value(quan1))));
   
}


}//end class 
