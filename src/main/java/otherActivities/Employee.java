package otherActivities;

public class Employee {

  String name;
  int age;
  double salary;
  String location;
  Nationality nationality;

  public Employee(String name, int age, double salary, String location, Nationality nationality) {
    this.name = name;
    this.age = age;
    this.salary = salary;
    this.location = location;
    this.nationality = nationality;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return this.age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public double getSalary() {
    return this.salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Nationality getNationality() {
    return this.nationality;
  }

  public void setNationality(Nationality nationality) {
    this.nationality = nationality;
  }

  public void displayEmployeeDetails() {
    System.out.println("Employee Details:");
    System.out.println("Name: " + this.name);
    System.out.println("Age: " + this.age);
    System.out.println("Salary: " + this.salary);
    System.out.println("Location: " + this.location);
    System.out.println("Nationality: " + this.nationality);
  }

  public void raiseSalary(double percentage) {
    if (percentage > 0) {
      this.salary += this.salary * (percentage / 100);
      System.out.println("Salary raised by " + percentage + "%. New salary: " + this.salary);
    } else {
      System.out.println("Invalid percentage. Salary not raised.");
    }
  }

  public static void main(String[] args) {
    Employee employee = new Employee("John Doe", 30, 50000, "New York", Nationality.AMERICAN);
    employee.displayEmployeeDetails();
  }
}
