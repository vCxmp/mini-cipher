/*
 * 1/20/2025
 */
import java.util.List;

/*
 * This class represents a MultiCipher. It can provide encryption or decryption through 
 *  multiple cipher methods such as CaesarKey, CaesarShift, and Substitution all at once. 
 *  This extends the Cipher class.
 */
public class MultiCipher extends Cipher {
    private List<Cipher> ciphers;
    
    /*
     * This creates a MultiCipher object and takes in a list of the different cipher methods.
     */
    public MultiCipher(List<Cipher> ciphers) {
        if (ciphers == null) {
            throw new IllegalArgumentException("ciphers list cannot be null");
        }
        this.ciphers = ciphers;
    }

    /*
     * This encrypts a user provided input using all of the different cipher methods provided 
     *      in the ciphers list. 
     * Parameters: 
     *      - input: the word or phrase that the user wants to encrypt
     * Return: 
     *      - String: the newly encrypted word or phrase
     */
    public String encrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("The provided input is null.");
        }
        String answer = input;
        for (int i = 0; i < this.ciphers.size(); i++) {
            answer = this.ciphers.get(i).encrypt(answer);
        }
        return answer;
    }

    /*
     * This decrypts an encrypted word or phrase back to its original phrase using the provided
     */
    public String decrypt(String input) {
        if (input == null) {
            throw new IllegalArgumentException("The provided input is null.");
        }
        String answer = input;
        for (int i = this.ciphers.size() - 1; i >= 0; i--) {
            answer = this.ciphers.get(i).decrypt(answer);
        }
        return answer;
    }
}
