public class Product {
    String productName;
    int quantity;

    Product(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    // This helps when saving to the file
    public String toString() {
        return this.productName + "," + this.quantity;
    }
}