import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class SubscriptionTest {
    private Subscription subInd;private Subscription subCor;
    private DateInfo dateInfo; private Journal forbes;
    private Journal gq; private Individual ind; private Corporation corp;
    protected void setup(){
         dateInfo = new DateInfo(2024,5);
         forbes = new Journal(36,10,"Forbes");
         gq = new Journal(6,50,"GQ");
         ind = new Individual("Alp 90*","Çanakkale/Esenler","1234",10,2027,555);
         corp = new Corporation("Something","İstanbul","TT-Bank",1001,1,10,2030,123123);
         subInd = new Subscription(dateInfo,2,forbes,ind);
         subCor = new Subscription(dateInfo,250,gq,corp);
         subInd.setPayment(new PaymentInfo());
         subCor.setPayment(new PaymentInfo());
    }

    @Test
    void acceptPayment() {
        setup();
        assertEquals(0,subInd.getPayment().getReceivedPayment());
        assertEquals(0,subCor.getPayment().getReceivedPayment());

        subInd.acceptPayment(200); // meaning paid for at least 3 months
        subCor.acceptPayment(25000); // paid for 2 months

        assertEquals(200,subInd.getPayment().getReceivedPayment());
        assertEquals(25000,subCor.getPayment().getReceivedPayment());
    }

    @Test
    void canSend() {
        setup();
        subInd.acceptPayment(200); // meaning paid for at least 3 months
        subCor.acceptPayment(12500); // paid for 2

        // SubInd can get issues of month 5-6-7 2024
        // SubCor can get issues of month 5-6   2023
        assertTrue(subInd.canSend(5));
        assertTrue(subInd.canSend(6));
        assertTrue(subInd.canSend(7));
        assertFalse(subInd.canSend(8));

        assertTrue(subCor.canSend(5));
        assertTrue(subCor.canSend(6));
        assertFalse(subCor.canSend(7));

    }

    @Test
    void getSubscriber() {
        setup();
        Individual outcome = new Individual("Alp 90*","Çanakkale/Esenler","1234",10,2027,555);
        assertEquals(outcome.toString(),subInd.getSubscriber().toString());
    }

    @Test
    void getDates() {
        setup();
        String outcome = "5/2024 > 4/2025";
        assertEquals(outcome,subCor.getDates().toString());
    }

    @Test
    void getPayment() {
        setup();
        subInd.acceptPayment(200); // meaning paid for at least 3 months
        subCor.acceptPayment(12500); // paid for 2
        int outcome = 12500;
        assertEquals(outcome,subCor.getPayment().getReceivedPayment());
    }

    @Test
    void getCopies() {
        setup();
        int outcome1 = 2;
        int outcome2 = 250;
        assertEquals(outcome1,subInd.getCopies());
        assertEquals(outcome2,subCor.getCopies());
    }

    @Test
    void setCopies() {
        setup();
        assertEquals(2,subInd.getCopies());
        subInd.setCopies(subInd.getCopies()+3);
        assertEquals(5,subInd.getCopies());
    }

    @Test
    void getJournal() {
        setup();
        subInd.acceptPayment(200); // meaning paid for at least 3 months
        subCor.acceptPayment(12500); // paid for 2
        String outcome1 = "Journal: " + "Forbes" +
                " {Freqeuncy= " + 36 +
                ",Issue Price= $" + 10.0 +'}';
        String outcome2 = "Journal: " + "GQ" +
                " {Freqeuncy= " + 6 +
                ",Issue Price= $" + 50.0 +'}';
        assertEquals(outcome1,subInd.getJournal().toString());
        assertEquals(outcome2,subCor.getJournal().toString());
    }

}