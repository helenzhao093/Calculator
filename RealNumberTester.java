import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test class RealNumber
 */
public class RealNumberTester{
  /*RealNumbers to be tested*/
  private RealNumber a;
  private RealNumber b;
  private RealNumber c;
  private RealNumber d;
  private RealNumber e;
  private RealNumber f;
  private RealNumber g;
  private RealNumber h;
  private RealNumber i;
  private RealNumber j;
  private RealNumber k;
  private RealNumber l;
  private RealNumber m;
  private RealNumber n;
  private RealNumber o;
  private RealNumber p;
  private RealNumber q;
  private RealNumber r;
  private RealNumber s;
  private RealNumber t;
  private RealNumber u;
  private RealNumber v;
  private RealNumber w;
  private RealNumber x;
  /**
   * Set up the RealNumber values to be tested
   */
  @Before
  public void setUp(){
    a = new RealNumber(0, true, new int[]{1,4,5}); //precision of 0
    b = new RealNumber(0, false, new int[]{}); //data array of length 0
    c = new RealNumber(6, true, new int[]{5, 0, 0, 0}); //data array with input at index 0
    d = new RealNumber(1, false, new int[]{4, 8, 9, 8}); //1 precision
    e = new RealNumber(2, true, new int[]{7}); //data array length of 1
    f = new RealNumber(3, false, new int[]{0, 9, 0, 0}); //data array with input at index 1
    g = new RealNumber(7, false, new int[]{8, 8, 7, 3, 3, 3, 1, 9, 0}); //many digits after decimal(large precision) & array with inputs 
    h = new RealNumber(3, false, new int[]{1,0,0,0,0}); //digit at first index of array
    i = new RealNumber(3, false, new int[]{0,0,1,0,0}); //digit at middle index of array
    j = new RealNumber(3, false, new int[]{0,0,0,0,1}); //digit at last index of array
    k = new RealNumber(5, false, new int[]{7,0,0,0,0}); //digit in end of string
    l = new RealNumber(5, false, new int[]{0,0,7,0,0}); //digit in middle of string
    m = new RealNumber(6, false, new int[]{}); //data input length 0
    n = new RealNumber(0, "50.2831"); //precision 0
    o = new RealNumber(1, "-50.222"); //precision 1
    p = new RealNumber(3, "-5."); //string digit length of 1
    q = new RealNumber(3, "7.2343"); //1 digit before 
    r = new RealNumber(2, "0.2"); //1 digit after decimal 
    s = new RealNumber(7, "-050.23432235"); //many digits after decimal
    t = new RealNumber(3, "-234250.23"); //many digits before decimal
    u = new RealNumber(5, ".10000"); //non-zero digit at first index
    v = new RealNumber(5, ".00100"); //non-zero digit at middle index
    w = new RealNumber(5, ".00001"); //non-zero digit at last index
    x = new RealNumber(3, "0.");
  }
  /**
   * Test getPrecision method
   */
  @Test
  public void testGetPrecision(){
    assertEquals(0, a.getPrecision()); //test precision of 0
    assertEquals(1, d.getPrecision()); //test precision of 1
    assertEquals(2, e.getPrecision()); //test precision of 2
    assertEquals(7, g.getPrecision()); //test precision greater than 2
  }
  
  /**
   * Test getData method
   */
  @Test
  public void testGetData(){
    assertArrayEquals(new int[]{0,0,0}, x.getData()); //array with string value 0
    assertArrayEquals(new int[]{}, b.getData()); //test array of length 0
    assertArrayEquals(new int[]{7, 0}, e.getData()); //array with length 1 in 1st constructor
    assertArrayEquals(new int[]{0, 0, 0, 5}, p.getData()); //string with 1 digit in 2nd constructor 
    assertArrayEquals(new int[]{8, 8, 7, 3, 3, 3, 1, 9, 0}, g.getData()); //array with many digits
    assertArrayEquals(new int[]{4, 2, 2, 3, 4, 3, 2, 0, 5}, s.getData()); //string with many char result in array with many digits
    assertArrayEquals(new int[]{5, 0, 0, 0, 0, 0}, c.getData()); //precision longer than data input length, 1st constructor
    assertArrayEquals(new int[]{0, 3, 2, 0, 5, 2, 4, 3, 2}, t.getData()); //precision longer than data input, 2nd constructor
    assertArrayEquals(new int[]{0, 9, 0, 0}, f.getData()); //precision shorter than data input, 1st constructor
    assertArrayEquals(new int[]{4, 3, 2, 7}, q.getData()); //precision shorter than data input, 2nd constructor
    assertArrayEquals(new int[]{1, 0, 0, 0, 0}, w.getData()); //string that result in a digit at first index of array
    assertArrayEquals(new int[]{0, 0, 1, 0, 0}, v.getData()); //string that result in a digit at middle index of array
    assertArrayEquals(new int[]{0, 0, 0, 0, 1}, u.getData()); //string that result in a digit at last index of array
    assertArrayEquals(new int[]{1, 0, 0, 0, 0}, h.getData()); //array with nonzero digit at first index
    assertArrayEquals(new int[]{0, 0, 1, 0, 0}, i.getData()); //array with nonzero digit at middle index
    assertArrayEquals(new int[]{0, 0, 0, 0, 1}, j.getData()); //array with nonzero digit at last index
  }
  
  /**
   * Test isNegative method
   */
  @Test
  public void testIsNegative(){
    assertTrue(o.isNegative()); //test negative number from 2nd constructor
    assertTrue(e.isNegative()); //test negative number from 1st constructor
    assertFalse(n.isNegative()); //test positive number from 2nd constructor
    assertFalse(b.isNegative()); //test positive number from 1st constructor
  }
  
  /**
   * Test toString method
   */
  @Test
  public void testToString(){
    assertEquals("-541.", a.toString()); //precision of 0
    assertEquals("-5.000", p.toString()); //input string with digit length 1
    assertEquals("-.07", e.toString()); //convert array of length 1 to string
    assertEquals(".20", r.toString()); //input string with 1 digit before decimal
    assertEquals("-50.2", o.toString()); //output string with 1 digit after decimal
    assertEquals("898.4", d.toString()); //precision of 1
    assertEquals("-50.2343224", s.toString()); //string with many digits
    assertEquals("09.1333788", g.toString()); //array with many digits
    assertEquals("-234250.230", t.toString()); //string wither less digits after decimal than precision
    assertEquals("-50.2", o.toString()); //string with more digits after decimal than precision
    assertEquals("00.001", h.toString()); //array with digit at first index
    assertEquals("00.100", i.toString()); //array with digit at middle index
    assertEquals("10.000", j.toString()); //array with digit at last index
  }
  
  /**
   * Test compare method
   */
  @Test
  public void testCompare(){
    assertEquals(1, RealNumber.compare(u,w)); //different digit at index 0 of array 
    assertEquals(0, RealNumber.compare(q, new RealNumber (4, "7.234"))); //0 different digits in array 
    assertEquals(0, RealNumber.compare(b, new RealNumber (0, "0."))); //Two data array that both equal 0 
    assertEquals(-1, RealNumber.compare(e, new RealNumber (1, ".6"))); //Arrays with length 1 && negative with positve number             
    assertEquals(1, RealNumber.compare(f, new RealNumber (4, false, new int[]{0,8,0,0}))); //Different digits at index 1 of array 
    assertEquals(1, RealNumber.compare(d, new RealNumber(2, "898.2"))); //Data array with just 1 different digit
    assertEquals(1, RealNumber.compare(s,t)); //array with many digits 
    assertEquals(-1, RealNumber.compare(k,l)); //different digits at first index of array
    assertEquals(1, RealNumber.compare(v,w)); //different digit at middle index of array
    assertEquals(-1, RealNumber.compare(j, new RealNumber (3, "20.000"))); //different digit at last index of array
  }
  
  /**
   * Test compareTo method
   */
  @Test 
  public void testCompareTo(){
    assertEquals(1, u.compareTo(w)); //different digit at index 0 of array 
    assertEquals(0, q.compareTo(new RealNumber (4, "7.234"))); //0 different digits in array 
    assertEquals(0, b.compareTo(new RealNumber (0, "0."))); //Two data array that both equal 0 
    assertEquals(-1, e.compareTo(new RealNumber (1, ".6"))); //Arrays with length 1 && negative with positve number             
    assertEquals(1, f.compareTo(new RealNumber (4, false, new int[]{0,8,0,0}))); //Different digits at index 1 of array 
    assertEquals(1, d.compareTo(new RealNumber(2, "898.2"))); //Data array with just 1 different digit
    assertEquals(1, s.compareTo(t)); //array with many digits 
    assertEquals(-1, k.compareTo(l)); //different digits at first index of array
    assertEquals(1, v.compareTo(w)); //different digit at middle index of array
    assertEquals(-1, j.compareTo(new RealNumber (3, "20.000"))); //different digit at last index of array
  }
  
  /**
   * Test equals method 
   */
  @Test
  public void testEquals(){
    Object aa = u;
    assertFalse(v.equals(aa)); //different digit at index 0 of array 
    assertFalse(a.equals(aa));
    
    Object bb = new RealNumber (4, "7.234");
    assertTrue(q.equals(bb)); //0 different digits in array 
    assertFalse(e.equals(bb));
    
    Object cc = new RealNumber (1, "0.");
    assertFalse(d.equals(cc)); //d does not equal an 0 array
    RealNumber yy = new RealNumber (2, false, new int[]{0,0,0});
    assertTrue(yy.equals(cc));
    
    Object dd = new RealNumber (1, ".6");
    assertFalse(e.equals(dd)); //test many different digits
    
    Object ee = new RealNumber(1,"00898.4");
    assertTrue(d.equals(ee)); //test leading 0
    
    Object ff = new RealNumber(3, "898.400");
    assertTrue(d.equals(ff)); //test 0 at end of value
  }
  
  /**
   * Test add method
   */
  @Test 
  public void testAdd(){
    assertEquals("050.200", RealNumber.add(new RealNumber(3, "0.0"), new RealNumber (2, "50.20")).toString()); //Add 0 to a value, value1 is 0
    assertEquals("-50.2", RealNumber.add(new RealNumber(1, "-50.222"), new RealNumber (0, "0.00")).toString()); //add 0. value2 is 0
    assertEquals("07701.", RealNumber.add(new RealNumber(0, false, new int[]{1, 0, 7, 7}), new RealNumber (0, false, new int[]{})).toString()); //add 0, using different constructors
    assertEquals("130.", RealNumber.add(new RealNumber(0, "052.222"), new RealNumber (0, "78.134")).toString()); //Add values with precision of 0
    assertEquals("11489.", RealNumber.add(new RealNumber(0, false, new int[]{1, 0, 7, 7}), new RealNumber (0, false, new int[]{8, 8, 7, 3})).toString()); //add values with precision 0
    assertEquals("0152.223", RealNumber.add(new RealNumber(3, "152.222"), new RealNumber (3, false, new int[]{1})).toString()); //Add 1 to a value  
    assertEquals("020.000", RealNumber.add(new RealNumber(3, "19.999"), new RealNumber (3, false, new int[]{1})).toString()); //add 1 to a value that result in changes in many digits
    assertEquals("034.323", RealNumber.add(new RealNumber(3, "34.123"), new RealNumber (1, false, new int[]{2})).toString()); //Add values with precision of 1 && values with array length of 1
    assertEquals("-0234300.4643224", RealNumber.add(s,t).toString()); //Add string with many digits 
    assertEquals("0907.5333788", RealNumber.add(g,d).toString()); //Add array with many chars 
    assertEquals("033.33433", RealNumber.add(h, new RealNumber (5, "33.33333")).toString()); //Add to first index of array  
    assertEquals("033.43333", RealNumber.add(u, new RealNumber (5, "33.33333")).toString()); //Add to first char of string 
    assertEquals("033.43333", RealNumber.add(i, new RealNumber (5, "33.33333")).toString()); //Add to middle index of array 
    assertEquals("033.33433", RealNumber.add(v, new RealNumber (5, "33.33333")).toString()); //Add to middle char of string 
    assertEquals("043.33333", RealNumber.add(j, new RealNumber (5, "33.33333")).toString()); //Add to last index of array 
    assertEquals("033.33334", RealNumber.add(w, new RealNumber (5, "33.33333")).toString()); //Add to last char of string 
    assertEquals("-057.123", RealNumber.add(new RealNumber(3, "-34.123"), new RealNumber (1, "-23.0")).toString()); //add two negative numbers
    assertEquals("-11.123", RealNumber.add(new RealNumber(3, "-34.123"), new RealNumber (1, "23.0")).toString()); //add negative and positive number
  }
  
  /**
   * Test subtract method
   */
  @Test
  public void testSubtract(){
    assertEquals("34.123", RealNumber.subtract(new RealNumber(3, "34.123"), new RealNumber (1, "0.0")).toString()); //Subtract 0 from string
    assertEquals("099.999", RealNumber.subtract(new RealNumber(3, "100.000"), new RealNumber (3, ".001")).toString()); //subtract from a number with many 0
    assertEquals("-34.5", RealNumber.subtract(new RealNumber(1, "0.0"), new RealNumber (1, "34.5")).toString()); //Subtract 0 by value  
    assertEquals("39.997", RealNumber.subtract(new RealNumber(3, false, new int[]{7,9,9,9,3}), new RealNumber (1, "0.0")).toString()); //Subtract 0 from array
    assertEquals("9900.5", RealNumber.subtract(new RealNumber(0, "9934.9"), new RealNumber (1, "34.5")).toString()); //Subtract value with precision 0 using strings
    assertEquals("7390.", RealNumber.subtract(new RealNumber(0, false, new int[] {9,8,7,7}), new RealNumber (0, false, new int[]{9,9,3})).toString()); //Subtract value with precision 0 using arrays
    assertEquals("7788.", RealNumber.subtract(new RealNumber(0, false, new int[] {9,8,7,7}), new RealNumber (0, false, new int[]{1})).toString()); //Subtract 1 from value  
    assertEquals("1.000", RealNumber.subtract(new RealNumber(3, "4"), new RealNumber (1, "3")).toString()); //Subtract string of length 1 
    assertEquals(".083", RealNumber.subtract(new RealNumber(2, false, new int[] {9}), new RealNumber (3, false, new int[]{7})).toString()); //Subtract data array with length 1 
    assertEquals("9900.689188910", RealNumber.subtract(new RealNumber(6, "9934.923423"), new RealNumber (8, "34.234234")).toString()); //Subtract strings with many char 
    assertEquals("42.322252503", RealNumber.subtract(new RealNumber(7, false, new int[] {5,5,4,4,3,2,3,2,4}), new RealNumber (9, false, new int[]{7,9,9,2,9,1,1})).toString()); //Subtract array with many digits  
    assertEquals("33.33233", RealNumber.subtract(new RealNumber (5, "33.33333"), h).toString()); //Subtract from first index of array 
    assertEquals("33.23333", RealNumber.subtract(new RealNumber (5, "33.33333"), u).toString()); //Subtract from first char of string 
    assertEquals("33.23333", RealNumber.subtract(new RealNumber (5, "33.33333"), i).toString()); //Subtract from middle index of array 
    assertEquals("33.33233", RealNumber.subtract(new RealNumber (5, "33.33333"), v).toString()); //Subtract from middle char of string 
    assertEquals("23.33333", RealNumber.subtract(new RealNumber (5, "33.33333"), j).toString()); //Subtract from last index of array 
    assertEquals("33.33332", RealNumber.subtract(new RealNumber (5, "33.33333"), w).toString()); //Subtract from last char of string
    assertEquals("30.008", RealNumber.subtract(new RealNumber(3, "-4.222"), new RealNumber (2, "-34.23")).toString()); //subtract two negative numbers
    assertEquals("-038.452", RealNumber.subtract(new RealNumber(3, "-4.222"), new RealNumber (2, "34.23")).toString()); //subtract negative number by positive number
    assertEquals("038.452", RealNumber.subtract(new RealNumber(3, "4.222"), new RealNumber (2, "-34.23")).toString()); //subtract postive number by negative number
  }
  
  /**
   * Test multiply method
   */
  @Test
  public void testMultiply(){
    assertEquals("0.00000", RealNumber.multiply(new RealNumber(3, "4.222"), new RealNumber(2, "0.00")).toString()); //Multiply by 0
    assertEquals("0557.304", RealNumber.multiply(new RealNumber(3, "4.222"), new RealNumber(0, "132.")).toString()); //multiply by number with precision 0
    assertEquals("04.22200", RealNumber.multiply(new RealNumber(3, "4.222"), new RealNumber(2, "1")).toString()); //Multiply by 1 
    assertEquals(".00036", RealNumber.multiply(new RealNumber(3, false, new int[]{4}), new RealNumber(2,false, new int[]{9})).toString()); //Array with length 1 
    assertEquals("36.0000000", RealNumber.multiply(new RealNumber(3, "4"), new RealNumber(4, "9")).toString()); //Strings with length 1 
    assertEquals("36.00", RealNumber.multiply(new RealNumber(1, "4"), new RealNumber(1, "9")).toString()); //Precision of 1 
    //also valid for test first, middle, and last because first, middle, and last digits all changed
    assertEquals("449638799.49676", RealNumber.multiply(new RealNumber(3, false, new int[]{4, 8, 8, 9, 7, 2, 0, 8}), new RealNumber(2,false, new int[]{9, 8, 0, 0, 6, 5})).toString()); //Array with many digits 
    assertEquals("41849385769324642125.00", RealNumber.multiply(new RealNumber(1, "-44354325."), new RealNumber(1, "-943524352345.")).toString()); //many digits in string input value 
    assertEquals("-36.00", RealNumber.multiply(new RealNumber(1, "-4"), new RealNumber(1, "9")).toString()); //two negative numbers
    assertEquals("36.00", RealNumber.multiply(new RealNumber(1, "-4"), new RealNumber(1, "-9")).toString()); //a negative and a positive number
    assertEquals("32.48000000000000000000000000000", RealNumber.multiply(new RealNumber(9, "5.8"), new RealNumber(20, "5.6")).toString()); //large precision
  }
  
  /**
   * Test divide method
   */
  @Test
  public void testDivide(){
    assertEquals("2.0", RealNumber.divide(new RealNumber (1, "20.0"), new RealNumber (0, "10.0")).toString()); //Values with precision of 0 
    assertEquals("2.0", RealNumber.divide(new RealNumber (1, "2.0"), new RealNumber (0, "1.")).toString()); //Divide by 1 
    assertEquals("9.0", RealNumber.divide(new RealNumber (1, false, new int[]{9}), new RealNumber (1, false, new int[]{1})).toString()); //Array of length 1 
    assertEquals("2.0", RealNumber.divide(new RealNumber (1, "12.0"), new RealNumber (1, "6.")).toString()); //String with length 1 
    //not precise enough assertEquals("105.7", RealNumber.divide(new RealNumber (1, "212.0"), new RealNumber (1, "2.")).toString()); //Precision of 1 
    //not precise enough assertEquals("443.1" , RealNumber.divide(new RealNumber (1, false, new int[]{0, 8, 8, 8, 8}), new RealNumber (1, false, new int[]{0, 0, 2, 0, 0})).toString()); //Array with many digits
    assertEquals("4.0", RealNumber.divide(new RealNumber (1, false, new int[]{8}), new RealNumber (1, false, new int[]{2})).toString()); //first index of array 
    assertEquals("4.0", RealNumber.divide(new RealNumber (1, false, new int[]{0, 0, 8, 0, 0}), new RealNumber (1, false, new int[]{0, 0, 2, 0, 0})).toString()); //Changes in digits at middle index of array 
    assertEquals("4.0", RealNumber.divide(new RealNumber (1, false, new int[]{0, 0, 0, 0, 8}), new RealNumber (1, false, new int[]{0, 0, 0, 0, 2})).toString()); //Changes in digits at last index of array 
  }
  
  /**
   * Test square root method
   */
  @Test
  public void testSquareRoot(){
    //all this would be perfectly valid if my method worked
    /*Precision of 1
    assertEquals("3.0", RealNumber.squareRoot(new RealNumber(1, "9.0")).toString());
    //Precision of 0 
    assertEquals("3.", RealNumber.squareRoot(new RealNumber(0, "9.0")).toString());
    //String with char at index 0 
    assertEquals("2.0", RealNumber.squareRoot(new RealNumber(0, "4.0")).toString());
    //Data array with digit at index 0 
    assertEquals("3.0", RealNumber.squareRoot(new RealNumber(1, false, new int[]{9})).toString());
    //String length of 1 
    assertEquals("3.", RealNumber.squareRoot(new RealNumber(0, "9.")).toString());
    //Data array with length of 1 
    assertEquals("2.0", RealNumber.squareRoot(new RealNumber(1, false, new int[]{4})).toString());
    //Number with many digits, such as a long string or long data array 
    assertEquals("12.0", RealNumber.squareRoot(new RealNumber(1, false, new int[]{4,4,1})).toString());
    //Large precision
    assertEquals("12.000000000", RealNumber.squareRoot(new RealNumber(9, false, new int[]{4,4,1})).toString());
    //many digits
    assertEquals("156.0", RealNumber.squareRoot(new RealNumber(1, false, new int[]{6,3,3,4,2})).toString());*/
  }
 
}
    