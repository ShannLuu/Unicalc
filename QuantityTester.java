package hw8;

/**Names: Shan Lu and Barbara Vargas
 * Login: cs12fei     cs12fow
 * Id:    A13361648   A13971081
 * Date: Dec 4, 2017
 */
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.junit.*;

//This class tests the methods in the Quanity class for correctness
public class QuantityTester {
	private Quantity initial;
	private Quantity oneArg;
	private Quantity thrArg;
	private Quantity tester;
	
   /**This method sets up all needed variables so that all tests can run
    * @parm:none
    * @return:none
    */
    @Before
    public void setUp() {
    	    initial = new Quantity();
    	    oneArg = new Quantity(5.0, Arrays.asList("m"), Arrays.asList("s","s"));
    	    thrArg = new Quantity(10.0, Arrays.asList("m"), Arrays.asList("s","s"));
    	    tester = new Quantity(15.0, Arrays.asList("m"), Arrays.asList("s","s"));
    }
    
    /**This method tests that the no arg constructor in Quantity class. Checks
     * that value is initialized to 1 and no units
     * @param: none
     * @return: none
     */
    @Test
    public void testNonArgCon() {
       //get the tostring of the quantity object
    	  String iniStr = initial.toString();
       //correct output
    	  String one = "1.0";
       //test correcr with resulting 
    	   assertEquals(iniStr,one);
    }
    
    /**This method tests the correctness of the one arg constructor in Quantity
     * class. It makes sure that a deep copy is made correctly
     * @param: none
     * @return: none
     */
    @Test
    public void testOneArgCon() {
       //call the one arg constructor
       Quantity tester1 = new Quantity(oneArg);
       //convert output to string
       String oneArgStr = oneArg.toString();
       String tester1Str = tester1.toString();
       //test correct with resulting
       assertEquals(tester1Str,oneArgStr);
    }
    
    /**This method tests the correctness of the three arg constructor which
     * creates a new quantity object given a double and units for the num and den
     * @parm: none
     * @return:none
     */
    @Test
    public void testThrArgCon() {
       //get string 
    	    String thrArgStr = thrArg.toString();
          //correct output
    	    String test = "10.0 m s^-2";
          //test correct with resulting
    	    assertEquals(thrArgStr, test);
    }
    
    /**This method tests the Mul method which should return a new Quantity
     * object with the correct value after multiplaction
     * @param:none
     * @return:none
     */
    @Test
    public void testMul() {
       //quantity object to test
    	    Quantity test = tester.mul(thrArg);
          //create string
    	    String testStr = test.toString();
          //corect output
    	    String product = "150.0 m^2 s^-4";
          //test correct with resulting
    	    assertEquals(testStr, product);
    }
    
    /**This method tests that div method accuratley produces and returns the
     * quotient of the input arg
     * @param: none
     * @return: none
     */
    @Test
    public void testDiv() {
       //quanity object to test
    	    Quantity test = tester.div(oneArg);
          //string of output
    	    String testStr = test.toString();
          //correct output
    	    String division = "3.0";
          //test correct with resulting
    	    assertEquals(testStr,division);
    }
    
    /**This method tests the pow method returns a quantity that is raised to the
     * input power
     * @param: none
     * @return: none
     */
    @Test
    public void testPow() {
       //Quantity to test
    	    Quantity test = thrArg.pow(2);
          //string it
    	    String testStr = test.toString();
          //correct output
    	    String power = "100.0 m^2 s^-4";
          //test correct with resulting
    	    assertEquals(testStr, power);
    }
    
    /**This method tests that the add method returns a new Quantity object with
     * the correct sum and units
     * @param: none
     * @return:none
     */
    @Test
    public void testAdd() {
       //quanity to test
    	    Quantity test = thrArg.add(tester);
          //string it 
    	    String testStr = test.toString();
          //correct output
    	    String addition = "25.0 m s^-2";
          //test correct and resulting
    	    assertEquals(testStr, addition);
    }
    
    /**This method tests the sub method returns a new Quantity object with the
     * correct sub value with units
     * @param:none
     * @return:none
     */
    @Test
    public void testSub() {
       //create quan to test
    	    Quantity test = tester.sub(thrArg);
          //string it
    	    String testStr = test.toString();
          //correct output
    	    String substraction = "5.0 m s^-2";
          //test correct with resulting
    	    assertEquals(testStr, substraction);
    }
    
    /**This method tests the negate method in Quantity class and makes sure it
     * correctly returns a new Quan obbject that is negated
     * @param:none
     * @return:none
     */
    @Test
    public void testNegate() {
       //create quan to test
    	    Quantity test = tester.negate();
          //string it
    	    String testStr = test.toString();
          //correct output
    	    String negation = "-15.0 m s^-2";
          //test correct and resulting
    	    assertEquals(negation, testStr);
    }
    
    /**This method tests that the equals method in Quantity returns true if the
     * passed objects units are the same as the calling objects when rounded to
     * five places
     * @param:none
     * @return:none
     */
    @Test
    public void testEquals() {
       //object to test
    	    Object test = new Quantity(15.0, Arrays.asList("m"), Arrays.asList("s","s"));
          //test result is as expected
    	    assertTrue(tester.equals(test));
    }
    
    /** This method tests the that hashCode returns the correct integer such
     * that equal quantities always return the same integer
     * @param: none
     * @return:none
     */
    @Test
    public void testHashCode() {
       //quan to test
    	    Quantity test = new Quantity(15.0, Arrays.asList("m"), Arrays.asList("s","s"));
          //int result
    	    int testHash = test.hashCode();
          //correct
    	    int testerHash = tester.hashCode();
          //test correct and resulting
    	    assertEquals(testHash, testerHash);
    }
    
    /**This method tests that the noralizedUnit method creates a new normalized
     * quantity equivalent to 1 given unit
     * @param:none
     * @return:none
     */
    @Test
    public void testNormUnit() {
       //array for quantity object
    	    List<String> emp = new ArrayList<String>();
    	    //Quantity.normalizedUnit("km",db);
    	    //Quantity test = new Quantity(1.0, Arrays.asList("km"), emp);
          //add to map
    	    Map<String, Quantity> db = new HashMap<String, Quantity>();
    	    db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
          //get the noralized unit
    	    Quantity result = Quantity.normalizedUnit("km",db);
          //correct output
    	    Quantity shouldBe = new Quantity(1000.0, Arrays.asList("meter"), emp);
          //test correct and resulting
    	    assertTrue(result.equals(shouldBe));
    }
    
    /**This method tests the normalize method and takes in a databbase and
     * returns a copy but in normalized form
     * @param:none
     * @return:none
     */
    @Test
    public void testNormalize() {
       //array for quan object
    	    List<String> emp = new ArrayList<String>();
          //quan to test
    	    Quantity test = new Quantity(2.0, Arrays.asList("km"), emp);
          //add to map
    	    Map<String, Quantity> db = new HashMap<String, Quantity>();
    	    db.put("km", new Quantity(1000, Arrays.asList("meter"), emp));
          //noramalize
    	    Quantity normalized = test.normalize(db);
          //correct output
    	    Quantity shouldBe = new Quantity(2000.0, Arrays.asList("meter"),emp);
          //test correct and resulting
    	    assertTrue(shouldBe.equals(normalized));
    }
    
    
}
