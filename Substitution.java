/*
 * Veer Desai
 * 1/20/2025
 */
import java.util.*;
/*
 * This class represents a Substitution object. It helps to encrypt or decrypt a word or phrase
 *      using the Substitution cipher method. This extends the Cipher class.
 */
public class Substitution extends Cipher {
    private String encoding;
    private Map<Character, Character> charting;

    /*
     * Creates a Substitution object with no set encoding sequence
     */
    public Substitution() {
        this.encoding = null;
        this.charting = new HashMap<>();
    }
    /*
     * Creates a Substitiution object with an encoding sequence set
     */
    public Substitution(String encoding) {
        this.charting = new HashMap<>();
        setEncoding(encoding);
    }

    /*
     * This checks if the encoding sequence has any duplicate character within it.
     * Parameter: 
     *      - encoding: the encoding sequence
     * Return:
     *      - boolean: true if there is a duplicate character and false otherwise
     */
    public boolean hasDuplicate(String encoding) {
        Set<Character> checker = new HashSet<Character>();
        char[] holder = new char[encoding.length()];
        for (int i = 0; i < encoding.length(); i++) {
            holder[i] = encoding.charAt(i);
        }
        for (char single : holder) {
            if (!checker.add(single)) {
                return true;
            }
        }
        return false;
    }

    /*
     * This checks if all the individual characters in the encoding sequence are in the 
     *      valid range of acceptable characters.
     * Parameters: 
     *      - encoding: the provided encoding sequence
     * Return: 
     *      - boolean: returns true if all the characters of the encoding sequence are in range
     *          and false if not
     */
    public boolean isInRange(String encoding) {
        for (int i = 0; i < encoding.length(); i++) {
            char individual = encoding.charAt(i);
            if (individual < Cipher.MIN_CHAR || individual > Cipher.MAX_CHAR) {
                return false;
            }
        }
        return true;
    }

    /*
     * This updates the encoding for the substitution cipher. It maps each character of the 
     *      acceptable range of characters to a character of the provided encoding sequence. 
     * Parameters: 
     *      - encoding: the provided encoding sequence
     */
    public void setEncoding(String encoding) {
        if (encoding == null || encoding.length() != Cipher.TOTAL_CHARS || hasDuplicate(encoding) || !isInRange(encoding)) {
            throw new IllegalArgumentException("not a valid encoding sequence");
        }
        this.encoding = encoding;
        for (int i = 0; i < encoding.length(); i++) {
            char c = (char) (Cipher.MIN_CHAR + i);
            this.charting.put(c, encoding.charAt(i));
        }
    }

    /*
     * This encrypts whatever the user inputs using the provided encoding sequence via substitution
     *      of characters. It only substitutes if the certain character has another character 
     *      partnered up with it. Example is if A is parterned up to G in "APPLE" and no other 
     *      letters are parterned up with other characters of encoding sequence, then the encrypted
     *      word would be "GPPLE".
     * Parameters: 
     *      - input: the word or phrase the user wants to encrypt
     * Return: 
     *      - String: the newly encrypted word or phrase
     */
    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("The provided input is null.");
        }
        if (this.encoding == null) {
            throw new IllegalStateException("The encoding sequence was never set.");
        }
        return  mapSetter(this.charting, input);
    }

    /*
     * This decrypts an encrypted word or phrase back to its original state. So from the
     *      previous example, "GPPLE" gets decrypted back to "APPLE".
     * Parameters: 
     *      - input: the word or phrase that needs to be decrypted
     * Return: 
     *      - String: the newly decrypted word or phrase
     */
    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("The provided input is null.");
        }
        if (this.encoding == null) {
            throw new IllegalStateException("The encoding sequence was never set.");
        }
        Map<Character, Character> reversedMap = new HashMap<>();
        for (char c : this.charting.keySet()) {
            reversedMap.put(this.charting.get(c), c);
        } 
        return mapSetter(reversedMap, input);
    }

    /*
     * This contains the logic behind encrypting or decrypting a message and gives out the 
     *      encryption or decryption. 
     * Parameters: 
     *      - map: the key and values could reverse in meaning. Sometimes the key will be 
     *      all the characters in an encrypted message and the values would be all the characters
     *      in a normal message. Other times the keys would be the normal characters and the 
     *      values would be the encrypted characters
     *      - input: either the normal or encrypted message
     * Return: 
     *      - String: either the normal or encrypted message (whatever the input parameter is not)
     */
    private String mapSetter(Map<Character, Character> map, String input) {
        String answer = "";
        for (int i = 0; i < input.length(); i++) {
            if (map.containsKey(input.charAt(i))) {
                answer += map.get(input.charAt(i));
            }
            else {
                answer += input.charAt(i);
            }
        }
        return answer;
    }
}
