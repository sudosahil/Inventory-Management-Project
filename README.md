# Simple Java Inventory GUI
A basic desktop application for inventory management built with Java Swing. It allows a user to add, sell, and view product stock. All data is saved to a local `inventory.txt` file.
This project was built using basic Java Swing and I/O principles, adhering to a specific set of available classes and methods from course materials.
## Features
  * **Add/Purchase Stock**: Add a new product or increase the quantity of an existing one.
  * **Sell Stock**: Decrease the quantity of a product.
  * **Persistent Storage**: Automatically saves all changes to `inventory.txt` and reloads them on startup.
  * **Live GUI Display**: The inventory list in the GUI updates automatically after every purchase or sale.
  * **Pre-load Data**: You can add items to `inventory.txt` manually, and the program will load them on startup.
## File Structure
  * `InventoryApp.java`: The main file. Creates the Swing GUI window and handles user input.
  * `InventoryManager.java`: The "logic" file. Manages the `ArrayList` of products and handles all file reading/writing.
  * `Product.java`: A simple class that defines what a `Product` is (name and quantity).
  * `inventory.txt`: The text file where all data is stored. You must create this file and can add preloaded data to it.
## How to Run
You must have the Java Development Kit (JDK) installed to compile and run this program.
### 1\. Pre-load Data (Optional)
Create a file named `inventory.txt` in the same directory as the `.java` files. You can add items in this format:
```
Apple,50
Banana,150
Orange,75
```
### 2\. Compile
Open your terminal or command prompt, navigate to the folder containing the files, and run the `javac` compiler:
```bash
javac InventoryApp.java InventoryManager.java Product.java
```
### 3\. Run
After compiling, run the main application:
```bash
java InventoryApp
```

The GUI window will appear, and it will automatically load any data from `inventory.txt`.
