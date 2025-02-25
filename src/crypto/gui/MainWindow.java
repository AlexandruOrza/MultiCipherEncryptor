package crypto.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import crypto.ciphers.Caesar;
import crypto.ciphers.OneTimePad;
import crypto.ciphers.Vigenere;

/**
 * MainWindow class provides the graphical user interface (GUI) for the cryptography application.
 * It allows users to select a cipher (Caesar, Vigenere, One-Time Pad), to input text to encrypt or decrypt, and to display the result.
 */
@SuppressWarnings("serial")
public final class MainWindow extends JFrame implements ActionListener{
	//Declarates the GUI components
	private JPanel cipherOptionsPane;
	private JPanel textBoxesPane;
	private JLabel inputLabel;
	private JLabel keyLabel;
	private JLabel cipherLabel;
	private JTextArea inputBox;
	private JTextField key;
	private JTextArea outputTextBox;
	private JButton encryptButton;
	private JButton decryptButton;
	private ButtonGroup ciphers;
	private JRadioButton caesarRB;
	private JRadioButton vigenereRB;
	private JRadioButton oneTimePadRB;
	boolean oneTimePadKeyGenerationMessage = false; // Flag to check if the One-Time Pad key generation message is displayed
	
	/**
     * Constructor to initialize the main window of the GUI.
     * It sets up the layout, components, and action listeners.
     * 
     * @param title The title of the window.
     */
	public MainWindow(String title) {
		super(title); // Sets the window title
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensures the app closes when the window is closed
		setSize(500, 350); // Sets the window size
		Container mainContainer = getContentPane();  // Gets the content pane to add components
		setLocationRelativeTo(null);  // Centers the window on the screen
		mainContainer.setLayout(new FlowLayout()); // Sets the layout for the main container
		
		// Initializes the UI components
		cipherOptionsPane = new JPanel();
		textBoxesPane = new JPanel();
		inputLabel = new JLabel("Input the text you want to encrypt/decrypt: ", JLabel.CENTER);
		keyLabel = new JLabel("Key: ");
		cipherLabel = new JLabel("Choose cipher: ", JLabel.CENTER);
		inputBox = new JTextArea(10, 20);
		outputTextBox = new JTextArea(10, 20);
		outputTextBox.setEditable(false); // Output text box is not editable by the user
		key = new JTextField(20);
		encryptButton = new JButton("Encrypt");
		decryptButton = new JButton("Decrypt");
		ciphers = new ButtonGroup();
		caesarRB = new JRadioButton("Caesar");
		vigenereRB = new JRadioButton("Vigenere");
		oneTimePadRB = new JRadioButton("One-Time Pad");
		
		// Add radio buttons to the ButtonGroup (ensures only one cipher can be selected)
		ciphers.add(caesarRB);
		ciphers.add(vigenereRB);
		ciphers.add(oneTimePadRB);
		
		// Add components to the main container
		mainContainer.add(inputLabel);
		mainContainer.add(textBoxesPane);
		textBoxesPane.add(new JScrollPane(inputBox)); // Scrollable input box
		textBoxesPane.add(new JScrollPane(outputTextBox)); // Scrollable ioutput box
		mainContainer.add(keyLabel);
		mainContainer.add(key);
		mainContainer.add(encryptButton);
		mainContainer.add(decryptButton);
		mainContainer.add(cipherOptionsPane);
		cipherOptionsPane.add(cipherLabel);
		cipherOptionsPane.add(caesarRB);
		cipherOptionsPane.add(vigenereRB);
		cipherOptionsPane.add(oneTimePadRB);
		
		// Register action listeners for the buttons and radio buttons
		caesarRB.addActionListener(this);
		vigenereRB.addActionListener(this);
		oneTimePadRB.addActionListener(this);
		encryptButton.addActionListener(this);
		decryptButton.addActionListener(this);
		
		setVisible(true); // Makes the window visible
	}
	
	/**
     * This method is called when an action event occurs (button press, radio button selection).
     * It handles encryption and decryption based on the selected cipher.
     * 
     * @param e The action event triggered by the user interaction.
     */
	public void actionPerformed(ActionEvent e) {
		
		// Handles logic for One-Time Pad cipher selection
		if (oneTimePadRB.isSelected()) {
			if(oneTimePadKeyGenerationMessage == false) {
				// Display a message when One-Time Pad is selected
	            key.setText("A one-time key will be generated!");
	            oneTimePadKeyGenerationMessage = true;
			}
            key.setEditable(false); // Disables key editing for One-Time Pad
            
	        if (e.getSource() == encryptButton) {
	            key.setEditable(false); // Locks key during encryption
	        }
	        
	        else if (e.getSource() == decryptButton) {
	            key.setEditable(true); // Unlocks key during decryption
	            oneTimePadKeyGenerationMessage = false;
	        }
	    } 
		// Reset the key text field when a different cipher is selected
	    else if((caesarRB.isSelected() || vigenereRB.isSelected())
	    		&& !(e.getSource() == encryptButton)
	    		&& !(e.getSource() == decryptButton)) {
	        key.setText(""); // Clears key text field
	        key.setEditable(true); // Makes key editable again
	    }
		
		 // Handles encryption for Caesar cipher
		if(e.getSource() == encryptButton && caesarRB.isSelected()) {
			int userEncryptionKey;
			String userInputText;
			
			Caesar caesar = new Caesar();
			
			userInputText = inputBox.getText(); // Get text input for encryption
			caesar.getPlainText(userInputText); // Set the plaintext to Caesar cipher
			
			try {
				// Ensure the user enters a positive encryption key
				do {
				userEncryptionKey = Integer.parseInt(key.getText());
				if(userEncryptionKey < 0) {
					key.setText(""); // Reset key if it's negative
				}
				}while(userEncryptionKey < 0);
				caesar.getKey(userEncryptionKey); // Set the encryption key to Caesar cipher
			}
			catch(Exception ex) {
				key.setText("Input a positive number!"); // Handle non-integer input
			}
			outputTextBox.setText(caesar.outputCipherText()); // Displays the encrypted text
		}
		
		// Handles encryption for Vigenere cipher
		else if(e.getSource() == encryptButton && vigenereRB.isSelected()) {
			char[] userEncryptionKey;
			String userInputText;

			Vigenere vigenere = new Vigenere();
			
			userInputText = inputBox.getText(); // Get text input for encryption
			vigenere.getPlainText(userInputText); // Set the plaintext to Vigenere cipher
			
			// Ensures the key contains only alphabetic characters
			do {
				userEncryptionKey = key.getText().toCharArray();
				if(new String(userEncryptionKey).matches(".*[^a-zA-Z].*")) {
					key.setText(""); // Reset key if it contains non-alphabetic characters
				}
			}while(new String(userEncryptionKey).matches(".*[^a-zA-Z].*"));
			
			vigenere.getKey(userEncryptionKey); // Sets the encryption key to Vigenere cipher
			
			outputTextBox.setText(vigenere.outputCipherText()); // Displays the encrypted text
		}
		
		// Handle encryption for One-Time Pad cipher
		else if(e.getSource() == encryptButton && oneTimePadRB.isSelected()) {
			String userInputText;
			
			OneTimePad oneTimePad = new OneTimePad();
			
			userInputText = inputBox.getText(); // Get text input for encryption
			oneTimePad.getPlainText(userInputText); // Set the plaintext to One-Time Pad cipher
			
			outputTextBox.setText(oneTimePad.outputCipherText()); // Displays the encrypted text
			key.setText(oneTimePad.outputKey()); // Displays the randomly generated key for One-Time Pad
		}
		
		// Handles decryption for Caesar cipher
		else if(e.getSource() == decryptButton && caesarRB.isSelected()) {
			int userDecryptionKey;
			String userInputText;
			
			Caesar caesar = new Caesar();
			
			userInputText = inputBox.getText(); // Gets text input for decryption
			
			try {
				// Ensure the user enters a positive decryption key
				do {
				userDecryptionKey = Integer.parseInt(key.getText());
				if(userDecryptionKey < 0) {
					key.setText(""); // Reset key if it's negative
				}
				}while(userDecryptionKey < 0);
				caesar.getKey(userDecryptionKey); // Sets the decryption key to Caesar cipher
			}
			catch(Exception ex) {
				key.setText("Input a positive number!"); // Handles non-integer input
			}

			caesar.getCipherText(userInputText); // Sets the ciphertext for Caesar cipher
			outputTextBox.setText(caesar.outputPlainText()); // Display the decrypted text
		}
		
		// Handles decryption for Vigenere cipher
		else if(e.getSource() == decryptButton && vigenereRB.isSelected()) {
			char[] userDecryptionKey;
			String userInputText;
			
			Vigenere vigenere = new Vigenere();
			
			userInputText = inputBox.getText(); // Gets text input for decryption
			vigenere.getCipherText(userInputText); // Sets the ciphertext to Vigenere cipher
	
			// Ensures the key contains only alphabetic characters
			do {
				userDecryptionKey = key.getText().toCharArray();
				if(new String(userDecryptionKey).matches(".*[^a-zA-Z].*")) {
					key.setText(""); // Resets key if it contains non-alphabetic characters
				}
			}while(new String(userDecryptionKey).matches(".*[^a-zA-Z].*"));
			vigenere.getKey(userDecryptionKey); // Sets the decryption key to Vigenere cipher
			
			outputTextBox.setText(vigenere.outputPlainText()); // Displays the decrypted text
			
		}
		
		// Handles decryption for One-Time Pad cipher
		else if(e.getSource() == decryptButton && oneTimePadRB.isSelected()) {
			char[] userDecryptionKey;
			String userInputText;
			
			OneTimePad oneTimePad = new OneTimePad();
			
			userInputText = inputBox.getText(); // Get text input for decryption
			oneTimePad.getCipherText(userInputText); // Set the ciphertext to One-Time Pad cipher
			
			// Ensures the key contains only alphabetic characters
			do {
				userDecryptionKey = key.getText().toCharArray();
				key.setText(""); // Resets key if it contains non-alphabetic characters
			}while(new String(userDecryptionKey).matches(".*[^a-zA-Z].*"));
			
			oneTimePad.getKey(userDecryptionKey); // Sets the decryption key to One-Time Pad cipher
			
			outputTextBox.setText(oneTimePad.outputPlainText()); // Displays the decrypted text
		}

		// Displays a message if no cipher is selected to let the user know
		else if(!caesarRB.isSelected()
				&& !vigenereRB.isSelected()
				&& !oneTimePadRB.isSelected()) {
			outputTextBox.setText("Choose a cipher!");
		}
	}
}
