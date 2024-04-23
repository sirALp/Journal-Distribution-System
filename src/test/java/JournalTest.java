import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JournalTest {
    private Subscription subscription; private Journal journal;
    void setup(){
        journal = new Journal(12,20,"Joyce");
        subscription = new Subscription(
                new DateInfo(2024,10),5,
                journal,
                new Individual("Alp","Çanakkale","1717",10,2030,456)
        );
    }

    @Test
    void addSubscription() {
        setup();
        assertTrue(journal.addSubscription(subscription));
    }

    @Test
    void testGetSubscriptions() {
        setup();
        assertEquals(0,journal.getSubscriptions().size());
        journal.addSubscription(subscription);

        assertNotNull(journal.getSubscriptions());
        assertEquals(5,journal.getSubscriptions().get(0).getCopies());

        journal.addSubscription(subscription);
        assertEquals(10,journal.getSubscriptions().get(0).getCopies());
        journal.addSubscription(subscription);
        assertEquals(20,subscription.getCopies());
    }
}