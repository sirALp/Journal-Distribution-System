import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistributorTest {
    private Subscription subInd;private Subscription subCor;
    private DateInfo dateInfo; private Journal forbes;
    private Journal gq; private Individual ind; private Corporation corp;
    private Distributor distributor;
    protected void setup() {
        distributor = new Distributor();
        dateInfo = new DateInfo(2024, 5);
        forbes = new Journal(36, 10, "Forbes");
        gq = new Journal(6, 50, "GQ");
        ind = new Individual("Alp 90*", "Çanakkale/Esenler", "1234", 10, 2027, 555);
        corp = new Corporation("Tekin", "İstanbul", "TT-Bank", 1001, 1, 10, 2030, 123123);
        subInd = new Subscription(dateInfo, 2, forbes, ind);
        subCor = new Subscription(dateInfo, 250, gq, corp);
        subInd.setPayment(new PaymentInfo());
        subCor.setPayment(new PaymentInfo());
        distributor.addSubscriber(ind);
        distributor.addSubscriber(corp);
        distributor.addJournal(forbes);
        distributor.addJournal(gq);
        distributor.addSubscription(forbes.getIssn(), ind, subInd);
        distributor.addSubscription(gq.getIssn(), corp, subCor);
    }
    void searchJournal() {
        setup();
        String outcome1 ="Journal: Forbes {Freqeuncy= 36,Issue Price= $10.0}";
        Journal j1 = distributor.searchJournal("1000F");
        assertNotNull(j1);
        Journal j2 = distributor.searchJournal("1001G");
        assertNotNull(j2);
        Journal j3 = distributor.searchJournal("1002G");
        assertNull(j3);
    }
    @Test
    void searchSubscriber() {
        setup();
        assertNotNull(distributor.searchSubscriber("Alp "));
        assertNotNull(distributor.searchSubscriber("Tekin"));
        assertNull(distributor.searchSubscriber("Alp *90"));
        assertNull(distributor.searchSubscriber("Mehmet"));
    }

    @Test
    void listAllSendingOrders() {
        setup();
        subInd.acceptPayment(320); // 5.66 month of worth payment
        subCor.acceptPayment(25000); // 4 month of worth  payment
        distributor.listAllSendingOrders(6,2024);
        distributor.listAllSendingOrders(8,2024);
        /* Corp didn't pay enough - 5th month
         only individual will get */
        distributor.listAllSendingOrders(9,2024);
        // an earlier date than subscription date
        distributor.listAllSendingOrders(10,2023);
        // no one will get - since its 6th month and nobody paid for it
        distributor.listAllSendingOrders(10,2024);

    }

    @Test
    void listSendingOrders() {
        setup();
        subInd.acceptPayment(320); // 5.66 month of worth payment
        subCor.acceptPayment(25000); // 4 month of worth  payment
        distributor.listSendingOrders("1000F",6,2024);
        distributor.listSendingOrders("1000F",8,2024);
        distributor.listSendingOrders("1001G",6,2024);
        distributor.listSendingOrders("1001G",9,2024);
    }

    @Test
    void listIncompletePayments() {
        setup();
        subInd.acceptPayment(320); // 5.66 month of worth payment
        subCor.acceptPayment(25000); // 4 month of worth  payment
        // both individual and corporate will be listed


        subCor.acceptPayment(50000); // corporate done with his payments
        System.out.println(subCor.getPayment().getReceivedPayment());
        subInd.acceptPayment(200); // individual still has to pay 200$ more to get 720$
        System.out.println(subInd.getPayment().getReceivedPayment());

        subCor.acceptPayment(5000); // corporate had done already , this won't change anything
        System.out.println(subCor.getPayment().getReceivedPayment());
        subInd.acceptPayment(250); // needed 200 they paid 250 , 50$ will be returned
        //listIncompletePayments();

    }
    /**
     * takes a String as a parameter,
     * if that String is a type of ISSN ,meaning contains a number ,
     * then search is done by ISSN
     * otherwise journal is searched by the Subscriber Name
     */

    @Test
    void listSubscriptions() {

        setup();
        // 1000F in this Test Setup refers to Forbes Journal
        // Meaning Forbes will be printed
        distributor.listSubscriptions("1000F");
        // 1001G in this Test Setup refers to GQ Journal
        // Meaning GQ will be printed
        distributor.listSubscriptions("1001G");

        // non-exist ISSN
        distributor.listSubscriptions("2005A");

        // Searching through name
        distributor.listSubscriptions("Alp");
        distributor.listSubscriptions("Tekin");


    }
}