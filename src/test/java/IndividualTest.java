import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndividualTest {

    @Test
    void getBillingInformation() {
        Individual ind = new Individual(
                "Alp --45$$","Çanakkale","1234-5454",
                12,2028,444);
        String outcome = "Billing Information\n" +
                "Customer Name : " + "Alp " +
                "\nCustomer Address : " + "Çanakkale" +
                "\n Credit Card No : " + "1234-5454" +
                "\nExpire Month : " + 12 +
                "\nExpire Year : " + 2028;
        assertEquals(outcome,ind.getBillingInformation());
    }
}