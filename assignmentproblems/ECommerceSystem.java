package week5.assignmentproblems;

import java.time.LocalDateTime;
import java.util.*;

final class Product {
    private final String productId;
    private final String name;
    private final String category;
    private final String manufacturer;
    private final double basePrice;
    private final double weight;
    private final String[] features;
    private final Map<String, String> specifications;

    private Product(String id, String name, String category, String manufacturer,
                    double price, double weight, String[] features, Map<String, String> specs) {
        if (id == null || name == null || category == null || manufacturer == null)
            throw new IllegalArgumentException("Invalid product data");
        this.productId = id;
        this.name = name;
        this.category = category;
        this.manufacturer = manufacturer;
        this.basePrice = price;
        this.weight = weight;
        this.features = features != null ? features.clone() : new String[0];
        this.specifications = specs != null ? new HashMap<>(specs) : new HashMap<>();
    }

    public static Product createElectronics(String id, String name, String manufacturer, double price, double weight) {
        return new Product(id, name, "Electronics", manufacturer, price, weight,
                new String[]{"Warranty", "User Manual"}, Map.of("Warranty", "1 year"));
    }

    public static Product createClothing(String id, String name, String manufacturer, double price, double weight) {
        return new Product(id, name, "Clothing", manufacturer, price, weight,
                new String[]{"Size Chart", "Care Instructions"}, Map.of("Material", "Cotton"));
    }

    public static Product createBooks(String id, String name, String manufacturer, double price, double weight) {
        return new Product(id, name, "Books", manufacturer, price, weight,
                new String[]{"Author Info", "Publisher"}, Map.of("Pages", "300"));
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getManufacturer() { return manufacturer; }
    public double getBasePrice() { return basePrice; }
    public double getWeight() { return weight; }
    public String[] getFeatures() { return features.clone(); }
    public Map<String, String> getSpecifications() { return new HashMap<>(specifications); }

    public final double calculateTax(String region) {
        switch (region.toUpperCase()) {
            case "US": return basePrice * 0.07;
            case "EU": return basePrice * 0.20;
            case "IN": return basePrice * 0.18;
            default: return basePrice * 0.10;
        }
    }

    @Override
    public String toString() {
        return "Product{" + name + ", " + category + ", $" + basePrice + "}";
    }
}

class Customer {
    private final String customerId;
    private final String email;
    private final String accountCreationDate;
    private String name;
    private String phoneNumber;
    private String preferredLanguage;

    public Customer(String id, String email, String date) {
        this.customerId = id;
        this.email = email;
        this.accountCreationDate = date;
    }

    public String getCustomerId() { return customerId; }
    public String getEmail() { return email; }
    public String getAccountCreationDate() { return accountCreationDate; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phone) { this.phoneNumber = phone; }
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String lang) { this.preferredLanguage = lang; }

    String getCreditRating() { return "Good"; }

    public String getPublicProfile() {
        return "Customer{name='" + name + "', language='" + preferredLanguage + "'}";
    }
}

class ShoppingCart {
    private final String cartId;
    private final String customerId;
    private final List<Object> items = new ArrayList<>();
    private double totalAmount;
    private int itemCount;

    public ShoppingCart(String cartId, String customerId) {
        this.cartId = cartId;
        this.customerId = customerId;
    }

    public boolean addItem(Object product, int quantity) {
        if (!(product instanceof Product)) return false;
        for (int i = 0; i < quantity; i++) items.add(product);
        itemCount += quantity;
        totalAmount += ((Product) product).getBasePrice() * quantity;
        totalAmount -= calculateDiscount();
        return true;
    }

    private double calculateDiscount() {
        return itemCount > 5 ? totalAmount * 0.05 : 0.0;
    }

    String getCartSummary() {
        return "Cart{" + cartId + ", items=" + itemCount + ", total=" + totalAmount + "}";
    }
}

class Order {
    private final String orderId;
    private final LocalDateTime orderTime;

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() { return orderId; }
    public LocalDateTime getOrderTime() { return orderTime; }
}

class PaymentProcessor {
    private final String processorId;
    private final String securityKey;

    public PaymentProcessor(String id, String key) {
        this.processorId = id;
        this.securityKey = key;
    }

    public boolean processPayment(double amount) {
        return amount > 0;
    }
}

class ShippingCalculator {
    private final Map<String, Double> shippingRates;

    public ShippingCalculator(Map<String, Double> rates) {
        this.shippingRates = new HashMap<>(rates);
    }

    public double calculateShipping(String region, double weight) {
        return shippingRates.getOrDefault(region, 10.0) * weight;
    }
}

public final class ECommerceSystem {
    private static final Map<String, Object> productCatalog = new HashMap<>();

    public static boolean processOrder(Object order, Object customer) {
        return order instanceof Order && customer instanceof Customer;
    }

    public static void addProduct(Product p) {
        productCatalog.put(p.getProductId(), p);
    }

    public static Object getProduct(String id) {
        return productCatalog.get(id);
    }

    public static void main(String[] args) {
        Product phone = Product.createElectronics("P100", "Smartphone", "BrandX", 500, 0.5);
        Customer c = new Customer("C001", "test@mail.com", "2022-01-01");
        c.setName("Alice");
        ShoppingCart cart = new ShoppingCart("CART1", c.getCustomerId());
        cart.addItem(phone, 2);
        System.out.println(cart.getCartSummary());

        Order o = new Order("O1001");
        boolean ok = ECommerceSystem.processOrder(o, c);
        System.out.println("Order processed: " + ok);
    }
}

