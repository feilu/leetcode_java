package onlinetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * http://stackoverflow.com/questions/10069230/how-to-detect-the-first-occurrence-of-palindrome
 * 
 * rolling hash function.
 * 
 * @author flu
 * 
 */
public class PalindromeFromStream {

    // golden ration (1+sqrt(5))/2
    // private static final int PRIME = (int) (Math.sqrt(5) + 1) * (2 ^ 31);
    private static final int PRIME = 31;

    private StringBuilder current = new StringBuilder();

    // use big integer to avoid overflow
    private long hash = 0;
    private long revHash = 0;
    private long power = 0;

    public boolean check(char s) {
        current.append(s);

        // this is not working when hash is overflow
        hash = (hash * PRIME + s);
        System.out.println("hash is " + hash + ", " + current.toString().hashCode());

        revHash = (revHash + (long) Math.pow(PRIME, power++) * s); // this is wrong
        System.out.println("rev hash is " + revHash + ", " + new StringBuilder(current).reverse().toString().hashCode());

        // only check if even characters.
        if (power % 2 == 0 && hash == revHash
                        && current.toString().equals(new StringBuilder(current).reverse().toString())) {
            System.out.println(current.toString() + " is palindrome.");
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        PalindromeFromStream checker = new PalindromeFromStream();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String s = reader.readLine();
                if (s.length() > 1)
                    throw new IllegalArgumentException();

                if (checker.check(s.charAt(0)))
                    break;
            }
        }
    }

}
