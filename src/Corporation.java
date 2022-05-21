import java.io.Serializable;

public class Corporation implements Serializable {

    private String name;
    private String lastName;
    private int age;
    private String position;

    public Corporation(String name, String lastName, int age, String position) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lName) {
        this.lastName = lName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return lastName +
                " " + name + ", "
                + age + " лет" +
                ", " + position
                ;
    }
}


