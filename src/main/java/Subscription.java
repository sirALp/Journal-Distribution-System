import java.io.Serializable;

public class Subscription implements Serializable {

    private final DateInfo dates;
    private PaymentInfo payment;
    private int copies;
    private final Journal journal;
    private final Subscriber subscriber;

    public Subscription(DateInfo dates, int copies, Journal journal,Subscriber subscriber){
        this.copies = copies;
        this.journal = journal;
        this.dates = dates;
        this.subscriber = subscriber;
        this.payment = new PaymentInfo();
    }

    public void setPayment(PaymentInfo payment) {
        this.payment = payment;
    }

    public void acceptPayment(double amount){
        if (paidMonths() <12){
            double annualCost = journal.getIssuePrice()* journal.getFrequency()*getCopies();
            if(payment.getReceivedPayment() + amount > annualCost){
                System.out.println("Thanks ! You've paid your Annual Subscription Fee, Change is $" +(
                        payment.getReceivedPayment() + amount - annualCost));
                amount = annualCost - payment.getReceivedPayment();
            }
            payment.increasePayment(amount);
        }
        else System.out.println("You've already paid for your annual subscription, you can not pay more!");
    }

    public boolean canSend(int issueMonth){
        int paidMonth = (int) Math.floor(payment.getReceivedPayment() / (journal.getIssuePrice()* journal.getFrequency() / 12 * getCopies()));
        int monthDif = issueMonth - dates.getStartMonth();
        if(monthDif < 0 ) monthDif +=12;

        if (paidMonth-monthDif >= 1)
            return true;
        else return false;

    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public DateInfo getDates() {
        return dates;
    }

    public PaymentInfo getPayment() {
        return payment;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public Journal getJournal() {
        return journal;
    }


    private int paidMonths(){
        return (int) Math.floor(payment.getReceivedPayment() / (journal.getIssuePrice() * journal.getFrequency() /12*getCopies()));
    }

    @Override
    public String toString() {
        return "Subscription > " + subscriber +
                " ," + journal+
                " ,Copies: " + copies +
                " ,Start>End Dates: " + dates.toString()+
                " ,Paid Months: " + paidMonths() +"out of 12";
    }
}
