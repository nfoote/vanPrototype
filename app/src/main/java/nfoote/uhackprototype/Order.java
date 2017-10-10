package nfoote.uhackprototype;

public class Order {

    String name;

    public Order(){

    }

    public Order(String name){
        this.name = name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

}