package crypto;

import crypto.gui.MainWindow;

/**
 * The MultiCipherEncryptor class serves as the entry point for the cryptography application.
 * It initializes the main GUI window for cipher encryption and decryption operations.
 * This class launches the graphical user interface (GUI) for the user to interact with the application.
 */
public class MultiCipherEncryptor {

	/**
     * The main method is the entry point of the application.
     * It creates an instance of the MainWindow class, which contains the GUI for cipher operations.
     * 
     * @param args Command-line arguments (not used in this case).
     */
	public static void main(String[] args) {
		// Creates and displays the main window of the application
        // The MainWindow constructor takes the title of the window as a parameter
		@SuppressWarnings("unused")
		MainWindow mainWindow = new MainWindow("MultiCypherEncryptor");
		/* The @SuppressWarnings("unused") annotation is used to avoid warnings
		 * since all methods are called inside the MainWindow class from the GUI
		 */
	}
}
