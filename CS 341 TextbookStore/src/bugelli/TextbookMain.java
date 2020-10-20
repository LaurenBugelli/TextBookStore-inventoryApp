package bugelli;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
/**
* Textbook main to hold, display, and increment inventory
*
* @author  Lauren Bugelli
* @version 1.0
* @since   10-20-2020 
* 
*/
public class TextbookMain {

	private JFrame frame;
	private JTextField textItem;
	private JTextField textCost;
	private JTextField textQ;
	private JButton btnAdd;
	private JTextArea textCartOutput;
	private Double total = 0.00;
	private JTextField textKey;
	private JButton btnFindKey;
	private JButton btnPrintInv;
	public Library inventory = new Library();
	public Item s1 = new Item();

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextbookMain window = new TextbookMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TextbookMain() {
		initialize();
		createEvents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTitleLabel = new JLabel("Textbook Inventory");
		lblTitleLabel.setFont(new Font("Sitka Subheading", Font.BOLD, 17));
		lblTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitleLabel.setBounds(95, 11, 253, 22);
		frame.getContentPane().add(lblTitleLabel);
		
		JLabel lblItemLabel = new JLabel("Item:");
		lblItemLabel.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblItemLabel.setBounds(10, 36, 65, 14);
		frame.getContentPane().add(lblItemLabel);
		
		JLabel lblCostLabel = new JLabel("Cost: $");
		lblCostLabel.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblCostLabel.setBounds(10, 61, 65, 14);
		frame.getContentPane().add(lblCostLabel);
		
		JLabel lblQLabel = new JLabel("Quantity: ");
		lblQLabel.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblQLabel.setBounds(10, 92, 83, 14);
		frame.getContentPane().add(lblQLabel);
		
		textItem = new JTextField();
		textItem.setBounds(67, 33, 107, 20);
		frame.getContentPane().add(textItem);
		textItem.setColumns(10);
		
		textCost = new JTextField();
		textCost.setBounds(67, 58, 107, 20);
		frame.getContentPane().add(textCost);
		textCost.setColumns(10);
		
		textQ = new JTextField();
		textQ.setBounds(88, 89, 86, 20);
		frame.getContentPane().add(textQ);
		textQ.setColumns(10);
		
		btnAdd = new JButton("Add a Book to Inventory");
		btnAdd.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		btnAdd.setBounds(10, 120, 189, 23);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 414, 96);
		frame.getContentPane().add(scrollPane);
		
		textCartOutput = new JTextArea();
		textCartOutput.setColumns(3);
		textCartOutput.setRows(100);
		scrollPane.setViewportView(textCartOutput);
		
		JLabel lblOptions = new JLabel("Check for Textbook or Print Inventory ");
		lblOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblOptions.setFont(new Font("Sitka Subheading", Font.ITALIC, 14));
		lblOptions.setBounds(181, 36, 253, 22);
		frame.getContentPane().add(lblOptions);
		
		btnFindKey = new JButton("Find Book by Key");
		btnFindKey.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		btnFindKey.setBounds(224, 119, 189, 23);
		frame.getContentPane().add(btnFindKey);
		
		btnPrintInv = new JButton("Print Entire Library");
		btnPrintInv.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		btnPrintInv.setBounds(224, 57, 189, 23);
		frame.getContentPane().add(btnPrintInv);
		
		JLabel lblKey = new JLabel("Key: ");
		lblKey.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblKey.setBounds(198, 92, 83, 14);
		frame.getContentPane().add(lblKey);
		
		textKey = new JTextField();
		textKey.setColumns(10);
		textKey.setBounds(245, 89, 168, 20);
		frame.getContentPane().add(textKey);
	}
	/**
	 * Method to perform action of adding items to cart and producing a total
	 */
	private void createEvents() {
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addInventory();
			}
		});
		btnFindKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printBook();
			}
		});
		btnPrintInv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 printInv();
			}
		});
	}
	/**
	 * Method to produce output
	 */
	private void addInventory() {
		//Textbook Item Object  
		s1 = new Item();
		s1.title = textItem.getText();
		s1.price = Double.parseDouble(textCost.getText());
		s1.quantity = Integer.parseInt(textQ.getText());
		
		
		//Textbook HashMap to store item object and SKU iterator
		inventory = new Library();
		int count = inventory.itemList.getOrDefault(s1, 0); // ensure count will be one of 0,1,2,3,...
		inventory.itemList.put(s1, count + 1);
	}
	private void printBook() {
	
		
	}
	private void printInv() {
		inventory.getInv();
	}
}
