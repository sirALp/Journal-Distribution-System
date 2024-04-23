public class Individual extends Subscriber {
    private String creditCardNr;
    private int expireMonth,expireYear;
    private int CCV;

    public Individual(String name,String address,String creditCardNr,int expireMonth,int expireYear,int CCV){
        super(name,address);
        this.CCV = CCV;
        this.creditCardNr = creditCardNr;
        this.expireMonth = expireMonth;
        this.expireYear = expireYear;
    }

    @Override
    public String getBillingInformation() {
        return "Billing Information\n" +
                "Customer Name : " + this.getName() +
                "\nCustomer Address : " + this.getAddress() +
                "\n Credit Card No : " + creditCardNr +
                "\nExpire Month : " + expireMonth +
                "\nExpire Year : " + expireYear;
    }

}
