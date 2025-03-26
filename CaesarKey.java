/*
 * 1/20/2025
 */
import java.util.*;

/*
 * This class represents a CaesarKey. It helps to encrypt or decrypt a word or phrase
 *  using the Caeser key cipher method. This extends the Substitution cipher class.
 */
public class CaesarKey extends Substitution {
    private String key;
    private String encoding;
    /*
     * Takes in a key to construct the CaesarKey object
     * Parameters: 
     *  - key: the key that goes in front of encoding sequence
     * Exceptions: 
     *  - IllegelArgumentException: gets thrown if the key is null or is empty or has
     *      duplicate characters within it or has any character that is outsde of the 
     *      acceptable characters range
     */
    public CaesarKey(String key) {
        if (key == null || key.isEmpty() || hasDuplicate(key) || !isInRange(key)) {
            throw new IllegalArgumentException("not a valid key");
        }
        this.key = key;
        String defaultEncoding = "";
        for (int c = Cipher.MIN_CHAR; c <= Cipher.MAX_CHAR; c++) {
            defaultEncoding += (char) c;
        }
        this.encoding = newEncoding(defaultEncoding);
        setEncoding(this.encoding);
    }

    /*
     * Creates the encoding sequence with the characters of the key in front of it. If characters
     *      of the key did not exist in the default encoding sequence, then they get pushed to the back
     *      of the sequence in order.
     * Parameters:
     *      - defaultEncoding: the default encoding sequence which has the characters in order. Such as
     *          "ABCDEFG"
     * Return:
     *      - String: the new encoding sequence to be used in encryption or decryption with the 
     *          characters of the key in the very front of it
     */
    private String newEncoding(String defaultEncoding) {
        String answer = "";
        Queue<Character> storage = new LinkedList<>();
        for (int i = 0; i < key.length(); i++) {
            storage.add(key.charAt(i));
        }
        for (int i = 0; i < defaultEncoding.length(); i++) {
            if (!storage.contains(defaultEncoding.charAt(i))) {
                storage.add(defaultEncoding.charAt(i));
            }
        }
        int size = storage.size();
        for (int i = 0; i < size; i++) {
            answer += storage.remove();
        }
        return answer;
    }
}
