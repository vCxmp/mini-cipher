/*
 * 1/20/2025
 */
import java.util.*;

/*
 * This class represents a CaesarShift object. It helps to encrypt or decrypt a word or phrase 
 *      using the CaesarShift subsitituon method. It extends the Subsitution cipher class.
 */
public class CaesarShift extends Substitution {
    private int shift;
    private String encoding;

    /*
     * Takes in a shift number to construct the CaesarShift object
     * Exceptions: 
     *      - IllegalArgumentException: gets throws if the shift number is 0 or a negative number
     */
    public CaesarShift(int shift) {
        if (shift <= 0) {
            throw new IllegalArgumentException("shift cannot be 0 or a negative number");
        }
        this.shift = shift;
        String defaultEncoding = "";
        for (int c = Cipher.MIN_CHAR; c <= Cipher.MAX_CHAR; c++) {
            defaultEncoding += (char) c;
        }
        this.encoding = newEncoding(defaultEncoding);
        setEncoding(this.encoding);
    }

    /*
     * Creates an encoding sequence where the default encoding sequence has its characters shifted 
     *      to the left by the shift number amount. If a character is at the front in something
     *      like "ABCDEFG" and has to get shifted two times, then the new encoding sequence
     *      would be "CDEFGAB".
     * Parameters: 
     *      - defaultEncoding: the default encoding sequence such as "ABCDEFG"
     * Return:
     *      - String: the new encoding sequence with the shift number taken into consideration
     */
    private String newEncoding(String defaultEncoding) {
        String answer = "";

        Queue<Character> storage = new LinkedList<>();
        for (int i = 0; i < defaultEncoding.length(); i++) {
            storage.add(defaultEncoding.charAt(i));
        }
        for (int i = 0; i < shift; i++) {
           char c = storage.remove();
           storage.add(c);
        }
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            answer += storage.remove();
        }
        return answer;
    }
}
