/**
 * The RealNumber program represents an arbitrary number in decimal 
 * notation and can perform simple math operations on the numbers. 
 * 
 * @author <em> Helen Zhao <em>
 */
public class RealNumber{
  
  /**
   * The number of digits to the right of the decimal point
   */
  private int precision = 0;
  
  /**
   * Indicates whether the number is a negative value
   */
  private boolean isNegative = false;
  
  /**
   * An array that stores each digit of the real number.
   * data[0] stores least significant digit.
   * data[data.length-1]stores most significant digit. 
   */
  private int[] data = new int[]{};
  
  /**Constructor. 
    * 
    * @param precision   number of digits right of decimal 
    * @param isNegative  whether number is negative
    * @param data        stores each digit of the number
    */
  public RealNumber(int precision, boolean isNegative, int[] data){
    this.precision = precision; 
    this.isNegative = isNegative; 
    if (precision > data.length){
      this.data = new int[precision];
      /*additional 0's added after decimal place if precison is 
       greater than data.length*/
      for (int i = 0; i < data.length; i++){
        this.data[i] = data[i];
      }
    }
    else 
      this.data = data;
  }
  
  /**
   * Constructor.
   * pre-condition: must have decimal in input string
   * ex) "500" should be "500."
   * 
   * @param precision  number of digits after decimal
   * @param value      string representation of the number
   * @throws NumberFormatException if value is not a valid number
   */
  public RealNumber(int precision, String value){
    this.precision = precision;
    int placesbeforedecimal = 1; //number of digits before decimal
    try{
      if (value.charAt(0) == '-'){
        StringBuilder builder = new StringBuilder();
        isNegative = true;
        /*negative sign removed from value*/
        for (int i = 1; i < value.length(); i++){
          builder.append(value.charAt(i));
        }
        value = builder.toString(); //negative sign removed
      }
      int i = 0; //index of the string value input
      StringBuilder builder2 = new StringBuilder();//stores string of just digits of the number
      /*remove decimal point*/
      for (i = 0; i < value.length(); i++){ 
        if (value.charAt(i) != '.')
          builder2.append(value.charAt(i));
        else 
          placesbeforedecimal = i; //index of decimal = number of spaces before decimal
      }
      int d = 0; //will store the index of the first nonzero digit before the decimal
      for (d = 0; d < placesbeforedecimal && Integer.parseInt(Character.toString(builder2.toString().charAt(d))) == 0; d++){
      }
      /*if the string input equals 0, p will be -1 at end of loop*/
      if (d == -1){
        data = new int[builder2.toString().length()];
      }
      else{
        placesbeforedecimal = placesbeforedecimal - d; //remove the number of digits that are not leading zeros before decimal
        data = new int[placesbeforedecimal + precision]; //does not store leading zeros
      }
      int k = 0;
      if (builder2.toString().length() - d <= data.length){
        /*digits are added to the data array, starting with the most significant one, until we reach the beginning of the string*/
        for (k = data.length - 1, d = d; d < builder2.toString().length(); k--, d++){
          data[k] = Integer.parseInt(Character.toString(builder2.toString().charAt(d)));
        }
      }
      if (builder2.toString().length() - d > data.length){
        /*the first j + 1 digits are added to the data array*/
        for (k = data.length - 1, d = d; k >= 0; k--, d++){
          data[k] = Integer.parseInt(Character.toString(builder2.toString().charAt(d)));
        }
        boolean carriedover = false;
        if (Integer.parseInt(Character.toString(builder2.toString().charAt(d))) >= 5){
          if (data[0] != 9){
            data[0] += 1;
          }
          if (data[0] == 9){
            int y = 0; //index of data array
            for(y = 0; y < data.length && data[y] == 9; y++){
              data[y] = 0;
            }
            if (y == data.length){
              data = new int[data.length + 1];
              data[data.length - 1] = 1;
            }
            else{
              data[y] += 1;
            }
          }
        }
        /*if value a j+2 is greater than or equal to 5, data[0] + 1 and value is rounded up*/
          }
        }
    /*string entered is not a valid number*/
    catch (NumberFormatException nfe) {
      System.out.println("The string entered is not a valid number");
    }
    catch (StringIndexOutOfBoundsException ex){
      System.out.println("The string entered is not a valid number");
    }
  }
  
  /**
   * get precision of the RealNumber instance
   * 
   * @return number of digits after the decimal
   */
  public int getPrecision(){
    return precision;
  }
  
  /**
   * get the array representation of the real number
   * 
   * @return the array that stores the digits of the real number
   */
  public int[] getData(){
    return data;
  }
  
  /**
   * get the sign of the number 
   * 
   * @return true is number is negative and false otherwise
   */
  public boolean isNegative(){
    return isNegative;
  }
  
  /**
   * Overrides the inherited method of Object. 
   * Converts the data array to the string value of the real number. 
   * 
   * @return a string representation of the number
   */
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder(); //stores the string representation of the number
    if (this.getData().length == 0){
      return "0.";
    }
    if (isNegative == true){
      builder.append('-');
    }
    /*append the digits before the decimal to builder*/
    int i = this.getData().length - 1;
    while (i >= precision){
      builder.append(this.getData()[i]);
      i--;
    }
    /*add decimal point to builder*/
    builder.append('.');
    /*add the digits after the decimal to builder*/
    while (i >= 0){
      builder.append(this.getData()[i]);
      i--;
    }
    return builder.toString();
  }
  
  /**
   * Call the class to compare the values of two real numbers
   * 
   * @param value1  a real number to be compared to value2
   * @param value2  the real number being compared with value1
   * @return        -1 if value1 is less than value2, 1 if value1 is greater than value2, and 0 is value1 equals value2
   */
  public static int compare(RealNumber value1, RealNumber value2){
    if (value1.isNegative == true && value2.isNegative == false){
      return -1;
    }
    if (value1.isNegative == false && value2.isNegative == true){
      return 1;
    }
    int i = value1.getData().length - 1;
    int i2 = value2.getData().length - 1;
    int j = 0; //index of the left most digit in the array that is not a 0
    int j2 = 0;
    //j will store index of the left most digit in value1's data that is not a 0
    while (j < value1.getData().length - value1.getPrecision() - 1 && value1.getData()[j] == 0){
      j++;
    }
    //j2 will store index of the left most digit in value2's data that is not a 0
    while (j2 < value2.getData().length - value1.getPrecision() - 1 && value2.getData()[j2] == 0){
      j2++;
    }
    //j will store index of the left most digit in value1's data that is not a 0
    while (i >= 0 && value1.getData()[i] == 0){
      i--;
    }
    //j2 will store index of the left most digit in value2's data that is not a 0
    while (i2 >= 0 && value2.getData()[i2] == 0){
      i2--;
    }
    //if both values are equal to 0, then j1 and j2 equal their repective array's length*/
    if (i == -1 && i2 == -1){
      return 0;
    }
    //if value1 is 0 and value2 is not 0, return -1
    if (j == value1.getData().length && j2 < value2.getData().length){
      return -1;
    }
    if (j2 == value2.getData().length && j < value1.getData().length){
      return 1; 
    }
    if (value1.isNegative() == false && value2.isNegative() == false){
      /*if the number of digits before the decimal in value1 is greater than that in value 2,
       then value1 is greater value2*/
      if (value1.getData().length - value1.getPrecision() > value2.getData().length - value2.getPrecision()){
        return 1;
      }
      /*if value2 had more digits before decimal, then value2 is larger*/
      if (i - value1.getPrecision() < i2 - value2.getPrecision()){
        return -1;
      }
      /*compare all the digits between i and j with digits between i2 and j2. 
       at the end, i and i2 will store the indexes of the first digit that differs between
       value1 and value2 or -1*/
      while ((i >= j && i2 >= j2) && value1.getData()[i] == value2.getData()[i2]){
        i--;
        i2--;
      }
      /*if i stores -1, then value2 has more significant digits*/
      if ((i == -1 || i == j - 1) && i2 >= j2){
        return -1;
      }
      /*if i2 stores -1, then value1 has more significant digits*/
      else if ((i2 == -1 || i2 == j2 - 1) && i >= j){
        return 1;
      }
      /*value1 and value2 have the same number of signifant digits*/
      else if ((i == -1 || i == j - 1) && i2 < j2){
        return 0;
      }
      else if ((i2 == -1 || i2 == j - 1) && i < j){
        return 0;
      }
      /*compare the first non-similar digits that are in the same digit place of value1 and value2*/ 
      else if (value1.getData()[i] > value2.getData()[i2]){
        return 1;
      }
      else if (value1.getData()[i] < value2.getData()[i2]){
        return -1;
      }
      else 
        return 0;
    }
    /*if value1 and value2 are both negative, then the inverse is true. 
     All the statements above will return the opposite sign value*/
    else {
      if (i - value1.getPrecision() > i2 - value2.getPrecision()){
        return -1;
      }
      if (i - value1.getPrecision() < i2 - value2.getPrecision()){
        return 1;
      }
      while (value1.getData()[i] == value2.getData()[i2] && ((i > 0 && i2 > 0) && (i > j && i2 > j2))){
        i--;
        i2--;
      }
      if ((i == -1 || i == j - 1) && i2 >= j2){
        return 1;
      }
      else if ((i2 == -1 || i2 == j2 - 1) && i >= j){
        return -1;
      }
      else if ((i == -1 || i == j - 1) && i2 < j2){
        return 0;
      }
      else if ((i2 == -1 || i2 == j - 1) && i < j){
        return 0;
      }
      else if (value1.getData()[i] > value2.getData()[i2]){
        return -1;
      }
      else if (value1.getData()[i] < value2.getData()[i2]){
        return 1;
      }
      else 
        return 0;
    }
  }
  
  /**
   * Call the method on an instance. 
   * compares an instance of realnumber to another real number
   * 
   * @param value  the value that the realnumber instance is being compared to
   * @return -1 if value1 is less than value2, 1 if value1 is greater than value2, and 0 is value1 equals value2
   */
  public int compareTo(RealNumber value){
    return RealNumber.compare(this, value);
  }
  
  
  /**
   * Overrides the inherited method of Object
   * Determine whether two instances of real number are equal
   * 
   * @param o  an object that is an instance of RealNumber
   * @return   true if o is numerically equal to this instance
   */
  @Override
  public boolean equals (Object o){
    /*typecast object o to realnumber
     if o is an instance of realnumber*/
    if (o instanceof RealNumber){
      RealNumber a = (RealNumber) o; 
      /*if compare method returns 0, then the 
       instances are numerically equal*/
      if (this.compareTo(a) == 0){
        return true;
      }
      else
        return false;
    }
    else 
      return false;
  }
  
  /**
   * Mathematically add two RealNumber instances
   * 
   * @param value1  RealNumber to be added to value2
   * @param value2  RealNumber to be added with value1
   * @return string representation of the RealNumber that is the sum of value1 and value2
   */
  public static RealNumber add(RealNumber value1, RealNumber value2){
    int p1 = value1.getPrecision(); //precision of value1
    int p2 = value2.getPrecision(); //precision of value2
    int l1 = value1.getData().length; //length of value1's data array
    int l2 = value2.getData().length; //length of value2's data array
    int[] sum = new int[]{}; //array will store the digits of the summation 
    int precision = 0; //store the precision of the summation
    /*if the signs of the values are different, then the summation is the difference 
     of the positive signs of the two values so subtraction method is called. 
     Have to first change the sign of the negative number before calling the subtraction method*/
    if (value1.isNegative() != value2.isNegative()){
      if (value1.isNegative){
        RealNumber negatevalue1 = new RealNumber(value1.getPrecision(), false, value1.getData());
        return RealNumber.subtract(value2, negatevalue1);
      }
      if (value2.isNegative){
        RealNumber negatevalue2 = new RealNumber(value2.getPrecision(), false, value2.getData());
        return RealNumber.subtract(value1, negatevalue2);
      }
    }
    boolean isNegative = value1.isNegative(); //determine if the sum is positive or negative
    /*precision of summation is the larger of the precision of value1 and value2*/
    if (p1 >= p2){
      precision = p1;
      /* value1 has greater precision and digits before decimal,
       so length of sum is l1 + 1*/
      if (l1 - p1 >= l2 - p2){
        sum = new int[l1 + 1];
      }
      /*value2 has more digits before decimal*/
      else
        sum = new int[l2 - p2 + p1 + 1];
    }
    else {
      precision = p2;
      /*value2 has greater precision and digits before decimal*/
      if (l2 - p2 >= l1 - p1){
        sum = new int[l2 + 1];
      }
      /*value1 has more digits before decimal*/
      else 
        sum = new int[l1 - p1 + p2 + 1];
    }
    int i1 = 0; //index of value1
    int i2 = 0; //index of value2
    int i = 0; //index of sum[]
    if (p1 > p2){
      /*all the digits between last digit of value1 and last digit of value2
       are added to beginning of sum[]. i1 will store the first index where value1
       and value2 both have inputs at the place value.*/
      while (i < p1 - p2){
        sum[i] = value1.getData()[i1];
        i++;
        i1++;
      }
    }
    /*same as above except value2 has greater precision*/
    if (p2 > p1){
      while (i < p2 - p1){
        sum[i] = value2.getData()[i2];
        i++;
        i2++;
      }
    }
    boolean carriedover = false; //whether there is a digit carried over
    /*add the digits that are at the same place value.*/
    for (i = i, i2 = i2, i1 = i1; (i < sum.length && i2 < l2) && i1 < l1; i++, i2++, i1++){
      if (carriedover == false){
        /*if the sum of digits is less than 10, then sum[i] equals the sum of the digits*/
        if (value1.getData()[i1] + value2.getData()[i2] < 10){
          sum[i] = value1.getData()[i1] + value2.getData()[i2];
        }
        /*if sum of digits is greater than 10, carriedover is true 
         and sum[i] equals the sum of the digits at i1 and i2 modulus 10.*/
        else{
          sum[i] = (value1.getData()[i1] + value2.getData()[i2]) % 10;
          carriedover = true;
        }
      }
      /*if carried over is true, then 1 has to be added to conditional statements in the statements above*/
      else {
        if (value1.getData()[i1] + value2.getData()[i2] + 1 < 10){
          sum[i] = value1.getData()[i1] + value2.getData()[i2] + 1;
          carriedover = false;
        }
        else{
          sum[i] = (value1.getData()[i1] + value2.getData()[i2] + 1) % 10;
        }
      }
    }
    if (carriedover){
      sum[i] = 1;
    }
    /*if value1 has more places before decimal than value2*/
    if (l1 - p1 > l2 - p2){
      /*the place values that only value1 has a input for is initialized for sum[i]. 
       the conditions are similar to the ones above expect value2[i2] = 0.*/
      for (i1 = i1; i1 < l1; i1++, i++){
        if (carriedover == true){
          if (value1.getData()[i1] + 1 >= 10){
            sum[i] = (value1.getData()[i1] + 1) % 10;
          }
          else {
            sum[i] = value1.getData()[i1] + 1;
            carriedover = false;
          }
        }
        else {
          sum[i] = value1.getData()[i1];
        }
      }
      /*if carried over is still true after i = l1, 
       then there is still a 1 that needs to be carried over*/
      if (carriedover)
        sum[i] = 1;
    }
    if (l2 - p2 > l1 - p1){
      /*the place values that only value2 has a input for is initialized for sum[i]. 
       the conditions are similar to the ones above expect value1[i1] = 0.*/
      for (i2 = i2; i2 < l2; i2++, i++){
        if (carriedover){
          if (value2.getData()[i2] + 1 >= 10){
            sum[i] = (value2.getData()[i2] + 1) % 10;;
          }
          else {
            sum[i] = value2.getData()[i2] + 1;
            carriedover = false;
          }
        }
        else {
          sum[i] = value2.getData()[i2];
        }
      }
      if (carriedover)
        sum[i] = 1;
    }
    return new RealNumber(precision, isNegative, sum);
  }
  
  /**
   * Mathetically subtract value2 from value1. 
   * 
   * @param value1  RealNumber that value2 will be subtracted from 
   * @param value2  the value that value1 will be subtracted by
   * @return        the string representation of the difference between value1 and value2
   */
  public static RealNumber subtract (RealNumber value1, RealNumber value2){
    boolean isNegative = false; //store the sign of the difference
    RealNumber v = value1; //store value1 at v 
    /*if the signs of value1 and value2 are different, the call the add method*/
    if (value1.isNegative() != value2.isNegative()){
      if (value1.isNegative()){
        RealNumber negatevalue2 = new RealNumber(value2.getPrecision(), true, value2.getData());
        return RealNumber.add(value1, negatevalue2);
      }
      else {
        RealNumber negatevalue2 = new RealNumber(value2.getPrecision(), false, value2.getData());
        return RealNumber.add(value1, negatevalue2);
      }
    }
    /*if the values are positive and value2 is more than value1, then switch the values.
     The difference is negative.*/
    if (value1.isNegative == false && value1.compareTo(value2) < 0){
      value1 = value2; 
      value2 = v;
      isNegative = true;
    }
    /*if the values are negative and value1 is greater than value2, then switch the values.
     The difference is positive*/
    if (value1.isNegative == true && value1.compareTo(value2) > 0){
      value1 = value2;
      value2 = v;
      isNegative = false;
    }
    int[] difference = new int[]{};//store the digits of the difference
    int precision = 0; //store the precision of the difference
    int i = 0; //the index of difference[]
    int i1 = 0; //index of value1
    int i2 = 0; //index of value2
    boolean carriedOver = false; //whether a number was carriedover
    /*precision of difference equals that of the larger of the precision of value1 and value2*/  
    if (value1.getPrecision() >= value2.getPrecision()){       
      precision = value1.getPrecision();       
      /*if value1 has a greater precision, then length of the difference is same as length of value1's data*/
      difference = new int[value1.getData().length];
      /*the digits between the end of value1 and value2 equals value1's digits*/
      for (i = 0, i1 = 0; i < value1.getPrecision() - value2.getPrecision(); i++, i1++){
        difference[i] = value1.getData()[i1];
      }
    }
    else{
      precision = value2.getPrecision();
      /*if value2 has a greater precison, then the length of difference equals the number of places
       before the decimal for value1 + the precision of value2*/
      difference = new int[value1.getData().length - value1.getPrecision() + value2.getPrecision()];
      /*the corresponding place value in the difference would be 9 - 1 for all the place values between
       the end of value1 and value2. carriedover is true*/
      difference[0] = 10 - value2.getData()[0];
      for (i = 1, i2 = i; i < value2.getPrecision() - value1.getPrecision(); i++, i2++){
        difference[i] = 9 - value2.getData()[i2];
      }
      carriedOver = true;
    }
    int j = 0; //store index of right most nonzero digit in value2's data
    //j stores index of right most nonzero digit
    for (j = value2.getData().length - 1; j > 0 && value2.getData()[j] == 0 && j >= 0; j--){
    }
    /*difference[] will store the difference of the digits at place values where both
     value1 and value2 have inputs*/
    for (i = i, i1 = i1, i2 = i2; i2 <= j; i++, i1++, i2++){
      /*carriedover is true. must subtract 1 from value1's digit at i*/
      if(carriedOver){
        if(value1.getData()[i1] - 1 - value2.getData()[i2] >= 0){
          difference[i] = value1.getData()[i1] - 1 - value2.getData()[i2];
          carriedOver = false;
        }
        /*value1's digit at i has to subtract 1, borrow 10, and then subtract value2 at i2*/
        else 
          difference[i] = value1.getData()[i1] + 9 - value2.getData()[i2];
      }
      else{
        /*if difference between digits is greater than 0, the difference[i] equals the difference*/
        if(value1.getData()[i1] - value2.getData()[i2] >= 0){
          difference[i] = value1.getData()[i1] - value2.getData()[i2];
        }
        /*or else, must add 10 to value1's digit at i then substract digit at value2's digit at i2*/
        else{
          difference[i] = value1.getData()[i1] + 10 - value2.getData()[i2];
          carriedOver = true;
        }
      }
    }
    /*if value1 has digits past the left most place value of value2, 
     then the value at the corresponding place value in the difference's 
     data array is similar to the above conditions except value's digit is 0.*/
    for (i = i, i1 = i1; i < difference.length; i++, i1++){
      if (carriedOver){
        if (value1.getData()[i1] - 1 < 0){
          difference[i] = value1.getData()[i1] + 9;
        }
        else {
          difference[i] = value1.getData()[i1] - 1;
          carriedOver = false;
        }
      }
      else 
        difference[i] = value1.getData()[i1];
    }
    return new RealNumber(precision, isNegative, difference);
  }
  /**
   * Mathematically multiply RealNumber value1 and RealNumber value2
   * 
   * @param value1  RealNumber to be multiply by value2
   * @param value2  RealNumber to be multiply be value1
   * @return the string representation of the product of value1 and value2
   */
  public static RealNumber multiply(RealNumber value1, RealNumber value2){
    /*value1 has to be longer than value2. If value2 is longer, then switch
     the two values*/
    if (value1.getData().length < value2.getData().length){
      RealNumber v2 = value2;
      value2 = value1;
      value1 = v2;
    }
    int numb [][] = new int[value2.getData().length][value1.getData().length + 1]; //2-D array of digits
    int i1 = 0; //index of value1
    int i2 = 0; //index of value2
    boolean carriedover = false; //whether a digit is carried over
    boolean isNegative = false; //whether the product is negative
    if (value1.isNegative() != value2.isNegative())
      isNegative = true;
    /*precision of product is the sum of the precisions of the inputs*/
    int precision = value1.getPrecision() + value2.getPrecision(); 
    /*fill row i2 with the result of multiplying value1 by the i2th digit of value2*/
    for (i2 = 0; i2 < value2.getData().length; i2++){
      int numCarriedOver = 0; //stores the tenth digit of the multiple that is carried over
      for (i1 = 0; i1 < value1.getData().length; i1++){
        numb[i2][i1] = (value1.getData()[i1]*value2.getData()[i2] + numCarriedOver) % 10;
        numCarriedOver = (value1.getData()[i1]*value2.getData()[i2] + numCarriedOver)/10;
      }
      numb[i2][value1.getData().length] = numCarriedOver; //the last column in a row is the numCarriedOver
    }
    /*stores the product of value1 and value2. The maximum possible digit of the product
     is the sum of the lengths of value1 and value2*/
    int multiple[] = new int[value1.getData().length + value2.getData().length]; 
    int j = 0; 
    int k = 0;
    /*add values to the result array until i = value1's length
     j is the row and is incremented up until value2's length.
     k is the column and equals i.*/
    for (int i = 0; i <= value1.getData().length; i++){
      for (j = 0, k = i; j < value2.getData().length && k >= 0; j++, k--){
        multiple[i] += numb[j][k];
      }
    }
    /*for the other half of the multiple array, start at the end of the multiple array.
     j is the row and start at the end of value 2 and goes down. 
     k is i minus the difference between multiple's length and value1's length minus 1.
     k is incremented until value1's length is reached. 
     the appropriate numb[][] are added and the sum is inputed into the corresponding multiple[]*/
    for (int i = multiple.length - 1; i > value1.getData().length; i--){
      for (j = value2.getData().length - 1, k = i - (multiple.length - value1.getData().length - 1); j >= 0 && k <= value1.getData().length; j--, k++){
        multiple[i] += numb[j][k];
      }
    }
    /*multiple array has numbers greater than 9. have to carry over the tenth digit of the value at 
     multiple[i] to the next digit in the array*/
    int a = 0; //stores the sum at multiple[i]
    int numCarriedOver = 0; //the tenth digit of the value at multiple[i]
    /*tenth digit of the value at multiple[i] is added to the next value in multiple[] and so on.*/
    for (int i = 0; i < multiple.length; i++){
      a = multiple[i];
      multiple[i] = (multiple[i] + numCarriedOver) % 10;
      numCarriedOver = (a + numCarriedOver)/10;
    }
    return new RealNumber (precision, isNegative, multiple);
  }
  /**
   * Mathematically divide RealNumber value1 by RealNumber value2
   * 
   * @param value1 the divident
   * @param value2 the divisor 
   * @return       the string representation of quotient of value1 and value2
   * @throw ArithmeticException if value2 is 0
   */
  public static RealNumber divide(RealNumber value1, RealNumber value2){
    if (value2.compareTo(new RealNumber(2, "0.")) == 0){
      throw new ArithmeticException();
    }
    int precision = 0; //stores precision of the quotient
    /*if value2 is negative, change it to positive*/
    if (value2.isNegative()){
      value2.isNegative = false;
    }
    /*precision of quotient is the larger precision of value1 and value2*/
    if (value1.getPrecision() >= value2.getPrecision()){
      precision = value1.getPrecision();
    }
    else 
      precision = value2.getPrecision();
    /*shift the decimal point of value1 so first non-zero digit of the value 
     is immediately to the right of decimal point*/
    int v2oldp = value2.getPrecision(); //store the original precision of value2
    /*start at the least most significant digit of value2, keep moving from 
     right to left of value2's data array until first non-zero digit is reached*/
    int i = value2.getData().length - 1;
    for (i = i; i >= 0 && value2.getData()[i] == 0; i--){
    }
    int v2newp = i + 1; //new precision is i+1
    int numTimesTwo = 0; //number of times value2 is multiplied by 2
    /*while the digit to right of decimal is less than 5, multiple value2 by 2 by
     calling multiply method*/
    while (value2.getData()[i] < 5){
      value2 = RealNumber.multiply(value2, new RealNumber(0, "2."));
      numTimesTwo++;
    }
    /*multiple value1 by 2 the same amount of times value2 was multiply by 2*/
    while (numTimesTwo > 0){
      value1 = RealNumber.multiply(value1, new RealNumber(0, "2."));
      numTimesTwo--;
    }
    /*v2 stores the new value2. 
     precision is the v2newp, sign is positive, 
     v2's data array is the same as value2's data array*/
    RealNumber v2 = new RealNumber(v2newp, false, value2.getData());
    /*precision is the value1's precision plus the change in precision between value2 and v2. 
     sign is the same as value1. 
     data array is v1newdata[]*/
    RealNumber v1 = new RealNumber(value1.getPrecision() + (v2newp - v2oldp), value1.isNegative(), value1.getData());
    /*old_x is 2.823529 - 1.882352*v2*/
    RealNumber old_x = RealNumber.subtract(new RealNumber (6, "2.823529"), (RealNumber.multiply(new RealNumber(6, "1.882352"), v2)));
    /*better guess x is old_x * (2 - old_x * v2)*/
    RealNumber x = RealNumber.multiply(RealNumber.subtract(new RealNumber (0, "2."), RealNumber.multiply(old_x, v2)), old_x);
    /*a[] is the data array of the desired difference between old_x and x*/
    int[] a = new int[precision + 1];
    a[0] = 1;
    /*b is 1/10 the desired output precision*/
    RealNumber b = new RealNumber(precision, false, a); //precision should be precision + 1 but then my method doesnt work...
    /*while the difference between old_x and x is less than b, keep changing x*/
    while ((RealNumber.subtract(old_x, x)).compareTo(b) > 0){
      old_x = x;
      x = RealNumber.multiply(RealNumber.subtract(new RealNumber (0, "2."), RealNumber.multiply(old_x, v2)), old_x);
    }
    RealNumber n = RealNumber.multiply(x, v1);
    RealNumber m = new RealNumber (precision, n.toString());
    return m;
  }
  
  /**
   * Take the square root of an input RealNumber value
   * 
   * @param a RealNumber value whose square root will be taken of
   * @return the RealNumber string value of the square root the input value
   * @throw ArithmeticException if input value is a negative value
   */
  public static RealNumber squareRoot (RealNumber value){
    //if the input value is negative then throw Arithmetic Exception error
    if (value.isNegative() == true){
      throw new ArithmeticException();
    }
    //store the value of the square root, will become more and more precise
    RealNumber x;
    //if the value is greater than one, then x equals value
    if (value.compareTo(new RealNumber(1, "1.0")) >= 0){
      x = value;
    }
    //if the value is less than one, then x = 1
    else {
      x = new RealNumber(value.getPrecision(), "1.0");
    }
    //store the data array of the desired different between x and better x
    int[] a = new int[value.getPrecision() + 1];
    a[0] = 1;
    //the difference between x and better x is compared to RealNumber b 
    RealNumber b = new RealNumber(value.getPrecision(), false, a);
    RealNumber old_x = x;
    //x = old_x - (old_x * old_x - value) / (2 * old_x)
    x = RealNumber.subtract(old_x, RealNumber.divide(RealNumber.subtract(RealNumber.multiply(old_x, old_x), value), RealNumber.multiply(old_x, new RealNumber(1, "2.0"))));
    //at the end of the loop, the difference between x and oldx is less than 1/10 the desired precision
    //subgoal is that x will store a new value and oldx will store the value of x before entering the loop 
    while ((RealNumber.subtract(old_x, x)).compareTo(b) > 0){
      x = RealNumber.subtract(old_x, RealNumber.divide(RealNumber.multiply(old_x, RealNumber.subtract(old_x, value)), RealNumber.multiply(old_x, new RealNumber(1, "2.0"))));
      old_x = x;
    }
    //return the square root
    RealNumber m = new RealNumber(value.getPrecision(), x.toString());
    return m;
  }
}





