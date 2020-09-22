package app.lesson3;

public class LightEngine implements Engine {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "LightEngine{" +
                "name='" + name + '\'' +
                '}';
    }
}
