package crypto.ciphers;

import java.util.Random;

/**
 * The OneTimePad class provides encryption and decryption functionality using the One-Time Pad cipher.
 * This cipher generates a random key equal in length to the plaintext and uses it for encryption and decryption.
 * The encryption and decryption processes are based on generating a random key for shifting the letters of the alphabet.
 */
public final class OneTimePad {
	private char[] key; // The key used for encryption and decryption.
	private String userInputText; // The input text to be encrypted or decrypted
	private char[] plainTextChar; // Array of characters representing the plain text
	private char[] cipherTextChar; // Array of characters representing the cipher text
	private StringBuffer cipherText; // StringBuffer to hold the cipher text
	private StringBuffer plainText; // StringBuffer to hold the decrypted plain text
	
	/**
	 * 
	 * Sets the input plain text, sanitizes it by removing whitespace and newlines, and prepares it for encryption.
	 * 
	 * @param inputText The text to be encrypted, should be a string of letters.
	 */
	public void getPlainText(String inputText) {
		userInputText = inputText;
		userInputText = userInputText.toLowerCase();
		userInputText = userInputText.replace(" ", "");
		userInputText = userInputText.replace("\n", "");
		cipherText = new StringBuffer(userInputText.length());
		plainTextChar = userInputText.toCharArray();
	}
	
	/**
	 * 
	 * Sets the input plain text, sanitizes it by removing whitespace and newlines, and prepares it for decryption.
	 * 
	 * @param inputText The text to be decrypted, should be a string of letters.
	 */
	public void getCipherText(String inputText) {
		userInputText = inputText;
		userInputText = userInputText.toLowerCase();
		userInputText = userInputText.replace(" ", "");
		userInputText = userInputText.replace("\n", "");
		plainText = new StringBuffer(userInputText.length());
		cipherTextChar = userInputText.toCharArray();
	}
	
    /**
     * Sets the key for the One-Time Pad cipher from the user. This key will only be used for decryption.
     * 
     * @param inputKey The key to be used decryption, passed as a character array.
     * @see generateKey()
     */
	public void getKey(char[] inputKey) {
		key = inputKey;
	}
	
	/**
     * Generates a new random key of the same length as the plain text.
     * The key is randomly generated using letters from 'a' to 'z'.
     */
	private void generateKey() {
		Random randChar = new Random();
		key = new char[userInputText.length()];
		for(int i = 0; i < userInputText.length(); i++) {
		key[i] = (char)(randChar.nextInt(26) + 'a');
		}
	}
	
	/**
     * Performs the encryption of the input text using the One-Time Pad cipher.
     * Each character in the plain text is shifted by the corresponding character in the key.
     */
	private void encryption() {
		// Iterates through each character of the cipher text and applies One-Time Pad cipher encryption
		generateKey();
		for(int i = 0; i < userInputText.length(); i++) {
			char ch = plainTextChar[i];
			ch = (char) ('a' + (ch - 'a' + (key[i] - 'a')) % 26); // Applies the One-Time Pad shift
			cipherText.append(ch); // Appends the encrypted character to the cipher text
		}
	}
	
	/**
     * Performs the decryption of the cipher text using the One-Time Pad cipher.
     * Each character in the cipher text is shifted back by the corresponding character in the key.
     */
	private void decryption() {
		// Iterates through each character of the cipher text and applies One-Time Pad cipher decryption
		for(int i = 0; i < userInputText.length(); i++) {
			char ch = cipherTextChar[i];
			ch = (char) ('a' + (ch - 'a' - (key[i] - 'a') + 26) % 26); // Applies the reverse One-Time Pad shift
			plainText.append(ch); // Appends the decrypted character to the plain text
			if((i % 35) == 0 && i > 0) {
				plainText.append('\n'); /* Adds a newline after each 35 characters
				   						   to ensure text readability */
			}
		}
	}
	
	/**
     * Returns the encrypted cipher text.
     * 
     * @return The resulting encrypted cipher text as a string.
     */
	public String outputCipherText() {
		encryption(); // Performs encryption
		return cipherText.toString(); // Returns the cipher text as a string
	}
	
	/**
     * Returns the decrypted plain text.
     * 
     * @return The resulting decrypted plain text as a string.
     */
	public String outputPlainText() {
		decryption(); // Performs decryption
		return plainText.toString(); // Returns the plain text as a string
	}
	
	/**
     * Returns the generated key used for encryption and decryption as a string.
     * 
     * @return The key used in the One-Time Pad cipher.
     */
	public String outputKey() {
		return (new String(key));
	}
}
