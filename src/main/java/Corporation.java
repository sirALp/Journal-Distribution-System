public class Corporation extends Subscriber{

    private String bankName;
    private int bankCode;
    private int issueDay,issueMonth,issueYear;
    private int accountNumber;

    public Corporation(String name, String address, String bankName,
                       int bankCode, int issueDay, int issueMonth,
                       int issueYear, int accountNumber) {
        super(name, address);
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.issueDay = issueDay;
        this.issueMonth = issueMonth;
        this.issueYear = issueYear;
        this.accountNumber = accountNumber;
    }

    @Override
    public String getBillingInformation() {
        return "Billing Information\n" +
                "Corporation Name : " + this.getName() +
                "\nCorporation Address : " + this.getAddress() +
                "Bank Name : " + bankName + "  Bank Code : " + bankCode +
                "\nAccount Number : " + accountNumber +
                "\nIssue Day/Month/Year : " + issueDay +'/'+issueMonth+'/'+issueYear;
    }
}
