import java.io.Serializable;

public class PaymentInfo implements Serializable {
    private final double discountRatio=0.1;
    private double receivedPayment;

    public PaymentInfo(){ receivedPayment = 0; }

    public void increasePayment(double amount){
        receivedPayment += amount;
    }

    public double getReceivedPayment(){
        return receivedPayment;
    }


}
