import java.io.File; //
import java.io.FileReader; //
import java.io.FileWriter; //
import java.io.BufferedReader; //
import java.io.IOException; //
import java.util.ArrayList; //

public class InventoryManager {

    ArrayList<Product> inventory;
    String filename = "inventory.txt";

    public InventoryManager() {
        inventory = new ArrayList<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File f = new File(filename);
        if (!f.exists()) { //
            return;
        }

        try {
            FileReader fr = new FileReader(f); //
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                // We can split the string to get the parts
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        int qty = Integer.parseInt(parts[1]);
                        inventory.add(new Product(parts[0], qty));
                    } catch (Exception e) {
                        // Just skip bad lines
                    }
                }
            }
            br.close();
            fr.close(); //
        } catch (IOException e) {
            System.out.println("Could not read file");
        }
    }

    private void saveToFile() {
        try {
            FileWriter fw = new FileWriter(filename); //

            for (Product p : inventory) {
                fw.write(p.toString() + "\n"); //
            }
            fw.close(); //
        } catch (IOException e) {
            System.out.println("Could not save file");
        }
    }

    public void addProduct(String name, int qtyToAdd) {
        boolean found = false;

        for (Product p : inventory) {
            if (p.productName.equals(name)) { //
                p.quantity = p.quantity + qtyToAdd;
                found = true;
            }
        }

        if (found == false) {
            inventory.add(new Product(name, qtyToAdd));
        }
        saveToFile();
    }

    public String recordSale(String name, int qtyToSell) {
        String result = "Product not found.";
        boolean changed = false;

        for (Product p : inventory) {
            if (p.productName.equals(name)) { //
                if (p.quantity >= qtyToSell) {
                    p.quantity = p.quantity - qtyToSell;
                    result = "Sale recorded.";
                    changed = true;
                } else {
                    result = "Not enough stock.";
                    changed = true;
                }
            }
        }

        if (changed) {
            saveToFile();
        }
        return result;
    }

    public String getInventoryString() {
        // Use StringBuffer like in the presentation
        StringBuffer sb = new StringBuffer("--- Current Inventory ---\n");

        for (Product p : inventory) {
            // --- THIS IS THE CHANGED LINE ---
            sb.append(p.productName + " - " + p.quantity + "\n"); //
        }
        return sb.toString(); //
    }
}