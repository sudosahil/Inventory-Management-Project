public class Product {
    String productName;
    int quantity;

    /**
     * Constructor to initialize the product.
     */
    Product(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    /**
     * Returns a string for saving to the file.
     */
    public String toString() {
        return this.productName + "," + this.quantity;
    }
}