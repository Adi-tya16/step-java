package week5.practiceproblems;

public class AccessModifierDemo {
    private int age;
    String name;
    protected double salary;
    public boolean isMarried;

    private void privateMethod() {
        System.out.println("Private method called");
    }

    void defaultMethod() {
        System.out.println("Default method called");
    }

    protected void protectedMethod() {
        System.out.println("Protected method called");
    }

    public void publicMethod() {
        System.out.println("Public method called");
    }

    AccessModifierDemo(int a, String n, double s, boolean marry) {
        this.age = a;
        this.name = n;
        this.salary = s;
        this.isMarried = marry;
    }

    public void testInternalAccess() {
        System.out.println("testInternalAccess called");
        System.out.println("Age: " + age);
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
        System.out.println("Married: " + isMarried);

        privateMethod();
        defaultMethod();
        protectedMethod();
        publicMethod();
    }

    public static void main(String[] args) {
        AccessModifierDemo obj = new AccessModifierDemo(25, "John", 55000.0, true);

        // obj.age = 30;
        obj.name = "David";
        obj.salary = 60000.0;
        obj.isMarried = false;

        // obj.privateMethod();
        obj.defaultMethod();
        obj.protectedMethod();
        obj.publicMethod();

        obj.testInternalAccess();
    }
}

class SamePackageTest {
    public static void testAccess() {
        AccessModifierDemo obj = new AccessModifierDemo(30, "Alice", 75000.0, true);

        // obj.age = 40;
        obj.name = "Eva";
        obj.salary = 80000.0;
        obj.isMarried = true;

        // obj.privateMethod();
        obj.defaultMethod();
        obj.protectedMethod();
        obj.publicMethod();
    }
}
