package week3.assignmentproblems;

import java.util.Scanner;

public class OnlineShoppingSystem {

    static class Product {
        private String productId;
        private String productName;
        private double price;
        private String category;
        private int stockQuantity;

        private static int totalProducts = 0;
        private static String[] categories = {"Electronics", "Clothing", "Books", "Grocery"};
        private static int counter = 0;

        public Product(String name, double price, String category, int stock) {
            this.productId = generateId();
            this.productName = name;
            this.price = price;
            this.category = category;
            this.stockQuantity = stock;
            totalProducts++;
        }

        private static String generateId() {
            counter++;
            return String.format("P%03d", counter);
        }

        public String getId() { return productId; }
        public String getName() { return productName; }
        public double getPrice() { return price; }
        public String getCategory() { return category; }
        public int getStockQuantity() { return stockQuantity; }

        public void reduceStock(int q) { stockQuantity -= q; }
        public void increaseStock(int q) { stockQuantity += q; }

        public void displayProduct() {
            System.out.println(productId + " | " + productName + " | " + category + " | " + price + " | Stock: " + stockQuantity);
        }

        public static Product findProductById(Product[] products, String productId) {
            for (Product p : products) {
                if (p != null && p.productId.equals(productId)) return p;
            }
            return null;
        }

        public static void getProductsByCategory(Product[] products, String category) {
            for (Product p : products) {
                if (p != null && p.category.equalsIgnoreCase(category)) {
                    p.displayProduct();
                }
            }
        }
    }

    static class ShoppingCart {
        private String cartId;
        private String customerName;
        private Product[] products;
        private int[] quantities;
        private double cartTotal;
        private int itemCount;
        private static int counter = 0;

        public ShoppingCart(String name) {
            this.cartId = generateId();
            this.customerName = name;
            this.products = new Product[20];
            this.quantities = new int[20];
            this.cartTotal = 0;
            this.itemCount = 0;
        }

        private static String generateId() {
            counter++;
            return String.format("C%03d", counter);
        }

        public void addProduct(Product product, int quantity) {
            if (product.getStockQuantity() >= quantity) {
                products[itemCount] = product;
                quantities[itemCount] = quantity;
                product.reduceStock(quantity);
                itemCount++;
                calculateTotal();
                System.out.println(quantity + " x " + product.getName() + " added to cart.");
            } else {
                System.out.println("Not enough stock available.");
            }
        }

        public void removeProduct(String productId) {
            for (int i = 0; i < itemCount; i++) {
                if (products[i].getId().equals(productId)) {
                    products[i].increaseStock(quantities[i]);
                    System.out.println("Removed " + products[i].getName() + " from cart.");
                    for (int j = i; j < itemCount - 1; j++) {
                        products[j] = products[j + 1];
                        quantities[j] = quantities[j + 1];
                    }
                    products[itemCount - 1] = null;
                    quantities[itemCount - 1] = 0;
                    itemCount--;
                    calculateTotal();
                    return;
                }
            }
            System.out.println("Product not found in cart.");
        }

        public void calculateTotal() {
            cartTotal = 0;
            for (int i = 0; i < itemCount; i++) {
                cartTotal += products[i].getPrice() * quantities[i];
            }
        }

        public void displayCart() {
            System.out.println("Cart ID: " + cartId + " | Customer: " + customerName);
            for (int i = 0; i < itemCount; i++) {
                System.out.println(products[i].getName() + " x " + quantities[i] + " = " + (products[i].getPrice() * quantities[i]));
            }
            System.out.println("Total: " + cartTotal);
        }

        public void checkout() {
            if (itemCount == 0) {
                System.out.println("Cart is empty.");
            } else {
                displayCart();
                System.out.println("Checkout complete. Thank you for shopping!");
                products = new Product[20];
                quantities = new int[20];
                cartTotal = 0;
                itemCount = 0;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Product[] products = new Product[10];
        products[0] = new Product("Laptop", 60000, "Electronics", 5);
        products[1] = new Product("Phone", 30000, "Electronics", 10);
        products[2] = new Product("Shirt", 1500, "Clothing", 20);
        products[3] = new Product("Jeans", 2000, "Clothing", 15);
        products[4] = new Product("Novel", 500, "Books", 25);
        products[5] = new Product("Notebook", 100, "Books", 50);
        products[6] = new Product("Rice", 60, "Grocery", 100);
        products[7] = new Product("Milk", 40, "Grocery", 50);
        products[8] = new Product("Tablet", 20000, "Electronics", 8);
        products[9] = new Product("Jacket", 2500, "Clothing", 12);

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();
        ShoppingCart cart = new ShoppingCart(customerName);

        int choice;
        do {
            System.out.println("\n--- Online Shopping Menu ---");
            System.out.println("1. View All Products");
            System.out.println("2. View Products by Category");
            System.out.println("3. Add Product to Cart");
            System.out.println("4. Remove Product from Cart");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    for (Product p : products) if (p != null) p.displayProduct();
                    break;
                case 2:
                    System.out.print("Enter category: ");
                    String category = sc.nextLine();
                    Product.getProductsByCategory(products, category);
                    break;
                case 3:
                    System.out.print("Enter product ID: ");
                    String pid = sc.nextLine();
                    Product prod = Product.findProductById(products, pid);
                    if (prod != null) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        sc.nextLine();
                        cart.addProduct(prod, qty);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter product ID to remove: ");
                    String removeId = sc.nextLine();
                    cart.removeProduct(removeId);
                    break;
                case 5:
                    cart.displayCart();
                    break;
                case 6:
                    cart.checkout();
                    break;
                case 7:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 7);

        sc.close();
    }
}

