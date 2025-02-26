package crypto.ciphers;

/**
 * This class provides encryption and decryption functionality using the Vigenère cipher algorithm.
 * It allows users to input plain text and a cipher key, then generates encrypted text (ciphertext) or decrypted text (plain text).
 * The encryption and decryption processes rely on the Vigenère cipher, which uses a repeating key for shifting the letters of the alphabet.
 */
public final class Vigenere {
	private char[] key; // The key used for encryption and decryption.
	private int keyLength; // The length of the key.
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
		// Processes input text: converts to lowercase, removes spaces and newlines
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
		// Processes input text: converts to lowercase, removes spaces and newlines
		userInputText = inputText;
		userInputText = userInputText.toLowerCase();
		userInputText = userInputText.replace(" ", "");
		userInputText = userInputText.replace("\n", "");
		plainText = new StringBuffer(userInputText.length());
		cipherTextChar = userInputText.toCharArray();
	}
	
	/**
     * Sets the key for the Vigenère cipher and calculates the key's length.
     * 
     * @param inputKey The key to be used for encryption/decryption, passed as a character array.
     */
	public void getKey(char[] inputKey) {
		key = inputKey;
		keyLength = computeKeyLength(); // Computes the length of the key
	}
	
	/**
     * Computes the length of the cipher key.
     * 
     * @return The length of the key.
     */
	@SuppressWarnings("unused")
	private int computeKeyLength() {
		int length = 0;
		for(char i : key) {
			length += 1;
		}
		return length;
	}
	
	/**
     * Performs the encryption of the input text using the Vigenère cipher.
     * It loops through the plain text and shifts each character by the corresponding character in the key.
     */
	private void encryption() {
		// Iterates through each character of the plain text and applies Vigenère cipher encryption
		for(int i = 0; i < userInputText.length(); i++) {
			char ch = plainTextChar[i];
			ch = (char) ('a' + (ch - 'a' + (key[i % keyLength] - 'a')) % 26); // Applies the Vigenère shift
			cipherText.append(ch); // Appends the encrypted character to the cipher text
		}
	}
	
	/**
     * Performs the decryption of the cipher text using the Vigenère cipher.
     * It loops through the cipher text and shifts each character back by the corresponding character in the key.
     */
	private void decryption() {
		// Iterates through each character of the plain text and applies Vigenère cipher encryption
		for(int i = 0; i < userInputText.length(); i++) {
			char ch = cipherTextChar[i];
			ch = (char) ('a' + (ch - 'a' - (key[i % keyLength] - 'a') + 26) % 26); // Applies the reverse Vigenère shift
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
}
