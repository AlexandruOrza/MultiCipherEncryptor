package crypto.ciphers;

/* 
 * Caesar Cipher Implementation for Encryption and Decryption. 
 * This class provides methods for performing Caesar cipher encryption and decryption on a given text using a specified key.
 * It handles both lowercase and uppercase letters by converting all inputs to lowercase and ignores whitespaces and newlines.
 */

public final class Caesar {
	private int key; // The encryption key (shift value for Caesar cipher)
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
     * Sets the encryption key for Caesar cipher.
     * 
     * @param inputKey The key (shift value) for encryption. It will be reduced to the range 0-25.
     */
	public void getKey(int inputKey) {
		// Ensure the key is within 0-25
		key = (inputKey % 26);
	}
	
	 /**
     * Encrypts the plain text using the Caesar cipher.
     */
	private void encryption() {
		// Iterates through each character of the plain text and applies Caesar cipher encryption
		for(int i = 0; i < userInputText.length(); i++) {
			char ch = plainTextChar[i];
			ch = (char) ('a' + (ch - 'a' + key) % 26); // Applies the Caesar shift
			cipherText.append(ch); // Appends the encrypted character to the cipher text
		}
	}
	
	/**
     * Decrypts the cipher text using the Caesar cipher.
     */
	private void decryption() {
		// Iterates through each character of the plain text and applies Caesar cipher decryption
		for(int i = 0; i < userInputText.length(); i++) {
			char ch = cipherTextChar[i];
			ch = (char) ('a' + (ch - 'a' - key + 26) % 26); // Applies the reverse Caesar shift
			plainText.append(ch); // Appends the decrypted character to the plain text
			if((i % 35) == 0 && i > 0) {
				plainText.append('\n');  /* Adds a newline after each 35 characters
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

