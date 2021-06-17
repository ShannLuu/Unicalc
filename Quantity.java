package hw8;

import java.util.Map;
import java.util.TreeSet;
import java.util.HashMap;  
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList; 
import java.util.Arrays;
import java.util.Collections;

/*Names:Barbara Vargas, Shan Lu
 * Date: Dec 5 2017
 */
//This class represents Quantity objects and thier associated units and contains
//the methods to manilpulate these Quantities
public class Quantity {
	double value;
	Map<String,Integer> map;
	
   /**This constructor creates a default quantity of value 1 and no unit
    * @param:none
    * @return:none
    */
    public Quantity() {
    	     value = 1.0;
    	     map = new HashMap<String,Integer>();    	     
    }
    
    /**One arg constructor creates a deep copu
     * @param:Quantity toCopy
     * @return: none
     */
    public Quantity(Quantity toCopy) 
    {     //set value
    	    this.value = toCopy.value;
          //set map
    	    this.map = new HashMap<String,Integer>(toCopy.map);    	    
    }
    
    /**Three arg constructor that makes a quanity object
     * @param: doubble ele1, List<String>ele2, List<String>ele3
     * @return:none
     */
    public Quantity(double ele1, List<String>ele2, List<String>ele3) {
       //create new map
        	map = new HashMap<String,Integer>();
         //throw exception if lists are null
    	    if(ele2 == null || ele3 == null) {
    	    	   throw new IllegalArgumentException();
    	    }  

          //set the value 
    	    this.value = (double)ele1;
    	    
          //update units
    	    for(int i=0; i<ele2.size(); i++) {
    	    	    String unit = ele2.get(i);
    	    	    if(this.map.containsKey(unit)) {
    	    	    	  int exponent = this.map.get(unit);
    	    	    	  exponent++;
    	    	    	  this.map.put(unit, exponent);
    	    	    }
    	    	    else {
    	    	    	    this.map.put(unit, 1);
    	    	    } 	    	    
    	    }
    	    //update units
    	    for(int i=0; i<ele3.size(); i++) {
    	    	   String unit = ele3.get(i);
    	    	   if(this.map.containsKey(unit)) {
 	    	    	  int exponent = this.map.get(unit);
 	    	    	  exponent--;
 	    	    	  this.map.put(unit, exponent);
 	    	    }
    	    	   else {
    	    		    this.map.put(unit, -1);
    	    	   }
    	    }
    	    
    	    List<String> thisList = new ArrayList<String>(this.map.keySet());
    	    for(int i=0; i<thisList.size(); i++) {
    	    	   String unit = thisList.get(i);
    	    	   if(this.map.get(unit) == 0) {
    	    		  map.remove(unit);
    	    	   }
    	    }
    }
    
    /**This method takes a single Quantity argument multiplies this by the
     * argument and returns the result
     * @param:Quantity quan
     * @return: Quantity
     */
    public Quantity mul(Quantity quan) throws IllegalArgumentException{
       //throw excep if param is null
    	    if(quan == null ) {
    	    	  throw new IllegalArgumentException();
    	    }
          //create Quantity object to return
    	    Quantity quanReturn = new Quantity();
          //multiply
    	    quanReturn.value = this.value*quan.value;
    	     	    
    	    List<String> thisList = new ArrayList<String>(this.map.keySet());
    	    List<String> quanList = new ArrayList<String>(quan.map.keySet());    	    
    	    //update units
    	    for(int i=0; i<thisList.size(); i++) {
    	    	    String unit = thisList.get(i);    	    	 
    	    	    int exponent = this.map.get(unit);    	    	    	    	    	    	      
    	    	    quanReturn.map.put(unit, exponent);
    	    }
    
    	    //update units
    	    for(int i=0; i<quanList.size(); i++) {
    	    	    String unit = quanList.get(i);
    	    	    if(quanReturn.map.containsKey(unit)) {
	    	       int exponent = quan.map.get(unit);
	    	       int thisExpo = quanReturn.map.get(unit);
	    	    	   quanReturn.map.put(unit, exponent+thisExpo);	    	       
    	    	    }
    	    	    else {
    	    	    	    int exponent = quan.map.get(unit);   	    	 
    	    	    	    quanReturn.map.put(unit,exponent);
    	    	    }
	    }
    	    	   
    	    List<String> returnList = new ArrayList<String>(quanReturn.map.keySet());
    	    for(int i=0; i<returnList.size(); i++) {
    	    	   String unit = returnList.get(i);
    	    	   if(quanReturn.map.get(unit) == 0) {
    	    		  quanReturn.map.remove(unit,0);
    	    	   }
    	    }  	    
    	    return quanReturn;   	   
    }
    
   /**This method takes a single Quanity argument divides this bby the argument
    * and returns the result
    * @param: Quantity quan
    * @return: Quantity quanReturn
    */
	public Quantity div(Quantity quan) throws IllegalArgumentException{
      //check if param is null or the value is 0
    	    if(quan == null || quan.value == 0) {
    	    	   throw new IllegalArgumentException();
    	    }
          //create quantity object to return 
    	    Quantity quanReturn = new Quantity();
    	    Quantity temp = new Quantity();
          //get the keySet in lists
    	    List<String> thisList = new ArrayList<String>(this.map.keySet());
    	    List<String> quanList = new ArrayList<String>(quan.map.keySet()); 
    	    
          //divide and store value
    	    quanReturn.value = this.value/quan.value;    	    
    	    //update units
    	    	for(int j=0; j<quanList.size(); j++) {
    	    		String quanUnit = quanList.get(j);
    	    		int expo = -(quan.map.get(quanUnit));
    	    		temp.map.put(quanUnit, expo);
    	    	}
    	    	quanReturn = this.mul(temp);
    	    	quanReturn.value = this.value/quan.value;
    	    	return quanReturn;
    }
    
    /**This method takes a single int argument (pos,neg, or 0) and raises this
     * to the given power and returns the result
     * @param: int exponent
     * @return: Quantity quanReturn
     */
    public Quantity pow(int exponent) {
       //create quantity to return
    	    Quantity quanReturn = new Quantity();
          //check for 0 exponent
    	    if(exponent == 0) {
    	    	   quanReturn = new Quantity();
    	    }
    	    else {
             //raise it to the power given by parameter
    	    	    quanReturn.value = Math.pow(this.value, exponent);
                //put untit sin list
    	    	    List<String> thisList = new ArrayList<String>(this.map.keySet());
                //loop through ;ist 
    	    	    for(int i=0; i<thisList.size(); i++) {
    	    	    	    String unit = thisList.get(i);
    	    	    	    int expo = this.map.get(unit);
    	    	    	    int power = expo*exponent;
                      //update map
    	    	    	    quanReturn.map.put(unit,power);
    	    	    }
    	    }
    	    return quanReturn;
    }    
    
    /**This method takes a single Quantity argument adds to it and returns the
     * result
     * @param: Quantity quan
     * @return: Quantity quanReturn
     */
    public Quantity add(Quantity quan) throws IllegalArgumentException{
       //check for null obbjects and different units
    	    if(quan == null || !this.map.equals(quan.map)) {
    	    	  throw new IllegalArgumentException();
    	    }
          //create quantity to return
    	    Quantity quanReturn = new Quantity();
          //add and set the value in the quanReturn
    	    quanReturn.value = quan.value+this.value;
          //set map
    	    quanReturn.map = new HashMap<String,Integer>(this.map);
    	    return quanReturn;
    }
    
    /**This method takes a singele Quantity argument and subbtracts it from this
     * and returns the result
     * @param:Quantity quan
     * @return: Quantity
     */
    public Quantity sub(Quantity quan) throws IllegalArgumentException{
       //check if quan is null or has diferent unit 
    	    if(quan == null || !this.map.equals(quan.map)) {
    	    	   throw new IllegalArgumentException(); 
    	    }
          //create quantity to return
    	    Quantity quanReturn = new Quantity();
          //subtract
    	    quanReturn.value = this.value-quan.value;  
          //set the ret quan map
    	    quanReturn.map = new HashMap<String,Integer>(this.map);
    	    return quanReturn;
    }
    
    /**This method returns a new Quantity which is the negation of Quantity
     * @param:none
     * @return:none
     */
    public Quantity negate() {
       //get the keys in list
    	    List<String> thisList = new ArrayList<String>(this.map.keySet());
          //create quantity object to return once negated
    	    Quantity quanReturn = new Quantity();
          //negate
    	    quanReturn.value = -(this.value);
          //loop through keys
    	    for(int i=0; i<thisList.size(); i++) {
    	    	   String unit = thisList.get(i);
    	    	   quanReturn.map.put(unit, this.map.get(unit));
    	    }
    	    return quanReturn;
    }
    
    /**This method returns the quantity as a string
     * @param:none
     * @return:String
     */
    public String toString()
    {
      double valueOfTheQuantity = this.value;
      Map<String,Integer> mapOfTheQuantity = this.map;
     
      TreeSet<String> orderedUnits = new TreeSet<String>(mapOfTheQuantity.keySet());
        
      StringBuffer unitsString = new StringBuffer();
        
      for (String key : orderedUnits) {
        int expt = mapOfTheQuantity.get(key);
        unitsString.append(" " + key);
        if (expt != 1)
             unitsString.append("^" + expt);
      }
        
      DecimalFormat df = new DecimalFormat("0.0####");
      return df.format(valueOfTheQuantity) + unitsString.toString();
    }
    
    /**This method takes any single object and returns true if and only if that
     * object is a Quantity whose units are exactly the same as the calling
     * object and whose value is the same when rounded to five places after dec
     * point
     * @param:Object temp
     * @return:true iff units are same up to 5 dec places, else false
     */
    public boolean equals(Object temp) throws IllegalArgumentException{
    	    boolean tempReturn = false;
          //throw exception if param is null
    	    if (temp == null) {
    	    	   throw new IllegalArgumentException();
    	    }
          
    	    if(temp instanceof Quantity) {
    	    	   Quantity quan = (Quantity)temp;
               //check if equal
    	    	   if(this.toString().equals(quan.toString())) {
    	    		  tempReturn = true;
    	    	   }
    	    }
    	    return tempReturn;
    }
    
    public int hashCode() {
    	    return this.toString().hashCode();
    }
    
    /**This method takes a string and a map and creates a bbrand new normalized
     * Quantity equivalent to 1 of the given unit
     * @param: String unit, Map<String,Quantity> database
     * @return: Quantity
     */
    public static Quantity normalizedUnit(String unit, Map<String,Quantity> database) {
       //create quantity to return
       Quantity quanReturn = new Quantity(); 
          //if unit is not in datababse
    	    if(!database.containsKey(unit)) {
             List<String> unitList= new ArrayList<String>();
             //add unit
             unitList.add(unit);
             List<String> emp= new ArrayList<String>();
             //adjust return quan
             quanReturn = new Quantity(1.0,unitList, emp);
    	    }
    	    else {
             //if in databbase then call normalize to norm the unit 
    	    	    quanReturn = database.get(unit).normalize(database);
    	    }
    	    return quanReturn;
    }
    
    /**This method returns a copy of this (calling object) but in normalized
     * form
     * @param: Map<String,Quantity> database
     * @return: Quantity
     */
    public Quantity normalize(Map<String,Quantity> database) {
       //put the keys in a string list
       	List<String> thisList = new ArrayList<String>(this.map.keySet());
         //create a quantity to return
       	Quantity quanReturn = new Quantity();
         //get the valuye
       	quanReturn.value = this.value;
         //create a map for the return quantity
       	quanReturn.map = new HashMap<String,Integer>(this.map);
         //loop through the keys
       	for(int i=0; i<thisList.size(); i++) {      	   
       	   String unit = thisList.get(i);
            //check if the databbase contains the key
       	   if(database.containsKey(unit)) {
       		  int exponent = this.map.get(unit);
       		  if(exponent>0) {
                 //recursive call to normalized
       		     Quantity normalized = Quantity.normalizedUnit(unit,database);
                 //update units
       		     Quantity denomenator = new Quantity(1.0, Arrays.asList(unit),Arrays.asList());
       		     Quantity division =normalized.div(denomenator);
       		     Quantity power = division.pow(exponent);
                 //return normalized quantity
       		     quanReturn = this.mul(power);       		   
       		  }
       		  if(exponent<0) {
               //call to normalized
       			Quantity normalized = Quantity.normalizedUnit(unit,database);
               //update units
       			Quantity numerator = new Quantity(1.0, Arrays.asList(unit),Arrays.asList());
       			Quantity division = numerator.div(normalized);
       			Quantity power = division.pow(exponent);
               //return normalized quanity
       			quanReturn = this.mul(power);       			
       		  }
       	   }
       	}       	       	
       	return quanReturn;
       	
    }

}
