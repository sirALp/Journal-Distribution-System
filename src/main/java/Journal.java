import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Journal implements Serializable {
    private static int ISSN = 1000;
    private String issn; private String name;
    private int frequency;
    private double issuePrice;
    private ArrayList<Subscription> subscriptions = new ArrayList<>();

    public Journal(int frequency,double issuePrice,String name){
        this.frequency = frequency;
        this.issuePrice = issuePrice;
        this.name = name;
        this.issn = String.valueOf(ISSN) + name.toUpperCase().charAt(0);
        ISSN++;
    }


    /** if the subscriptions to be added , is already exist in list of journal then
        input's copy number - subscription's -  is added on top (of whose copies, that already exist)
    **/
    public boolean addSubscription(Subscription subscription){
        AtomicReference<Boolean> equal= new AtomicReference<>(false);
        if ( subscription.getSubscriber() != null ){
            subscriptions.forEach( each -> {
                if ( each.getJournal().equals(subscription.getJournal())){
                    each.setCopies( each.getCopies()+subscription.getCopies() );
                    equal.set(true);
                }
            }  );
            if (!equal.get()) subscriptions.add(subscription);
            return true;
        }
        else return false;

    }

    public ArrayList<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public String getIssn() {
        return issn;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public double getIssuePrice() {
        return issuePrice;
    }

    @Override
    public String toString() {
        return
                "Journal: " + name +
                " {Freqeuncy= " + frequency +
                ",Issue Price= $" + issuePrice +'}';
    }
}
