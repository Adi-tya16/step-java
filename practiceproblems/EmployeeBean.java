package week5.practiceproblems;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Method;

public class EmployeeBean implements Serializable {
    // TODO: Create private fields following JavaBean conventions:
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    // TODO: Create default no-argument constructor (JavaBean requirement)
    public EmployeeBean() {}

    // TODO: Create parameterized constructor for convenience
    public EmployeeBean(String employeeId, String firstName, String lastName, double salary,
                        String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary);
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // TODO: Generate standard JavaBean getter methods:
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public Date getHireDate() { return hireDate; }
    public boolean isActive() { return isActive; }

    // TODO: Generate standard JavaBean setter methods:
    public void setEmployeeId(String id) { this.employeeId = id; }
    public void setFirstName(String name) { this.firstName = name; }
    public void setLastName(String name) { this.lastName = name; }
    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }
    public void setDepartment(String department) { this.department = department; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    public void setActive(boolean active) { isActive = active; }

    // TODO: Create computed properties
    public String getFullName() { return firstName + " " + lastName; }
    public long getYearsOfService() {
        if (hireDate == null) return 0;
        long diff = new Date().getTime() - hireDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) / 365;
    }
    public String getFormattedSalary() {
        return NumberFormat.getCurrencyInstance().format(salary);
    }

    // TODO: Create derived properties with validation
    public void setFullName(String fullName) {
        if (fullName != null && fullName.contains(" ")) {
            String[] parts = fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    // TODO: Override toString()
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", isActive=" + isActive +
                '}';
    }

    // TODO: Override equals() and hashCode() based on employeeId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    public static void main(String[] args) {
        // TODO: Create EmployeeBean using default constructor + setters
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFirstName("Alice");
        emp1.setLastName("Smith");
        emp1.setSalary(60000);
        emp1.setDepartment("HR");
        emp1.setHireDate(new Date(120, 0, 1)); // Jan 1, 2020
        emp1.setActive(true);

        // TODO: Create EmployeeBean using parameterized constructor
        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Johnson", 75000,
                "IT", new Date(118, 5, 15), true);

        // TODO: Demonstrate all getter methods
        System.out.println(emp1.getEmployeeId() + " - " + emp1.getFullName() + " - " + emp1.getDepartment());
        System.out.println(emp2.getEmployeeId() + " - " + emp2.getFullName() + " - " + emp2.getDepartment());

        // TODO: Test computed properties
        System.out.println(emp1.getFullName() + " has " + emp1.getYearsOfService() + " years of service.");
        System.out.println(emp2.getFullName() + " salary: " + emp2.getFormattedSalary());

        // TODO: Test validation in setter methods
        emp1.setSalary(-5000); // ignored
        System.out.println("Validated salary: " + emp1.getSalary());

        // TODO: Show JavaBean in action with collections (sorting, searching)
        java.util.List<EmployeeBean> employees = new java.util.ArrayList<>();
        employees.add(emp1);
        employees.add(emp2);

        employees.sort((a, b) -> Double.compare(a.getSalary(), b.getSalary()));
        System.out.println("Sorted by salary: " + employees);

        employees.stream().filter(EmployeeBean::isActive).forEach(System.out::println);
    }
}

// TODO: Create a JavaBean utility class:
class JavaBeanProcessor {
    // TODO: Create static method printAllProperties(EmployeeBean emp)
    public static void printAllProperties(EmployeeBean emp) {
        try {
            Method[] methods = emp.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                    Object value = m.invoke(emp);
                    System.out.println(m.getName() + " = " + value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO: Create static method copyProperties(EmployeeBean source, EmployeeBean target)
    public static void copyProperties(EmployeeBean source, EmployeeBean target) {
        try {
            Method[] methods = source.getClass().getMethods();
            for (Method m : methods) {
                if ((m.getName().startsWith("get") || m.getName().startsWith("is")) && m.getParameterCount() == 0) {
                    String propName = m.getName().startsWith("get") ?
                            m.getName().substring(3) : m.getName().substring(2);
                    Object value = m.invoke(source);
                    try {
                        Method setter = target.getClass().getMethod("set" + propName, m.getReturnType());
                        setter.invoke(target, value);
                    } catch (NoSuchMethodException ignored) {}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
