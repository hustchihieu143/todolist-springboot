package hieuphan.oop;

public abstract class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }

    public abstract String getName();
    public abstract void setName(String name);

}
