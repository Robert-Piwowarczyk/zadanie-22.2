public class User {
    private String firstName;
    private String lastName;
    private int age;

    public User(String name, String lastName, int age) {
        this.firstName = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName+" "+age+" lata";
    }
}
