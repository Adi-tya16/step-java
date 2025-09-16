package week5.practiceproblems;

// TODO: Import AccessModifierDemo
import week5.practiceproblems.AccessModifierDemo;

// TODO: Create class ExtendedDemo that extends AccessModifierDemo
public class ExtendedDemo extends AccessModifierDemo {
    // TODO: Create constructor that calls super constructor
    ExtendedDemo(int a, String n, double s, boolean marry) {
        super(a, n, s, marry);
    }

    public void testInheritedAccess() {
        // TODO: Try accessing inherited fields with different modifiers
         //age = 40;
         name = "Tom";
        salary = 90000.0;
        isMarried = true;

        // TODO: Try calling inherited methods with different modifiers
        // privateMethod();
         defaultMethod();
        protectedMethod();
        publicMethod();

        // TODO: Document which protected members are accessible
        // TODO: Show that private members are NOT inherited
    }

    // TODO: Override protected method from parent class
    @Override
    protected void protectedMethod() {
        System.out.println("Overridden protected method in ExtendedDemo");
    }

    public static void main(String[] args) {
        // TODO: Test inheritance access rules
        // TODO: Create both parent and child objects
        // TODO: Compare what each can access
        ExtendedDemo child = new ExtendedDemo(32, "Lisa", 85000.0, false);
        child.testInheritedAccess();

        AccessModifierDemo parent = new AccessModifierDemo(45, "John", 95000.0, true);
         parent.salary = 100000;
        parent.isMarried = true;
        parent.publicMethod();
    }
}
