package week4.assignmentproblems;

 public class FoodOrder {
    String customerName;
    String foodItem;
    int quantity;
    double price;

    FoodOrder() {
        this.customerName = "Unknown";
        this.foodItem = "NA";
        this.quantity = 0;
        this.price = 0;
    }

    FoodOrder(String foodItem) {
        this.customerName = "NA";
        this.foodItem = foodItem;
        this.quantity = 1;
        this.price = 100;
    }

    FoodOrder(String foodItem, int quantity) {
        this.customerName = "NA";
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = quantity * 100;
    }

    FoodOrder(String customerName, String foodItem, int quantity, double price) {
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.price = price;
    }

    void printBill() {
        System.out.println("Customer: " + customerName + ", Food: " + foodItem +
                ", Quantity: " + quantity + ", Total Price: " + price);
    }

    public static void main(String[] args) {
        FoodOrder f1 = new FoodOrder();
        FoodOrder f2 = new FoodOrder("Pizza");
        FoodOrder f3 = new FoodOrder("Burger", 3);
        FoodOrder f4 = new FoodOrder("Sam", "Pasta", 2, 250);

        f1.printBill();
        f2.printBill();
        f3.printBill();
        f4.printBill();
    }
}

