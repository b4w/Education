package resources;

public class TestResource implements IResourceServer{
    private String name;
    private int age;

    public TestResource() {
        this.name = "";
        this.age = 0;
    }

    public TestResource(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }
}
