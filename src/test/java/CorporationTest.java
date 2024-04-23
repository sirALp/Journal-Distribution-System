import org.junit.AfterClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.*;

public class CorporationTest {
    private Corporation corporation; private Corporation corporation2;
    void setup(){
        corporation = new Corporation(
                "Tekin AS","Istanbul/Davutpasa","QNB",3417,1,
                2,2024,341700);
        corporation2 = new Corporation(
                "ALP AS17","Çanakkale/Esenler","Ziraat",1717,1,
                1,2023,171700);
    }
    @Test
    public void  gettingBillingInformation(){
        setup();
        String outcome = "Billing Information\n" +
                "Corporation Name : " + "Tekin AS\n" +
                "Corporation Address : " + "Istanbul/Davutpasa" +
                "Bank Name : " + "QNB" + "  Bank Code : " + 3417 +
                "\nAccount Number : " + 341700 +
                "\nIssue Day/Month/Year : " + 1 +'/'+2+'/'+2024;
        assertEquals(outcome,corporation.getBillingInformation());
    }
    @Test
    public void  gettingBillingInformation2(){
        setup();
        String outcome = "Billing Information\n" +
                "Corporation Name : " + "ALP AS\n" +
                "Corporation Address : " + "Çanakkale/Esenler" +
                "Bank Name : " + "Ziraat" + "  Bank Code : " + 1717 +
                "\nAccount Number : " + 171700 +
                "\nIssue Day/Month/Year : " + 1 +'/'+1+'/'+2023;
        assertEquals(outcome,corporation2.getBillingInformation());
    }
}