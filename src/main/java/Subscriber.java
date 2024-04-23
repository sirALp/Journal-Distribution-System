import java.io.Serializable;

public abstract class Subscriber implements Serializable {

    private String name,address;

    public Subscriber(String name,String address){
        this.address = address;
        this.name = name.replaceAll("[^A-z\s]","");
    }

    public abstract String getBillingInformation();

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Subscriber: " + name +
                " {Address: "+getAddress() +"}";
    }



}
