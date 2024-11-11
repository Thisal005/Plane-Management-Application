import java.util.Scanner;

public class Person {
    // Scanner object for input.
    Scanner scanner = new Scanner(System.in);

    // Private instance variables.
    private String name;
    private String sname;
    private String email;

    // Constructor to initialize the Person object.
    public Person(String name, String sname, String email) {
        this.name = name;
        this.sname = sname;
        this.email = email;
    }

    // Getter and setter methods for name.
    public String getName() {
        return this.name;
    }

    public void setName() {
        // Ask the user to enter the name.
        System.out.print("Enter the name: ");
        this.name = scanner.nextLine();
    }

    // Getter and setter methods for surname.
    public String getSname() {
        return this.sname;
    }

    public void setSname() {
        // Ask the user to enter the surname.
        System.out.print("Enter the Surname: ");
        this.sname = scanner.nextLine();
    }

    // Getter and setter methods for email.
    public String getEmail() {
        return this.email;
    }

    public void setEmail() {
        // Ask the user to enter the email.
        System.out.print("Enter the email: ");
        this.email = scanner.nextLine();
    }

    // Method to print personal information.
    public void information() {
        System.out.println("\n-----  PERSONAL INFORMATION  ------");
        System.out.println("Name: " + name);
        System.out.println("Surname: " + sname);
        System.out.println("E-mail: " + email);
    }
}
