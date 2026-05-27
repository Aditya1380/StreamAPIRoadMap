package util;

public class Transaction {
	private String customerName;
    private double amount;

    public Transaction(String customerName, double amount) {
        this.customerName = customerName;
        this.amount = amount;
    }

    public String getCustomerName() { return customerName; }
    public double getAmount() { return amount; }
}
