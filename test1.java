import java.util.Scanner;

public class test1 {

    public static void validateCreditCard(long ccNumber) {
        String ccNumberStr = Long.toString(ccNumber);

        if (ccNumberStr.length() < 8 || ccNumberStr.length() > 9) {
            System.out.println("Invalid credit card number");
        }
        else {
            System.out.println("Valid credit card number");
        }
    }
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your credit card number: ");
        long ccNumber = scanner.nextLong();  
        validateCreditCard(ccNumber);

        long remaining = RemoveLastDigit(ccNumber);
        System.out.println("After removing the last digit: " +remaining);
        
        ccNumber = ReverseDigit(remaining);
        System.out.println("Reversing the digits: "+ccNumber);
        
        ccNumber = OddNumber(ccNumber);
        System.out.println("After doubling the odd-positioned digits:" +ccNumber);

        ccNumber = addDigits(ccNumber);
        System.out.println("Sum of all digits: "+ccNumber);

        ccNumber = subtract(ccNumber);
        System.out.println("10-7= "+ccNumber);

        boolean isValid = compareLastDigit(remaining % 10, ccNumber);
        if (isValid) {
            System.out.println("The credit card number is valid.");
        } else {
            System.out.println("The credit card number is invalid.");
        }

        scanner.close();
        }

    public static long RemoveLastDigit(long ccNumber){
        return ccNumber/10;
    }
    public static long ReverseDigit(long ccNumber){
        long reverse = 0;
        while(ccNumber !=0){
            long digit = ccNumber % 10;
            reverse = reverse*10 + digit;
            ccNumber /= 10;
        }
        return reverse;
    }
    public static long OddNumber(long ccNumber) {
        String ccNumberStr = Long.toString(ccNumber);
        long processedNumber = 0; 

        for (int i = 0; i < ccNumberStr.length(); i++) {
            char digitChar = ccNumberStr.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            if (i % 2 == 0) {
                digit *= 2; 
                if (digit > 9) {
                    digit = (digit % 10) + (digit / 10);
                }
            }
            processedNumber = processedNumber * 10 + digit; // Constructing the long value
    }
    return processedNumber;}
    
    public static long addDigits(long ccNumber) {
    long sum = 0;
    while (ccNumber != 0) {
        long digit = ccNumber % 10; 
        sum += digit; 
        ccNumber /= 10; 
    }

    return sum; 
    }
    
    public static long subtract(long ccNumber) {
    long lastDigit = ccNumber % 10; 
    long result = 10 - lastDigit; 
    return result; 
    }
    
    public static boolean compareLastDigit(long lastDigit, long resultFromStepE) {
        return lastDigit == resultFromStepE;
    }
    

    }

  



    
