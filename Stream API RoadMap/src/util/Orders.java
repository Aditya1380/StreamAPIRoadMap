package util;

import java.time.LocalDate;

public class Orders {
	private String orderId;
	private LocalDate orderDate;
	private double amount;

	public Orders(String orderId, LocalDate orderDate, double amount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.amount = amount;
    }

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Order " + orderId + " (" + orderDate + ") - $" + amount;
	}
}
