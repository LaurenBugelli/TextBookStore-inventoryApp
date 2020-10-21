package bugelli;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

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
	public HashMap<Integer, Item> itemList = new HashMap<Integer, Item>();
	private JLabel lblSku;
	private JTextField textSKU;
	private JButton btnDeleteByKey;
	private JButton btnSave;
	

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
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public TextbookMain() throws NumberFormatException, IOException {
		initialize();
		checkFile();
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
		lblTitleLabel.setBounds(212, 11, 212, 22);
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
		btnAdd.setBounds(10, 120, 176, 23);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 154, 414, 79);
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
		btnFindKey.setBounds(191, 120, 140, 23);
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
		
		lblSku = new JLabel("SKU:");
		lblSku.setFont(new Font("Sitka Small", Font.PLAIN, 15));
		lblSku.setBounds(10, 15, 65, 14);
		frame.getContentPane().add(lblSku);
		
		textSKU = new JTextField();
		textSKU.setColumns(10);
		textSKU.setBounds(67, 12, 107, 20);
		frame.getContentPane().add(textSKU);
		
		btnDeleteByKey = new JButton("Delete ");
		btnDeleteByKey.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		btnDeleteByKey.setBounds(337, 119, 87, 23);
		frame.getContentPane().add(btnDeleteByKey);
		
		btnSave = new JButton("Save Inventory to File");
		btnSave.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		btnSave.setBounds(117, 238, 189, 23);
		frame.getContentPane().add(btnSave);
	}
	
	private void checkFile() throws NumberFormatException, IOException {
		File f = new File("out.txt");
		if(f.exists()) {
			String filePath = "out.txt";
			
		    String line;
		    BufferedReader reader = new BufferedReader(new FileReader(filePath));
		    while ((line = reader.readLine()) != null)
		    {
		        String[] parts = line.split(":", 2);
		        if (parts.length >= 2)
		        {
		            Integer key = Integer.parseInt(parts[0]);
		            String value = parts[1];
		            value.trim();
		            
		            String[] splitStr = value.split("\\s+");
		            Item add = new Item();
		            add.title = splitStr[0];
		            add.price = Double.parseDouble(splitStr[1]);
		            add.quantity = Integer.parseInt(splitStr[2]);
		            itemList.put(key, add);
		        } else {
		            System.out.println("ignoring line: " + line);
		        }
		    }
		    reader.close();
		}
	    
	}
	/**
	 * All events that happen within the app
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
		btnDeleteByKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 deleteBook();
			}
		});
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					writeFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	public void writeFile()throws IOException {
		File file = new File("out.txt");		//create a File object
		FileWriter fw = new FileWriter(file);	//create a FileWriter
		PrintWriter pw = new PrintWriter(fw);	//Create a PrintWriter
		
		itemList.entrySet().forEach(entry->{
		    pw.println(entry.getKey() + ":" + entry.getValue().getItemPlain());  
		 });
		
		pw.close();
	}
	
	private void addInventory() {
		//Textbook Item Object  
		s1 = new Item();
		
		s1.title = textItem.getText();
		s1.price = Double.parseDouble(textCost.getText());
		s1.quantity = Integer.parseInt(textQ.getText());
		
		
		//Textbook HashMap to store item object and SKU iterator
		inventory = new Library();
		int SKU = Integer.parseInt(textSKU.getText());
		if (itemList.containsKey(SKU)) {
			textCartOutput.setText("Please select a unique key for your inventory, that is already taken.");
			textCartOutput.append("\nSKUs that are already taken include: " + itemList.keySet().toString());
		}else
			itemList.put(SKU, s1);
	}
	private void printBook() {
		int find = Integer.parseInt(textKey.getText());
		String bookFound = itemList.get(find).getItemInfo();
		textCartOutput.setText(bookFound);
	}
	private void printInv() {
		inventory.getInv(itemList);
	}
	private void deleteBook() {
		int find = Integer.parseInt(textKey.getText());
		if (itemList.containsKey(find)) {
			String bookFound = itemList.get(find).getItemInfo();
			textCartOutput.setText(bookFound + " was deleted from inventory");
			itemList.remove(find);
		}else
			textCartOutput.setText("No book found with that unique SKU");
	}
}
