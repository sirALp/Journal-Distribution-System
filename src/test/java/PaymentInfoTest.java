import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInfoTest {
    private PaymentInfo paymentInfo = new PaymentInfo();
    @Test
    void increasePayment() {
        assertEquals(0,paymentInfo.getReceivedPayment());
        paymentInfo.increasePayment(250);
        assertEquals(250,paymentInfo.getReceivedPayment());
        paymentInfo.increasePayment(100);
        assertEquals(350,paymentInfo.getReceivedPayment());
    }

}