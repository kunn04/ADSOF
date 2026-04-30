public class Person {
    private String name;
    private int age;
    private double weight;
    private int height;
    private boolean male;

    public Person(String name, int age, double weight, int height, boolean male) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.male = male;   
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public boolean isMale() {
        return male;    
    }

    @Override
    public String toString() {
        return String.format("%s(age:%d, %s]", 
            name, age, male ? "male" : "female");
    }
    
}
