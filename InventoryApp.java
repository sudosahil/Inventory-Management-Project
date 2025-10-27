import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class InventoryApp {

    JFrame frame;
    JLabel nameL, qtyL, statusL;
    JTextField nameF, qtyF;
    JButton b_purch, b_sale;

    JTextArea displayArea;
    JScrollPane scrollPane;

    InventoryManager manager;

    /**
     * Constructor sets up the GUI.
     */
    public InventoryApp() {
        manager = new InventoryManager();

        frame = new JFrame("Inventory Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 460);
        frame.setLayout(null);

        nameL = new JLabel("Product Name:");
        nameF = new JTextField();
        qtyL = new JLabel("Quantity:");
        qtyF = new JTextField();
        b_purch = new JButton("Purchase Stock");
        b_sale = new JButton("Record Sale");
        statusL = new JLabel("Welcome. Data loaded from file.");

        displayArea = new JTextArea();
        scrollPane = new JScrollPane(displayArea);

        nameL.setBounds(20, 20, 100, 20);
        nameF.setBounds(130, 22, 240, 20);
        qtyL.setBounds(22, 50, 100, 20);
        qtyF.setBounds(130, 53, 240, 20);
        b_purch.setBounds(20, 90, 170, 30);
        b_sale.setBounds(200, 91, 170, 30);
        statusL.setBounds(18, 135, 350, 20);
        scrollPane.setBounds(21, 165, 350, 240);

        frame.add(nameL);
        frame.add(nameF);
        frame.add(qtyL);
        frame.add(qtyF);
        frame.add(b_purch);
        frame.add(b_sale);
        frame.add(statusL);
        frame.add(scrollPane);

        b_purch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameF.getText();
                String qtyStr = qtyF.getText();

                if(name.length() == 0) {
                    statusL.setText("Name cannot be empty.");
                    return;
                }

                try {
                    int qty = Integer.parseInt(qtyStr);

                    if (name.equals("") || qty <= 0) {
                        statusL.setText("Invalid name or quantity.");
                        return;
                    }

                    manager.addProduct(name, qty);
                    statusL.setText("Stock purchased: " + qty + " of " + name);
                    nameF.setText("");
                    qtyF.setText("");

                    refreshDisplay();

                } catch (NumberFormatException ex) {
                    statusL.setText("Error: Quantity must be a number.");
                }
            }
        });

        b_sale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameF.getText();
                String qtyStr = qtyF.getText();

                try {
                    int qty = Integer.parseInt(qtyStr);

                    if(qty < 1) {
                        statusL.setText("Quantity must be positive.");
                        return;
                    }

                    if (name.equals("") || qty <= 0) {
                        statusL.setText("Invalid name or quantity.");
                        return;
                    }
                    String result = manager.recordSale(name, qty);
                    statusL.setText(result);
                    nameF.setText("");
                    qtyF.setText("");

                    refreshDisplay();

                } catch (NumberFormatException ex) {
                    statusL.setText("Error: Quantity must be a number.");
                }
            }
        });

        frame.setVisible(true);

        refreshDisplay();
    }

    /**
     * A method to update the text area.
     */
    private void refreshDisplay() {
        String inventoryText = manager.getInventoryString();
        displayArea.setText(inventoryText);
    }

    /**
     * Main method to run the application.
     */
    public static void main(String[] args) {
        new InventoryApp();
    }
}