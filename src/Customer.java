public class Customer {
    protected String firstName;
    protected String lastName;
    protected int age;
    protected int experienceYears;

    public Customer(int age, String firstName, String lastName, int experienceYears) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experienceYears = experienceYears;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}
